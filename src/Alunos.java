import java.time.Period;

public class Alunos {
    private String nome;
    private int idade;
    private String matricula;
    private Turma turma;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Alunos(String nome, int idade, String matricula) {
        this.nome = nome;
        this.idade = idade;
        this.matricula = matricula;
        this.turma = turma;
    }

    @Override
    public String toString(){
        return "aluno: " + nome + '\n'+ "idade: " + idade + '\n' + "matricula: " + matricula + '\n' + "turma: " + turma;

    }

}

