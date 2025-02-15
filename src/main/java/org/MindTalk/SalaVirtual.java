package org.MindTalk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class SalaVirtual {
    private boolean chamadaAtiva = true;
    private final HeartbeatAdapter wearable;
    private String nome;
    private PsicologoSubject psicologo;
    private PacienteObserver paciente;

    public SalaVirtual(String nome, PsicologoSubject psicologo, PacienteObserver paciente) {
        this.nome = nome;
        this.psicologo = psicologo;
        this.paciente = paciente;
        this.wearable = new HeartbeatAdapter("batimentos.txt");
    }

    public void gerarSalaVirtual(PsicologoSubject psicologo, PacienteObserver paciente) {
        String nomeArquivo = psicologo.getNome() + psicologo.getCrp() + "_" + paciente.getNome() + paciente.getCpf()+ ".txt";
        File arquivo = new File(nomeArquivo);

        try(FileWriter writer = new FileWriter(arquivo)){
            writer.flush();
           //Geracao do arquivo de sala virtual
        }catch (IOException e) {
            e.printStackTrace();
        }
        arquivo = new File("batimentos.txt");
        try(FileWriter writer = new FileWriter(arquivo)){
            writer.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarChamada() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(CoresTerminal.VERDE + " Videochamada iniciada..." + CoresTerminal.RESET);
        System.out.println(CoresTerminal.AZUL+" Pressione ENTER para encerrar."+CoresTerminal.RESET);

        Thread leitura = new Thread(() -> {
            while (chamadaAtiva) {
                int bpm = wearable.getHeartRate();
                System.out.println(CoresTerminal.VERMELHO+" Batimentos cardíacos: " + CoresTerminal.RESET + CoresTerminal.BOLD+ bpm+ CoresTerminal.RESET+ CoresTerminal.VERMELHO+" BPM" +CoresTerminal.RESET);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        leitura.start();
        scanner.nextLine();
        leitura.interrupt();
        chamadaAtiva = false;
        System.out.println(" Videochamada encerrada.");

        //erro de sleep interrupted
//        java.lang.InterruptedException: sleep interrupted
//        at java.base/java.lang.Thread.sleep0(Native Method)
//        at java.base/java.lang.Thread.sleep(Thread.java:509)
//        at org.MindTalk.HeartbeatAdapter.getHeartRate(HeartbeatAdapter.java:26)
//        at org.MindTalk.SalaVirtual.lambda$iniciarChamada$0(SalaVirtual.java:47)
//        at java.base/java.lang.Thread.run(Thread.java:1583)
    }

}
