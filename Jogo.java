import java.util.Random;

public class Jogo {
    private Tabuleiro tabuleiro;
    private int turno;

    public Jogo(Tabuleiro t){
        this.setTabuleiro(t);
        this.setTurno(0);
    }

    public void setTabuleiro(Tabuleiro t){
        // verifica se há entidades no tabuleiro
        if(tabuleiro.length > 0)
            this.tabuleiro = t;
    }

    public void setTurno(int turno){
        if(turno >= 0)
            this.turno = turno;
    }

    public int getTurno(){
        return this.turno;
    }

    public tabuleiro getTabuleiro(){
        return this.tabuleiro;
    }

    public Item gerarItem(){

    }
}