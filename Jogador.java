public class Jogador extends Entidade implements Movimentacao {
    //private Item item;
    
    public Jogador(String label)
    {
        super(label);
    }
    public void movimentar(String direcao)
    {
        switch (direcao.toLowerCase())
        {
            case "n":
                this.setPosicaoX(this.posicaoX-1);
                break;
            case "s":
                this.setPosicaoX(this.posicaoX+1);
                break;
            case "l":
                this.setPosicaoY(this.posicaoY+1);
                break;
            case "o":
                this.setPosicaoY(this.posicaoY-1);
                break;
            default:
                //alguma coisa
        }
    }  
}
