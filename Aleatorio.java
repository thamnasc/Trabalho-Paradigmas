import java.util.Random;

public class Aleatorio {
    public int sortearNumero(int min, int max)
    {
        Random aleatorio = new Random();
        return aleatorio.nextInt(max-min+1) + min;
    }
}