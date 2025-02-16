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

        System.out.println(" Bem-vindo ao MindTalk ");

        //Sistema de login
        do {
            menu.exibirMenuInicialPsicologo();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(CoresTerminal.AMARELO +"Cadastrar Psicólogo" + CoresTerminal.RESET);
                    System.out.print("Nome: ");
                    nomePsicologo = scanner.nextLine();
                    System.out.print("CRP: ");
                    crpPsicologo = scanner.nextLine();

                    if (facade.pesquisarPsicologo(nomePsicologo, crpPsicologo)) {
                        System.out.println(CoresTerminal.VERMELHO + "Psicólogo já cadastrado no sistema." + CoresTerminal.RESET);
                    } else {
                        facade.cadastrarPsicologo(nomePsicologo, crpPsicologo);
                        System.out.println(CoresTerminal.VERDE + "Psicólogo cadastrado com sucesso." + CoresTerminal.RESET);
                    }
                    break;

                case 2:
                    System.out.println(CoresTerminal.AMARELO + "️Realizar Login" + CoresTerminal.RESET);
                    System.out.print("Nome: ");
                    nomePsicologo = scanner.nextLine();
                    System.out.print("CRP: ");
                    crpPsicologo = scanner.nextLine();
                    psicologoLogado = facade.realizarLoginPsicologo(nomePsicologo, crpPsicologo);

                    if (psicologoLogado != null) {
                        System.out.println(CoresTerminal.VERDE + "Login efetuado com sucesso." + CoresTerminal.RESET);
                        System.out.println("Psicólogo logado: " +CoresTerminal.BOLD+ psicologoLogado.getNome() + CoresTerminal.RESET);
                        opcao = 100;
                    } else {
                        System.out.println(CoresTerminal.VERMELHO+"Login Não efetuado." + CoresTerminal.RESET);
                    }
                    break;

                case 0:
                    System.out.println(CoresTerminal.AMARELO+" Saindo do MindTalk..."+CoresTerminal.RESET);
                    break;

                default:
                    System.out.println(CoresTerminal.BG_VERMELHO+" Opção inválida. Por favor, escolha uma opção válida."+CoresTerminal.RESET);
            }

            //Sistema Principal
            if(opcao == 100){
                break;
            }
        } while (opcao != 0);

        if (opcao == 100) {
            do {
                menu.exibirMenuPrincipalPsicologo();
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        System.out.println(CoresTerminal.AMARELO + " Iniciar Sala Virtual" + CoresTerminal.RESET);
                        System.out.print("Nome da Sala Virtual: ");
                        String nomeSalaVirtual = scanner.nextLine();
                        System.out.println(CoresTerminal.BOLD + "Voce deseja listar os seus pacientes, para escolher qual será atendido? (S/N)" + CoresTerminal.RESET);
                        String escolha = scanner.nextLine();
                        //Exibição dos pacientes para escolha do psicologo
                        if (escolha.equalsIgnoreCase("S")) {
                            facade.exibirListaPacientes(psicologoLogado);
                        }
                        System.out.println("\nEscolha o paciente que deseja atender");
                        System.out.println("Digite o nome: ");
                        String nomePaciente = scanner.nextLine();
                        System.out.println("Digite o CPF: ");
                        String cpfPaciente = scanner.nextLine();

                        PacienteObserver paciente = facade.pesquisarPaciente(nomePaciente, cpfPaciente, psicologoLogado);
                        if (paciente == null) {
                            System.out.println(CoresTerminal.VERMELHO + "Paciente nao encontrado, Tente novamente." + CoresTerminal.RESET);
                            break;
                        }
                        CriarSalaCommand criarSalacommand = new CriarSalaCommand(facade, nomeSalaVirtual, psicologoLogado, paciente);
                        criarSalacommand.iniciarChamadaVirtual();
                        break;

                    case 2:
                        facade.exibirListaPacientes(psicologoLogado);
                        break;

                    case 3:
                        System.out.println("Cadastre um paciente:");
                        System.out.print("Nome: ");
                        nomePaciente = scanner.nextLine();
                        System.out.print("CPF: ");
                        cpfPaciente = scanner.nextLine();
                        PacienteObserver pacienteCadastrado = facade.cadastrarPaciente(nomePaciente, cpfPaciente, psicologoLogado);
                        System.out.println(CoresTerminal.VERDE + "Paciente cadastrado com sucesso." + CoresTerminal.RESET);
                        break;

                    case 0:
                        System.out.println(CoresTerminal.AMARELO+" Saindo do MindTalk..."+CoresTerminal.RESET);
                        break;

                    default:
                        System.out.println(CoresTerminal.VERMELHO + "Opção inválida. Por favor, escolha uma opção válida." + CoresTerminal.RESET);
                }
            } while (opcao != 0);
        }
        scanner.close();
    }
}