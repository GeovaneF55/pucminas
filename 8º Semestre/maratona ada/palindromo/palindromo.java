import java.util.Scanner;

public class palindromo {

	public static boolean ehPalindromo (String frase) {
		boolean pali = true;

		frase = frase.replaceAll("\\s+","");
		frase = frase.replaceAll(",","");
		frase = frase.toUpperCase();

		for(int i=0; i<frase.length(); i++) {
			if(frase.charAt(i) != frase.charAt(frase.length() - 1 - i)){
				pali = false;
			}
		}
		return pali;
	} 

	public static void main (String arg []){
		Scanner sc1 = new Scanner(System.in);
		String frase = "";
		while (sc1.hasNext()){
			frase = sc1.nextLine();
			if(!frase.contains("FIM")){		
				if(ehPalindromo(frase)){
					System.out.println("SIM");
				} else {
					System.out.println("NAO");
				}
			}
		}
	}
}
