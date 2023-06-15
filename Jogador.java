public class Jogador extends EntidadeMovel {
    private Item item;
    private String comando;
     
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
    public void setComando(String comando)
    {
        this.comando = comando;
    }
    public void movimentar()
    {
        switch (this.comando.toLowerCase())
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
