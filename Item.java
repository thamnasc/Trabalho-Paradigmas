/*
Quando um jogador se movimentar para uma posição do tabuleiro que houver um
item, o jogador deve armazenar esse item e eliminá-lo do tabuleiro. Na sequência,
um novo item qualquer deverá surgir em outra posição do tabuleiro.
*/ 
public class Item extends Inerte {
    /* Tipos:
        a - Denunciar fake news
        b - Fugir
        c - Ler uma notícia real
        d - Ouvir um boato
    */
    private char tipo;

    public Item(String label)
    {
        super(label);
        this.sorteiarTipo();
    }
    private void setTipo(int tipo)
    {
        switch (tipo)
        {
            case 1:
                this.tipo = 'a';
                break;
            case 2:
                this.tipo = 'b';
                break;
            case 3:
                this.tipo = 'c';
                break;
            case 4:
                this.tipo = 'd';
        }
    }
    public String getTipo()
    {
        switch (this.tipo)
        {
            case 'a':
                return "Denunciar Fake News";
            case 'b':
                return "Fugir";
            case 'c':
                return "Ler uma notícia real";
            case 'd':
                return "Ouvir um boato";
            default: return " ";
        }
    }
    private void sorteiarTipo()
    {
        this.setTipo(aleatorio(1, 4));
    }
}