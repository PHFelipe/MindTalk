package org.MindTalk;

public class FactoryPsicologo implements FactoryUsuario {
    @Override
    public Usuario criarUsuario(String nome, String CRP) {
        return new PsicologoSubject(nome, CRP);
    }
}