import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static void menuMostrar(){
        String menuText = """
                Menu insano do caba
                1- listar alunos
                2- criar alunos
                3- excluir alunos
                4- atualizar alunos
                """;
        System.out.println(menuText);
    }

    static void main(){
        List<Alunos> listaAlunos = new ArrayList<>();

        Alunos novoaluno = new Alunos("aluno1", 19, "edsw");
        listaAlunos.add(novoaluno);


        menuMostrar();

        Scanner scanner = new Scanner(System.in);

//        System.out.print("digite o nome do aluno: ");
//        String nome = scanner.nextLine();
//
//        System.out.print("digite a idade do aluno: ");
//        String idadeString = scanner.nextLine();
//        int idade = Integer.parseInt(idadeString);
//
//        System.out.print("digite a matricula do aluno: ");
//        String matricula = scanner.nextLine();
//
//        Alunos alunoscanner = new Alunos(nome, idade, matricula);
//
//        listaAlunos.add(alunoscanner);
//        System.out.println(listaAlunos);
        System.out.print("Escolha a opção desejada: " + listaAlunos.get(0).getMatricula());
        var opcaoTexto = scanner.nextLine();

        int opcao = Integer.parseInt(opcaoTexto);

        switch(opcao){
            case 1:
                System.out.println("opcao 1 selecionada");
                System.out.println(listaAlunos);
                break;
            case 2:
                System.out.println("opcao 2 selecionada");
                break;
            case 3:
                System.out.println("opcao 3 selecionada");
                break;
            case 4:
                System.out.println("opcao 4 selecionada");
                break;

        }



    }
}
