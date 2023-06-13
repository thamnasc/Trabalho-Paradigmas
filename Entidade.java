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
    public int aleatorio(int min, int max)
    {
        Random aleatorio = new Random();
        return aleatorio.nextInt(max-min+1) + min;
        // ex: fakenews no início
        // para um dos índices -> de 1 a 7, então min = 1 e max = 7
        // mas o random não inclui o 7 no intervalo, ele sorteará
        // de 0 a 6, assim, precisaremos incrementar com +1 para garantir o sorteio
        // de [0,8] não incluindo 8. No final, precisamos ajustar qual 
        // é o intervalo inicial (min), então somamos min.
    }
}