public class Jogador extends EntidadeMovel {
    private Item item;
    private String comando;
    private boolean ouviuBoato;
    private boolean fugiu;
     
    public Jogador(String label)
    {
        super(label);
        this.setOuviuBoato(false);
        this.setFugiu(false);
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
    public void setOuviuBoato(boolean b)
    {
        this.ouviuBoato = b;
    }
    public void setFugiu(boolean f)
    {
        this.fugiu = f;
    }
    public void movimentar()
    {
        if (!this.ouviuBoato && !this.fugiu)
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
    public void ouvirBoato()
    {
        this.setLinha(this.aleatorio.sortearNumero(0, 8));
        this.setColuna(this.aleatorio.sortearNumero(0, 8));
    }
    @Override
    public String toString()
    {
        return String.format(Cores.ANSI_GREEN + " " + this.label + " " + Cores.ANSI_RESET);
    }
}
