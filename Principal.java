import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Jogo jogo = new Jogo(4);
        jogo.getTabuleiro().imprimirTabuleiro();
        // devolve e remove o primeiro elemento da lista
        Jogador j1 = jogo.getJogadores().pollFirst();
        FakeNews f = jogo.getFakeNews().pollLast();
/*         System.out.println("Digite S para movimentar em direção ao sul");
        System.out.println("N em direção ao norte");
        System.out.println("L em direção ao leste e");
        System.out.println("O em direção ao oeste"); */



/*         String movimento = "Movimento inválido";
        while (!movimento.equals("Eliminado"))
        {
            j1.setComando(input.nextLine());
            movimento = jogo.getTabuleiro().movimentaPersonagem(j1.getLinha(),j1.getColuna());
            System.out.println(movimento);
            if (movimento.equals("Eliminado"))
                System.out.println(j1.getLabel()+" foi eliminado!");
            jogo.getTabuleiro().imprimirTabuleiro();
        } */

        String movimento = "Movimento inválido";
        while (!movimento.equals("Eliminado"))
        {
            int oldL = f.getLinha(), oldC = f.getColuna();
            movimento = jogo.getTabuleiro().movimentaPersonagem(oldL,oldC);
            System.out.print(f.getLabel()+" andou da posição ["+(oldL+1)+"]["+(oldC+1)+"]");
            System.out.println(" para ["+(f.getLinha()+1)+"]["+(f.getColuna()+1)+"]");
            System.out.println(movimento);
            if (movimento.equals("Eliminado"))
                System.out.println(f.getLabel()+" foi eliminado!");
            jogo.getTabuleiro().imprimirTabuleiro();
            Thread.sleep(2000);
        } 

    }
}