import java.util.Random;
 
public abstract class Entidade {
    protected String label;
    
    public Entidade(String label)
    { 
        this.setLabel(label);
    }
    public void setLabel(String label)
    {
        this.label = label;
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