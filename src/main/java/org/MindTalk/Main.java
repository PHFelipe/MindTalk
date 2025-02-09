package org.MindTalk;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        MindTalkMenu menu = new MindTalkMenu();
        SistemaFacade facade = new SistemaFacade();
        int opcao;
        String nomePsicologo;
        String crpPsicologo;
        PsicologoSubject psicologoLogado = null;

        System.out.println("💡 Bem-vindo ao MindTalk 💡");

        do {
            menu.exibirMenuInicial();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.println("👨‍⚕️ Cadastrar Psicólogo");
                    System.out.print("Nome: ");
                    nomePsicologo = scanner.nextLine();
                    System.out.print("CRP: ");
                    crpPsicologo = scanner.nextLine();

                    if (facade.pesquisarPsicologo(nomePsicologo, crpPsicologo)) {
                        System.out.println("❌ Psicólogo já cadastrado no sistema.");
                    } else {
                        facade.cadastrarPsicologo(nomePsicologo, crpPsicologo);
                        System.out.println("✅ Psicólogo cadastrado com sucesso.");
                    }
                    break;

                case 2:
                    System.out.println("👨‍⚕️ Realizar Login");
                    System.out.print("Nome: ");
                    nomePsicologo = scanner.nextLine();
                    System.out.print("CRP: ");
                    crpPsicologo = scanner.nextLine();
                   psicologoLogado = facade.realizarLogin(nomePsicologo, crpPsicologo);

                    if (psicologoLogado != null) {
                        System.out.println("✅ Login efetuado com sucesso.");
                        System.out.println("👨‍⚕️ Psicólogo logado: " + psicologoLogado.getNome());
                        opcao = 100;
                    } else {
                        System.out.println("❌ Login Não efetuado.");
                    }
                    break;

                case 0:
                    System.out.println("👋 Saindo do MindTalk...");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida. Por favor, escolha uma opção válida.");
            }
            if(opcao == 100){
                break;
            }
        } while (opcao != 0);

        if (opcao == 100) {
            System.out.println("Cadastre um paciente:");
            System.out.print("Nome: ");
            String nomePaciente = scanner.nextLine();
            System.out.print("CPF: ");
            String cpfPaciente = scanner.nextLine();
            PacienteObserver paciente = facade.cadastrarPaciente(nomePaciente, cpfPaciente);
            facade.associarPaciente(psicologoLogado, paciente);

            menu.exibirMenuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (opcao) {
                case 1:
                    System.out.println("👨‍⚕️ Iniciar Sala Virtual");
                    System.out.print("Nome da Sala Virtual: ");
                    String nomeSalaVirtual = scanner.nextLine();
                    CriarSalaCommand criarSalacommand = new CriarSalaCommand(facade, nomeSalaVirtual, psicologoLogado, paciente);
                    criarSalacommand.iniciarSalaVirtual();
                    break;

                case 2:
                    System.out.println("👋 Saindo do MindTalk...");
                    break;

                default:
                    System.out.println("⚠️ Opção inválida. Por favor, escolha uma opção válida.");
            }
        }

        scanner.close();
    }
}
