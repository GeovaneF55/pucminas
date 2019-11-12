import java.util.Scanner;

public class agm{

	public static void preencheGrafo(int n_vertices, int[][] matriz, Scanner sc1) {
		int aresta = 0;

		for(int i = 0;i < n_vertices; i++){
			for(int j = i + 1;j < n_vertices; j++){
				aresta = sc1.nextInt();       
				if(aresta != -1){
					matriz[j][i] = aresta;
					matriz[i][j] = aresta;
				}else{
					matriz[j][i] = 0;
					matriz[i][j] = 0;
				}
			}
		}
	}

	public static void preencheNodulos(int n_vertices, int[][] matriz, int[] atual, int[] peso) {
		int total = 0;
		
		for(int i = 0; i < n_vertices; i++){
			atual[i] = 0;
			peso[i] = Integer.MAX_VALUE;
		}
		peso[0] = 0;
		for(int h = n_vertices; h > 0; h--){
			int nodulo = -1;
			for(int i = 0; i < n_vertices; i++){
				if(nodulo == -1||peso[i] < peso[nodulo]){
					nodulo = i;
				}
				atual[nodulo] = 1;
			}

			if(peso[nodulo] == Integer.MAX_VALUE){
				total = Integer.MAX_VALUE;
				break;
			}

			total += peso[nodulo];
			for(int i = 0; i < n_vertices;i++){
				if(peso[i] > matriz[nodulo][i]){
					peso[i] = matriz[nodulo][i];
				}
			}
		}
	}

	public static void main(String[]args){
		Scanner sc1 = new Scanner(System.in);
		int n_vertices = sc1.nextInt();
		int atual[] = new int[n_vertices];
		int peso[] = new int[n_vertices];
		int matriz[][] = new int [n_vertices][n_vertices];
		while(n_vertices != 0){
			preencheGrafo(n_vertices, matriz, sc1);
			
			preencheNodulos(n_vertices, matriz, atual, peso);
			
			int pesoFinal = 0;
			for(int i = 0;i < n_vertices;i++){
				pesoFinal += peso[i];
			}
			System.out.println(pesoFinal+1);

			n_vertices = sc1.nextInt();
		}
	}
}
