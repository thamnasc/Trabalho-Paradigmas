import java.util.Scanner;
 
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Mínimo de jogadores: 1.");
        System.out.println("Máximo de jogadores: 4.");
        System.out.print("Digite o número de jogadores que irão participar: ");
        int jogadoresVivos = input.nextInt();
        Jogo jogo = new Jogo(jogadoresVivos);

        jogo.getTabuleiro().imprimirTabuleiro();
        // devolve e remove o primeiro elemento da lista
        //Jogador j1 = jogo.getJogadores().pollFirst();
        //FakeNews f = jogo.getFakeNews().pollLast();
        int oldL, oldC;
        String movimento;
        while (jogo.getTurno() <= 20 && jogo.getFakeNews().size() > 0 && jogo.getJogadores().size() > 0)
        {
            System.out.println("======= TURNO " + jogo.getTurno() + " =======");
            //turno dos jogadores (while para passar por todos ----usar jogadoresVivos)
            /*while (....){
                Jogador j = jogo.getJogadores().pollFirst();
                System.out.println("Vez de " + j.getLabel());
                quer usar = false
                if tem item 
                    if boato
                        executa
                    else 
                        quer usar = input
                        if quer usar
                            executa
                if (quer usar && not fugir) || not quer usar  
                    pede direcao  
                    movimenta
                item = null

                (-----movimenta--------)
                while (movimento.equals("Movimento inválido")){
                    //se movimento foi invalido, movimenta de novo (mesmo while da fakenews)
                    oldL = j.getLinha(); 
                    oldC = j.getColuna(); //guardar posicao antiga
                    movimento = jogo.getTabuleiro().movimentarPersonagem(oldL,oldC);
                    if (movimento.equals("Eliminado"))
                        jogadoresVivos--;
                    else if (movimento.equals("Movimento válido"))
                        //colocar j no final da fila
                }          
            }*/
            
            
            
            //turno das fakenews (while para passar por todas ---usar numF > 0)
            int numF = jogo.getFakeNews().size();
            while (numF > 0)
            {
                FakeNews f = jogo.getFakeNews().pollFirst();
                movimento = "Movimento inválido";
                while (movimento.equals("Movimento inválido")){
                    oldL = f.getLinha(); 
                    oldC = f.getColuna(); //guardar posicao antiga
                    movimento = jogo.getTabuleiro().movimentarPersonagem(oldL,oldC); //movimentar
                    if (movimento.equals("Movimento válido"))
                        //colocar f no final da fila
                    else if //duplicado
                        //colocar as duas no final da fila
                        
                    else if (movimento.equals("Eliminado")){
                        System.out.println(f.getLabel()+" foi eliminado!");
                        numF--;
                    }
                    
                }
                System.out.print(f.getLabel()+" andou da posição ["+(oldL+1)+"]["+(oldC+1)+"]");
                System.out.println(" para ["+(f.getLinha()+1)+"]["+(f.getColuna()+1)+"]");
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