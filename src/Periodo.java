public enum Periodo {
    MATUTINO,
    VESPERTINO,
    NOTURNO;

    private static final Periodo[] periodos = Periodo.values();


    public static Periodo numPeriodo(int index) {
        if (index >= 0 && index < periodos.length) {
            return periodos[index];
        } else {

            return null;
        }


    }


}
