package org.MindTalk;

import java.io.FileNotFoundException;
import java.util.List;

class SistemaFacade {
    private DatabaseSingleton banco = DatabaseSingleton.getInstance();

    public PsicologoSubject cadastrarPsicologo(String nome, String crp) {
        FactoryUsuario factoryPsicologo = new FactoryPsicologo();
        PsicologoSubject psicologo = (PsicologoSubject) factoryPsicologo.criarUsuario(nome, crp);
        banco.salvarPsicologo("Psicologo: " + nome + " | CRP: " + crp);
        return psicologo;
    }

    public PacienteObserver cadastrarPaciente(String nome, String cpf,PsicologoSubject psicologo) {
        FactoryUsuario factoryPaciente = new FactoryPaciente();
        PacienteObserver paciente = (PacienteObserver) factoryPaciente.criarUsuario(nome, cpf);
        String dadosPaciente = "Paciente: " + nome + " | CPF: " + cpf;
        String dadosPsicologo = "Psicologo: " + psicologo.getNome() + " | CRP: " + psicologo.getCrp();
        banco.salvarPaciente(dadosPaciente, dadosPsicologo);
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


    public boolean pesquisarPsicologo(String nome, String crp) throws FileNotFoundException {
        return banco.pesquisarPsicologo(nome, crp);
    }

    public void exibirListaPacientes(PsicologoSubject psicologo) {
        try {
            List<String> pacientes = banco.getListaPacientes(psicologo);
            for (String paciente : pacientes) {
                System.out.println(CoresTerminal.BOLD + paciente + CoresTerminal.RESET);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public PacienteObserver pesquisarPaciente(String nome, String cpf, PsicologoSubject psicologo) throws FileNotFoundException {
        return banco.pesquisarPaciente(nome, cpf, psicologo);
    }

}

