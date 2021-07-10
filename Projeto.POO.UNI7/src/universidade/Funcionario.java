package universidade;

import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class Funcionario {

    protected boolean ativo = true;

    protected String nome;
    protected final LocalDate dataNascimento;
    protected BigDecimal salario;
    protected final LocalDate dataAdmissao;
    protected final int matricula;

    public Funcionario(String nome,
                       LocalDate dataNascimento,
                       BigDecimal salario,
                       LocalDate dataAdmissao,
                       int matricula)
    {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
        this.matricula = matricula;
    }

    /**
     * Atualiza salário do funcionário.
     * @param novoSalario valor do novo salário do funcionário. Deve ser maior que o atual.
     * @throws IllegalArgumentException caso o novo salário não seja superior ao atual.
     */
    public void atualizarSalario(BigDecimal novoSalario) throws IllegalArgumentException
    {
        if( novoSalario.compareTo(salario) < 0 )
        {
            throw new IllegalArgumentException("Valor definido para o salário inferior ao anterior ou ao mínimo.");
        }
        salario = novoSalario;
    }

    public void ativar(){ativo=true;}
    public void desativar(){ativo=false;}
    public boolean getAtivo(){return ativo;}

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    @Override
    public String toString()
    {
        StringBuilder resultado = new StringBuilder("\nAtivo: " + ativo +
                "\nMatricula: " + matricula +
                "\nNome: " + nome +
                "\nData de Nascimento: " + dataNascimento +
                "\nData de Admissão: " + dataAdmissao +
                "\nSalário: " + salario);
        return resultado.toString();
    }
}
