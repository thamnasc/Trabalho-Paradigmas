public class Item extends EntidadeInerte {
    private String tipo;

    public Item(String label)
    {
        super(label);
        this.sortearTipo();
    }
    private void setTipo(int tipo)
    {
        switch (tipo)
        {
            case 1:
                this.tipo = "denunciar fake news";
                break;
            case 2:
                this.tipo = "fugir";
                break;
            case 3:
                this.tipo = "ler uma notícia real";
                break;
            case 4:
                this.tipo = "ouvir um boato";
                break;
            default:
                this.tipo = "";
                break;
        }
    }
    public String getTipo()
    {
        return this.tipo;
    }
    private void sortearTipo()
    {
        this.setTipo(this.aleatorio.sortearNumero(1, 4));
    }
    @Override
    public String toString()
    {
        return String.format(Cores.ANSI_YELLOW + " " + this.label + " " + Cores.ANSI_RESET);
    }
}