import java.time.LocalDate;
import java.time.Period;

public class Alunos {
    private String nome;
    private LocalDate dataNascimento;
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Alunos(String nome, LocalDate dataNascimento, String matricula, Turma turma) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.matricula = matricula;
        this.turma = turma;
    }

    @Override
    public String toString(){
        return "aluno: " + nome + '\n'+ "data nascimento: " + dataNascimento + '\n' + "idade: " + getIdade() + " anos" + '\n' + "matricula: " + matricula + '\n' + "turma: " + turma;

    }

}

