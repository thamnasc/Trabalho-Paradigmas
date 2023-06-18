import java.util.Random;
 
public abstract class Entidade {
    protected String label;
    protected Aleatorio aleatorio;
    
    public Entidade(String label)
    { 
        this.setLabel(label);
        this.setAleatorio(new Aleatorio());
    }
    public void setLabel(String label)
    {
        this.label = label;
    }
    private void setAleatorio(Aleatorio a)
    {
        this.aleatorio = a;
    }
    public String getLabel()
    {
        return this.label;
    }
    @Override
    public String toString()
    {
        return String.format(" " + this.label + " ");
    }
}