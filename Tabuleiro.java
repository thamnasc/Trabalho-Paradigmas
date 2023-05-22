public class Tabuleiro {
    private Entidade matriz;

    public Tabuleiro(){
        // adicionar jogadores a posição tal
        // gerar itens aleatórios
        // 
        Entidade[][] matrizAux = new Entidade[9][9];
        this.setTabuleiro(matrizAux);
    }

    public void setTabuleiro(matriz){
        this.matriz = matriz;
    }

    public void imprimirTabuleiro(){

    }
    
}