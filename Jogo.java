import java.util.*;

public class Jogo {
    private Tabuleiro tabuleiro;
    private int turno;
    private LinkedList<Jogador> jogadores;
    private LinkedList<FakeNews> fakeNews;
 
    public Jogo(int numJ)
    {
        LinkedList<Jogador> j = new LinkedList<Jogador>();
        this.setJogadores(j);
        this.criarJogadores(numJ);
        LinkedList<FakeNews> f = new LinkedList<FakeNews>();
        this.setFakenews(f);
        this.criarFakeNews();
        Tabuleiro tabuleiro = new Tabuleiro(this.jogadores, this.fakeNews);
        this.setTabuleiro(tabuleiro);
        this.setTurno(1);
    }
    private void setJogadores(LinkedList<Jogador> j)
    {
        this.jogadores = j;
    }
    private void setFakenews(LinkedList<FakeNews> f)
    {
        this.fakeNews = f;
    }
    private void setTabuleiro(Tabuleiro t)
    {
        this.tabuleiro = t;
    }
    private void setTurno(int turno)
    {
        if (turno > 0)
            this.turno = turno;
    }
    public int getTurno()
    {
        return this.turno;
    }
    public Tabuleiro getTabuleiro()
    {
        return this.tabuleiro;
    }
    public LinkedList<Jogador> getJogadores()
    {
        return this.jogadores;
    }
    public LinkedList<FakeNews> getFakeNews()
    {
        return this.fakeNews;
    }
    public void incrementarTurno(){
        this.setTurno(this.turno + 1);
    }
    private void criarJogadores(int numJ)
    {
        if (numJ >= 1)
        {
            Jogador j1 = new Jogador("J1");
            j1.setLinha(0);
            j1.setColuna(4);
            jogadores.addFirst(j1);
        }
        if (numJ >= 2) 
        {
            Jogador j2 = new Jogador("J2");
            j2.setLinha(8);
            j2.setColuna(4);
            jogadores.addLast(j2);
        }
        if (numJ >= 3) 
        {
            Jogador j3 = new Jogador("J3");
            j3.setLinha(4);
            j3.setColuna(8);
            jogadores.addLast(j3);
        }
        if (numJ == 4)
        {
            Jogador j4 = new Jogador("J4");
            j4.setLinha(4);
            j4.setColuna(0);
            jogadores.addLast(j4);
        }
    }
    private void criarFakeNews()
    {
        for (int i = 0; i < 2; i++)
        {
            FakeNews1 f1 = new FakeNews1("F1");
            this.fakeNews.addLast(f1);
        }
        for (int i = 0; i < 2; i++)
        {
            FakeNews2 f2 = new FakeNews2("F2");
            this.fakeNews.addLast(f2);
        }
        for (int i = 0; i < 2; i++)
        {
            FakeNews3 f3 = new FakeNews3("F3");
            this.fakeNews.addLast(f3);
        }
    }
}