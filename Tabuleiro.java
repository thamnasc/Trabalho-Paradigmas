import java.util.*;

public class Tabuleiro {
    private Entidade[][] matriz;
    private int linha;
    private int coluna;
    private Aleatorio aleatorio;
    private Jogador jEliminado;

    public Tabuleiro(LinkedList<Jogador> j, LinkedList<FakeNews> f)
    { 
        Entidade[][] matrizAux = new Entidade[9][9];
        this.setMatriz(matrizAux);
        this.setAleatorio(new Aleatorio());
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
    private void setAleatorio(Aleatorio a)
    {
        this.aleatorio = a;
    }
    public int getLinha()
    {
        return this.linha;
    }
    public int getColuna()
    {
        return this.coluna;
    }
    public String movimentarEntidade(int l, int c)
    {
        EntidadeMovel e = (EntidadeMovel) this.matriz[l][c];
        e.movimentar();
        int newL = e.getLinha();
        int newC = e.getColuna();

        boolean movimentoInvalido = newL < 0 || newL > 8 || newC < 0 || newC > 8;
        boolean mesmaPosicao = l == newL && c == newC;
        
        if (mesmaPosicao)
            return "Movimento inválido";
        if (movimentoInvalido)
        {
            if (e instanceof Jogador)
            {
                e.setLinha(l);
                e.setColuna(c);
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

        if (e instanceof Jogador)
            mensagem = movimentarJogador((Jogador) e, l, c);

        if (e instanceof FakeNews)
            mensagem =  movimentarFakeNews((FakeNews) e, l, c);

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
        
            return "Capturou um item";
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

            // gera outro item aleatório no tabuleiro
            this.gerarItem();

            // duplica a fakenews
            this.sorteiarSetorAdjacente(newL, newC);
            FakeNews fx2;
            if (f instanceof FakeNews1)
                fx2 = (FakeNews1) new FakeNews1("F1");
            else if (f instanceof FakeNews2)
                fx2 = (FakeNews2) new FakeNews2("F2");
            else 
                fx2 = (FakeNews3) new FakeNews3("F3");
            fx2.setLinha(this.linha);
            fx2.setColuna(this.coluna);
            this.matriz[this.linha][this.coluna] = fx2;

            return "Duplicada";
        }

        if (e instanceof Jogador)
        {
            this.jEliminado = (Jogador) this.matriz[newL][newC];
            this.matriz[newL][newC] = f;
            this.matriz[l][c] = new Setor("  ");
            return "Eliminou jogador";
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
    public Jogador retornarJogadorEliminado()
    {
        return this.jEliminado;
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
        System.out.println(divisoria+"\n");
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
            this.encontrarSetorDisponivel(0, 8);
            this.matriz[this.linha][this.coluna] = new Setor("XX");
        }
    }
    private void posicionarJogadores(LinkedList<Jogador> j)
    {
        for (Jogador jogador : j)
            this.matriz[jogador.getLinha()][jogador.getColuna()] = jogador;
    }
    private void posicionarFakeNews(LinkedList<FakeNews> f)
    {
        // inicialmente, as fakenews não podem estar nas linhas e colunas 1 e 9
        for (FakeNews fakeNews : f)
        {
            this.encontrarSetorDisponivel(1, 7);
            this.matriz[this.linha][this.coluna] = fakeNews;
            fakeNews.setLinha(this.linha);
            fakeNews.setColuna(this.coluna);
        }
    }
    private void encontrarSetorDisponivel(int min, int max)
    {
        int l, c;
        do
        {
            l = this.aleatorio.sortearNumero(min, max);
            c = this.aleatorio.sortearNumero(min, max);
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
                newL = this.aleatorio.sortearNumero(1, 3) - 2; 
                newC = this.aleatorio.sortearNumero(1, 3) - 2; 
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
    public FakeNews retornarFakeNewsDuplicada()
    { 
        return (FakeNews) this.matriz[this.linha][this.coluna];
    }

    public LinkedList<FakeNews> denunciarFakeNews(LinkedList<FakeNews> f, int l, int c)
    {
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
            {
                int lAdj = l+i;
                int cAdj = c+j;
                boolean linhaValida = lAdj >= 0 && lAdj <= 8;
                boolean colunaValida = cAdj >= 0 && cAdj <= 8;
                if (linhaValida && colunaValida)
                    if (this.matriz[lAdj][cAdj] instanceof FakeNews)
                    {
                        Iterator<FakeNews> iterator = f.iterator();
                        int index = -1, foundIndex = -1;

                        while(iterator.hasNext())
                        {
                            index++;
                            FakeNews fakeNews = iterator.next();
                            if (fakeNews.getLinha() == lAdj && fakeNews.getColuna() == cAdj)
                                foundIndex = index;
                        }
                        f.remove(foundIndex);
                        this.matriz[lAdj][cAdj] = new Setor("  ");
                    }
            }
        return f;
    }

    public LinkedList<FakeNews> lerNoticiaReal(LinkedList<FakeNews> f)
    {
        int size = f.size();
        int i = this.aleatorio.sortearNumero(0, size-1);
        this.matriz[f.get(i).getLinha()][f.get(i).getColuna()] = new Setor("  ");
        f.remove(i);
        return f;
    }
}