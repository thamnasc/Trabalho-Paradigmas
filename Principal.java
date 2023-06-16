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
            numJ = jogo.getJogadores().size();
            while (turnosJ < numJ)
            {
                Jogador j = jogo.getJogadores().pollFirst();
                oldL = j.getLinha();
                oldC = j.getColuna();
                System.out.println("Vez de " + j.getLabel());
                comando = "n";
                String item = "";
                if (j.getItem() != null)
                {
                    item = j.getItem().getTipo();
                    if (item.equals("ouvir um boato"))
                    {
                        System.out.println("Ah, não! " + j.getLabel() + " acabou de ouvir um boato.");
                        System.out.println(j.getLabel()+ " foi movido para ["+(oldL+1)+"]["+(oldC+1)+"]");
                        comando = "nenhum";
                        movimento = "Movimento inválido";
                        while (movimento.equals("Movimento inválido"))
                        {
                            j.ouvirBoato();
                            movimento = jogo.getTabuleiro().movimentarEntidade(oldL, oldC);
                            if (movimento.equals("Eliminado"))
                                System.out.println(j.getLabel()+" foi eliminado!");
                            else if (movimento.equals("Movimento válido"))
                                jogo.getJogadores().addLast(j);
                            else if (movimento.equals("Capturou um item"))
                            {
                                System.out.println(j.getLabel()+" capturou um item!");
                                jogo.getJogadores().addLast(j);
                            }
                        }
                    }
                    else 
                    {
                        System.out.println(j.getLabel()+", você gostaria de executar a ação "+item+"?");
                        System.out.println("Por favor, digite S ou N");
                        comando = input.nextLine().toLowerCase();
                        if (comando.equals("s"))
                            //executa
                            System.out.println("Ainda preciso ser implementado!");
                    }
                    j.setItem(null);
                }
                if ((comando.equals("s") && !item.equals("fugir")) || comando.equals("n"))  
                {
                    movimento = "Movimento inválido";
                    while (movimento.equals("Movimento inválido"))
                    {                 
                        System.out.print("Por favor, informe para qual direção quer se movimentar: ");
                        j.setComando(input.nextLine());
                        movimento = jogo.getTabuleiro().movimentarEntidade(oldL, oldC);
                        if (movimento.equals("Eliminado"))
                            System.out.println(j.getLabel()+" foi eliminado!");
                        else if (movimento.equals("Movimento válido"))
                            jogo.getJogadores().addLast(j);
                        else if (movimento.equals("Capturou um item"))
                        {
                            System.out.println(j.getLabel()+" capturou um item!");
                            jogo.getJogadores().addLast(j);
                        }
                    } 
                }
                turnosJ++;   
                comando = "n";
                jogo.getTabuleiro().imprimirTabuleiro();      
            }
            
            if (jogo.getJogadores().size() > 0)
            {
                Thread.sleep(2000);
                System.out.println("======Turno das FakeNews======");
                int numF = jogo.getFakeNews().size();
                int turnosF = 0;
                while (turnosF < numF)
                {
                    FakeNews f = jogo.getFakeNews().pollFirst();
                    movimento = "Movimento inválido";
                    oldL = f.getLinha(); 
                    oldC = f.getColuna(); 
                    while (movimento.equals("Movimento inválido"))
                    {
                        movimento = jogo.getTabuleiro().movimentarEntidade(oldL, oldC);

                        if (movimento.equals("Movimento válido"))
                            jogo.getFakeNews().addLast(f);
                        else if (movimento.equals("Duplicada"))
                        {
                            System.out.println(f.getLabel()+" foi duplicada!");
                            jogo.getFakeNews().addLast(f);
                            jogo.getFakeNews().addLast(jogo.getTabuleiro().retornarFakeNewsDuplicada());
                        }
                        else if (movimento.equals("Eliminado"))
                            System.out.println(f.getLabel()+" foi eliminado!");
                    }
                    System.out.print(f.getLabel()+" andou da posição ["+(oldL+1)+"]["+(oldC+1)+"]");
                    System.out.println(" para ["+(f.getLinha()+1)+"]["+(f.getColuna()+1)+"]");
                
                    turnosF++;
                    jogo.getTabuleiro().imprimirTabuleiro();
                    Thread.sleep(2000);
                }
                jogo.incrementarTurno();
            }
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
    public static void imprimirMenu()
    {
        /*         System.out.println("Digite S para movimentar em direção ao sul");
        System.out.println("N em direção ao norte");
        System.out.println("L em direção ao leste e");
        System.out.println("O em direção ao oeste"); */
    }
}