import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        String nome = Ler.dados("Nome do aluno: ");
        
        while (!isNomeValido(nome)) {
            System.out.println("Nome inválido! O nome não pode conter números, caracteres especiais, estar em branco ou ser nulo.");
            nome = Ler.dados("Nome do aluno: ");
        }
        
        LocalDate dataNascimento = null;
        boolean dataValida = false;
        while (!dataValida) {
            String dataStr = Ler.dados("Data de nascimento (DD/MM/YYYY): ");
            dataNascimento = parseData(dataStr);
            
            if (dataNascimento == null) {
                System.out.println("Data inválida! Digite no formato DD/MM/YYYY.");
                continue;
            }
            
            int idade = calcularIdade(dataNascimento);
            
            if (idade < 14) {
                System.out.println("O aluno deve ter pelo menos 14 anos. Cadastro não permitido.");
                return;
            }
            
            if (idade > 130) {
                System.out.println("A idade máxima permitida é 130 anos. Cadastro não permitido.");
                return;
            }
            
            dataValida = true;
        }
        
        String matricula = Ler.dados("Matrícula do aluno: ");
        while (matricula == null || matricula.isBlank()) {
            System.out.println("Matrícula não pode estar em branco!");
            matricula = Ler.dados("Matrícula do aluno: ");
        }
        
        if (listaTurmas.isEmpty()) {
            System.out.println("Nenhuma turma cadastrada! Crie uma turma antes de adicionar alunos.");
            return;
        }
        
        Turma turmaSelected = selecionarTurma();
        if (turmaSelected == null) {
            System.out.println("Turma inválida ou inativa. Cadastro cancelado.");
            return;
        }
        
        String confirmText = Ler.dados("Deseja confirmar a criação do aluno? S/N: ").toUpperCase();
        while (!opcaoisValida(confirmText)) {
            System.out.println("Opção inválida! Digite S ou N");
            confirmText = Ler.dados("Deseja confirmar a criação do aluno? S/N: ").toUpperCase();
        }
        
        if (confirmText.equals("S")) {
            Alunos alunoNovo = new Alunos(nome, dataNascimento, matricula, turmaSelected);
            listaAlunos.add(alunoNovo);
            System.out.println("Aluno criado com sucesso!");
            System.out.println(alunoNovo);
        } else {
            System.out.println("Criação cancelada.");
        }
    }

    private static Turma selecionarTurma() {
        int contagem = 0;
        System.out.println("\n=== TURMAS ATIVAS DISPONÍVEIS ===");
        ArrayList<Turma> turmasAtivas = new ArrayList<>();
        
        for (Turma turma : listaTurmas) {
            if (turma.isAtivo()) {
                turmasAtivas.add(turma);
                contagem++;
                System.out.println(contagem + " - " + turma.getCurso() + " (" + turma.getSigla() + ") - Período: " + turma.getPeriodo());
            }
        }
        
        if (turmasAtivas.isEmpty()) {
            System.out.println("Nenhuma turma ativa disponível!");
            return null;
        }
        
        String opcaoStr = Ler.dados("Selecione a turma do aluno: ");
        while (!isNumero(opcaoStr)) {
            System.out.print("A opção desejada DEVE ser um número.");
            opcaoStr = Ler.dados("Número referente à turma: ");
        }
        
        int opcao = Integer.parseInt(opcaoStr) - 1;
        
        if (opcao < 0 || opcao >= turmasAtivas.size()) {
            System.out.println("Opção inválida!");
            return null;
        }
        
        return turmasAtivas.get(opcao);
    }

    private static boolean isNomeValido(String nome) {
        if (nome == null || nome.isBlank()) {
            return false;
        }
        
        if (nome.matches(".*\\d.*")) {
            return false;
        }
        
        if (!nome.matches("[a-zA-Zà-üÀ-Ü\\s]+")) {
            return false;
        }
        
        return true;
    }

    private static LocalDate parseData(String dataStr) {
        if (dataStr == null || dataStr.isBlank()) {
            return null;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(dataStr, formatter);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        return java.time.Period.between(dataNascimento, LocalDate.now()).getYears();
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
        if (listaAlunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
            return;
        }

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
        
        int opcao = Integer.parseInt(opcaoStr) - 1;
        
        if (opcao < 0 || opcao >= listaAlunos.size()) {
            System.out.println("Opção inválida!");
            return;
        }

        String menutxt = """
                (Escolha o atributo para modificar)
                1- Nome
                2- Data de Nascimento
                3- Matrícula
                4- Turma
                
                """;
        System.out.println(menutxt);
        System.out.println(listaAlunos.get(opcao));

        String opcaoUpdate = Ler.dados("Escolha qual Propriedade deseja editar: ");

        switch (opcaoUpdate){
            case "1":
                atualizarNome(opcao);
                break;
            case "2":
                atualizarDataNascimento(opcao);
                break;
            case "3":
                atualizarMatricula(opcao);
                break;
            case "4":
                atualizarTurmaAluno(opcao);
                break;
            default:
                System.out.println("Opcao invalida! escolha apenas entre os números da lista");
        }
    }

    private static void atualizarNome(int opcao){
        String novoNome = Ler.dados("Escolha o novo nome do Aluno: ");

        while(!isNomeValido(novoNome)){
            System.out.println("Esse nome é inválido. Não pode conter números, caracteres especiais ou estar em branco.");
            novoNome = Ler.dados("Escolha o novo nome do Aluno: ");
        }

        String confirm = Ler.dados("O novo nome será: " + novoNome + " Deseja prosseguir? S/N: ").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setNome(novoNome);
            System.out.println("Nome atualizado com sucesso!");
        }
        else{
            System.out.println("Cancelado...");
        }
        System.out.println(listaAlunos.get(opcao));
    }

    private static void atualizarDataNascimento(int opcao){
        LocalDate novaData = null;
        boolean dataValida = false;
        
        while (!dataValida) {
            String dataStr = Ler.dados("Digite a nova data de nascimento (DD/MM/YYYY): ");
            novaData = parseData(dataStr);
            
            if (novaData == null) {
                System.out.println("Data inválida! Digite no formato DD/MM/YYYY.");
                continue;
            }
            
            int idade = calcularIdade(novaData);
            
            if (idade < 14) {
                System.out.println("O aluno deve ter pelo menos 14 anos. Operação cancelada.");
                return;
            }
            
            if (idade > 130) {
                System.out.println("A idade máxima permitida é 130 anos. Operação cancelada.");
                return;
            }
            
            dataValida = true;
        }

        String confirm = Ler.dados("A nova data será: " + novaData + " (idade: " + calcularIdade(novaData) + " anos). Deseja prosseguir? S/N: ").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setDataNascimento(novaData);
            System.out.println("Data de nascimento atualizada com sucesso!");
        }
        else{
            System.out.println("Cancelado...");
        }
        System.out.println(listaAlunos.get(opcao));
    }


    private static void atualizarMatricula(int opcao){
        String novaMatricula = Ler.dados("Escolha a nova matrícula do Aluno: ");
        while(novaMatricula == null || novaMatricula.isBlank()){
            System.out.println("A matrícula não pode estar em branco!");
            novaMatricula = Ler.dados("Escolha a nova matrícula do Aluno: ");
        }

        String confirm = Ler.dados("A nova matrícula será: " + novaMatricula + " Deseja prosseguir? S/N: ").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setMatricula(novaMatricula);
            System.out.println("Matrícula atualizada com sucesso!");
        }
        else{
            System.out.println("Cancelado...");
        }
        System.out.println(listaAlunos.get(opcao));
    }

    private static void atualizarTurmaAluno(int opcao){
        Turma novaTurma = selecionarTurma();
        
        if (novaTurma == null) {
            System.out.println("Turma inválida ou inativa. Operação cancelada.");
            return;
        }

        String confirm = Ler.dados("A nova turma será: " + novaTurma.getCurso() + ". Deseja prosseguir? S/N: ").toUpperCase();
        while(!opcaoisValida(confirm)){
            System.out.println("Opção inválida! A escolha deve ser S ou N");
            confirm = Ler.dados("Deseja prosseguir? S/N: ").toUpperCase();
        }
        if(confirm.equals("S")){
            listaAlunos.get(opcao).setTurma(novaTurma);
            System.out.println("Turma atualizada com sucesso!");
        }
        else{
            System.out.println("Cancelado...");
        }
        System.out.println(listaAlunos.get(opcao));
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