import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.DirectoryStream.Filter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;

public class App {
    public static void main(String[] args) throws Exception {
        HashSet<Jogo> jogos = new HashSet<>();
        HashSet<Cliente> clientes = new HashSet<>();
        HashSet<Pedido> pedidos = new HashSet<>();
        boolean continua = true;
        Scanner sc = new Scanner(System.in);
        String arquivoClientes = "clientes.bin";
        String arquivoJogos = "jogos.bin";
        carregarClientesDeArquivo(arquivoClientes, clientes);
        carregarJogosDeArquivo(arquivoJogos, jogos);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        do {
            limpaConsole();
            String opcao = "";
            System.out.println("+-----------------------------------+");
            System.out.println("| 1 Comprar                         |");
            System.out.println("| 2 Histórico do Cliente            |");
            System.out.println("| 3 Consultar Compra                |");
            System.out.println("| 4 Relátorio de Vendas             |");
            System.out.println("| 5 Cadastrar Jogos                 |");
            System.out.println("| 6 Cadastrar Cliente               |");
            System.out.println("| 7 Salvar Dados e Sair             |");
            System.out.println("+-----------------------------------+");
            System.out.print("Informe a opção desejada: ");
            opcao = sc.nextLine();
            switch (opcao) {
                case "1":
                    comprarJogo(sc, clientes, jogos, format);
                    break;
                case "2":
                    pegarHistorico(sc, clientes);
                    break;
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
                    if (procura == null) {
                        System.out.println("Usuário não encontrado");
                        pause(sc);
                        break;
                    }
                    System.out.println("Informe a data do pedido: ");
                    String dataPedidoString = sc.nextLine();
                    limpaConsole();
                    try {
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date dataPedido = formato.parse(dataPedidoString);
                        procura.getPedidos().stream()
                                .filter(pedido -> formato.format(pedido.getData()).equals(formato.format(dataPedido)))
                                .forEach(pedido -> System.out.println(pedido.toString()));
                        pause(sc);
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Data incorreta, tente novamente");
                        break;
                    } catch (NullPointerException e) {
                        System.out.println("Data incorreta, tente novamente");
                        break;
                    }

                case "4":
                    limpaConsole();
                    Double valorMensal = 0d;
                    final Date dataHoje = new Date();

                    //clientes.stream().forEach(cls -> {
                    //   return cls.getPedidos().stream().filter(p -> p.getData().getMonth() == dataHoje.getMonth()).mapToDouble(Pedido::getValPago).sum();
                    //});

                    ArrayList<Pedido> listPedidos = new ArrayList<>();
                    
                    clientes.stream().forEach(cls -> {
                        listPedidos.addAll(cls.getPedidos());
                    });
                    
                    valorMensal =  listPedidos.stream().filter(p -> p.getData().getMonth() == dataHoje.getMonth()).mapToDouble(Pedido::getValPago).sum();


                    //valorMensal = clientes.stream().map(cli -> cli.getPedidos().stream().filter(pedido -> new Date().getMonth() == pedido.getData().getMonth())).;

                    Double valorMedio = valorMensal / listPedidos.size();
                    String formatTextMais;
                    String formatTextMenos;

                    Jogo maisVendido = null;
                    Jogo menosVendido = null;

                    try{
                        maisVendido = jogos.stream().max(Comparator.comparing(Jogo::getNumeroVendas)).get();
                        menosVendido = jogos.stream().min(Comparator.comparing(Jogo::getNumeroVendas)).get();

                        formatTextMais = "Mais Vendido:" + maisVendido.getNome()+" - nº de vendas: "+ maisVendido.getNumeroVendas()+" |";
                        formatTextMenos = "Mais Vendido:" + menosVendido.getNome()+" - nº de vendas: "+ menosVendido.getNumeroVendas()+" |";
                    }catch(Exception e){
                        formatTextMenos = "Mais Vendido:" + "Sem informações";
                        formatTextMais = "Mais Vendido:" + "Sem informações";
                    }
            

                    System.out.println("+-----------+");
                    System.out.println("| Valor Total Vendido: "+ valorMensal);
                    System.out.println("| Valor Médio Vendido: "+ Math.round(valorMedio));
                    System.out.println("| "+formatTextMais);
                    System.out.println("| "+formatTextMenos);
                    System.out.println("+-----------+");
                    pause(sc);

                    break;
                case "5":
                    limpaConsole();
                    Boolean continueJogo;
                    Boolean continuaPreco = false;
                    Boolean continuaNome = true;
                    String valorJogo;
                    String valorJogoVenda;
                    String nomeJogo = "";
                    CategoriaJogo ctJogo;

                    do {
                        limpaConsole();
                        System.out.println("Informe o nome do Jogo: ");
                        String nome = sc.nextLine().toLowerCase();
                        Long sizeSearch = jogos.stream().filter(j -> j.getNome().equals(nome.toLowerCase())).count();

                        if (sizeSearch == 0) {
                            continuaNome = false;
                            nomeJogo = nome;
                        } else {
                            System.out.println("--- Jogo repetido ---");
                            pause(sc);
                        }

                    } while (continuaNome);

                    do {
                        limpaConsole();
                        System.out.println("+----------------+");
                        System.out.println("| 1 LANÇAMENTO   |");
                        System.out.println("| 2 PREMIUM      |");
                        System.out.println("| 3 REGULAR      |");
                        System.out.println("| 4 PROMOÇÃO     |");
                        System.out.println("+----------------+");
                        System.out.print("Informe a Categoria: ");

                        try {
                            String numCategoria = sc.nextLine();
                            ctJogo = CategoriaJogo.values()[Integer.parseInt(numCategoria) - 1];
                            continueJogo = false;
                        } catch (Exception e) {
                            System.out.println("--- Categoria inválida ---");
                            pause(sc);

                            ctJogo = null;
                            continueJogo = true;
                        }

                    } while (continueJogo);

                    do {
                        limpaConsole();
                        System.out.println("Informe o preço: ");
                        valorJogo = sc.nextLine();
                        System.out.println("inform o preço de venda: ");
                        valorJogoVenda = sc.nextLine();

                        try {
                            Jogo jogo = new Jogo(Double.parseDouble(valorJogo), Double.parseDouble(valorJogoVenda),
                                    nomeJogo, ctJogo);
                            jogos.add(jogo);
                        } catch (Exception e) {
                            System.out.println("--- Valor incompátivel com a categoria ---");
                            pause(sc);

                            continuaPreco = true;
                        }

                    } while (continuaPreco);

                    limpaConsole();
                    System.out.println("--- jogo cadastrado ---");
                    pause(sc);

                    break;
                case "6":
                    limpaConsole();
                    System.out.println("informe o nome do Cliente: ");
                    String nomeCliente = sc.nextLine();
                    System.out.println("Informe o usuário: ");
                    String usuarioCliente = sc.nextLine();
                    System.out.println("Informe a senha do usuário: ");
                    String senhaCliente = sc.nextLine();
                    System.out.println("Informe a senha novamente: ");
                    String senhaNovamenteCliente = sc.nextLine();

                    int quantErros = 0;
                    while (!senhaCliente.equals(senhaNovamenteCliente)) {
                        quantErros++;
                        limpaConsole();
                        System.out.println("Senhas diferentes, por gentileza preencher novamente");
                        System.out.println("Informe a senha do usuário: ");
                        senhaCliente = sc.nextLine();
                        System.out.println("Informe a senha novamente: ");
                        senhaNovamenteCliente = sc.nextLine();

                        if (quantErros > 3) {
                            System.out.println("Tente novamente");
                            pause(sc);
                            break;
                        }
                    }
                    clientes.add(new Cliente(nomeCliente, usuarioCliente, senhaCliente));
                    break;
                case "7":
                    limpaConsole();
                    if (salvarClientesBin(arquivoClientes, clientes) &&
                            salvarJogosBin(arquivoJogos, jogos))
                        System.out.println("Os dados foram salvos com successo!");
                    else
                        System.out.println("Algum problema ocorreu na hora de salvar os dados...");

                    System.out.println("Obrigado por usar XulambsGames!");
                    continua = false;
                    break;
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

    public static void comprarJogo(Scanner sc, Set<Cliente> clientes, Set<Jogo> jogos, SimpleDateFormat format) {
        System.out.println("Informe o usuário do Cliente: ");
        String usuario =  sc.nextLine();
        List<Jogo> carrinhoDeCompra = new ArrayList<Jogo>();

        try {

            limpaConsole();

            Cliente cli = clientes.stream().filter(c -> c.getUsuario().equals(usuario)).findAny().orElseThrow(() -> new NoSuchElementException("Cliente Não Encotrado"));
            String opcao = "";

            do {
                System.out.println("+-----------------------------------+");
                System.out.println("| 1 Adicionar Jogo ao carrinho      |");
                System.out.println("| 2 Remover Jogo do carrinho        |");
                System.out.println("| 3 Fechar compra                   |");
                System.out.println("| 4 Sair                            |");
                System.out.println("+-----------------------------------+");
                System.out.print("Informe a opção desejada: ");
                opcao = sc.nextLine();


                switch (opcao) {
                    case "1":

                        adicionarJogo(carrinhoDeCompra, jogos, sc);
                        break;
                    case "2":

                        removerJogo(carrinhoDeCompra, jogos, sc);
                        break;
                    case "3":
                        Pedido compra = new Pedido(cli, carrinhoDeCompra, new Date());
                        cli.adicionarPedido(compra);
                        System.out.println("compra feita com sucesso!");
                        System.out.println(compra);
                        pause(sc);
                        opcao = "4";
                        break;

                    default:
                        System.out.println("opção invalida, tente novamente!");
                }

            } while (!opcao.equals("4"));

        } catch (NoSuchElementException e) {

            System.err.println("Cliente não Encontrado, Deseja tentar Novamente? (S - para sim)");
            String again = sc.nextLine();

            if(again.equalsIgnoreCase("S")) {
                comprarJogo(sc, clientes, jogos,format);
            }
        }

    }


    public static void adicionarJogo(List<Jogo> carrinho, Set<Jogo> jogos, Scanner sc) {
        System.out.println("digite o nome do jogo:");
        String nomeDoJogo = sc.nextLine();

        try {
            Jogo jogo = jogos.stream().filter(j -> j.getNome().equals(nomeDoJogo)).findFirst().get();

            carrinho.add(jogo);
        } catch (NoSuchElementException e) {
            System.err.println("Jogo não encontrado, deseja tentar novamente? (S - para sim)");

            String again = sc.nextLine();
            if(again.equalsIgnoreCase("s")) {

                adicionarJogo(carrinho, jogos, sc);
            }
        }
    }

    public static void removerJogo(List<Jogo> carrinho, Set<Jogo> jogos, Scanner sc) {

        System.out.println("digite o nome do jogo:");
        String nomeDoJogo = sc.nextLine();

        try {
            Jogo jogo = jogos.stream().filter(j -> j.getNome().equals(nomeDoJogo)).findAny().orElseThrow(() -> new NoSuchElementException("Jogo Não encontrado"));
            jogo.setNumeroVendas(jogo.getNumeroVendas()-1);
            carrinho.remove(jogo);
        } catch (NoSuchElementException e) {
            System.err.println("Jogo não encontrado, deseja tentar novamente? (S - para sim)");
            String again = sc.nextLine();
            if (again.equalsIgnoreCase("s")){
                removerJogo(carrinho, jogos, sc);
            }
        }
    }

    /**
     * Método para encapsular pegar o histórico de pedidos de um cliente
     * 
     * @param sc       Scanner de entrada do usuário
     * @param clientes Set de clientes em que o cliente será procurado
     */

    public static void pegarHistorico(Scanner sc, Set<Cliente> clientes) {
        System.out.println("Informe o usuário do Cliente: ");
        String usuario = sc.nextLine();
        try {

            Cliente cli = clientes.stream().filter(c -> c.getNome().equals(usuario)).findAny()
                    .orElseThrow(() -> new NoSuchElementException("CLiente Não Encotrado"));

            System.out.println("Informe a data do historico desejado: ");

            Date desejado = null;
            Boolean error;

            do {
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dataDesejada = sc.nextLine();
                    desejado = dateFormat.parse(dataDesejada);
                    error = false;
                } catch (ParseException e) {
                    System.err.println("Por favor digite a data no formato dd/MM/yyyy");
                    System.err.println("Data Invalida, tente novamente!");
                    error = true;
                }
            } while (error == true);

            System.out.println(cli.historicoDeCompras(desejado));

            System.out.println("Aperte qualquer tecla para limpar o terminal");
            pause(sc);
            limpaConsole();

        } catch (NoSuchElementException error) {
            System.err.println("Cliente não Encontrado, Deseja tentar Novamente? (S - para sim)");
            String again = sc.nextLine();

            if (again.equalsIgnoreCase("S")) {
                pegarHistorico(sc, clientes);
            }

        }
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
    public Jogo jogoMenosVendido(Set<Jogo> jogos) {
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