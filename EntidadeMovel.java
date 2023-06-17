public abstract class EntidadeMovel extends Entidade implements Movimentacao {
    protected int linha;
    protected int coluna;

    public EntidadeMovel(String label)
    {
        super(label);
        this.setAleatorio(new Aleatorio());
    } 
    public void setLinha(int linha)
    {
        this.linha = linha;
    }
    public void setColuna(int coluna) 
    {
        this.coluna = coluna;
    }
    public int getLinha() 
    {
        return this.linha;
    }
    public int getColuna() 
    {
        return this.coluna;
    }
}