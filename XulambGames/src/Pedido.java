import java.util.Date;
import java.util.List;

public class Pedido {

    private List<Jogo> jogos;
    private Cliente cliente;
    private Date data;
    private double valOriginal;
    private double valPago;

    public Pedido(Cliente c, List<Jogo> j, Date d) {
        this.cliente = c;
        this.jogos = j;
        this.data = d;

        for(Jogo jogo : j) {
            valOriginal += jogo.precoVenda();
        }

        this.valPago = valorFinal();
    }

    public Pedido(Cliente c, Date d) {
        this.cliente = c;
        this.data = d;
        this.valOriginal = 0;
        this.valPago = 0;
    }

    public Pedido(Cliente c, List<Jogo> j) {
        this.data = new Date();
        this.jogos = j;
        this.cliente = c;
        this.valOriginal = 0;

        for(Jogo jogo : j) {
            valOriginal += jogo.precoVenda();
        }

        this.valPago = valorFinal();
    }

    public boolean adicionarJogos(List<Jogo> jogos) {
        boolean result = this.jogos.addAll(jogos);
        if(result) {
            for (Jogo j : jogos) {
                valOriginal += j.precoVenda();
            }

            valPago = valorFinal();
        }
        return result;
    }

    public double valorFinal() {

        return this.valOriginal - cliente.categoria.desconto();

    }

}
