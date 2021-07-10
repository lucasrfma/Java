import universidade.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Exemplo {

    private static final ArrayList<AnalistaDeSistemas> analistas = new ArrayList<>();
    private static final ArrayList<Motorista> motoristas = new ArrayList<>();
    private static final ArrayList<Professor> professores = new ArrayList<>();
    private static final ArrayList<Secretario> secretarios = new ArrayList<>();
    private static final ArrayList<Tesoureiro> tesoureiros = new ArrayList<>();

    private static final ArrayList<Aluno> alunos = new ArrayList<>();
    private static final ArrayList<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {

        // Adicionando professores
        professores.add( new Professor("Professor A",
                LocalDate.of(1965,5,10),
                Escolaridade.DOUTORADO));
        professores.add( new Professor("Professor B",
                LocalDate.of(1967,5,10),
                Escolaridade.MESTRADO));

        // Adicionando outros funcionários
        analistas.add(new AnalistaDeSistemas("Analista A",
                LocalDate.of(1982,2,22),
                LinguagensUsadas.JAVA,
                "IntelliJ"));
        motoristas.add(new Motorista("Motorista A",
                LocalDate.of(1960,10,30),
                CategoriaCNH.AE));
        secretarios.add(new Secretario("Secretario A",
                LocalDate.of(1987,6,15)));
        tesoureiros.add(new Tesoureiro("Tesoureiro A",
                LocalDate.of(1985,4,15)));

        // Registrando turmas
        turmas.add(new Turma("Algoritmo",
                                professores.get(0)));
        turmas.add(new Turma("Orientação a Objeto",
                                professores.get(0)));
        turmas.add(new Turma("Matemática",
                                professores.get(1)));

        // Recebendo alunos
        alunos.add(new Aluno("Aluno A", 12345678910L));
        alunos.add(new Aluno("Aluno B", 23456789101L));
        alunos.add(new Aluno("Aluno C", 34567891012L));
        alunos.add(new Aluno("Aluno D", 45678910123L));

        // Listando funcionários
        listarProfessores();
        listarMotoristas();
        listarAnalistas();
        listarTesoureiros();
        listarSecretarios();

        // Matriculando alunos nas turmas
        System.out.println("\nMatriculando alunos em turmas:\n");
        matricularAluno(alunos.get(0),turmas.get(0));
        matricularAluno(alunos.get(0),turmas.get(2));
        matricularAluno(alunos.get(1),turmas.get(1));
        matricularAluno(alunos.get(2),turmas.get(1));
        matricularAluno(alunos.get(3),turmas.get(1));

        // listando turmas e alunos
        listarTurmas();
        listarAlunos();

        // Quando um aluno sai da universidade, ele é automaticamente desmatriculado
        // de todas as turmas em que estava inscrito:
        System.out.println("\nAluno saiu da universidade.");
        alunos.get(2).desativar();

        // listando novamente turmas e alunos após saída de um aluno:
        listarTurmas();
        listarAlunos();

        // Professores dão aulas
        System.out.println("\nProfessor A dá aula como titular à turma Algoritmo.");
        professores.get(0).darAula(turmas.get(0));
        System.out.println("Professor B dá aula como substituto à turma Orientação à Objeto.");
        professores.get(1).darAula(turmas.get(1));
        System.out.println("Professor B dá aula como titular à turma Matemática.");
        professores.get(1).darAula(turmas.get(2));

        // Consulta de informações detalhadas de dois professores
        System.out.println("\nExemplos de consulta de informações detalhadas de professores:");
        System.out.println(professores.get(0));
        System.out.println(professores.get(1));

        // Consulta de informações detalhadas de um aluno
        System.out.println("\nExemplo de consulta de informações detalhadas de um aluno:");
        System.out.println(alunos.get(0));
    }


    private static void matricularAluno(Aluno aluno, Turma turma)
    {
        if(!aluno.getAtivo())
        {
            System.out.println("Aluno inativo.");
        }
        if(!turma.getAtivo())
        {
            System.out.println("Turma inativa.");
        }
        try
        {
            if( turma.adicionarAluno(aluno))
            {
                aluno.adicionarTurma(turma);
                System.out.println("Matrícula realizada com sucesso.");
            }
            else {
                System.out.println("Aluno já matriculado nessa turma.");
            }
        }
        catch (NaoHaVagasNaTurmaException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void listarAlunos()
    {
        System.out.println("\n-------------------------LISTA ALUNOS-------------------------:\n\nMatrícula Nome                          N\u00BA Turmas Matriculadas");
        for (var aluno : alunos) {
            if( aluno.getAtivo() )
            {
                System.out.printf("\n%-10d%-30s%d",aluno.getMatricula(),aluno.getNome(),aluno.getNumeroTurmas());
            }
        }
        System.out.println("\n\n-------------------------FIM DA LISTA-------------------------");
    }
    private static void listarMotoristas()
    {
        System.out.println("\n-------------------------LISTA MOTORISTAS-------------------------\n\nMatrícula Nome                          Categoria CNH");
        for (var motorista : motoristas) {
            if( motorista.getAtivo() )
            {
                System.out.printf("\n%-10d%-30s%s",motorista.getMatricula(),motorista.getNome(),motorista.getCategoriaCNH());
            }
        }
        System.out.println("\n\n---------------------------FIM DA LISTA---------------------------");
    }
    private static void listarProfessores()
    {
        System.out.println("\n-------------------------LISTA PROFESSORES-------------------------\n\nMatrícula Nome                          Escolaridade");
        for (var professor : professores) {
            if( professor.getAtivo() )
            {
                System.out.printf("\n%-10d%-30s%s",professor.getMatricula(),professor.getNome(),professor.getEscolaridade());
            }
        }
        System.out.println("\n\n---------------------------FIM DA LISTA---------------------------");
    }

    private static void listarTurmas()
    {
        System.out.println("\n-------------------------------------LISTA TURMAS-------------------------------------\n\nID        Disciplina               Professor Responsável         N\u00BA Alunos Matriculados");
        for (var turma : turmas) {
            if( turma.getAtivo() )
            {
                System.out.printf("\n%-10d%-25s%-30s%d / %d",turma.getID(),turma.getDisciplina(),turma.getProfessor().getNome(),turma.getNumeroAlunos(),Turma.MAX_ALUNOS);
            }
        }
        System.out.println("\n\n-------------------------------------FIM DA LISTA-------------------------------------");
    }

    private static void listarSecretarios()
    {
        System.out.println("\n-------------------------LISTA SECRETARIOS-------------------------\n\nMatrícula Nome");
        for (var secretario : secretarios) {
            if( secretario.getAtivo() )
            {
                System.out.printf("\n%-10d%-30s",secretario.getMatricula(),secretario.getNome());
            }
        }
        System.out.println("\n\n---------------------------FIM DA LISTA---------------------------");
    }

    private static void listarTesoureiros()
    {
        System.out.println("\n-------------------------LISTA TESOUREIROS-------------------------\n\nMatrícula Nome");
        for (var tesoureiro : tesoureiros) {
            if( tesoureiro.getAtivo() )
            {
                System.out.printf("\n%-10d%-30s",tesoureiro.getMatricula(),tesoureiro.getNome());
            }
        }
        System.out.println("\n\n---------------------------FIM DA LISTA---------------------------");
    }

    private static void listarAnalistas()
    {
        System.out.println("\n-----------------------LISTA ANALISTAS DE SISTEMAS-----------------------\n\nMatrícula Nome                          Linguagem IDE");
        for (var analistaDeSistemas : analistas) {
            if( analistaDeSistemas.getAtivo() )
            {
                System.out.printf("\n%-10d%-30s%-10s%s",analistaDeSistemas.getMatricula(),analistaDeSistemas.getNome(),analistaDeSistemas.getLinguagemDeProgramacao(),analistaDeSistemas.getIDEFavorita());
            }
        }
        System.out.println("\n\n------------------------------FIM DA LISTA------------------------------");
    }



}
