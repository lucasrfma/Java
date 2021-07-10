package universidade;

public class RemocaoDeProfessorRegistradoComoTitularException extends RuntimeException{
    public RemocaoDeProfessorRegistradoComoTitularException()
    {
        super("Tentativa de desativamento de um professor registrado como titular de turmas."+
                "\nRegistre outros professores em seu lugar antes de desativá-lo.");
    }

}
