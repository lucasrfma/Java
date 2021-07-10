package universidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Motorista extends Funcionario{

    private static final BigDecimal SALARIO_INICIAL = BigDecimal.valueOf(2500);
    private static int proximaMatricula = 0;

    private CategoriaCNH categoriaCNH;
    /**
     * Cria uma instância de Motorista
     * @throws IllegalArgumentException caso o salário recebido seja inferior ao inicial
     * de motorista da instituição.
     */
    public Motorista(String nome, LocalDate dataNascimento, BigDecimal salario, LocalDate dataAdmissao, CategoriaCNH categoriaCNH) {
        super(nome, dataNascimento, SALARIO_INICIAL, dataAdmissao,proximaMatricula++);
        atualizarSalario(salario);
        this.categoriaCNH = categoriaCNH;
    }

    /**
     * Cria uma instância de Motorista contratado hoje
     */
    public Motorista(String nome, LocalDate dataNascimento, CategoriaCNH categoriaCNH)
    {
        this(nome,dataNascimento,SALARIO_INICIAL,LocalDate.now(),categoriaCNH);
    }

    public CategoriaCNH getCategoriaCNH() {
        return categoriaCNH;
    }

    public void setCategoriaCNH(CategoriaCNH categoriaCNH) {
        this.categoriaCNH = categoriaCNH;
    }

    public int getMatricula() {
        return matricula;
    }
}
