import java.util.Scanner;

public class MindTalkApp {
    public static void main(String[] args) {
        MindTalkFacade sistema = new MindTalkFacade();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MindTalk ===");
            System.out.println("1️⃣ Cadastrar Psicólogo");
            System.out.println("2️⃣ Cadastrar Paciente");
            System.out.println("3️⃣ Iniciar Videochamada");
            System.out.println("4️⃣ Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa o buffer

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do Psicólogo: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    sistema.cadastrarPsicologo(nome, email);
                }
                case 2 -> {
                    System.out.println("Escolha um Psicólogo:");
                    for (int i = 0; i < sistema.getPsicologos().size(); i++) {
                        System.out.println(i + " - " + sistema.getPsicologos().get(i).getNome());
                    }
                    int escolha = scanner.nextInt();
                    scanner.nextLine();
                    Psicologo psicologo = sistema.getPsicologos().get(escolha);

                    System.out.print("Nome do Paciente: ");
                    String nome = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    sistema.cadastrarPaciente(nome, email, psicologo);
                }
                case 3 -> {
                    System.out.println("Escolha um Psicólogo:");
                    Psicologo psicologo = sistema.getPsicologos().get(scanner.nextInt());
                    scanner.nextLine();

                    System.out.println("Escolha um Paciente:");
                    Paciente paciente = sistema.getPacientesDoPsicologo(psicologo).get(scanner.nextInt());
                    scanner.nextLine();

                    sistema.iniciarVideoChamada(psicologo, paciente);
                }
                case 4 -> {
                    System.out.println("👋 Saindo...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Opção inválida!");
            }
        }
    }
}
