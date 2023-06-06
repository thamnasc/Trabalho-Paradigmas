import java.util.*;

public class Tabuleiro {
    private Entidade[][] matriz;
    private int posicaoX;
    private int posicaoY;

    public Tabuleiro(LinkedList<Jogador> j, LinkedList<FakeNews> f)
    { 
        Entidade[][] matrizAux = new Entidade[9][9];
        this.setMatriz(matrizAux);
        inicializaTabuleiro();
        posicionaJogadores(j);        
        posicionaSetoresXX();
        posicionaFakeNews(f);
    }
    private void setMatriz(Entidade[][] matriz)
    {
        this.matriz = matriz;
    }
    private void setPosicaoX(int x)
    {
        if (x >= 0 && x <= 8)
            this.posicaoX = x;
    }
    public boolean movimentaEntidade(String direcao, int x, int y)
    {
        this.matriz[x][y].movimentar(direcao);
        int newX = this.matriz[x][y].getPosicaoX();
        int newY = this.matriz[x][y].getPosicaoY();
        boolean movimentoValido = newX >= 0 && newX <= 8 && newY >= 0 && newY <= 8;
        if (!movimentoValido)
        {
            this.matriz[x][y].setPosicaoX(x);
            this.matriz[x][y].setPosicaoY(y);
        }
        else
        {
            this.matriz[newX][newY] = this.matriz[x][y];
            this.matriz[x][y] = new Setor("  ");
        }
        return movimentoValido;
    }
    private void setPosicaoY(int y)
    {
        if (y >= 0 && y <= 8)
            this.posicaoY = y;
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
        encontraSetorDisponivel(0, 8);
        this.matriz[this.posicaoX][this.posicaoY] = new Setor("XX");
        encontraSetorDisponivel(0, 8);
        this.matriz[this.posicaoX][this.posicaoY] = new Setor("XX");
    }
    private void posicionaJogadores(LinkedList<Jogador> j)
    {
        for (int i = 0; i < j.size(); i++)
            this.matriz[j.get(i).getPosicaoX()][j.get(i).getPosicaoY()] = j.get(i);
    }
    private void posicionaFakeNews(LinkedList<FakeNews> f)
    {
        // inicialmente, as fakenews não podem estar nas linhas e colunas 1 e 9
        for (int i = 0; i < f.size(); i++)
        {
            encontraSetorDisponivel(1, 7);
            this.matriz[this.posicaoX][this.posicaoY] = f.get(i);
            f.get(i).setPosicaoX(this.posicaoX);
            f.get(i).setPosicaoY(this.posicaoY);
        }
    }
    private void encontraSetorDisponivel(int min, int max)
    {
        int x, y;
        do
        {
            x = aleatorio(min, max);
            y = aleatorio(min, max);
        } while (!this.matriz[x][y].toString().equals("    "));
        this.setPosicaoX(x);
        this.setPosicaoY(y);
    }
}