package org.MindTalk;


import java.io.FileWriter;
import java.io.IOException;

public class PsicologoSubject extends Usuario implements Observer {
        private String crp;
        private PacienteObserver paciente;

        public PsicologoSubject(String nome, String crp) {
                super(nome);
                this.crp = crp;
        }

        public String getCrp() {
            return crp;
        }

        public void setPaciente(PacienteObserver paciente) {
            this.paciente = paciente;
        }

        @Override
        public void notificarPaciente(String mensagem) {
            System.out.println(CoresTerminal.AZUL+" enviando notificação ao paciente "+ this.paciente.getNome() + CoresTerminal.RESET);

            limparArquivo("notificacoes.txt");

            try(FileWriter fr = new FileWriter("notificacoes.txt",true)){
                fr.write("notificacao:"+ mensagem);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void limparArquivo(String arquivo){
            try(FileWriter fr = new FileWriter(arquivo,false)){
                fr.flush();
                //Sobrescrevendo o arquivo
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
}

