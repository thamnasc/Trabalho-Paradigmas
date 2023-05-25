public class Tabuleiro {
    private Entidade[][] matriz;

    //public Tabuleiro(){}

    public Tabuleiro(){
        Entidade[][] matrizAux = new Entidade[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j <9; j++)
                matrizAux[i][j] = new Setor (" ");
        matrizAux[0][4] = new Jogador("J1");
        this.setTabuleiro(matrizAux);
    }

    public void setTabuleiro(Entidade[][] matriz){
        this.matriz = matriz;
    }

    public void imprimirTabuleiro(){
        String linha = "+----+----+----+----+----+----+----+----+----+";

        for (int i = 0; i < 9; i++)
        {
            System.out.println(linha);
            for (int j = 0; j < 9; j++)
            {
                
                System.out.print("|"+this.matriz[i][j]);
            }
            System.out.print("|");
            System.out.print("\n");
        }
        System.out.println(linha);
    }
    
}