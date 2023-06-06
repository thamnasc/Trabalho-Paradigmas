import java.util.Random;
public abstract class Entidade {
    protected String label;
    protected int posicaoX;
    protected int posicaoY;

    public Entidade(){}
    public Entidade(String label)
    { 
        this.setLabel(label);
    }

    public void setLabel(String label)
    {
        this.label = label;
    }
    public String getLabel()
    {
        return this.label;
    }
    public void setPosicaoX (int posicaoX){
        this.posicaoX = posicaoX;
    }
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }
    public int getPosicaoX() {
        return this.posicaoX;
    }
    public int getPosicaoY() {
        return this.posicaoY;
    }


    @Override
    public String toString()
    {
        return String.format(" " + this.label + " ");
    }

    public int aleatorio(int min, int max)
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
    
    public void movimentar(String direcao){
        if (direcao.equals("S"))
            this.setPosicaoY(this.posicaoY + 1);
        else if (direcao.equals("N"))
            this.setPosicaoY( this.posicaoY- 1);
        else if (direcao.equals("L"))
            this.setPosicaoX(this.posicaoX + 1);
        else if (direcao.equals("O"))
            this.setPosicaoX(this.posicaoX - 1);
    }

}