public class Item extends EntidadeInerte {
    private String tipo;
    private Aleatorio aleatorio;

    public Item(String label)
    {
        super(label);
        this.setAleatorio(new Aleatorio());
        this.sortearTipo();
    }
    private void setTipo(int tipo)
    {
        switch (tipo)
        {
            case 1:
                this.tipo = "denunciar fake news";
            case 2:
                this.tipo = "fugir";
            case 3:
                this.tipo = "ler uma notícia real";
            case 4:
                this.tipo = "ouvir um boato";
            default:
                this.tipo = "";
        }
    }
    private void setAleatorio(Aleatorio a)
    {
        this.aleatorio = a;
    }
    public String getTipo()
    {
        return this.tipo;
    }
    private void sortearTipo()
    {
        this.setTipo(this.aleatorio.sortearNumero(1, 4));
    }
}