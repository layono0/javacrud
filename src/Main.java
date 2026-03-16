import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static ArrayList<Alunos> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuAlunos();

    }
    public static void menuInicial(){
        String menuText = """
                Escolha o menu desejado
                1- Turmas
                2- Alunos
                3- Sair""";
        System.out.println(menuText);
        String opcao = Ler.dados("Selecione sua opção: ");
        switch (opcao) {
            case "1":

                break;
            case "2":
                menuAlunos();
                break;
            case "3":

                break;
        }
    }
    public static void menuTurmas() {
        String menuText = """
                Menu insano do caba
                1- listar turmas
                2- criar turma
                3- excluir turmas
                4- atualizar turma
                """;
        System.out.println(menuText);
        String opcao = Ler.dados("Selecione sua opção: ");
        switch (opcao) {
            case "1":

                break;
            case "2":

                break;
            case "3":

                break;
            case "4":

                break;
            default:
                System.out.println("Opção Inválida, tente novamente!");

        }
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
                atualizarAlunos();
                break;
            default:
                System.out.println("Opção Inválida, tente novamente!");
                menuAlunos();
        }
    }

    private static void criarTurma() {

        String nome = Ler.dados("Nome do aluno: ").replaceAll("\\\\s", "");

        while (!isLetra(nome)) {
            System.out.print("o nome deve ser digitado com letras e sem espacos");
            nome = Ler.dados("Nome da turma: ");
        }
        String sigla = Ler.dados("Sigla da turma: ").replaceAll("\\\\s", "");


        String menuText = """
                Periodos disponiveis
                1- Matutino
                2- Vespertino
                3- Noturno
                """;

        String opcaoText = Ler.dados("Escolha o Periodo da turma (1,2,3): ");
        while (!isNumero(opcaoText)) {
            System.out.print("Escolha o periodo desejado pelo número: ");
            opcaoText = Ler.dados("Escolha o Periodo da turma (1,2,3)s: ");
        }
        int opcao = Integer.parseInt(opcaoText);
        try {
            var periodo = Periodo.numPeriodo(opcao);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


    private static void criarAluno() {
        int idadeNum = 0;
        String nome = Ler.dados("Nome do aluno: ").replaceAll("\\\\s", "");


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
        //
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

    private static void atualizarAlunos(){


        int contagem = 0;

        System.out.println("Alunos cadastrados no momento");
        for (Alunos aluno : listaAlunos) {
            contagem++;
            System.out.println(contagem + " - " + aluno.getNome());

        }

        String opcaoStr = Ler.dados("Digite o numero referente ao aluno que deseja Editar: ");
        while (!isNumero(opcaoStr)) {
            System.out.print("A opcao desejada DEVE ser um numero." + '\n');
            opcaoStr = Ler.dados("Numero referente ao aluno: ");

        }
        int opcao = diminuiUm(Integer.parseInt(opcaoStr));

        String menutxt = """
                (Escolha o atributo para modificar)
                1- Nome
                2- Idade
                3- Matricula
                
                """;
        System.out.println(menutxt);
        System.out.println(listaAlunos.get(opcao));

        String opcaoUpdate = Ler.dados("Escolha qual Propriedade deseja editar: ");

            switch (opcaoUpdate){
                case "1":
                    atualizarNome(opcao);
                    break;
                case "2":
                    atualizarIdade(opcao);
                    break;
                case "3":
                    atualizarMatricula(opcao);
                    break;
                default:
                    System.out.println("Opcao invalida! escolha apenas entre os números da lista");

            }



    }

    private  static  void atualizarNome(int opcao){
        String novoNome = Ler.dados("Escolha o novo nome do Aluno: ");

        while(!isLetra(novoNome)){
            System.out.println("Esse nome e invalido, use apenas letras e sem espacos.");
            novoNome = Ler.dados("Escolha o novo nome do Aluno: ");
        }

        String confirm = Ler.dados("O novo nome sera: " + novoNome + " Deseja prosseguir com esse novo nome? S/N").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opcao invalida! a escolha deve ser S ou N");
            confirm = Ler.dados("O novo nome sera: \" + novoNome + \" Deseja prosseguir com esse novo nome? S/N ");
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setNome(novoNome);
        }
        else{
            System.out.println("Cancelado, voltando ao menu inicial...");
            menuAlunos();
        }
        System.out.println(listaAlunos);


    }


    private  static  void atualizarIdade(int opcao){
        String novaIdadeStr = Ler.dados("Escolha a nova idade do Aluno: ");
        while (!isNumero(novaIdadeStr)) {
            System.out.print("A opcao desejada DEVE ser um numero." + '\n');
            novaIdadeStr = Ler.dados("Numero referente ao aluno: ");

        }
        int idade = Integer.parseInt(novaIdadeStr);
        String confirm = Ler.dados("A nova idade sera: " + idade + " Deseja prosseguir com essa nova idade? S/N").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opcao invalida! a escolha deve ser S ou N");
            confirm = Ler.dados("A nova idade sera: \" + idade + \" Deseja prosseguir com essa nova idade? S/N ");
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setIdade(idade);
        }
        else{
            System.out.println("Cancelado, voltando ao menu inicial...");
            menuAlunos();
        }
        System.out.println(listaAlunos);
    }


    private  static  void atualizarMatricula(int opcao){
        String novaMatricula = Ler.dados("Escolha a nova matricula do Aluno: ");
        while(!isLetra(novaMatricula)){
            System.out.println("essa matricula e invalida, use apenas letras e sem espacos.");
            novaMatricula = Ler.dados("Escolha a nova matricula do Aluno: ");
        }

        String confirm = Ler.dados("A nova matricula sera: " + novaMatricula + " Deseja prosseguir com essa nova matricula? S/N").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opcao invalida! a escolha deve ser S ou N");
            confirm = Ler.dados("A nova matricula sera: \" + novaMatricula + \" Deseja prosseguir com essa nova matricula? S/N ");
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setNome(novaMatricula);
        }
        else{
            System.out.println("Cancelado, voltando ao menu inicial...");
            menuAlunos();
        }
        System.out.println(listaAlunos);
    }

}