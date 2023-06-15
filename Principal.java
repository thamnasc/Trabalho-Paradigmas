import java.util.Scanner;
 
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);

        System.out.println("Mínimo de jogadores: 1.");
        System.out.println("Máximo de jogadores: 4.");
        System.out.print("Digite o número de jogadores que irão participar: ");

        int numJ = input.nextInt();
        Jogo jogo = new Jogo(numJ);

        jogo.getTabuleiro().imprimirTabuleiro();

        int oldL, oldC;
        String movimento, comando;
        while (jogo.getTurno() <= 20 && jogo.getFakeNews().size() > 0 && jogo.getJogadores().size() > 0)
        {
            System.out.println("======= TURNO " + jogo.getTurno() + " =======");

            System.out.println("======Turno dos Jogadores======");
            int turnosJ = 0;
            numj = jogo.getJogadores().size();
            while (turnosJ < numJ)
            {
                Jogador j = jogo.getJogadores().pollFirst();
                System.out.println("Vez de " + j.getLabel());
                comando = "n";
                if (j.getItem() != null)
                {
                    if (j.getItem().getTipo().equals("ouvir um boato"))
                    {
                        System.out.println("Ah, não! Você acabou de ouvir um boato.");
                        comando = "nenhum";
                        //executa
                    }
                    else 
                    {
                        System.out.println(j.getLabel()+", você gostaria de executar a ação "+j.getItem().getTipo()+"?");
                        System.out.println("Por favor, digite S ou N");
                        comando = input.nextLine().toLowerCase();
                        if (comando.equals("s"))
                            //executa
                    }
                }
                if ((comando.equals("s") && !j.getItem().getTipo().equals("fugir")) || comando.equals("n"))  
                {
                    j.setItem(null);

                    while (movimento.equals("Movimento inválido"))
                    {                 
                        System.out.print("Por favor, informe para qual direção quer se movimentar: ");
                        j.getComando(input.nextLine());
                        movimento = jogo.getTabuleiro().movimentarPersonagem(oldL, oldC);
                        //if (movimento.equals("Eliminado"))
                        if (movimento.equals("Movimento válido"))
                            jogo.getJogadores().addLast(j);
                    } 
                }
                turnosJ++;         
            }
            
            System.out.println("======Turno das FakeNews======");
            int numF = jogo.getFakeNews().size();
            int turnosF = 0;
            while (turnosF < numF)
            {
                FakeNews f = jogo.getFakeNews().pollFirst();
                movimento = "Movimento inválido";
                while (movimento.equals("Movimento inválido"))
                {
                    oldL = f.getLinha(); 
                    oldC = f.getColuna(); //guardar posicao antiga
                    movimento = jogo.getTabuleiro().movimentarPersonagem(oldL, oldC); //movimentar

                    if (movimento.equals("Movimento válido"))
                        jogo.getFakeNews().addLast(f);
                    else if 
                    {
                        jogo.getFakeNews().addLast(f);
                        jogo.getFakeNews().addLast(jogo.getTabuleiro().retornarFakeNewsDuplicada());
                    }
                    else if (movimento.equals("Eliminado"))
                        System.out.println(f.getLabel()+" foi eliminado!");
                }
                System.out.print(f.getLabel()+" andou da posição ["+(oldL+1)+"]["+(oldC+1)+"]");
                System.out.println(" para ["+(f.getLinha()+1)+"]["+(f.getColuna()+1)+"]");

                turnosF++;
            }
            jogo.incrementarTurno();
        }


        
        /* ----------------THALI TESTE------------------------------
        while (!movimento.equals("Eliminado"))
        {
            j1.setComando(input.nextLine());
            movimento = jogo.getTabuleiro().movimentarPersonagem(j1.getLinha(),j1.getColuna());
            System.out.println(movimento);
            if (movimento.equals("Eliminado"))
                System.out.println(j1.getLabel()+" foi eliminado!");
            if (movimento.equals(" capturou um item"))
                System.out.println(j1.getLabel() + movimento + " do tipo " + j1.getItem().getTipo());
            jogo.getTabuleiro().imprimirTabuleiro();

        } 

        /*String movimento = "Movimento inválido";
        while (!movimento.equals("Eliminado"))
        {
            int oldL = f.getLinha(), oldC = f.getColuna();
            movimento = jogo.getTabuleiro().movimentarPersonagem(oldL,oldC);
            System.out.print(f.getLabel()+" andou da posição ["+(oldL+1)+"]["+(oldC+1)+"]");
            System.out.println(" para ["+(f.getLinha()+1)+"]["+(f.getColuna()+1)+"]");
            System.out.println(movimento);
            if (movimento.equals("Eliminado"))
                System.out.println(f.getLabel()+" foi eliminado!");
            jogo.getTabuleiro().imprimirTabuleiro();
            Thread.sleep(2000);
        } */

    }
    public static void imprimirMenu(){
        /*         System.out.println("Digite S para movimentar em direção ao sul");
        System.out.println("N em direção ao norte");
        System.out.println("L em direção ao leste e");
        System.out.println("O em direção ao oeste"); */
    }
}