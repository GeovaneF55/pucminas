import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class ordenacao {

	public static Comparator<String> StringComparator 
                          = new Comparator<String>() {

	    public int compare(String s1, String s2) {
		return s1.compareTo(s2);
	    }

	};

	public static void main (String arg []) {
		Scanner sc1 = new Scanner(System.in);
		ArrayList<String> frases = new ArrayList<String>();
		String frase = "";

		while (sc1.hasNext()){
			frase = sc1.nextLine();
			if(!frase.contains("FIM")){
				frases.add(frase);
			}
		}

		frases.sort(StringComparator);

		for(int i=0; i<frases.size(); i++) {
			System.out.println(frases.get(i));	
		}
	}
}
