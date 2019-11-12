import java.util.Scanner;

public class euleriano{
	public static void preencheGrafo(int n_vertices, int[][] matriz, Scanner sc1) {
		int aresta = 0;
		for(int i = 0; i < n_vertices; i++){
			for(int j = i + 1; j < n_vertices; j++){
				aresta = sc1.nextInt();
				if(aresta != -1){
					matriz[i][j] = aresta;
					matriz[j][i] = aresta;
				}else{
					matriz[i][j] = 0;
					matriz[i][j] = 0;
				}
			}
		}
		int h = 0;
		for(int j = 0; j < n_vertices; j++){
			if(matriz[h][j] > 0 || matriz[j][h] > 0){
				matriz[h][j] = 0;
				matriz[j][h] = 0;
				h = j;
				j = -1;
			}
		}
	}
	public static boolean ehEuleriano(int n_vertices, int[][] matriz) {
		boolean euleriano = true;
		for(int i = 0; i < n_vertices; i++){
			for(int j = i + 1; j < n_vertices; j++){
				if(matriz[i][j] > 0){
					euleriano = false;
				}
			}
		}
		return euleriano;
	}
	
	public static void main(String[]args){
		Scanner sc1 = new Scanner(System.in);
		int n_vertices = sc1.nextInt();
		int matriz[][] = new int [n_vertices][n_vertices];
		
		while(n_vertices != 0){
			preencheGrafo(n_vertices, matriz, sc1);
			
			if(ehEuleriano(n_vertices, matriz)){
				System.out.println("SIM");
			}else{
				System.out.println("NAO");
			}

			n_vertices = sc1.nextInt();
		}

	}
}
