package org.MindTalk;

import java.io.*;
import java.util.ArrayList;
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

    public void salvarPsicologo(String dados){
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(dados +" []" +"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void salvarPaciente(String dadosPaciente, String dadosPsicologo){
        List<String> linhas = new ArrayList<>();
        boolean encontrado = false;
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))){
            String linha;
            while((linha = reader.readLine()) != null){
                if(linha.contains(dadosPsicologo)){
                    encontrado = true;
                    linha = linha.replace(" []", " [" + dadosPaciente + "]");
                    if (linha.contains("[" + dadosPaciente + "]")) {
                        //System.out.println("Paciente já cadastrado.");
                    } else if (linha.contains("[")) {
                        linha = linha.replace("]", ", " + dadosPaciente + "]");
                    }
                }
                linhas.add(linha);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        if (encontrado) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String linha : linhas) {
                    writer.write(linha);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Psicólogo não encontrado.");
        }
    }

    public void salvarDados(String dados) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(dados + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getListaPacientes(PsicologoSubject psicologoSubject) throws FileNotFoundException {
        List<String> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if(linha.contains("Psicologo: " + psicologoSubject.getNome() + " | CRP: " + psicologoSubject.getCrp())) {
                    int inicio = linha.indexOf("[");
                    int fim = linha.indexOf("]");
                    if (inicio != -1 && fim != -1) {
                        String conteudo = linha.substring(inicio + 1, fim);

                        String[] listaPacientes = conteudo.split(", ");
                        for (String paciente : listaPacientes) {
                            pacientes.add(paciente.trim());
                        }
                    }
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return pacientes;
    }
    public PacienteObserver pesquisarPaciente(String nome, String cpf, PsicologoSubject psicologo) throws FileNotFoundException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String linha;
            FactoryPaciente factoryPaciente = new FactoryPaciente();
            while ((linha = reader.readLine()) != null) {
                if(linha.contains("Psicologo: " + psicologo.getNome() + " | CRP: " + psicologo.getCrp())) {
                    if (linha.contains("Paciente: " + nome + " | CPF: " + cpf)) {
                        return (PacienteObserver) factoryPaciente.criarUsuario(nome, cpf);
                    }
                }
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
