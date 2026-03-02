import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main(){
        List<Alunos> listaAlunos = new ArrayList<>();

        Alunos novoaluno = new Alunos("aluno1", 19, "edsw");
        listaAlunos.add(novoaluno);



        Scanner scanner = new Scanner(System.in);

        System.out.print("digite o nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("digite a idade do aluno: ");
        String idadeString = scanner.nextLine();
        int idade = Integer.parseInt(idadeString);

        System.out.print("digite a matricula do aluno: ");
        String matricula = scanner.nextLine();

        Alunos alunoscanner = new Alunos(nome, idade, matricula);

        listaAlunos.add(alunoscanner);
        System.out.println(listaAlunos);

    }
}
