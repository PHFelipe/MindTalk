package org.MindTalk;

class SistemaFacade {
    private Notificador notificador = new Notificador();
    private DatabaseSingleton banco = DatabaseSingleton.getInstance();

    public Psicologo cadastrarPsicologo(String nome, String crp) {
        Psicologo psicologo = new Psicologo(nome, crp);
        banco.salvarDados("Psicologo: " + nome + " | CRP: " + crp);
        return psicologo;
    }

    public Paciente cadastrarPaciente(String nome, String cpf) {
        Paciente paciente = new Paciente(nome, cpf);
        banco.salvarDados("Paciente: " + nome + " | CPF: " + cpf);
        notificador.adicionarPaciente(new PacienteObserver(nome));
        return paciente;
    }

    public void criarSalaVirtual(String nome, Psicologo psicologo, Paciente paciente) {
        WearableAdapter wearable = new WearableAdapter(new WearableDevice());
        SalaVirtual sala = new SalaVirtual(nome, psicologo, paciente, wearable);

        banco.salvarDados("Sala: " + nome + " | Psicologo: " + psicologo.getNome() + " | Paciente: " + paciente.getNome());

        notificador.notificarTodos("Nova Sala Virtual criada: " + nome);
        sala.iniciarAtendimento();
    }
}
