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
                this.setLinha(this.linha-1);
                break;
            case "s":
                this.setLinha(this.linha+1);
                break;
            case "l":
                this.setColuna(this.coluna+1);
                break;
            case "o":
                this.setColuna(this.coluna-1);
                break;
            default:
                //precisa implementar algo para quando o jogador digitar alguma letra inesperada
        }
    }  
}
