package universidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AnalistaDeSistemas extends Funcionario{

    private static final BigDecimal SALARIO_INICIAL = BigDecimal.valueOf(3500);
    private static int proximaMatricula = 0;

    private LinguagensUsadas linguagemDeProgramacao;
    private String IDEFavorita;

    /**
     * Cria uma instância de Analista de Sistemas
     * @throws IllegalArgumentException caso o salário recebido seja inferior ao inicial
     * de analista de sistemas da instituição.
     */
    public AnalistaDeSistemas(String nome, LocalDate dataNascimento, BigDecimal salario, LocalDate dataAdmissao, LinguagensUsadas linguagemDeProgramacao, String IDEFavorita)
            throws IllegalArgumentException{
        super(nome, dataNascimento, SALARIO_INICIAL, dataAdmissao,proximaMatricula++);
        this.linguagemDeProgramacao = linguagemDeProgramacao;
        this.IDEFavorita = IDEFavorita;
        atualizarSalario(salario);
    }

    /**
     * Cria uma instância de Analista de Sistemas contratado hoje.
     */
    public AnalistaDeSistemas(String nome, LocalDate dataNascimento, LinguagensUsadas linguagemDeProgramacao, String IDEFavorita)
    {
        this(nome,dataNascimento,SALARIO_INICIAL,LocalDate.now(),linguagemDeProgramacao,IDEFavorita);
    }

    public int getMatricula() {
        return matricula;
    }

    public String getIDEFavorita() {
        return IDEFavorita;
    }

    public void setIDEFavorita(String IDEFavorita) {
        this.IDEFavorita = IDEFavorita;
    }

    public LinguagensUsadas getLinguagemDeProgramacao() {
        return linguagemDeProgramacao;
    }

    public void setLinguagemDeProgramacao(LinguagensUsadas linguagemDeProgramacao) {
        this.linguagemDeProgramacao = linguagemDeProgramacao;
    }
}
