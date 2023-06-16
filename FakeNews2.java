public class FakeNews2 extends FakeNews {
    public FakeNews2 (String label)
    {
        super(label);
    }
    public void movimentar()
    {
        //int dir = aleatorio(1, 4);
        int dir = this.aleatorio.sortearNumero(1, 4);
        switch (dir)
        {
            case 1: //norte
                this.setLinha(this.linha - 2);
                break;
            case 2: //sul
                this.setLinha(this.linha + 2);
                break;
            case 3: //leste
                this.setColuna(this.coluna + 2);
                break;
            case 4: //oeste
                this.setColuna(this.coluna - 2);
                break;
        }
    }       
}