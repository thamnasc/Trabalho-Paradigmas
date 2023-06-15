import java.util.*;

public class Tabuleiro {
    private Entidade[][] matriz;
    private int linha;
    private int coluna;
    private Aleatorio aleatorio;

    public Tabuleiro(LinkedList<Jogador> j, LinkedList<FakeNews> f)
    { 
        Entidade[][] matrizAux = new Entidade[9][9];
        this.setMatriz(matrizAux);
        this.inicializarTabuleiro();
        this.posicionarJogadores(j);        
        this.posicionarSetoresXX();
        this.posicionarFakeNews(f);
        this.gerarItem();
        this.gerarItem();
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
    public int getLinha()
    {
        return this.linha;
    }
    public int getColuna()
    {
        return this.coluna;
    }
    public String movimentarPersonagem(int l, int c)
    {
        Personagem p = (Personagem) this.matriz[l][c];
        p.movimentar();
        int newL = p.getLinha();
        int newC = p.getColuna();

        boolean movimentoInvalido = newL < 0 || newL > 8 || newC < 0 || newC > 8;
        boolean mesmaPosicao = l == newL && c == newC;
        
        if (mesmaPosicao)
            return "Movimento inválido";
        if (movimentoInvalido)
        {
            if (p instanceof Jogador)
            {
                p.setLinha(l);
                p.setColuna(c);
                return "Movimento inválido";
            }
            else // se saiu da borda, fakeNews é eliminada
            {
                this.matriz[l][c] = new Setor("  ");
                return "Eliminado";
            }
        }
        
        if (this.matriz[newL][newC].getLabel().equals("XX"))
        {
            this.matriz[l][c] = new Setor("  ");
            return "Eliminado";
        }

        String mensagem = "";

        if (p instanceof Jogador)
            mensagem = movimentarJogador((Jogador) p, l, c);

        if (p instanceof FakeNews)
            mensagem =  movimentarFakeNews((FakeNews) p, l, c);

        return mensagem;
    }
    private String movimentarJogador(Jogador j, int l, int c)
    {
        int newL = j.getLinha();
        int newC = j.getColuna();

        Entidade e = this.matriz[newL][newC];

        if (e instanceof Item)
        {
            // armazena item
            j.setItem((Item) e);

            // movimenta jogador
            this.matriz[newL][newC] = j;
            this.matriz[l][c] = new Setor("  ");
            // gera outro item aleatório no tabuleiro
            this.gerarItem();
        
            return "Movimento válido";
        }

        if (e instanceof FakeNews)
        {
            this.matriz[l][c] = new Setor("  ");
            return "Eliminado";
        }
        
        if (e instanceof Jogador)
        {
            j.setLinha(l);
            j.setColuna(c);
            return "Movimento inválido";
        }

        // se for um setor normal
        this.matriz[newL][newC] = j;
        this.matriz[l][c] = new Setor("  ");
        return "Movimento válido";
    }
    private String movimentarFakeNews(FakeNews f, int l, int c)
    {
        int newL = f.getLinha();
        int newC = f.getColuna();

        Entidade e = this.matriz[newL][newC];

        if (e instanceof Item)
        {
            this.matriz[newL][newC] = f; 
            this.matriz[l][c] = new Setor("  ");

            // duplica a fakenews
            this.sorteiarSetorAdjacente(newL, newC);
            if (f instanceof FakeNews1)
                this.matriz[this.linha][this.coluna] = new FakeNews1("F1");
            else if (f instanceof FakeNews2)
                this.matriz[this.linha][this.coluna] = new FakeNews2("F2");
            else 
                this.matriz[this.linha][this.coluna] = new FakeNews3("F3");

            // gera outro item aleatório no tabuleiro
            this.gerarItem();

            return "Movimento válido";
        }

        if (e instanceof Jogador)
        {
            this.matriz[newL][newC] = f;
            this.matriz[l][c] = new Setor("  ");
            // elimina jogador
        }

        if (e instanceof FakeNews)
        {
            f.setLinha(l);
            f.setColuna(c);
            return "Movimento inválido";
        }

        // se for um setor normal
        this.matriz[newL][newC] = f;
        this.matriz[l][c] = new Setor("  ");
        return "Movimento válido";
    }
    public void imprimirTabuleiro()
    {
        String divisoria = "+----+----+----+----+----+----+----+----+----+";

        for (int i = 0; i < 9; i++)
        {
            System.out.println(divisoria);
            for (int j = 0; j < 9; j++)
                System.out.print("|"+this.matriz[i][j]);
            System.out.println("|");
        }
        System.out.println(divisoria);
    }
    private void inicializarTabuleiro()
    {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                this.matriz[i][j] = new Setor("  ");
    }
    private void posicionarSetoresXX()
    {
        for (int i = 0; i < 4; i++) 
        {
            encontrarSetorDisponivel(0, 8);
            this.matriz[this.linha][this.coluna] = new Setor("XX");
        }
    }
    private void posicionarJogadores(LinkedList<Jogador> j)
    {
        for (int i = 0; i < j.size(); i++)
            this.matriz[j.get(i).getLinha()][j.get(i).getColuna()] = j.get(i);
    }
    private void posicionarFakeNews(LinkedList<FakeNews> f)
    {
        // inicialmente, as fakenews não podem estar nas linhas e colunas 1 e 9
        for (int i = 0; i < f.size(); i++)
        {
            encontrarSetorDisponivel(1, 7);
            this.matriz[this.linha][this.coluna] = f.get(i);
            f.get(i).setLinha(this.linha);
            f.get(i).setColuna(this.coluna);
        }
    }
    private void encontrarSetorDisponivel(int min, int max)
    {
        int l, c;
        do
        {
            l = this.aleatorio.sortearAleatorio(min, max);
            c = this.aleatorio.sortearAleatorio(min, max);
        } while (!this.matriz[l][c].getLabel().equals("  "));
        this.setLinha(l);
        this.setColuna(c);
    }
    private void gerarItem()
    {
        this.encontrarSetorDisponivel(0, 8);
        Item i = new Item("??");
        this.matriz[this.linha][this.coluna] = i;
    }
    private void sorteiarSetorAdjacente(int l, int c)
    {
        int newL = 0, newC = 0;
        boolean linhaInvalida = true;
        boolean colunaInvalida = true;
        boolean setorInvalido = true;
        
        while (setorInvalido)
        {
            while (linhaInvalida || colunaInvalida)
            {
                // para variar de -1 a 1
                newL = this.aleatorio.sortearAleatorio(1, 3) - 2; 
                newC = this.aleatorio.sortearAleatorio(1, 3) - 2; 
                linhaInvalida = newL + l < 0 || newL + l > 8;
                colunaInvalida = newC + c < 0 || newC + c > 8;
            }
            linhaInvalida = true;
            colunaInvalida = true;
            setorInvalido = !this.matriz[newL + l][newC + c].getLabel().equals("  ");
        }
        this.setLinha(newL + l);
        this.setColuna(newC + c);
    }
}