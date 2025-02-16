package org.MindTalk;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CriarSalaCommand implements Command{
    private SistemaFacade facade;
    private PsicologoSubject psicologo;
    private PacienteObserver paciente;
    private String nomeSala;

    public CriarSalaCommand( SistemaFacade facade, String nomeSala, PsicologoSubject psicologo, PacienteObserver paciente) {
        this.facade = facade;
        this.nomeSala = nomeSala;
        this.psicologo = psicologo;
        this.paciente = paciente;
        this.psicologo.setPaciente(paciente);
    }

    @Override
    public void iniciarChamadaVirtual() {
        System.out.println(" Criando Sala Virtual...");
        facade.iniciarChamadaVirtual(nomeSala, psicologo, paciente);
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}