package org.MindTalk;

public class CriarSalaCommand implements Command{
    //Falta adicionar a fachada aqui!!
    private PsicologoSubject psicologo;
    private PacienteObserver paciente;
    private String nomeSala;

    public CriarSalaCommand( String nomeSala, PsicologoSubject psicologo, PacienteObserver paciente) {
        this.nomeSala = nomeSala;
        this.psicologo = psicologo;
        this.paciente = paciente;
    }
    @Override
    public void executar() {
        System.out.println("🎉 Criando Sala");
    }


}
