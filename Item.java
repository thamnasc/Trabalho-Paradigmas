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
    private String tipo;
    private Aleatorio aleatorio;

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
                return "Denunciar Fake News";
            case 2:
                return "Fugir";
            case 3:
                return "Ler uma notícia real";
            case 4:
                return "Ouvir um boato";
            default: return " ";
        }
    }
    public String getTipo()
    {
        return this.tipo;
    }
    private void sorteiarTipo()
    {
        this.setTipo(this.aleatorio.sorteiarNumero(1, 4));
    }
}