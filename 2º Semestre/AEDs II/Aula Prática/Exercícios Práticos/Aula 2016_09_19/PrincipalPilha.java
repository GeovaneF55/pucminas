/**
 * Pilha dinamica
 * @author Max do Val Machado
 * @version 2 01/2015
 */
public class PrincipalPilha {
	public static void main(String[] args) {
		try {
			System.out.println(" ==== PILHA DINAMICA ====");
			Pilha pilha = new Pilha();
			int x1, x2, x3;

			pilha.inserir(0);
			pilha.inserir(1);
			pilha.inserir(2);
			pilha.inserir(3);
			pilha.inserir(4);
			pilha.inserir(5);

			System.out.println("\nApos insercoes: ");
			System.out.print("Mostrar Ex.1: ");
			pilha.mostrarEx1();
			System.out.print("Mostrar Ex.2: ");
			pilha.mostrarEx2();
			System.out.print("Mostrar Ex.3: ");
			pilha.mostrarEx3();
			System.out.print("Mostrar Ex.4: ");
			pilha.mostrarEx4();

			x1 = pilha.remover();
			x2 = pilha.remover();
			x3 = pilha.remover();

			System.out.println("\nApos as remocoes (" + x1 + "," + x2 + "," + x3 + "): ");
			System.out.print("Mostrar Ex.1: ");
			pilha.mostrarEx1();
			System.out.print("Mostrar Ex.2: ");
			pilha.mostrarEx2();
			System.out.print("Mostrar Ex.3: ");
			pilha.mostrarEx3();
			System.out.print("Mostrar Ex.4: ");
			pilha.mostrarEx4();

			System.out.println("Somar Ex.1: " + pilha.somarEx1());
			System.out.println("Somar Ex.2: " + pilha.somarEx2());
			System.out.println("Maior Ex.3: " + pilha.maiorEx3());
			System.out.println("Maior Ex.4: " + pilha.maiorEx4());

		}
		catch(Exception erro) {
			System.out.println(erro.getMessage());
		}
	}
}
