import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Jogo jogo = new Jogo(4);
        jogo.getTabuleiro().imprimirTabuleiro();
/*         Jogador j1 = jogo.getJogadores().getFirst();

        System.out.println("Digite S para movimentar em direção ao sul");
        System.out.println("N em direção ao norte");
        System.out.println("L em direção ao leste e");
        System.out.println("O em direção ao oeste");

        if(!jogo.getTabuleiro().movimentaPersonagem(input.nextLine(), j1.getLinha(), j1.getColuna()))
            System.out.println("Movimento Inválido!");
        jogo.getTabuleiro().imprimirTabuleiro(); */
    }
}