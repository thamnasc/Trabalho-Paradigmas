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
        String linha = "+----+----+----+----+----+----+----+----+----+";

        for (int i = 1; i <= 9; i++)
        {
            System.out.println(linha);
            for (int j = 1; j <= 9; j++)
            {
                
                    System.out.println("|"+this.matriz[i][j]);
            }
        }
    }
    
}