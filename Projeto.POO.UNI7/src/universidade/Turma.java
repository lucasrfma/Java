package universidade;

import java.util.HashSet;
import java.util.Set;

public class Turma {

    public static final int MAX_ALUNOS = 30;
    private static int proximoID = 0;

    private final int ID;
    private boolean ativo = true;

    private final String disciplina;
    private Professor professor;
    private final Set<Aluno> alunos;

    public Turma(String disciplina, Professor professor) throws IllegalArgumentException
    {
        this.disciplina = disciplina;
        setProfessor(professor);
        alunos = new HashSet<>();
        ID = proximoID++;
    }

    public void ativar(){ativo = true;}

    /**
     * Desativa a turma.
     * Desativar a turma significa retirar essa turma das listas de turmas
     * do professor responsável por ela, assim como de todos os alunos matriculados.
     */
    public void desativar(){
        for( var aluno: alunos )
        {
            aluno.removerTurma(this);
        }
        Professor professor = this.professor;
        this.professor = null;
        professor.removerTurma(this);
        ativo = false;
    }

    public boolean getAtivo(){return ativo;}

    public boolean removerAluno(Aluno aluno)
    {
        return alunos.remove(aluno);
    }

    public boolean adicionarAluno(Aluno aluno) throws NaoHaVagasNaTurmaException
    {
        if( alunos.size() >= MAX_ALUNOS )
        {
            throw new IllegalArgumentException();
        }
        return alunos.add(aluno);
    }

    /**
     * Modifica o professor responsável pela turma.
     * Retira essa turma da lista de turmas do professor antigo.
     * Adiciona essa turma à lista de turmas do professor novo.
     */
    public void setProfessor(Professor professor) {
        if( this.professor != null )
        {
            Professor antigo = this.professor;
            antigo.removerTurma(this);
        }
        if( this.professor != professor)
        {
            this.professor = professor;
            this.professor.adicionarTurma(this);
        }
    }

    public String getDisciplina() {
        return disciplina;
    }

    public Professor getProfessor() {
        return professor;
    }

    public Set<Aluno> getAlunos() {
        return alunos;
    }

    public int getID(){return ID;}

    public int getNumeroAlunos(){ return alunos.size();}

    @Override
    public String toString()
    {
        return ID + " - " + disciplina + " - " + professor.getNome();
    }

    /**
     * Conta as presenças dos alunos matriculados nessa turma.
     * Acesso setado à protected para evitar ser chamado sem que
     * aconteça uma aula.
     * O método darAula da classe Professor chama esse método.
     */
    protected void contarPresencas() {
        for( var aluno : alunos )
        {
            aluno.marcarPresenca();
        }
    }
}
