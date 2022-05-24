import java.text.ParseException;
import java.util.Scanner;
import com.sun.source.tree.Tree;

import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
<<<<<<< HEAD
=======

>>>>>>> 6e36c0f66ba14ce664734e829b36a133199eec97

        boolean continua = true;
        Scanner sc = new Scanner(System.in);

        do {

            String opcao = "";

            System.out.println("+-----------------------------------+");
            System.out.println("| 1 Comprar                         |");
            System.out.println("| 2 Histório do Cliente             |");
            System.out.println("| 3 Consultar Compra                |");
            System.out.println("| 4 Relátorio de Vendas             |");
            System.out.println("| 5 Cadastrar Jogos                 |");
            System.out.println("| 6 Cadastrar Cliente               |");
            System.out.println("| 7 Salvar Dados                    |");
            System.out.println("+-----------------------------------+");

            opcao = sc.next();

            switch (opcao) {
                case "1":

                case "2":

                case "3":

                case "4":

                case "5":
                    limpaConsole();

                    System.out.println("Informe o nome do Jogo: ");
                    String nomeJogo = sc.nextLine();
                    System.out.println("Informe o preço: ");

                    try {
                        Double precoJogo = Double.parseDouble(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("Valor Incorreto, tente novamente");
                        break;
                    }

                    System.out.println("Informe a Categoria: ");
<<<<<<< HEAD
                    ICategoriaJogo categoriaJogo;
=======
                    ICategoriaJogo categoriaJogo = ICategoriaJogo.


>>>>>>> 6e36c0f66ba14ce664734e829b36a133199eec97

                case "6":
                    limpaConsole();
                    System.out.println("informe o nome do Cliente: ");
                    String nomeCliente = sc.nextLine();
                    System.out.println("Informe o usuário: ");
                    String usuarioCliente = sc.nextLine();
                    System.out.println("Informe a senha do usuário: ");
                    String senhaCliente = sc.next();
                    System.out.println("Informe a senha novamente: ");
                    String senhaNovamenteCliente = sc.next();

                    int quantErros = 0;
                    while (senhaCliente != senhaNovamenteCliente) {
                        quantErros++;
                        limpaConsole();
                        System.out.println("Senhas diferentes, por gentileza preencher novamente");
                        System.out.println("Informe a senha do usuário: ");
                        senhaCliente = sc.next();
                        System.out.println("Informe a senha novamente: ");
                        senhaNovamenteCliente = sc.next();

<<<<<<< HEAD
                        if (quantErros > 3) {
=======
                        if(quantErros > 3){
>>>>>>> 6e36c0f66ba14ce664734e829b36a133199eec97
                            System.out.println("Tente novamente");
                            pause(sc);
                            break;
                        }
<<<<<<< HEAD
=======

                    }
>>>>>>> 6e36c0f66ba14ce664734e829b36a133199eec97

                    }

                    Cliente cliente = new Cliente(nomeCliente, usuarioCliente, senhaCliente);

                    // salvar cliente;

                    break;

                case "7":

                default:
                    System.out.println("Caractere inválido, por gentileza escolher uma opção válida");
            }

        } while (continua);

    }

    private static void limpaConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause(Scanner sc) {
        sc.nextLine();

    }

    TreeSet<Jogo> jogos = new TreeSet<>();
    TreeSet<Cliente> clientes = new TreeSet<>();

    /**
     * Método que busca um jogo na lista de jogos do app.
     * 
     * @param nome string
     * @return Jogo, retorna o Jogo encontrado.
     * @throws NoSuchElementException caso não encontrar o jogo na lista de jogos
     */
    public Jogo encontrarJogo(String nome) {
        Jogo achado = null;

        for (Jogo j : jogos) {
            if (j.getNome().equals(nome)) {
                achado = j;
            }
        }

        if (achado == null) {
            throw new NoSuchElementException("Jogo não encontrado");
        } else {
            return achado;
        }
    };

    /**
     * metódo que cadastra um jogo na arvore de jogos da loja.
     *
     * @param j Jogo à ser adicionado na arvore de jogos
     * @return boolean, true caso consiga adicionar, false caso não seja possivel
     *         adicionar.
     *
     */

    public boolean cadastrarJogo(Jogo j) {
        boolean result = jogos.add(j);
        return result;
    }

    /**
     * metodo que busca o jogo mais vendido na arvore de jogos
     *
     * @return jogo com mais vendas
     *
     */

    public Jogo jogoMaisVendido() {
        Jogo maisVendido = jogos.first();
        for (Jogo j : jogos) {
            if (j.getNumeroVendas() > maisVendido.getNumeroVendas()) {
                maisVendido = j;
            }
        }

        return maisVendido;
    }

    /**
     * Metodo que retorna o jogo menos vendido na arvore de jogos,
     * 
     * @return jogo com menos numero de vendas
     */

    public Jogo jogoMenosVendido() {
        Jogo menosVendido = jogos.first();
        for (Jogo j : jogos) {
            if (j.getNumeroVendas() < menosVendido.getNumeroVendas()) {
                menosVendido = j;
            }
        }

        return menosVendido;
    }

}