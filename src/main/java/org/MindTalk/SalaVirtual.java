package org.MindTalk;

class SalaVirtual {
    private String nome;
    private Psicologo psicologo;
    private Paciente paciente;
    private MonitorCardiaco monitor;

    public SalaVirtual(String nome, Psicologo psicologo, Paciente paciente, MonitorCardiaco monitor) {
        this.nome = nome;
        this.psicologo = psicologo;
        this.paciente = paciente;
        this.monitor = monitor;
    }

    public void iniciarAtendimento() {
        System.out.println("\n🎥 Sala Virtual: " + nome + " iniciou.");
        System.out.println("👨‍⚕️ Psicólogo: " + psicologo.getNome() + " | 🏥 Paciente: " + paciente.getNome());

        // Simular monitoramento cardíaco por 10 segundos
        for (int i = 0; i < 10; i++) {
            System.out.println("💓 Batimentos cardíacos do paciente: " + monitor.getBatimento() + " BPM");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("📌 Atendimento encerrado.");
    }
}
