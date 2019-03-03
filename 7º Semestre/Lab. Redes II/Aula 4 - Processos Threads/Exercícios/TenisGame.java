import java.util.Random;

public class TenisGame extends Thread {
    private int time;
    private String[] jogadores;

    public static int lastTeamPlay = 1;

    public TenisGame(int time, String jogador1, String jogador2) {
        this.time = time;
        this.jogadores = new String[2];
        this.jogadores[0] = jogador1;
        this.jogadores[1] = jogador2;
    }

    public synchronized void jogada(int i){
        lastTeamPlay = time;
        System.out.print("\n" + "Jogada " + i + ": " + this.jogadores[randomTeamPlayer()] +
        " do time " + this.time + " REBATE");
    }

    public void run() {
        try {
            for(int i = 0; i < 30; i++) {
                if (lastTeamPlay != time){
                    jogada(i);
                }
                Thread.sleep(100);
            }
        }
        catch (InterruptedException e) {
        }
        return;
    }

    public int randomTeamPlayer(){
        Random gerador = new Random();
        return gerador.nextInt(2);
    }

    public static void main(String [] args) {
        Thread t1 = new TenisGame(1, "Geovane", "Fonseca");
        Thread t2 = new TenisGame(2, "Sousa", "Santos");
        t1.start();
        t2.start();
    }
}