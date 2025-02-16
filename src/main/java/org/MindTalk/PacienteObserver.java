package org.MindTalk;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class PacienteObserver extends Usuario {
    private String cpf;

    public PacienteObserver(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public String receberNotificacao() {
        String arquivo = "notificacoes.txt";
        verificarOuCriarArquivo(arquivo);

        // Usando CountDownLatch para sincronizar a thread de leitura
        CountDownLatch latch = new CountDownLatch(1);
        String[] notificacao = {null};

        // Thread para a leitura
        Thread leitura = new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    if (linha.startsWith("notificacao:")) {
                        notificacao[0] = linha.substring(12).trim();
                        System.out.println(CoresTerminal.CIANO + notificacao[0] + CoresTerminal.RESET);
                        limparArquivo(arquivo);
                        break;
                    }
                    Thread.sleep(1000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown(); // Indica que a thread terminou
            }
        });

        leitura.start();

        // Esperar até que a thread de leitura termine
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return notificacao[0];  // Se a notificação não for encontrada, retornará null
    }



    private void verificarOuCriarArquivo(String arquivo) {
        File file = new File(arquivo);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("Arquivo de notificações criado: " + arquivo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void limparArquivo(String arquivo){
        try(FileWriter fr = new FileWriter(arquivo,false)){
            //Sobrescrevendo o arquivo
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void estadoWearable(String estado){
        String arquivo = "estadowearable.txt";
        try(FileWriter fr = new FileWriter(arquivo,false)){
            fr.write("Estado: " + estado);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getCpf() {
        return cpf;
    }

    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        MindTalkMenu menu = new MindTalkMenu();
        SistemaFacade facade = new SistemaFacade();
        PacienteObserver pacienteLogado = null;
        int opcao;

        do{
            menu.exibirMenuInicialPaciente();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println(CoresTerminal.AMARELO + "️Realizar Login" + CoresTerminal.RESET);
                    System.out.println("Digite seu Nome:");
                    String nomePaciente = scanner.nextLine();
                    System.out.println("Digite seu CPF:");
                    String cpfPaciente = scanner.nextLine();
                    pacienteLogado = facade.realizarLoginPaciente(nomePaciente, cpfPaciente);
                    if(pacienteLogado != null) {
                        System.out.println(CoresTerminal.VERDE + "Login efetuado com sucesso." + CoresTerminal.RESET);
                        System.out.println("Paciente logado: " +CoresTerminal.BOLD+ pacienteLogado.getNome() + CoresTerminal.RESET);
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
        }while(opcao != 0);

        if (opcao == 100) {
            do {
                String notificacao = pacienteLogado.receberNotificacao();
                if (notificacao != null) {
                    menu.exibirMenuPrincipalPaciente(0);
                    opcao = scanner.nextInt();
                    scanner.nextLine();

                    switch (opcao) {
                        case 1:
                            notificacao = null;
                            System.out.println(CoresTerminal.AMARELO + "Ao entrar na sala virtual seu wearable sera ligado." + CoresTerminal.RESET);
                            pacienteLogado.estadoWearable("Ligado");
                            System.out.println("Atendimento em execucao...");
                            while(notificacao == null){
                                notificacao = pacienteLogado.receberNotificacao();
                                Thread.sleep(500);
                            }
                             pacienteLogado.estadoWearable("Desligado");
                            break;
                        case 0:
                            System.out.println(CoresTerminal.AMARELO + " Saindo do MindTalk..." + CoresTerminal.RESET);
                            break;
                        default:
                            System.out.println(CoresTerminal.BG_VERMELHO + " Opção inválida. Por favor, escolha uma opção válida." + CoresTerminal.RESET);
                            break;
                    }
                } else if (notificacao == null) {
                    menu.exibirMenuPrincipalPaciente(1);
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcao) {
                        case 1:
                            break;
                        case 0:
                            System.out.println(CoresTerminal.AMARELO + " Saindo do MindTalk..." + CoresTerminal.RESET);
                            break;
                        default:
                            System.out.println(CoresTerminal.BG_VERMELHO + " Opção inválida. Por favor, escolha uma opção válida." + CoresTerminal.RESET);
                            break;
                    }
                }
            }while(opcao != 0);


        }
    }
}
