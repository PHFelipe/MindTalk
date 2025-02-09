package org.MindTalk;


public class PsicologoSubject extends Usuario {
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


        public void notificarPaciente(String mensagem) {
            System.out.println("\uD83D\uDCE2 enviando notificação ao paciente "+ this.paciente.getNome());
            this.paciente.receberNotificacao(mensagem);
        }
}

