public class Setor extends Entidade {

    public Setor(String label)
    {
        super(label);
    } 
    public boolean isXX()
    {
        return this.label.equals("XX");
    }
    public void movimentar(String direcao)
    {
        System.out.println("Movimentando...");
    }   
}
