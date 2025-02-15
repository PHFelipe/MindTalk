public class PsicologoFactory extends UsuarioFactory {
    @Override
    public Usuario criarUsuario(String nome, String email) {
        return new Psicologo(nome, email);
    }
}
