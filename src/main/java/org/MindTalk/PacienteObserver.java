package org.MindTalk;

import java.io.*;
import java.net.Socket;

public class PacienteObserver extends Usuario {
    private String cpf;

    public PacienteObserver(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public void receberNotificacao() {

        Thread leitura = new Thread(() -> {
            String arquivo = "notificacoes.txt";
            boolean notificacaoRecebida = false;

            //Verificacao se o arquivo existe antes de realizar leitura
            File file = new File(arquivo);
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }

            //Inicio da parte de leitura
            while (!notificacaoRecebida) {
                try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                    String linha;
                    while ((linha = reader.readLine()) != null) {
                        if (linha.startsWith("notificacao:")){
                            notificacaoRecebida = true;
                            System.out.println(CoresTerminal.CIANO + linha.substring(12).trim() + CoresTerminal.RESET);
                            limparArquivo(arquivo);
                            break;
                        }
                        Thread.sleep(1000);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        leitura.start();
    }

    public void limparArquivo(String arquivo){
        try(FileWriter fr = new FileWriter(arquivo,false)){
            //Sobrescrevendo o arquivo
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void iniciarWearable() throws IOException {
        Socket socket = new Socket("localhost", 12345);
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        printStream.println(1);// 1 = iniciar Wearable
        System.out.println("Conectado ao wearable.");
    }

    public String getCpf() {
        return cpf;
    }

    public static void main(String[] args) {
        PacienteObserver paciente = new PacienteObserver("Joao", "123456789");
        paciente.receberNotificacao();
    }
}