import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static ArrayList<Alunos> listaAlunos = new ArrayList<>();

    public static void main(String[] args) {
        menuAlunos();

    }

    public static void menuAlunos() {
        String menuText = """
                Menu insano do caba
                1- listar alunos
                2- criar alunos
                3- excluir alunos
                4- atualizar alunos
                """;
        System.out.println(menuText);
        String opcao = Ler.dados("Selecione sua opção: ");
        switch (opcao) {
            case "1":
                System.out.println(listaAlunos);
                break;
            case "2":
                criarAluno();
                break;
            case "3":
                excluirObj();
                break;
            case "4":
                System.out.println("opcao 4");
                break;
            default:
                System.out.println("Opção Inválida, tente novamente!");
                menuAlunos();
        }
    }

    private static void criarAluno() {
        int idadeNum = 0;
        String nome = Ler.dados("Nome do aluno: ").replaceAll("\\\\s", "");
        ;
        while (!isLetra(nome)) {
            System.out.print("o nome deve ser digitado com letras e sem espacos");
            nome = Ler.dados("Nome do aluno: ");
        }
        String idade = Ler.dados("Idade do aluno");
        while (!isNumero(idade)) {
            System.out.print("a idade deve ser um número: ");
            idade = Ler.dados("Idade do aluno: ");


        }
        idadeNum = Integer.parseInt(idade);
        String matricula = Ler.dados("Matricula do aluno: ").replaceAll("\\\\s", "");

        Alunos alunoNovo = new Alunos(nome, idadeNum, matricula);
        listaAlunos.add(alunoNovo);
        System.out.println(listaAlunos);
        menuAlunos();

    }

    private static boolean isNumero(String numero) {
//
//            if(numero == null && numero.isBlank()){
//                return  false;
//            }

        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }


    }

    private static boolean isLetra(String str) {
        if (!Pattern.matches("[a-zA-Z]+", str)) {
            return false;
        } else {
            return true;
        }


    }
    private static boolean opcaoisValida(String str){

        if(str.equalsIgnoreCase("S") || str.equalsIgnoreCase("N")){
            return true;
        }
        else{
            return false;
        }
    }
    private static int diminuiUm(int indice){
        return indice - 1;
    }

    private static void excluirObj() {
        int contagem = 0;
        System.out.println("Alunos cadastrados no momento");
        for (Alunos aluno : listaAlunos) {
            contagem++;
            System.out.println(contagem + " - " + aluno.getNome());

        }
        String opcaoStr = Ler.dados("Digite o numero referente ao aluno que deseja excluir: ");
        while (!isNumero(opcaoStr)) {
            System.out.print("A opcao desejada DEVE ser um numero.");
            opcaoStr = Ler.dados("Numero referente ao aluno: ");

        }
        int opcao = diminuiUm(Integer.parseInt(opcaoStr));
        String confirm = Ler.dados("Voce deseja MESMO excluir o aluno? confirme com S/N: ").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opcao invalida! a escolha deve ser S ou N");
            confirm = Ler.dados("Voce deseja MESMO excluir o aluno? confirme com S/N: ");
        }
        if(confirm.equals("S")){
            listaAlunos.remove(opcao);
        }
        else{
            System.out.println("O usuario nao foi removido...");
        }


        System.out.println(listaAlunos);

    }

    private static void atualizarObj(){
        
    }

}