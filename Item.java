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
        this.sorteiaTipo();
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
    public char getTipo()
    {
        return this.tipo;
    }
    private void sorteiaTipo()
    {
        this.setTipo(aleatorio(1, 4));
    }
}