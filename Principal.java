import java.util.*;
/* Trabalho realizado pelas alunas
    Isabela da Silva Barata     GRR20213395
    Thalita Maria do Nascimento GRR20211079
 */
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        imprimirMenu();
        System.out.print("Digite o número de jogadores que irão participar: ");

        int numJ = input.nextInt();
        int jogadores = numJ;
        Jogo jogo = new Jogo(numJ);

        jogo.getTabuleiro().imprimirTabuleiro();

        int oldL, oldC;
        String movimento, comando;
        while (jogo.getTurno() <= 20 && jogo.getFakeNews().size() > 0 && jogo.getJogadores().size() > 0)
        {
            System.out.println("\n======= TURNO " + jogo.getTurno() + " =======\n");

            System.out.println("\n======Turno dos Jogadores======\n");
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
                            j.setOuviuBoato(true);
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
                            j.setOuviuBoato(false);
                        }
                    }
                    else 
                    {
                        System.out.println(j.getLabel()+", você gostaria de executar a ação "+item+"?");
                        System.out.println("Por favor, digite S ou N");
                        comando = input.nextLine().toLowerCase();
                        if (comando.equals("s"))
                        {
                            if (item.equals("denunciar fake news"))
                            {
                                jogo.getTabuleiro().denunciarFakeNews(oldL, oldC);
                                System.out.println(j.getLabel()+" denunciou fake news a sua volta!");
                            }
                            if (item.equals("ler uma notícia real"))
                            {
                                LinkedList<FakeNews> fakeNews = jogo.getTabuleiro().lerNoticiaReal(jogo.getFakeNews());
                                jogo.setFakenews(fakeNews);
                                System.out.println(j.getLabel()+" leu uma notícia real e uma fake news foi eliminada!");
                            }
                        }
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
                            item = j.getItem().getTipo();
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
                System.out.println("\n======Turno das FakeNews======\n");
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

        if (jogo.getFakeNews().size() == 0)
        {
            if (jogadores == 1)
                System.out.println("Você conseguiu fugir das fake news! Parabéns!");
            else
                System.out.println("Os jogadores derrotaram todas as fake news!");
        }   
        else if (jogo.getTurno() > 20)
            System.out.println("Game Over!");

    }
public static void imprimirMenu(){
    System.out.println("============== JOGO CORRA DAS FAKE NEWS ===============");
    System.out.println("===== O jogo consiste em eliminar todas as fake news do tabuleiro antes de ser eliminado por elas ==");
    System.out.print("\n");
    System.out.println("=== Elementos do jogo: ");
    System.out.println("-> F1, F2, F3: representam os 3 tipos de fake news, diferentes apenas no modo que se movimentam.");
    System.out.println("-> ??: representa um ITEM, que pode ser de quatro tipos: ");
    System.out.println("#### Item 1: denunciar fake news. Permite denunciar qualquer fake news em volta do joador, eliminando-as.");
    System.out.println("#### Item 2: fugir. Permite que o jogador se mova para qualquer posição do tabuleiro.");
    System.out.println("#### Item 3: ler uma noticia real. Permite que o jogador elimina uma fake news qualquer.");
    System.out.println("#### Item 4: ouvir um boato. Esse é o item ruim e faz com que no próximo turno o movimento do jogador seja aleatório.");
    System.out.println("-> J1, J2, J3, J4: representa cada um dos jogadores.");
    System.out.print("\n");
    System.out.println("=== As possíveis direções para movimentar um jogador são NORTE, SUL, LESTE OU OESTE.===");
    System.out.println("Digite S para movimentar em direção ao sul;");
    System.out.println("# Digite N para direção norte;");
    System.out.println("# Digite L para direção leste;");
    System.out.println("# Digite O para direção oeste."); 
    System.out.print("\n");
    System.out.println("Minimo de jogadores: 1.");
    System.out.println("Máximo de jogadores: 4.");
    System.out.println("======= BOA SORTE! E que o jogo comece! ========");
    }
}