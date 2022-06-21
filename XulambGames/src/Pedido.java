import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pedido implements Serializable {

    private List<Jogo> jogos;
    private Cliente cliente;
    private Date data;
    private double valOriginal;
    private double valPago;

    /**
     * @param cliente cliente do pedido
     * @param jogos lista de jogos para ser adicionados ao pedido
     * @param data data do pedido
     */
    public Pedido(Cliente cliente, List<Jogo> jogos, Date data) {
        this.cliente = cliente;
        this.jogos = jogos;
        this.data = data;

        for (Jogo jogo : jogos) {
            valOriginal += jogo.precoVenda();
        }

        this.valPago = 0;
    }

    /**
     * @param cliente cliente do pedido
     * @param data data do pedido
     */
    public Pedido(Cliente cliente, Date data) {
        this.cliente = cliente;
        this.data = data;
        this.valOriginal = 0;
        this.valPago = 0;
    }

    /**
     * @param cliente cliente do pedido
     * @param jogos lista de jogos para ser adicionado ao pedido
     */
    public Pedido(Cliente cliente, List<Jogo> jogos) {
        this.data = new Date();
        this.jogos = jogos;
        this.cliente = cliente;
        this.valOriginal = 0;

        for (Jogo jogo : jogos) {
            valOriginal += jogo.precoVenda();
        }

        this.valPago = 0;
    }

    /**
     * responsável por adicionar os jogos ao pedido
     * 
     * @param jogos lista de jogos para ser adicionado
     * @return verdadeiro ou falso, dependendo se a adição do jogo foi feita com êxito
     */
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

    /**
     * responsável por adicionar um jogo à lista de jogos
     * */

    public void adicionarJogo(Jogo jogo) {
        this.jogos.add(jogo);
    }

    /**
     * responsável por retornar o valor final do pedido baseado nas regras de negócio
     * 
     * @return valor final do pedido, com descontos
     */
    public double valorFinal() {
        double valorFinal = this.valOriginal;
        valorFinal -= valorFinal * descontoPorCategoria();
        if (cliente.getCategoria() != null) {
            valorFinal -= valorFinal * this.cliente.getCategoria().getDesconto();
        }
        return valorFinal;

    }

    /**
     * @return retorna os jogos do pedido
     */
    public List<Jogo> getJogos() {
        return jogos;
    }

    /**
     * @param jogos modifica a lista de jogos do pedido
     */
    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }

    /**
     * @return retorna o cliente do pedido
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente modifica o cliente do pedido
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return retorna a data do pedido
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data modifica a data do pedido
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return retorna o alor original do pedido, sem descontos
     */
    public double getValOriginal() {
        return valOriginal;
    }

    /**
     * @param valOriginal modifica o valor original do pedido, sem descontos
     */
    public void setValOriginal(double valOriginal) {
        this.valOriginal = valOriginal;
    }

    /**
     * @return retorna o valor pago dos pedidos, com descontos
     */
    public double getValPago() {
        return valPago;
    }

    /**
     * @param valPago modifica o valor pago do pedido, com descontos
     */
    public void setValPago(double valPago) {
        this.valPago = valPago;
    }

    /**
     * responsável por retornar os descontos, com base na regra de negócios
     * 
     * @return desconto, baseado nas regras de negócio
     */
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
