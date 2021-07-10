package universidade;

import java.util.HashSet;
import java.util.Set;

public class Aluno {

    private static int proximaMatricula = 0;

    private final int matricula;
    private boolean ativo = true;

    private String nome;
    private final long cpf;

    private final Set<Turma> turmas;
    private int presencas = 0;

    public Aluno(String nome, long cpf)
    {
        this.nome = nome;
        matricula = proximaMatricula++;
        this.cpf = cpf;
        this.turmas = new HashSet<>();
    }

    public void desativar()
    {
        for( var turma : turmas )
        {
            turma.removerAluno(this);
        }
        turmas.clear();
        ativo=false;
    }
    public void ativar(){ativo=true;}
    public boolean getAtivo(){return ativo;}

    public void marcarPresenca()
    {
        ++presencas;
    }

    public boolean adicionarTurma(Turma turma)
    {
        return turmas.add(turma);
    }

    public void removerTurma(Turma turma)
    {
        turmas.remove(turma);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }

    public long getCpf() {
        return cpf;
    }

    public Set<Turma> getTurmas() {
        return turmas;
    }

    public int getPresencas() {
        return presencas;
    }

    @Override
    public String toString()
    {
        StringBuilder resultado = new StringBuilder("\nAtivo: " + ativo +
                "\nMatricula: " + matricula +
                "\nNome: " + nome +
                "\nPresenças: " + presencas +
                "\nCPF:" + cpf +
                "\nTurmas:\n");
        for( var turma : turmas )
        {
            resultado.append(" ").append(turma).append("\n");
        }
        return resultado.toString();
    }

    @Override
    public boolean equals(Object o)
    {
        if( o == null)
        {
            return false;
        }
        if( !(o instanceof Aluno))
        {
            return false;
        }
        return ((Aluno) o).getMatricula() == getMatricula();
    }

    public int getNumeroTurmas()
    {
        return turmas.size();
    }

}
