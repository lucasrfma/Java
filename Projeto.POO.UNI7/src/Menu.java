import universidade.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public class Menu {

    private static final ArrayList<AnalistaDeSistemas> analistas = new ArrayList<>();
    private static final ArrayList<Motorista> motoristas = new ArrayList<>();
    private static final ArrayList<Professor> professores = new ArrayList<>();
    private static final ArrayList<Secretario> secretarios = new ArrayList<>();
    private static final ArrayList<Tesoureiro> tesoureiros = new ArrayList<>();

    private static final ArrayList<Aluno> alunos = new ArrayList<>();
    private static final ArrayList<Turma> turmas = new ArrayList<>();

    private static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        char choice;
        do{
            System.out.println("""

                    Sistema da Universidade

                    1 - Menu Alunos
                    2 - Menu Funcionários
                    3 - Menu Turmas
                    4 - Registrar acontecimento de aula

                    0 - Sair.""");
            choice = lerLinha().toLowerCase().charAt(0);

            switch (choice)
            {
                case '1': menuAlunos(); break;
                case '2': menuFuncionarios(); break;
                case '3': menuTurmas(); break;
                case '4': registrarAula(); break;
                case '0': break;
                default:
                    System.out.println("\nOpção Inválida. Escolha novamente:\n");
                    break;
            }
        }while(choice != '0');
    }

    private static void registrarAula(){
        try
        {
            System.out.println("""
                    Registro de aula dada.
                    Digite as seguintes informações:
                    
                    Matrícula do professor que deu a aula:""");
            int matricula = Integer.parseInt(lerLinha());
            System.out.println("\nID da turma:");
            int id = Integer.parseInt(lerLinha());
            professores.get(matricula).darAula(turmas.get(id));
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Erro: Verifique a matrícula e o ID inseridos.");
        }
        catch (IOException e)
        {
            System.out.println("Erro de input.");
        }

    }

    private static void menuAlunos() throws IOException {
        char choice;
        do{
            System.out.println("""
                    Menu Alunos

                    1 - Novo Aluno
                    2 - Listar Alunos
                    3 - Consultar Aluno Específico
                    4 - Matricular Aluno em Turma
                    5 - Desmatricular Aluno de Turma
                    6 - Remover Aluno da Universidade

                    0 - Retornar ao menu principal""");
            choice = lerLinha().toLowerCase().charAt(0);

            switch (choice)
            {
                case '1': novoAluno(); break;
                case '2': listarAlunos(); break;
                case '3': consultarAluno(); break;
                case '4': matricularAlunoMenu(); break;
                case '5': desmatricularAlunoMenu(); break;
                case '6': removerAlunoMenu(); break;
                case '0': break;
                default:
                    System.out.println("\nOpção Inválida. Escolha novamente:\n");
                    break;
            }
            System.out.println("\nAperte enter para continuar.");
            reader.readLine();
        }while(choice != '0');

    }

    private static void removerAlunoMenu() {
        int matricula;
        try{
            System.out.println("Digite a matrícula do aluno.");
            matricula = Integer.parseInt(lerLinha());

            alunos.get(matricula).desativar();
        }
        catch (Exception e)
        {
            System.out.println("Erro de input. Operação cancelada.");
        }
    }

    private static void desmatricularAlunoMenu() {
        int matricula;
        int IDTurma;
        try{
            System.out.println("Digite a matrícula do aluno.");
            matricula = Integer.parseInt(lerLinha());

            System.out.println("Digite o ID da turma.");
            IDTurma = Integer.parseInt(lerLinha());

            desmatricularAluno(alunos.get(matricula),turmas.get(IDTurma));
        }
        catch (Exception e)
        {
            System.out.println("Erro de input. Operação cancelada.");
        }
    }

    private static void desmatricularAluno(Aluno aluno, Turma turma) {
        try{
            if( turma.removerAluno(aluno))
            {
                aluno.removerTurma(turma);
                System.out.println("Aluno desmatriculado com sucesso.");
            }
            else {
                System.out.println("Aluno não está matriculado nessa turma.");
            }
        }
        catch (Exception e)
        {
            System.out.println("Erro ao desmatricular aluno.\nVerifique a matrícula e o ID.");
        }
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

    private static void matricularAlunoMenu()
    {
        int matricula;
        int IDTurma;
        try{
            System.out.println("Digite a matrícula do aluno.");
            matricula = Integer.parseInt(lerLinha());

            System.out.println("Digite o ID da turma.");
            IDTurma = Integer.parseInt(lerLinha());

            matricularAluno(alunos.get(matricula),turmas.get(IDTurma));
        }
        catch (Exception e)
        {
            System.out.println("Erro de input. Operação cancelada.");
        }
    }

    private static void consultarAluno()
    {
        System.out.println("\nConsulta de aluno.\n\nDigite o número de matrícula do aluno a consultar:\n");
        try{
            int matricula = Integer.parseInt(lerLinha());

            System.out.println(alunos.get(matricula));
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Matrícula inválida. Tente novamente.");
        }
        catch (Exception e)
        {
            System.out.println("Ocorreu um erro. Tente novamente.");
        }
    }

    private static void novoAluno()
    {
        System.out.println("\nInscrição de aluno. Insira as seguintes informações:");

        try{
            String nome = pedirNome();

            System.out.print("\nCPF: ");
            long cpf = Long.parseLong(lerLinha());

            alunos.add(new Aluno(nome, cpf));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    private static void menuTurmas() throws IOException {
        char choice;
        do{
            System.out.println("""
                    Menu Turmas

                    1 - Nova Turma
                    2 - Listar Turmas
                    3 - Consultar Turma Específica
                    4 - Remover Turma

                    0 - Retornar ao menu principal""");
            choice = lerLinha().toLowerCase().charAt(0);

            switch (choice)
            {
                case '1': novaTurma(); break;
                case '2': listarTurmas(); break;
                case '3': consultarTurma(); break;
                case '4': removerTurma(); break;
                case '0': break;
                default:
                    System.out.println("\nOpção Inválida. Escolha novamente:\n");
                    break;
            }
            System.out.println("\nAperte enter para continuar.");
            reader.readLine();
        }while(choice != '0');

    }

    private static void removerTurma() {
        System.out.println("\nRemoção de turma.\n\nDigite o número ID da turma a remover:\n");
        try{
            int ID = Integer.parseInt(lerLinha());

            turmas.get(ID).desativar();
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Matrícula inválida. Tente novamente.");
        }
        catch (Exception e)
        {
            System.out.println("Ocorreu um erro. Tente novamente.");
        }
    }

    private static void consultarTurma() {
        System.out.println("\nConsulta de turma.\n\nDigite o número ID da turma a consultar:\n");
        try{
            int ID = Integer.parseInt(lerLinha());
            System.out.println(turmas.get(ID));
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Matrícula inválida. Tente novamente.");
        }
        catch (Exception e)
        {
            System.out.println("Ocorreu um erro. Tente novamente.");
        }
    }

    private static void novaTurma() throws IOException {
        System.out.println("\nCriação de turma. Insira as seguintes informações:");
        System.out.println("\nNome da disciplina:");
        String disciplina = lerLinha();
        System.out.println("\nMatrícula do professor titular:");
        int matricula = Integer.parseInt(lerLinha());
        try
        {
            turmas.add(new Turma(disciplina,professores.get(matricula)));
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("\nNão há professor com essa matrícula.\nTurma não criada.");
        }
    }

    private static void menuFuncionarios() throws IOException {
        char choice;
        do{
            System.out.println("""
                    Menu Funcionários

                    1 - Novo Professor
                    2 - Novo Analista de Sistemas
                    3 - Novo Motorista
                    4 - Novo Tesoureiro
                    5 - Novo Secretário
                    6 - Listar Funcionários
                    7 - Consultar Funcionário Específico
                    8 - Remover Funcionário

                    0 - Retornar ao menu principal""");
            choice = lerLinha().toLowerCase().charAt(0);
            try
            {
                switch (choice)
                {
                    case '1': novoProfessor(); break;
                    case '2': novoADS(); break;
                    case '3': novoMotorista(); break;
                    case '4': novoTesoureiro(); break;
                    case '5': novoSecretario(); break;
                    case '6': listarFuncionarios(); break;
                    case '7': consultarFuncionario(); break;
                    case '8': removerFuncionario(); break;
                    case '0': break;
                    default:
                        System.out.println("\nOpção Inválida. Escolha novamente:\n");
                        break;
                }
                System.out.println("\nAperte enter para continuar.");
                reader.readLine();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        }while(choice != '0');
    }

    private static void removerFuncionario() throws IOException {
        System.out.println("""
                        Remoção de Funcionário.
                        
                        Indique seu cargo:
                        1 - Professor;
                        2 - Analista de Sistemas;
                        3 - Motorista;
                        4 - Tesoureiro;
                        5 - Secretário.
                        """);
        int escolha = Integer.parseInt(lerLinha());
        System.out.println("Indique a matrícula do funcionário:");
        int matricula = Integer.parseInt(lerLinha());
        try{
            switch (escolha) {
                case 1 -> professores.get(matricula).desativar();
                case 2 -> analistas.get(matricula).desativar();
                case 3 -> motoristas.get(matricula).desativar();
                case 4 -> tesoureiros.get(matricula).desativar();
                case 5 -> secretarios.get(matricula).desativar();
                default -> System.out.println("Opção de cargo inválida.");
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Não há funcionário com esse cargo e matrícula.");
        }
    }

    private static String lerLinha() throws IOException {
        String input;
        do{
            input = reader.readLine();
        }while (input.length() < 1);
        return input;
    }

    private static void consultarFuncionario() throws IOException {
        System.out.println("""
                        Consulta de Funcionário específico.
                        
                        Indique seu cargo:
                        1 - Professor;
                        2 - Analista de Sistemas;
                        3 - Motorista;
                        4 - Tesoureiro;
                        5 - Secretário.
                        """);
        int escolha = Integer.parseInt(lerLinha());
        System.out.println("Indique a matrícula do funcionário:");
        int matricula = Integer.parseInt(lerLinha());
        try{
            switch (escolha) {
                case 1 -> System.out.println(professores.get(matricula));
                case 2 -> System.out.println(analistas.get(matricula));
                case 3 -> System.out.println(motoristas.get(matricula));
                case 4 -> System.out.println(tesoureiros.get(matricula));
                case 5 -> System.out.println(secretarios.get(matricula));
                default -> System.out.println("Opção de cargo inválida.");
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            System.out.println("Não há funcionário com esse cargo e matrícula.");
        }
    }

    private static void novoTesoureiro() {
        System.out.println("\nInscrição de Tesoureiro. Insira as seguintes informações:");

        try{
            String nome = pedirNome();

            System.out.print("\nData de Nascimento:");
            LocalDate dataNascimento = pedirData();

            BigDecimal salario = pedirSalario();

            tesoureiros.add(new Tesoureiro(nome,
                    dataNascimento,
                    salario,
                    LocalDate.now()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void novoSecretario() {
        System.out.println("\nInscrição de Secretario. Insira as seguintes informações:");

        try{
            String nome = pedirNome();

            System.out.print("\nData de Nascimento:");
            LocalDate dataNascimento = pedirData();

            BigDecimal salario = pedirSalario();

            secretarios.add(new Secretario(nome,
                    dataNascimento,
                    salario,
                    LocalDate.now()));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void novoMotorista() {
        System.out.println("\nInscrição de Motorista. Insira as seguintes informações:");

        try{
            String nome = pedirNome();

            System.out.print("\nData de Nascimento:");
            LocalDate dataNascimento = pedirData();

            System.out.println("""

                    Escolha uma opção categoria de CNH:
                    1 - A
                    2 - B
                    3 - C
                    4 - D
                    5 - E
                    6 - AB
                    7 - AC
                    8 - AD
                    9 - AE""");
            int opcao = Integer.parseInt(lerLinha());
            CategoriaCNH cnh = switch (opcao)
                    {
                        case 1 -> CategoriaCNH.A;
                        case 2 -> CategoriaCNH.B;
                        case 3 -> CategoriaCNH.C;
                        case 4 -> CategoriaCNH.D;
                        case 5 -> CategoriaCNH.E;
                        case 6 -> CategoriaCNH.AB;
                        case 7 -> CategoriaCNH.AC;
                        case 8 -> CategoriaCNH.AD;
                        case 9 -> CategoriaCNH.AE;
                        default -> null;
                    };
            if(cnh == null)
            {
                System.out.println("Opção inválida. Inserção cancelada.");
                return;
            }

            BigDecimal salario = pedirSalario();

            motoristas.add(new Motorista(nome,
                    dataNascimento,
                    salario,
                    LocalDate.now(),
                    cnh));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static LocalDate pedirData() throws IOException {
        System.out.println("Formato dd/mm/aa");
        String[] dataNascimento = lerLinha().split("/");

        return LocalDate.of(Integer.parseInt(dataNascimento[2]),
                Integer.parseInt(dataNascimento[1]),
                Integer.parseInt(dataNascimento[0]));
    }

    private static String pedirNome() throws IOException {
        System.out.print("\nNome: ");
        return lerLinha();
    }

    private static BigDecimal pedirSalario() throws IOException
    {
        System.out.print("\nSalário: ");
        return BigDecimal.valueOf(Double.parseDouble(lerLinha()));
    }


    private static void novoADS() {
        System.out.println("\nInscrição de Analista de Sistemas. Insira as seguintes informações:");

        try{
            String nome = pedirNome();

            System.out.print("\nData de Nascimento:");
            LocalDate dataNascimento = pedirData();

            System.out.println("""

                    Escolha uma opção de linguagem de programação:
                    1 - Java
                    2 - Kotlin
                    3 - Javascript
                    4 - Python""");
            int opcao = Integer.parseInt(lerLinha());
            LinguagensUsadas linguagem = switch (opcao)
                    {
                        case 1 -> LinguagensUsadas.JAVA;
                        case 2 -> LinguagensUsadas.KOTLIN;
                        case 3 -> LinguagensUsadas.JAVASCRIPT;
                        case 4 -> LinguagensUsadas.PYTHON;
                        default -> null;
                    };
            if(linguagem == null)
            {
                System.out.println("Opção inválida. Inserção cancelada.");
                return;
            }

            System.out.println("\nIDE preferida:");
            String ide = lerLinha();

            BigDecimal salario = pedirSalario();

            analistas.add(new AnalistaDeSistemas(nome,
                    dataNascimento,
                    salario,
                    LocalDate.now(),
                    linguagem,
                    ide));
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private static void listarFuncionarios() {
        if( professores.size() > 0)
        {
            listarProfessores();
        }
        if( analistas.size() > 0)
        {
            listarAnalistas();
        }
        if( motoristas.size() > 0 )
        {
            listarMotoristas();
        }
        if( secretarios.size() > 0 )
        {
            listarSecretarios();
        }
        if( tesoureiros.size() > 0 )
        {
            listarTesoureiros();
        }
    }

    private static void novoProfessor() {
        System.out.println("\nInscrição de Professor. Insira as seguintes informações:");

        try{
            String nome = pedirNome();

            System.out.print("\nData de Nascimento: ");
            LocalDate dataNascimento = pedirData();

            System.out.println("""

                    Escolha uma opção de escolaridade:
                    1 - Ensino Superior
                    2 - Pós Graduado
                    3 - Mestre
                    4 - Doutor""");
            int opcao = Integer.parseInt(lerLinha());
            Escolaridade escolaridade = switch (opcao)
                    {
                        case 1 -> Escolaridade.ENSINO_SUPERIOR;
                        case 2 -> Escolaridade.POS_GRAD;
                        case 3 -> Escolaridade.MESTRADO;
                        case 4 -> Escolaridade.DOUTORADO;
                        default -> Escolaridade.SEM_ENSINO;
                    };

            BigDecimal salario = pedirSalario();

            professores.add(new Professor(nome,
                    dataNascimento,
                    salario,
                    LocalDate.now(),
                    escolaridade));
        }
        catch (Exception e)
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
