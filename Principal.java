import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Jogo jogo = new Jogo(4);
        jogo.getTabuleiro().imprimirTabuleiro();
        // devolve e remove o primeiro elemento da lista
        Jogador j1 = jogo.getJogadores().pollFirst();

/*         System.out.println("Digite S para movimentar em direção ao sul");
        System.out.println("N em direção ao norte");
        System.out.println("L em direção ao leste e");
        System.out.println("O em direção ao oeste"); */
        String movimento = "Movimento inválido";
        while (!movimento.equals("Eliminado"))
        {
            movimento = jogo.getTabuleiro().movimentaPersonagem(input.nextLine(),j1.getLinha(),j1.getColuna());
            if (movimento.equals("Eliminado"))
                System.out.println(j1.getLabel()+" foi eliminado!");
            jogo.getTabuleiro().imprimirTabuleiro();
        }
    }
}