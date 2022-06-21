import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;


public class App {
    public static void main(String[] args) throws Exception {
        HashSet<Jogo> jogos = new HashSet<>();
        HashSet<Cliente> clientes = new HashSet<>();
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
                    limpaConsole();
                    System.out.println("Informe o usuario: ");
                    String nomeUsuario = sc.nextLine();
                    Cliente procura = clientes.stream()
                                              .filter(x -> x.getUsuario()
                                              .equals(nomeUsuario))
                                              .findAny()
                                              .orElse(null);
                    limpaConsole();
                    if(procura == null){
                        System.out.println("Usuário não encontrado");
                        pause(sc);
                        break;
                    }
                    System.out.println("Informe a data do pedido: ");
                    String dataPedidoString = sc.nextLine();
                    limpaConsole();
                    try{
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date dataPedido = formato.parse(dataPedidoString);
                        procura.getPedidos().stream()
                                            .filter(pedido -> formato.format(pedido.getData()).equals(formato.format(dataPedido)))
                                            .forEach(pedido -> System.out.println(pedido.toString()));
                    pause(sc);
                    break;
                    }
                    catch(IllegalArgumentException e){
                        System.out.println("Data incorreta, tente novamente");
                        break;
                    }
                    catch(NullPointerException e){
                        System.out.println("Data incorreta, tente novamente");
                        break;
                    }

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

                        if (quantErros > 3) {
                            System.out.println("Tente novamente");
                            pause(sc);
                            break;
                        }
                    }
                    clientes.add(new Cliente(nomeCliente, usuarioCliente, senhaCliente));

                    break;

                case "7":

                default:
                    System.out.println("Caractere inválido, por gentileza escolher uma opção válida");
            }

        } while (continua);

    }

    /**
     * Método responsável por limpar as mensagens anteriores do
     * console (terminal).
     */
    private static void limpaConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Método responsável por pausar o scanner (entrada do usuário), fazendo com que
     * seja necessário apertar enter para prosseguir.
     * 
     * @param sc Scanner que será "pausado"
     */
    private static void pause(Scanner sc) {
        sc.nextLine();
    }

    /**
     * Método que busca um jogo pelo seu nome na lista de jogos passada pelo
     * parâmetro.
     *
     * @param nome  string nome do jogo
     * @param jogos Set de Jogo em que será pesquisado
     * @return Jogo caso seja encontrado, null se não
     * @throws NoSuchElementException caso não encontre o jogo na lista de jogos
     */
    public Jogo encontrarJogo(String nome, Set<Jogo> jogos) {
        Jogo achado = jogos.stream().filter(jogo -> jogo.getNome().equals(nome)).findAny().orElse(null);

        if (achado == null)
            throw new NoSuchElementException("Jogo não encontrado");
        return achado;
    };

    /**
     * Metódo que cadastra um jogo no Set de jogos passado pelo parâmetro.
     *
     * @param jogo  Jogo à ser adicionado no Set de jogos
     * @param jogos Set de Jogo que terá o jogo adicionado
     * @return boolean, true caso consiga adicionar, false caso não seja possivel
     *         adicionar.
     *
     */
    public boolean cadastrarJogo(Jogo jogo, Set<Jogo> jogos) {
        return jogos.add(jogo);
    }

    /**
     * Método que retorna o jogo mais vendido no Set de jogos passado pelo
     * parâmetro. Retorna null caso não encontre.
     *
     * @return Jogo com maior número vendas, null caso não encontre
     *
     */
    public Jogo jogoMaisVendido(Set<Jogo> jogos) {
        return jogos.stream().max((jogo, t1) -> jogo.getNumeroVendas() > t1.getNumeroVendas() ? 1 : 0)
                .orElse(null);
    }

    /**
     * Método que retorna o jogo menos vendido no Set de jogos passado pelo
     * parâmetro. Retorna null caso não encontre.
     *
     * @return Jogo com menor número de vendas, null caso não encontre
     */
    public static Jogo jogoMenosVendido(Set<Jogo> jogos) {
        return jogos.stream().min((jogo, t1) -> jogo.getNumeroVendas() < t1.getNumeroVendas() ? 1 : 0)
                .orElse(null);
    }

    /**
     * 
     * @param fileName String nome do arquivo que será carregado pelo método
     * @param clientes Set de Cliente que terá os clientes carregados do arquivo
     *                 adicionados
     * @return boolean true caso dê tudo certo, false se dê algo errado
     */
    public static boolean carregarClientesDeArquivo(String fileName, Set<Cliente> clientes) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream obj = new ObjectInputStream(file);
            while (file.available() > 0) {
                clientes.add((Cliente) obj.readObject());
            }
            file.close();

            return true;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    /**
     * 
     * @param fileName String nome do arquivo que será carregado pelo método
     * @param jogos    Set de Jogo que terá os jogos carregados do arquivo
     *                 adicionados
     * @return boolean true caso dê tudo certo, false se dê algo errado
     */
    public static boolean carregarJogosDeArquivo(String fileName, Set<Jogo> jogos) {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream obj = new ObjectInputStream(file);
            while (file.available() > 0) {
                jogos.add((Jogo) obj.readObject());
            }

            file.close();

            return true;
        } catch (IOException e) {
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        }

    }

    /**
     * 
     * @param fileName String nome do arquivo que guardará os clientes
     * @param clientes Set de Cliente que será guardado no arquivo
     * @return boolean true caso tudo der certo, false se não
     */
    public static boolean salvarClientesBin(String fileName, Set<Cliente> clientes) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream obj = new ObjectOutputStream(file);
            for (Cliente c : clientes) {
                obj.writeObject(c);
            }

            file.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 
     * @param fileName String nome do arquivo que guardará os jogos
     * @param jogos    Set de Jogo que será guardado no arquivo
     * @return boolean true caso tudo der certo, false se não
     */
    public static boolean salvarJogosBin(String fileName, Set<Jogo> jogos) {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream obj = new ObjectOutputStream(file);
            for (Jogo j : jogos) {
                obj.writeObject(j);
            }

            file.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

}