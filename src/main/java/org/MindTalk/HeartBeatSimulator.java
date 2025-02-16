package org.MindTalk;
import java.io.*;
import java.util.Random;

public class HeartBeatSimulator {

    public static String estadoWearable(){
        String arquivo = "estadowearable.txt";

        // Verifica existência do arquivo, se não houver cria um novo
        try {
            File file = new File(arquivo);
            if (!file.exists()) {
                file.createNewFile();
                // Inicializa o arquivo com o estado fornecido
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("Estado: Desligado");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String linha = reader.readLine();
            if (linha != null && linha.startsWith("Estado: ")) {
                return linha.substring(8);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        File file = new File("batimentos.txt");
        while (true) {
            if(estadoWearable().equals("Desligado")){
                System.out.println(CoresTerminal.BOLD + "Wearable desligado, aguardando ser ligado..." + CoresTerminal.RESET);
            }
            while (estadoWearable().equals("Desligado")) {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(CoresTerminal.VERDE + "Wearable ligado, Simulador de Batimentos Iniciado..." + CoresTerminal.RESET);

            while (estadoWearable().equals("Ligado")) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    int heartbeat = 60 + random.nextInt(40);
                    writer.write(heartbeat + "\n");
                    writer.flush();
                    System.out.println("Escrevendo BPM: " + heartbeat);
                    Thread.sleep(1000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                Thread.sleep(500);
            }
            System.out.println(CoresTerminal.VERMELHO + "Wearable desligado, Simulador de Batimentos Encerrado..." + CoresTerminal.RESET);

        }
    }
}

