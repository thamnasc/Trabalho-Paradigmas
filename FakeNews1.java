public class FakeNews1 extends FakeNews {
    public FakeNews1 (String label){
        super(label);
    } 
    public void movimentar()
    {
        int dir = aleatorio(1, 4);
        switch (dir)
        {
            case 1: //norte
                this.setLinha(this.linha - 1);
                break;
            case 2: //sul
                this.setLinha(this.linha + 1);
                break;
            case 3: //leste
                this.setColuna(this.coluna + 1);
                break;
            case 4: //oeste
                this.setColuna(this.coluna - 1);
                break;
        }
    }   
}