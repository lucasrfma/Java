package universidade;

public enum Escolaridade {
    SEM_ENSINO(0),
    ENSINO_FUNDAMENTAL(1),
    ENSINO_MEDIO(2),
    ENSINO_SUPERIOR_INCOMPLETO(3),
    ENSINO_TECNICO(4),
    ENSINO_SUPERIOR(5),
    POS_GRAD(6),
    MESTRADO(7),
    DOUTORADO(8);

    private int nivel;

    private Escolaridade(int nivel)
    {
        this.nivel = nivel;
    }

    public int getNivel(){return nivel;}
}
