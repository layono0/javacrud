import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static ArrayList<Alunos> listaAlunos = new ArrayList<>();
        public static void main(String[] args){
            menuAlunos();

        }

        public static void menuAlunos(){
            String menuText = """
                Menu insano do caba
                1- listar alunos
                2- criar alunos
                3- excluir alunos
                4- atualizar alunos
                """;
            System.out.println(menuText);
            String opcao = Ler.dados("Selecione sua opção: ");
            switch (opcao){
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
        private static void criarAluno(){
            int idadeNum = 0;
            String nome = Ler.dados("Nome do aluno: ").replaceAll("\\\\s", "");;
            while(!isLetra(nome)){
                System.out.print("o nome deve ser digitado com letras e sem espacos");
                nome = Ler.dados("Nome do aluno: ");
            }
            String idade = Ler.dados("Idade do aluno");
            while(!isNumero(idade)){
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

        private static boolean isNumero(String numero){
//
//            if(numero == null && numero.isBlank()){
//                return  false;
//            }

            try {
                Integer.parseInt(numero);
                return true;
            }
            catch (NumberFormatException e){
                return  false;
            }


        }
        private static boolean isLetra(String str){
            if(!Pattern.matches("[a-zA-Z]+", str)){
                return false;
            }
            else{
                return true;
            }


        }
        private static void excluirObj(){
            for(Object obj : listaAlunos){
                System.out.println(obj);
            }
        }





    }

