import java.io.*;
import java.util.Random;

public class HeartbeatSimulator {
    public static void main(String[] args) {
        Random random = new Random();
        File file = new File("batimentos.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            System.out.println("🔥 Simulador de Batimentos Iniciado...");

            for (int i = 0; i < 30; i++) { // Simula por 30 segundos
                int heartbeat = 60 + random.nextInt(40); // Gera valores entre 60 e 100 BPM
                writer.write(heartbeat + "\n");
                writer.flush(); // Força gravação imediata
                System.out.println("💓 Escrevendo BPM: " + heartbeat);
                Thread.sleep(1000);
            }
            System.out.println("✅ Simulador Finalizado.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
