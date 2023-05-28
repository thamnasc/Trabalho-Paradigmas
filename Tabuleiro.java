import java.util.Random;

public class Tabuleiro {
    private Entidade[][] matriz;

    public Tabuleiro(int numJ)
    {
        Entidade[][] matrizAux = new Entidade[9][9];
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                matrizAux[i][j] = new Setor("  ");
        if (numJ >= 1)
            matrizAux[0][4] = new Jogador("J1");
        if (numJ >= 2)
            matrizAux[8][4] = new Jogador("J2");
        if (numJ >= 3)
            matrizAux[4][8] = new Jogador("J3");
        if (numJ == 4)
            matrizAux[4][0] = new Jogador("J4");


        boolean indisponivel = true;
        int i = 0, j = 0;
        while (indisponivel) {
            i = aleatorio(0,8);
            j = aleatorio(0,8);
            if (!matrizAux[i][j].toString().equals("  "))
                indisponivel = false;
        }
        matrizAux[i][j] = new Setor("XX");
        this.setTabuleiro(matrizAux);
    }
    public void setTabuleiro(Entidade[][] matriz)
    {
        this.matriz = matriz;
    }
    public void imprimirTabuleiro()
    {
        String linha = "+----+----+----+----+----+----+----+----+----+";

        for (int i = 0; i < 9; i++)
        {
            System.out.println(linha);
            for (int j = 0; j < 9; j++)
                System.out.print("|"+this.matriz[i][j]);
            System.out.println("|");
        }
        System.out.println(linha);
    }
    private int aleatorio(int min, int max)
    {
        Random aleatorio = new Random();
        return aleatorio.nextInt(max-min+1) + min;
        // ex: fakenews no início
        // para um dos índices -> de 1 a 7, então min = 1 e max = 7
        // mas o random não inclui o 7 no intervalo, ele sorteará
        // de 0 a 6, assim, precisaremos incrementar com +1 para garantir o sorteio
        // de [0,8] não incluindo 8. No final, precisamos ajustar qual 
        // é o intervalo inicial (min), então somamos min.
    }
    
}