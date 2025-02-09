package org.MindTalk;

public class FactoryPaciente implements FactoryUsuario {
    @Override
    public Usuario criarUsuario(String nome, String cpf) {
        return new PacienteObserver(nome, cpf);
    }
}