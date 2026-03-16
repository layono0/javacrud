public class Turma {
    private String curso;
    private String sigla;
    private Periodo periodo;
    private boolean ativo;




    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }



    public Turma(String curso, String sigla, Periodo periodo, boolean ativo) {
        this.curso = curso;
        this.sigla = sigla;
        this.periodo = periodo;
        this.ativo = ativo;
    }
    @Override
    public String toString() {
        return "Turma{" +
                "curso='" + curso + '\'' +
                ", sigla='" + sigla + '\'' +
                ", periodo=" + periodo +
                ", ativo=" + ativo +
                '}';
    }
}

