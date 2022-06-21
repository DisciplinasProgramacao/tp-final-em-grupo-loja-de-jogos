import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.sun.source.tree.Tree;

import java.util.*;

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

            opcao = sc.nextLine();

            switch (opcao) {
                case "1":

                case "2":
                    pegarHistorico(sc, clientes);
                    break;
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

                default:
                    System.out.println("Caractere inválido, por gentileza escolher uma opção válida");
            }

        } while (continua);


    }

    private static void limpaConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void pegarHistorico(Scanner sc, Set<Cliente> clientes) {
        System.out.println("Informe o nome do Cliente: ");
        String nome = sc.nextLine();
        try {
            Cliente cli = clientes.stream().filter(c -> c.getNome().equals(nome)).findAny().orElseThrow(() -> new NoSuchElementException("CLiente Não Encotrado"));

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

            if(again.equalsIgnoreCase("S")) {
                pegarHistorico(sc, clientes);
            }

        }
    }

    private static void pause(Scanner sc) {
        sc.nextLine();

    }

    /**
     * Método que busca um jogo na lista de jogos do app.
     *
     * @param nome string
     * @return Jogo, retorna o Jogo encontrado.
     * @throws NoSuchElementException caso não encontrar o jogo na lista de jogos
     */
    public Jogo encontrarJogo(String nome, Set<Jogo> jogos) {
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

    public boolean cadastrarJogo(Jogo j, Set<Jogo> jogos) {
        boolean result = jogos.add(j);
        return result;
    }

    /**
     * metodo que busca o jogo mais vendido na arvore de jogos
     *
     * @return jogo com mais vendas
     *
     */

    public Jogo jogoMaisVendido(Set<Jogo> jogos) {
        Jogo maisVendido = jogos.stream().max((jogo, t1) -> jogo.getNumeroVendas() > t1.getNumeroVendas() ? 1 : 0)
                .orElse(null);
        return maisVendido;
    }

    /**
     * Metodo que retorna o jogo menos vendido na arvore de jogos,
     *
     * @return jogo com menos numero de vendas
     */

    public static Jogo jogoMenosVendido(Set<Jogo> jogos) {
        Jogo menosVendido = jogos.stream().min((jogo, t1) -> jogo.getNumeroVendas() < t1.getNumeroVendas() ? 1 : 0)
                .orElse(null);

        return menosVendido;
    }

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