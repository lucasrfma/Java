package universidade;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Professor extends Funcionario{

    private static final BigDecimal SALARIO_INICIAL = BigDecimal.valueOf(3500);
    private static final BigDecimal BONUS_POS = BigDecimal.valueOf(1.05);
    private static final BigDecimal BONUS_MESTRADO = BigDecimal.valueOf(1.1);
    private static final BigDecimal BONUS_DOUTORADO = BigDecimal.valueOf(1.2);
    private static int proximaMatricula = 0;

    private final Set<Turma> turmas = new HashSet<>();
    private int aulasDadasTitular = 0;
    private int aulasDadasSubstituto = 0;
    private int aulasFaltadas = 0;
    private Escolaridade escolaridade;

    /**
     * Cria uma instância de Professor.
     * Escolaridade mínima aceita é de ENSINO_SUPERIOR
     * @throws IllegalArgumentException em caso de escolaridade não aceita,
     * ou em caso de salário abaixo do salário inicial de professor da instituição
     * acrescido do bônus de escolaridade adequado..
     */
    public Professor(String nome,
                     LocalDate dataNascimento,
                     BigDecimal salario,
                     LocalDate dataAdmissao,
                     Escolaridade escolaridade
                     )
    {
        super(nome, dataNascimento,salarioInicialComBonusEscolaridade(escolaridade), dataAdmissao,proximaMatricula++);
        setEscolaridade(escolaridade);
        atualizarSalario(salario);
    }

    /**
     * Cria uma instância de Professor contratado hoje
     */
    public Professor(String nome, LocalDate dataNascimento, Escolaridade escolaridade)
    {
        this(nome,dataNascimento,salarioInicialComBonusEscolaridade(escolaridade),LocalDate.now(),escolaridade);
    }

    private static BigDecimal salarioInicialComBonusEscolaridade(Escolaridade escolaridade)
    {
        return switch (escolaridade){
            case DOUTORADO -> SALARIO_INICIAL.multiply(BONUS_DOUTORADO);
            case MESTRADO -> SALARIO_INICIAL.multiply(BONUS_MESTRADO);
            case POS_GRAD -> SALARIO_INICIAL.multiply(BONUS_POS);
            default -> SALARIO_INICIAL;
        };
    }

    /**
     * Atualiza a escolaridade de um professor.
     * Escolaridade mínima aceita é de ENSINO_SUPERIOR.
     * @throws IllegalArgumentException em caso de escolaridade não aceita.
     */
    public void setEscolaridade(Escolaridade escolaridadeNova) throws IllegalArgumentException
    {
        if( escolaridadeNova.getNivel() < Escolaridade.ENSINO_SUPERIOR.getNivel())
        {
            throw new IllegalArgumentException("Escolaridade abaixo da mínima requerida.");
        }
        if( escolaridade != null && escolaridade.getNivel() > escolaridadeNova.getNivel())
        {
            throw new IllegalArgumentException("Escolaridade nova abaixo da atual.");
        }
        escolaridade = escolaridadeNova;
    }

    public Escolaridade getEscolaridade()
    {
        return escolaridade;
    }

    public int getMatricula() {
        return matricula;
    }

    /**
     * Adiciona uma turma à lista de turmas pelas quais esse professor é responsável.
     * Só pode ser adicionada uma turma pela qual esse professor foi registrado como titular.
     * Desse modo, esse método é feito para ser chamado pelo método setProfessor da
     * classe Turma.
     */
    protected void adicionarTurma(Turma turma)
    {
        if( turma.getProfessor() == this )
        {
            turmas.add(turma);
        }
    }

    /**
     * Remove uma turma da lista de turmas pelas quais esse professor é responsável.
     * Só pode ser retirada uma turma pela qual esse professor já não é responsável.
     * Desse modo, esse método é feito para ser chamado pelo método setProfessor da
     * classe Turma.
     */
    protected void removerTurma(Turma turma)
    {
        if( turma.getProfessor() != this)
        {
            turmas.remove(turma);
        }
    }

    /**
     * Esse professor dá aula a uma turma.
     * Conta essa atividade diferentemente se for o professor
     * responsável pela turma do que se for um professor substituto.
     */
    public void darAula(Turma turma)
    {
        if (turmas.contains(turma))
        {
            ++aulasDadasTitular;
        }
        else
        {
            ++turma.getProfessor().aulasFaltadas;
            ++aulasDadasSubstituto;
        }
        turma.contarPresencas();
    }

    @Override
    public void desativar()
    {
        if( turmas.size() > 0)
        {
            throw new RemocaoDeProfessorRegistradoComoTitularException();
        }
        ativo = false;
    }

    public int getAulasDadasSubstituto() {
        return aulasDadasSubstituto;
    }

    public int getAulasDadasTitular() {
        return aulasDadasTitular;
    }

    public int getTotalAulas()
    {
        return aulasDadasSubstituto+ aulasDadasTitular;
    }

    public int getAulasFaltadas() {
        return aulasFaltadas;
    }

    @Override
    public String toString()
    {
        StringBuilder resultado = new StringBuilder("\nAtivo: " + ativo +
                "\nMatricula: " + matricula +
                "\nNome: " + nome +
                "\nEscolaridade: " + escolaridade +
                "\nTotal de aulas dadas: " + getTotalAulas() +
                "\n\tAulas dadas como titular: " + aulasDadasTitular +
                "\n\tAulas dadas como substitudo: " + aulasDadasSubstituto +
                "\n\tAulas em que foi substituído: " + aulasFaltadas +
                "\nData de Nascimento: " + dataNascimento +
                "\nData de Admissão: " + dataAdmissao +
                "\nSalário: " + salario +
                "\nTurmas:\n");
        for( var turma : turmas )
        {
            resultado.append(" ").append(turma).append("\n");
        }
        return resultado.toString();
    }

}
