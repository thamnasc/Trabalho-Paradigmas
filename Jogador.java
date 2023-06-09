public class Jogador extends Personagem {
    //quando finalizado seu turno, setar item para null
    private Item item;
    
    public Jogador(String label)
    {
        super(label);
    }
    public void setItem(Item item)
    {
        this.item = item;
    }
    public Item getItem()
    {
        return this.item;
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
                this.setLinha(-1);
                this.setColuna(-1);
                break;
        }
    }  
}
