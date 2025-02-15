package org.MindTalk;
import java.io.*;
import java.util.Random;

public class HeartBeatSimulator {
    public static void main(String[] args) {
        Random random = new Random();
        File file = new File("batimentos.txt");


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            System.out.println("Simulador de Batimentos Iniciado...");

            for (int i = 0; i < 30; i++) {
                int heartbeat = 60 + random.nextInt(40);
                writer.write(heartbeat + "\n");
                writer.flush();
                System.out.println("Escrevendo BPM: " + heartbeat);
                Thread.sleep(1000);
            }
            System.out.println("Simulador Finalizado.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}