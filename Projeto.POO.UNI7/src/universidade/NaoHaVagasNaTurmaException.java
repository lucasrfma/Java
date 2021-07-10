package universidade;

public class NaoHaVagasNaTurmaException extends RuntimeException{
    public NaoHaVagasNaTurmaException()
    {
        super("Não há vagas nessa turma.");
    }
}
