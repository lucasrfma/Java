package universidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Tesoureiro extends Funcionario{

    private static final BigDecimal SALARIO_INICIAL = BigDecimal.valueOf(1900);
    private static int proximaMatricula = 0;
    /**
     * Cria uma instância de Tesoureiro
     * @throws IllegalArgumentException caso o salário recebido seja inferior ao inicial
     * de tesoureiro da instituição.
     */
    public Tesoureiro(String nome, LocalDate dataNascimento, BigDecimal salario, LocalDate dataAdmissao) {
        super(nome, dataNascimento, SALARIO_INICIAL, dataAdmissao,proximaMatricula++);
        atualizarSalario(salario);
    }

    /**
     * Cria uma instância de Tesoureiro contratado hoje
     */
    public Tesoureiro(String nome, LocalDate dataNascimento)
    {
        this(nome,dataNascimento,SALARIO_INICIAL,LocalDate.now());
    }

    public int getMatricula() {
        return matricula;
    }
}
