package org.MindTalk;

public class PacienteObserver extends Usuario{
    private String cpf;

    public PacienteObserver(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    public void receberNotificacao(String mensagem) {
        System.out.println("📢 Paciente " + nome + " recebeu notificação: " + mensagem);
    }

    public String getCpf() {
        return cpf;
    }
}
