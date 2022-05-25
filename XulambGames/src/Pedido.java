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


        return this.valOriginal - cliente.getCategoria().desconto();

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
        this.valPago = valPago;;
    }

    public double descontoPorCategoria () {
        int lancamentos = 0, premium = 0, promocao = 0, regular = 0;
        for (Jogo j : jogos) {
            switch (j.getCategoria()) {
                case "Lancamento":
                    lancamentos++;
                    break;
                case "Premium":
                    premium++;
                    break;
                case "Promocao":
                    promocao++;
                    break;
                default:
                    regular++;
            }
        }
        if (lancamentos >= 2 || (premium >= 2 && jogos.size() > 2) ||
                premium >= 3 || (regular >= 3 && (premium != 0 || lancamentos != 0) || regular >= 5)) {
            return 0.2;
        } else if (premium >= 2 || regular >= 4) {
            return 0.1;
        } else {
            return 0;
        }
    }

}
