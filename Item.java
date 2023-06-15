public class Item extends EntidadeInerte {
    private String tipo;
    private Aleatorio aleatorio;

    public Item(String label)
    {
        super(label);
        this.sorteiarTipo();
    }
    private void setTipo(int tipo)
    {
        switch (tipo)
        {
            case 1:
                return "Denunciar Fake News";
            case 2:
                return "Fugir";
            case 3:
                return "Ler uma notícia real";
            case 4:
                return "Ouvir um boato";
            default: return " ";
        }
    }
    public String getTipo()
    {
        return this.tipo;
    }
    private void sorteiarTipo()
    {
        this.setTipo(this.aleatorio.sorteiarNumero(1, 4));
    }
}