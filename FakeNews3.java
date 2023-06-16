public class FakeNews3 extends FakeNews {
    public FakeNews3 (String label)
    {
        super(label);
    } 
    public void movimentar()
    {
        //int dir = aleatorio(1, 4);
        int dir = this.aleatorio.sortearNumero(1, 4);
        switch (dir)
        {
            case 1: //noroeste
                this.setLinha(this.linha - 1);
                this.setColuna(this.coluna -1);
                break;
            case 2: //nordeste
                this.setLinha(this.linha - 1);
                this.setColuna(this.coluna + 1);
                break;
            case 3: //sudoeste
                this.setLinha(this.linha + 1);
                this.setColuna(this.coluna - 1);
                break;
            case 4: //sudeste
                this.setLinha(this.linha + 1);
                this.setColuna(this.coluna + 1);
                break;
        }
    }   
}