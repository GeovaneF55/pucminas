/* Testa a classe Fila com duas Pilhas e a classe Pilha com duas Filas
* @author Geovane Fonseca de Sousa Santos
* @version 1 08/2016
*/

class Principal {
	public static void main(String[] args) throws Exception{
		System.out.println("\n==== FILA COM DUAS PILHAS ====");
		FilaComPilhas fp = new FilaComPilhas();
		int x1, x2, x3;

		fp.inserir(5);
		fp.inserir(7);
		fp.inserir(8);
		fp.inserir(9);

		System.out.print("Apos insercoes(5, 7, 8, 9): ");
      		fp.mostrar();

     		x1 = fp.remover();
      		x2 = fp.remover();

		System.out.print("Apos remocoes (" + x1 + ", " + x2 + "):");
      		fp.mostrar();

		fp.inserir(3);
		fp.inserir(4);

		System.out.print("Apos insercoes(3, 4): ");
      		fp.mostrar();

		x1 = fp.remover();
      		x2 = fp.remover();
      		x3 = fp.remover();

		System.out.print("Apos remocoes (" + x1 + ", " + x2 + ", " + x3 + "):");
		fp.mostrar();

		fp.inserir(4);
		fp.inserir(5);

		System.out.print("Apos insercoes(4, 5): ");
		fp.mostrar();

		x1 = fp.remover();
      		x2 = fp.remover();

		System.out.print("Apos remocoes (" + x1 + ", " + x2 + "):");
      		fp.mostrar();

      		fp.inserir(6);
      		fp.inserir(7);

      		System.out.print("Apos insercoes(6, 7): ");
      		fp.mostrar();

      		x1 = fp.remover();

      		System.out.print("Apos remocao (" + x1 + "): ");
      		fp.mostrar();

		System.out.println("\n==== PILHA COM DUAS FILAS ====");
		PilhaComFilas pf = new PilhaComFilas();
		int y1, y2, y3;

		pf.inserir(0);
		pf.inserir(1);
		pf.inserir(2);
		pf.inserir(3);
		pf.inserir(4);

		System.out.print("Apos insercoes: (0, 1, 2, 3, 4): ");

		pf.mostrar();

		x1 = pf.remover();
		x2 = pf.remover();
		x3 = pf.remover();

		System.out.print("Apos as remocoes (" + x1 + "," + x2 + "," + x3 + "): ");
		pf.mostrar();

		pf.inserir(5);
		pf.inserir(8);
		pf.inserir(9);

		System.out.print("Apos insercoes (5, 8, 9): ");
                pf.mostrar();

		x1 = pf.remover();
		x2 = pf.remover();

		System.out.print("Apos as remocoes (" + x1 + "," + x2 + "): ");
                pf.mostrar();
	}
}
