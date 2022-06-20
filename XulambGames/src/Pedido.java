import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {

    private List<Jogo> jogos;
    private Cliente cliente;
    private Date data;
    private double valOriginal;
    private double valPago;

    public Pedido(Cliente c, List<Jogo> j, Date d) {
        this.cliente = c;
        this.jogos = j;
        this.data = d;

        for (Jogo jogo : j) {
            valOriginal += jogo.precoVenda();
        }

        this.valPago = 0;
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

        for (Jogo jogo : j) {
            valOriginal += jogo.precoVenda();
        }

        this.valPago = 0;
    }

    public boolean adicionarJogos(List<Jogo> jogos) {
        boolean result = this.jogos.addAll(jogos);
        if (result) {
            for (Jogo j : jogos) {
                valOriginal += j.precoVenda();
            }

            valPago = valorFinal();
        }
        return result;
    }

    public double valorFinal() {
        double valorFinal = this.valOriginal;
        valorFinal -= valorFinal * descontoPorCategoria();
        if (cliente.getCategoria() != null) {
            valorFinal -= valorFinal * this.cliente.getCategoria().getDesconto();
        }
        return valorFinal;

    }

    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValOriginal() {
        return valOriginal;
    }

    public void setValOriginal(double valOriginal) {
        this.valOriginal = valOriginal;
    }

    public double getValPago() {
        return valPago;
    }

    public void setValPago(double valPago) {
        this.valPago = valPago;
    }

    public double descontoPorCategoria() {
        double pontos = jogos.stream().mapToDouble(j -> {
            return j.getCategoria().getNivel();
        }).sum();

        if (pontos > 4.5) {
            return 0.2;
        } else if (pontos == 4) {
            return 0.1;
        } else {
            return 0d;
        }
    }

    @Override
    public String toString() {
        StringBuilder relatorio = new StringBuilder();

        relatorio.append("*********\n");
        relatorio.append("valor Original = " + this.valOriginal + " ------ valor Pago = " + valorFinal());

        return relatorio.toString();
    }
}
