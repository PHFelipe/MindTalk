package org.MindTalk;

import java.io.FileNotFoundException;

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

        psicologo.notificarPaciente("🎥Sua consulta na " + nomeSalaVirtual + " iniciou, seja bem-vindo(a).");
        sala.iniciarAtendimento();
    }

    public PsicologoSubject realizarLogin(String Nome, String CRP) throws FileNotFoundException {
        if(banco.pesquisarPsicologo(Nome, CRP)){
            FactoryUsuario factoryPsicologo = new FactoryPsicologo();
            return (PsicologoSubject) factoryPsicologo.criarUsuario(Nome, CRP);
        }else{
            return null;
        }
    }



    public void associarPaciente(PsicologoSubject psicologo, PacienteObserver paciente) {
        psicologo.setPaciente(paciente);
        banco.salvarDados("Psicologo: " + psicologo.getNome() + " | Paciente: " + paciente.getNome());
    }

    public boolean pesquisarPsicologo(String nome, String crp) throws FileNotFoundException {
        return banco.pesquisarPsicologo(nome, crp);
    }
}
