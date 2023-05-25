public class Setor extends Entidade {
    public Setor (String label){
        super(label);
    }

    public String toString(){
        return String.format(" " + this.label + "  ");
    }
}
