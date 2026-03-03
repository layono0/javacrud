import java.util.Scanner;

public class Ler {
   public static String dados(String mensagem){
       Scanner scanner = new Scanner(System.in);
       System.out.println(mensagem);
       return scanner.nextLine();
   }

}
