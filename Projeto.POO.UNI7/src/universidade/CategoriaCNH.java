package universidade;

public enum CategoriaCNH {
    A(2),
    B(4),
    C(6),
    D(8),
    E(10),
    AB(5),
    AC(7),
    AD(9),
    AE(11);

    private int nivel;

    private CategoriaCNH(int nivel)
    {
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
