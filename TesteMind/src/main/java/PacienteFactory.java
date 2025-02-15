public class PacienteFactory extends UsuarioFactory {
    @Override
    public Usuario criarUsuario(String nome, String email) {
        return new Paciente(nome, email);
    }
}
