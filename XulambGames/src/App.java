import java.text.ParseException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        

        boolean continua = true;
        Scanner sc = new Scanner(System.in);

        do{

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


            switch(opcao){
                case "1":

                case "2":


                case "3":

                case "4":

                case "5":
                    limpaConsole();
                    
                    System.out.println("Informe o nome do Jogo: ");
                    String nomeJogo = sc.nextLine();
                    System.out.println("Informe o preço: ");

                    try{
                        Double precoJogo = Double.parseDouble(sc.nextLine());
                    }catch(ParseException e){
                        System.out.println("Valor Incorreto, tente novamente");
                        break;
                    }

                    System.out.println("Informe a Categoria: ");
                    ICategoriaJogo categoriaJogo = ICategoriaJogo.
                    


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
                    while(senhaCliente != senhaNovamenteCliente){
                        quantErros++;
                        limpaConsole();
                        System.out.println("Senhas diferentes, por gentileza preencher novamente");
                        System.out.println("Informe a senha do usuário: ");
                        senhaCliente = sc.next();
                        System.out.println("Informe a senha novamente: ");
                        senhaNovamenteCliente = sc.next();
                        
                        if(quantErros > 3){
                            System.out.println("Tente novamente");
                            pause(sc);
                            break;
                        }
                        
                    }


                    Cliente cliente = new Cliente(nomeCliente,usuarioCliente,senhaCliente);

                    // salvar cliente;

                    break;

                    case "7":



                default:
                    System.out.println("Caractere inválido, por gentileza escolher uma opção válida");
            }

        }while(continua);

    }

    private static void limpaConsole() {        
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void pause(Scanner sc){
        sc.nextLine();
    }
}
