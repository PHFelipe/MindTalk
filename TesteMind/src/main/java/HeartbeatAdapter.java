import java.io.*;

public class HeartbeatAdapter {
    private final String filePath;

    public HeartbeatAdapter(String filePath) {
        this.filePath = filePath;
    }

    public int getHeartRate() {
        int heartRate = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String lastLine = null, line;

            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }

            if (lastLine != null) {
                lastLine = lastLine.trim(); // Remover espaços extras
                if (!lastLine.isEmpty()) { // Garantir que não está vazio antes de converter
                    heartRate = Integer.parseInt(lastLine);
                    System.out.println("💓 Batimentos cardíacos: " + heartRate + " BPM");

                    // Espera antes de limpar o arquivo para exibição
                    Thread.sleep(2000);
                    clearFile();
                }
            }

        } catch (IOException | NumberFormatException | InterruptedException e) {
            e.printStackTrace();
        }

        return heartRate;
    }

    private void clearFile() {
        try (FileWriter writer = new FileWriter(filePath, false)) { // false para sobrescrever
            writer.write(""); // Escreve um arquivo vazio
            //System.out.println("🗑️ Arquivo 'heart_rate.txt' limpo após leitura!");
        } catch (IOException e) {
            System.out.println("❌ Erro ao limpar o arquivo: " + e.getMessage());
        }
    }
}
