package org.MindTalk;

import java.io.*;
import java.util.List;

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

    public boolean pesquisarPsicologo(String nome, String crp) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.contains("Psicologo: " + nome + " | CRP: " + crp)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
