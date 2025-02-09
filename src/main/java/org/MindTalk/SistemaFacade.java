package org.MindTalk;

class SistemaFacade {
    private DatabaseSingleton banco = DatabaseSingleton.getInstance();

    public PsicologoSubject cadastrarPsicologo(String nome, String crp) {
        FactoryUsuario factoryPsicologo = new FactoryPsicologo();
        PsicologoSubject psicologo = (PsicologoSubject) factoryPsicologo.criarUsuario(nome, crp);
        banco.salvarDados("Psicologo: " + nome + " | CRP: " + crp);
        return psicologo;
    }

    public PacienteObserver cadastrarPaciente(String nome, String cpf) {
        FactoryUsuario factoryPaciente = new FactoryPaciente();
        PacienteObserver paciente = (PacienteObserver) factoryPaciente.criarUsuario(nome, cpf);
        banco.salvarDados("Paciente: " + nome + " | CPF: " + cpf);
        return paciente;
    }

    public void criarSalaVirtual(String nomeSalaVirtual, PsicologoSubject psicologo, PacienteObserver paciente) {
        WearableAdapter wearable = new WearableAdapter(new WearableDevice());
        SalaVirtual sala = new SalaVirtual(nomeSalaVirtual, psicologo, paciente, wearable);

        banco.salvarDados("Sala: " + nomeSalaVirtual + " | Psicologo: " + psicologo.getNome() + " | Paciente: " + paciente.getNome());

        psicologo.notificarPaciente("🎥 Sala Virtual: " + nomeSalaVirtual + " iniciou.");
        sala.iniciarAtendimento();
    }
}
