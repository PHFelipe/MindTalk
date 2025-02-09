package org.MindTalk;

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
    }
    @Override
    public void iniciarSalaVirtual() {
        System.out.println("🎉 Criando Sala Virtual...");
        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        facade.criarSalaVirtual(nomeSala, psicologo, paciente);
    }


}
