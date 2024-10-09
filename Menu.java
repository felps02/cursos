import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    static ArrayList<Professor> professores = new ArrayList<>();
    static ArrayList<Curso> cursos = new ArrayList<>();
    static ArrayList<Aluno> alunos = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao = 0;

        while(opcao != 7) {
            System.out.println("1 - Cadastrar Professor");
            System.out.println("2 - Cadastrar Curso");
            System.out.println("3 - Cadastrar Aluno");
            System.out.println("4 - Listar Professores");
            System.out.println("5 - Listar Cursos");
            System.out.println("6 - Listar Alunos");
            System.out.println("7 - Sair");
            opcao = Integer.parseInt(scanner.nextLine());

            try {
                switch (opcao) {
                    case 1:
                        cadastrarProfessor();
                        break;
                    case 2:
                        cadastrarCurso();
                        break;
                    case 3:
                        cadastrarAluno();
                        break;
                    case 4:
                        listarProfessores();
                        break;
                    case 5:
                        listarCursos();
                        break;
                    case 6:
                        listarAlunos();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    public static void cadastrarProfessor()
    {
                System.out.println("Digite o ID do professor: ");
                int id_professor = Integer.parseInt(scanner.nextLine());
                System.out.println("Digite o nome do professor: ");
                String nome = scanner.nextLine();
                System.out.println("Digite o departamento do professor: ");
                String departamento = scanner.nextLine();
                
                Professor professor = new Professor(id_professor, nome, departamento);
                professores.add(professor);
    }

    public static void cadastrarCurso() {
        System.out.println("Digite o ID do curso: ");
        int id_curso = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o nome do curso: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a carga horária do curso: ");
        int cargaHoraria = Integer.parseInt(scanner.nextLine());
    
        System.out.println("Selecione o ID do professor responsável: ");
        for (Professor professor : professores) {
            System.out.println(professor.getNome() + " (ID: " + professor.getId() + ")");
        }
        int professorId = Integer.parseInt(scanner.nextLine());
        Professor professor = null;
    
        for (Professor p : professores) {
            if (p.getId() == professorId) {
                professor = p;
                break;  // Achou o professor, sai do laço
            }
        }
    
        if (professor != null) {
            Curso curso = new Curso(id_curso, nome, cargaHoraria, professor);
            cursos.add(curso);
        } else {
            System.out.println("Professor não encontrado.");
        }
    }

    public static void cadastrarAluno() {
        System.out.println("Digite o ID do aluno: ");
        int id_aluno = Integer.parseInt(scanner.nextLine());
        System.out.println("Digite o nome do aluno: ");
        String nome = scanner.nextLine();
        System.out.println("Digite a data de nascimento: ");
        String dataNascimento = scanner.nextLine();
        System.out.println("Digite o CPF: ");
        String cpf = scanner.nextLine();
    
        System.out.println("Selecione o ID do curso do aluno: ");
        for (Curso curso : cursos) {
            System.out.println(curso.getNome() + " (ID: " + curso.getId() + ")");
        }
        int cursoId = Integer.parseInt(scanner.nextLine());
        Curso curso = null;
    
        for (Curso c : cursos) {
            if (c.getId() == cursoId) {
                curso = c;
                break;  // Achou o curso, sai do laço
            }
        }
    
        if (curso != null) {
            Aluno aluno = new Aluno(id_aluno, nome, dataNascimento, cpf, curso);
            curso.adicionarAluno(aluno);
            alunos.add(aluno);
        } else {
            System.out.println("Curso não encontrado.");
        }
    }

    public static void listarProfessores() {
        for (Professor professor : professores) {
            System.out.println("Professor: " + professor.getNome() + " | Departamento: " + professor.getDepartamento());
            System.out.println("Cursos ministrados:");
    
            boolean nenhumCurso = true;
    
            for (Curso curso : cursos) {
                if (curso.getProfessor().equals(professor)) {
                    System.out.println("- " + curso.getNome() + " (Alunos: " + curso.getAlunos().size() + ")");
                    nenhumCurso = false;  // Se entrou aqui, o professor tem curso
                }
            }
    
            if (nenhumCurso) {
                System.out.println("Nenhum curso ministrado.");
            }
        }
    }

    public static void listarCursos() 
    {
                cursos.forEach(curso -> {
                    System.out.println("Curso: " + curso.getNome() + " | Carga Horária: " + curso.getCargaHoraria());
                    System.out.println("Professor: " + curso.getProfessor().getNome());
                });
    }

    public static void listarAlunos()
    {
                alunos.forEach(aluno -> {
                    System.out.println("ID: " + aluno.getId_aluno() + "Aluno: " + aluno.getNome() + " | Nascimento: " + aluno.getDataNascimento() + " | CPF: " + aluno.getCpf());
                    System.out.println("Curso: " + aluno.getCurso().getNome());
                });
    }
}
