import com.sun.source.tree.Tree;

import java.util.*;

public class App {
    TreeSet<Jogo> jogos = new TreeSet<>();
    TreeSet<Cliente> clientes = new TreeSet<>();


/**
 * Método que busca um jogo na lista de jogos do app.
 * @param nome string
 * @return Jogo, retorna o Jogo encontrado.
 * @throws NoSuchElementException caso não encontrar o jogo na lista de jogos
 * */
    public Jogo encontrarJogo(String nome) {
        Jogo achado = null;

        for(Jogo j: jogos) {
            if(j.getNome().equals(nome)) {
                achado = j;
            }
        }

        if(achado == null) {
            throw new NoSuchElementException("Jogo não encontrado");
        } else {
            return achado;
        }
    };

/**
  * metódo que cadastra um jogo na arvore de jogos da loja.
  *
  * @param j Jogo à ser adicionado na arvore de jogos
  * @return boolean, true caso consiga adicionar, false caso não seja possivel adicionar.
  *
  * */

    public boolean cadastrarJogo(Jogo j) {
        boolean result = jogos.add(j);
        return result;
    }

/**
 * metodo que busca o jogo mais vendido na arvore de jogos
 *
 * @return  jogo com mais vendas
 *
 * */

    public Jogo jogoMaisVendido() {
        Jogo maisVendido = jogos.first();
        for(Jogo j : jogos) {
            if(j.getNumeroVendas() > maisVendido.getNumeroVendas()) {
                maisVendido = j;
            }
        }

        return maisVendido;
    }

/**
 * Metodo que retorna o jogo menos vendido na arvore de jogos,
 * @return jogo com menos numero de vendas
 * */

    public Jogo jogoMenosVendido() {
        Jogo menosVendido = jogos.first();
        for(Jogo j : jogos) {
            if(j.getNumeroVendas() < menosVendido.getNumeroVendas()) {
            menosVendido = j;
        }
    }

    return menosVendido;
    }

}