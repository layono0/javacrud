import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static ArrayList<Alunos> listaAlunos = new ArrayList<>();
    private static ArrayList<Turma> listaTurmas = new ArrayList<>();

    public static void main(String[] args) {
        menuInicial();
    }

    public static void menuInicial(){
        boolean continuar = true;
        while (continuar) {
            String menuText = """
                    Escolha o menu desejado
                    1- Turmas
                    2- Alunos
                    3- Sair""";
            System.out.println(menuText);
            String opcao = Ler.dados("Selecione sua opção: ");
            switch (opcao) {
                case "1":
                    menuTurmas();
                    break;
                case "2":
                    menuAlunos();
                    break;
                case "3":
                    System.out.println("Saindo do programa...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção Inválida, tente novamente!");
            }
        }
    }
    public static void menuTurmas() {
        boolean continuar = true;
        while (continuar) {
            String menuText = """
                    Menu de Turmas
                    1- listar turmas
                    2- criar turma
                    3- excluir turmas
                    4- atualizar turma
                    5- Voltar ao menu anterior
                    """;
            System.out.println(menuText);
            String opcao = Ler.dados("Selecione sua opção: ");
            switch (opcao) {
                case "1":
                    listarTurmas();
                    break;
                case "2":
                    criarTurma();
                    break;
                case "3":
                    excluirTurma();
                    break;
                case "4":
                    atualizarTurma();
                    break;
                case "5":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opção Inválida, tente novamente!");
            }
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
        String nome = Ler.dados("Nome da turma: ").replaceAll("\\s", "");

        while (!isLetra(nome)) {
            System.out.println("O nome deve ser digitado com letras e sem espaços");
            nome = Ler.dados("Nome da turma: ").replaceAll("\\s", "");
        }
        
        String sigla = Ler.dados("Sigla da turma: ").replaceAll("\\s", "");

        String menuText = """
                Períodos disponíveis
                1- Matutino
                2- Vespertino
                3- Noturno
                """;
        System.out.println(menuText);

        String opcaoText = Ler.dados("Escolha o Período da turma (1, 2 ou 3): ");
        while (!isNumero(opcaoText) || Integer.parseInt(opcaoText) < 1 || Integer.parseInt(opcaoText) > 3) {
            System.out.println("Escolha o período desejado pelo número (1, 2 ou 3): ");
            opcaoText = Ler.dados("Escolha o Período da turma: ");
        }
        
        int opcao = Integer.parseInt(opcaoText) - 1;
        Periodo periodo = Periodo.numPeriodo(opcao);

        String confirmText = Ler.dados("Deseja confirmar a criação da turma? S/N: ").toUpperCase();
        while (!opcaoisValida(confirmText)) {
            System.out.println("Opção inválida! Digite S ou N");
            confirmText = Ler.dados("Deseja confirmar a criação da turma? S/N: ").toUpperCase();
        }

        if (confirmText.equals("S")) {
            Turma novaTurma = new Turma(nome, sigla, periodo, true);
            listaTurmas.add(novaTurma);
            System.out.println("Turma criada com sucesso!");
            System.out.println(listaTurmas);
        } else {
            System.out.println("Criação cancelada.");
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
            listaAlunos.get(opcao).setMatricula(novaMatricula);
        }
        else{
            System.out.println("Cancelado, voltando ao menu inicial...");
            menuAlunos();
        }
        System.out.println(listaAlunos);
    }

    private static void listarTurmas() {
        if (listaTurmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada!");
        } else {
            System.out.println("\n=== TURMAS CADASTRADAS ===");
            for (int i = 0; i < listaTurmas.size(); i++) {
                System.out.println((i + 1) + " - " + listaTurmas.get(i));
            }
        }
    }

    private static void excluirTurma() {
        if (listaTurmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada!");
            return;
        }
        
        int contagem = 0;
        System.out.println("Turmas cadastradas no momento");
        for (Turma turma : listaTurmas) {
            contagem++;
            System.out.println(contagem + " - " + turma.getCurso() + " (" + turma.getSigla() + ")");
        }

        String opcaoStr = Ler.dados("Digite o numero referente à turma que deseja excluir: ");
        while (!isNumero(opcaoStr)) {
            System.out.print("A opção desejada DEVE ser um número.");
            opcaoStr = Ler.dados("Número referente à turma: ");
        }

        int opcao = diminuiUm(Integer.parseInt(opcaoStr));
        
        if (opcao < 0 || opcao >= listaTurmas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        String confirm = Ler.dados("Você deseja MESMO excluir a turma " + listaTurmas.get(opcao).getCurso() + "? Confirme com S/N: ").toUpperCase();
        while (!opcaoisValida(confirm)) {
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Você deseja MESMO excluir a turma? Confirme com S/N: ").toUpperCase();
        }

        if (confirm.equals("S")) {
            listaTurmas.remove(opcao);
            System.out.println("Turma removida com sucesso!");
        } else {
            System.out.println("A turma não foi removida...");
        }
    }

    private static void atualizarTurma() {
        if (listaTurmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada!");
            return;
        }

        int contagem = 0;
        System.out.println("Turmas cadastradas no momento");
        for (Turma turma : listaTurmas) {
            contagem++;
            System.out.println(contagem + " - " + turma.getCurso() + " (" + turma.getSigla() + ")");
        }

        String opcaoStr = Ler.dados("Digite o número referente à turma que deseja editar: ");
        while (!isNumero(opcaoStr)) {
            System.out.print("A opção desejada DEVE ser um número.\n");
            opcaoStr = Ler.dados("Número referente à turma: ");
        }

        int opcao = diminuiUm(Integer.parseInt(opcaoStr));
        
        if (opcao < 0 || opcao >= listaTurmas.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        String menuTxt = """
                (Escolha o atributo para modificar)
                1- Nome da Turma
                2- Sigla
                3- Período
                4- Status (Ativo/Inativo)
                """;
        System.out.println(menuTxt);
        System.out.println("Turma selecionada: " + listaTurmas.get(opcao));

        String opcaoUpdate = Ler.dados("Escolha qual propriedade deseja editar: ");

        switch (opcaoUpdate) {
            case "1":
                atualizarNomeTurma(opcao);
                break;
            case "2":
                atualizarSiglaTurma(opcao);
                break;
            case "3":
                atualizarPeriodoTurma(opcao);
                break;
            case "4":
                atualizarStatusTurma(opcao);
                break;
            default:
                System.out.println("Opção inválida! Escolha apenas entre os números da lista");
        }
    }

    private static void atualizarNomeTurma(int opcao) {
        String novoNome = Ler.dados("Escolha o novo nome da turma: ").replaceAll("\\s", "");

        while (!isLetra(novoNome)) {
            System.out.println("Esse nome é inválido. Use apenas letras e sem espaços.");
            novoNome = Ler.dados("Escolha o novo nome da turma: ").replaceAll("\\s", "");
        }

        String confirm = Ler.dados("O novo nome será: " + novoNome + ". Deseja prosseguir? S/N: ").toUpperCase();
        while (!opcaoisValida(confirm)) {
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }

        if (confirm.equals("S")) {
            listaTurmas.get(opcao).setCurso(novoNome);
            System.out.println("Nome atualizado com sucesso!");
        } else {
            System.out.println("Cancelado...");
        }
        System.out.println(listaTurmas.get(opcao));
    }

    private static void atualizarSiglaTurma(int opcao) {
        String novaSigla = Ler.dados("Escolha a nova sigla da turma: ").replaceAll("\\s", "");

        while (!isLetra(novaSigla)) {
            System.out.println("Essa sigla é inválida. Use apenas letras e sem espaços.");
            novaSigla = Ler.dados("Escolha a nova sigla da turma: ").replaceAll("\\s", "");
        }

        String confirm = Ler.dados("A nova sigla será: " + novaSigla + ". Deseja prosseguir? S/N: ").toUpperCase();
        while (!opcaoisValida(confirm)) {
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }

        if (confirm.equals("S")) {
            listaTurmas.get(opcao).setSigla(novaSigla);
            System.out.println("Sigla atualizada com sucesso!");
        } else {
            System.out.println("Cancelado...");
        }
        System.out.println(listaTurmas.get(opcao));
    }

    private static void atualizarPeriodoTurma(int opcao) {
        String menuText = """
                Períodos disponíveis
                1- Matutino
                2- Vespertino
                3- Noturno
                """;
        System.out.println(menuText);

        String opcaoText = Ler.dados("Escolha o novo período (1, 2 ou 3): ");
        while (!isNumero(opcaoText) || Integer.parseInt(opcaoText) < 1 || Integer.parseInt(opcaoText) > 3) {
            System.out.println("Opção inválida! Escolha entre 1, 2 ou 3");
            opcaoText = Ler.dados("Escolha o novo período: ");
        }

        int novoPeriodo = Integer.parseInt(opcaoText) - 1;
        Periodo periodo = Periodo.numPeriodo(novoPeriodo);

        String confirm = Ler.dados("O novo período será: " + periodo + ". Deseja prosseguir? S/N: ").toUpperCase();
        while (!opcaoisValida(confirm)) {
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }

        if (confirm.equals("S")) {
            listaTurmas.get(opcao).setPeriodo(periodo);
            System.out.println("Período atualizado com sucesso!");
        } else {
            System.out.println("Cancelado...");
        }
        System.out.println(listaTurmas.get(opcao));
    }

    private static void atualizarStatusTurma(int opcao) {
        Turma turmaAtual = listaTurmas.get(opcao);
        boolean novoStatus = !turmaAtual.isAtivo();
        String statusText = novoStatus ? "ATIVA" : "INATIVA";

        String confirm = Ler.dados("A turma será marcada como " + statusText + ". Deseja prosseguir? S/N: ").toUpperCase();
        while (!opcaoisValida(confirm)) {
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }

        if (confirm.equals("S")) {
            turmaAtual.setAtivo(novoStatus);
            System.out.println("Status atualizado para: " + statusText);
        } else {
            System.out.println("Cancelado...");
        }
        System.out.println(listaTurmas.get(opcao));
    }

}