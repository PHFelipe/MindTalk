package org.MindTalk;

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
                lastLine = lastLine.trim();
                if (!lastLine.isEmpty()) {
                    heartRate = Integer.parseInt(lastLine);
                    try {
                        for (int i =0; i < 20; i++) {
                            Thread.sleep(100);//Total = 2 Segundos
                        }
                    } catch (InterruptedException e) {
                    }
                    clearFile();
                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return heartRate;
    }

    private void clearFile() {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            writer.write("");
        } catch (IOException e) {
            System.out.println(CoresTerminal.VERMELHO+" Erro ao limpar o arquivo: " + e.getMessage() + CoresTerminal.RESET);
        }
    }
}