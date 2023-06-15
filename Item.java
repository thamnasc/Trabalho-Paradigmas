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
                return "denunciar fake news";
            case 2:
                return "fugir";
            case 3:
                return "ler uma notícia real";
            case 4:
                return "ouvir um boato";
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