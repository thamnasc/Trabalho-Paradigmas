public class Tabuleiro {
    private Entidade matriz;

    public Tabuleiro(){}

    public Tabuleiro(){
        // adicionar jogadores a posição tal
        // gerar itens aleatórios
        // 
        Entidade[][] matrizAux = new Entidade[9][9];
        this.setTabuleiro(matrizAux);
    }

    public void setTabuleiro(Entidade matriz){
        this.matriz = matriz;
    }

    public void imprimirTabuleiro(){

        for (int j = 1; j <= 9; j++)
        {
            System.out.println("+----");       
        }

        for (int i = 1; i <= 9; i++)
        {
            for (int j = 1; j <= 9; j++)
            {
                
                    
            }
        }
    }
    
}