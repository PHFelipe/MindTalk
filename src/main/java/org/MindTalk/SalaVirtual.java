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
        File arquivo = new File("batimentos.txt");
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
                    for (int i =0; i < 10 && chamadaAtiva; i++) {
                        Thread.sleep(100);//Total = 1 Segundo
                    }
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        leitura.start();
        scanner.nextLine();
        leitura.interrupt();
        chamadaAtiva = false;
        try{
            leitura.join();
        }catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(CoresTerminal.BOLD+" Videochamada encerrada."+ CoresTerminal.RESET);

    }

}
