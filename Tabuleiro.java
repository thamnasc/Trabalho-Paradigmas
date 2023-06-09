import java.util.*;

public class Tabuleiro {
    private Entidade[][] matriz;
    private int linha;
    private int coluna;

    public Tabuleiro(LinkedList<Jogador> j, LinkedList<FakeNews> f)
    { 
        Entidade[][] matrizAux = new Entidade[9][9];
        this.setMatriz(matrizAux);
        inicializaTabuleiro();
        posicionaJogadores(j);        
        posicionaSetoresXX();
        posicionaFakeNews(f);
        geraItem();
        geraItem();
    }
    private void setMatriz(Entidade[][] matriz)
    {
        this.matriz = matriz;
    }
    private void setLinha(int linha)
    {
        if (linha >= 0 && linha <= 8)
            this.linha = linha;
    }
    private void setColuna(int coluna)
    {
        if (coluna >= 0 && coluna <= 8)
            this.coluna = coluna;
    }
    public String movimentaPersonagem(String direcao, int l, int c)
    {
        Personagem p = (Personagem) this.matriz[l][c];
        p.movimentar(direcao);
        int newL = p.getLinha();
        int newC = p.getColuna();

        boolean movimentoValido = newL >= 0 && newL <= 8 && newC >= 0 && newC <= 8;

        if (!movimentoValido)
        {
            if (p instanceof Jogador)
            {
                p.setLinha(l);
                p.setColuna(c);
                return "Movimento Inválido";
            }
            else // se saiu da borda, fakeNews é eliminada
            {
                this.matriz[l][c] = new Setor("  ");
                return "Eliminado";
            }
        }

        if (this.matriz[newL][newC].getLabel().equals(" XX "))
        {
            this.matriz[l][c] = new Setor("  ");
            return "Eliminado";
        }

        String mensagem = "";

        if (p instanceof Jogador)
            mensagem = movimentaJogador((Jogador) p, l, c);

        if (p instanceof FakeNews)
            mensagem =  movimentaFakeNews((FakeNews) p, l, c);

        return mensagem;
    }
    private String movimentaJogador(Jogador j, int l, int c)
    {
        int newL = j.getLinha();
        int newC = j.getColuna();

        Entidade e = this.matriz[newL][newC];

        if (e instanceof Item)
        {
            // armazena item
            j.setItem((Item) e);

            // TODO: verifica se o jogador ouviu um boato

            // movimenta jogador
            this.matriz[newL][newC] = j;
            this.matriz[l][c] = new Setor("  ");
            // gera outro item aleatório no tabuleiro
            this.geraItem();

            return "Movimento válido";
        }

        if (e instanceof FakeNews)
        {
            this.matriz[l][c] = new Setor("  ");
            return "Eliminado";
        }
        
        if (e instanceof Jogador)
        {
            this.setLinha(l);
            this.setColuna(c);
            return "Movimento inválido";
        }

        // se for um setor normal
        return "Movimento válido";
    }
    private String movimentaFakeNews(FakeNews f, int l, int c)
    {
        int newL = f.getLinha();
        int newC = f.getColuna();

        Entidade e = this.matriz[newL][newC];

        if (e instanceof Item)
        {
            /*
            Caso uma fake news colida com um item presente em uma posição do tabuleiro, a
            mesma elimina o item do tabuleiro e cria uma cópia dela mesma em uma das 8 (oito)
            posições adjacentes livres, ou seja, a fake news é duplicada.
            */
            return "Movimento válido";
        }

        if (e instanceof Jogador)
        {
            // elimina jogador
        }

        if (e instanceof FakeNews)
        {
            return "Movimento inválido";
        }

        // se for um setor normal
        return "Movimento válido";

    }
    public void imprimirTabuleiro()
    {
        String linha = "+----+----+----+----+----+----+----+----+----+";

        for (int i = 0; i < 9; i++)
        {
            System.out.println(linha);
            for (int j = 0; j < 9; j++)
                System.out.print("|"+this.matriz[i][j]);
            System.out.println("|");
        }
        System.out.println(linha);
    }
    private int aleatorio(int min, int max)
    {
        Random aleatorio = new Random();
        return aleatorio.nextInt(max-min+1) + min;
        // ex: fakenews no início
        // para um dos índices -> de 1 a 7, então min = 1 e max = 7
        // mas o random não inclui o 7 no intervalo, ele sorteará
        // de 0 a 6, assim, precisaremos incrementar com +1 para garantir o sorteio
        // de [0,8] não incluindo 8. No final, precisamos ajustar qual 
        // é o intervalo inicial (min), então somamos min.
    }
    private void inicializaTabuleiro()
    {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                this.matriz[i][j] = new Setor("  ");
    }
    private void posicionaSetoresXX()
    {
        for (int i = 0; i < 4; i++) 
        {
            encontraSetorDisponivel(0, 8);
            this.matriz[this.linha][this.coluna] = new Setor("XX");
        }
    }
    private void posicionaJogadores(LinkedList<Jogador> j)
    {
        for (int i = 0; i < j.size(); i++)
            this.matriz[j.get(i).getLinha()][j.get(i).getColuna()] = j.get(i);
    }
    private void posicionaFakeNews(LinkedList<FakeNews> f)
    {
        // inicialmente, as fakenews não podem estar nas linhas e colunas 1 e 9
        for (int i = 0; i < f.size(); i++)
        {
            encontraSetorDisponivel(1, 7);
            this.matriz[this.linha][this.coluna] = f.get(i);
            f.get(i).setLinha(this.linha);
            f.get(i).setColuna(this.coluna);
        }
    }
    private void encontraSetorDisponivel(int min, int max)
    {
        int l, c;
        do
        {
            l = aleatorio(min, max);
            c = aleatorio(min, max);
        } while (!this.matriz[l][c].toString().equals("    "));
        this.setLinha(l);
        this.setColuna(c);
    }
    private void geraItem()
    {
        encontraSetorDisponivel(0, 8);
        Item i = new Item("??");
        this.matriz[this.linha][this.coluna] = i;
    }
}