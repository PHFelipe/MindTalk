package org.MindTalk;

import java.io.FileWriter;
import java.io.IOException;

class DatabaseSingleton {
    private static DatabaseSingleton instance;
    private static final String FILE_PATH = "banco_de_dados.txt";

    private DatabaseSingleton() {}

    public static DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton();
        }
        return instance;
    }

    public void salvarDados(String dados) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(dados + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
