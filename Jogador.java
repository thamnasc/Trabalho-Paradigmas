public class Jogador extends Entidade implements Movimentacao {
    //private Item item;
    
    public Jogador(String label)
    {
        super(label);
    }
    public void movimentar(String direcao)
    {
        System.out.println("Movimentando...");
    }  
}
