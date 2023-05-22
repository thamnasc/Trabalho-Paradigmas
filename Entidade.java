public abstract class Entidade {
    protected int id;

    public void setId(int id){
        if(id > 0)
            this.id = id;
    }

    public int getId(){
        return this.id;
    }
}