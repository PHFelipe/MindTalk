import java.util.*;

public class MindTalkFacade {
    private final List<Psicologo> psicologos = new ArrayList<>();
    private final Map<Psicologo, List<Paciente>> pacientesPorPsicologo = new HashMap<>();
    private final HeartbeatAdapter wearable = new HeartbeatAdapter("batimentos.txt");

    public void cadastrarPsicologo(String nome, String email) {
        Psicologo novoPsicologo = new Psicologo(nome, email);
        psicologos.add(novoPsicologo);
        pacientesPorPsicologo.put(novoPsicologo, new ArrayList<>());
        System.out.println("✅ Psicólogo cadastrado com sucesso!");
    }

    public void cadastrarPaciente(String nome, String email, Psicologo psicologo) {
        List<Paciente> pacientes = pacientesPorPsicologo.get(psicologo);
        for (Paciente p : pacientes) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                System.out.println("⚠️ Paciente já cadastrado para este psicólogo!");
                return;
            }
        }
        Paciente novoPaciente = new Paciente(nome, email);
        pacientes.add(novoPaciente);
        System.out.println("✅ Paciente cadastrado com sucesso!");
    }

    public void iniciarVideoChamada(Psicologo psicologo, Paciente paciente) {
        System.out.println("📢 Notificando " + paciente.getNome() + " sobre a videochamada...");
        new SalaVirtual(wearable).iniciarChamada();
    }

    public List<Psicologo> getPsicologos() {
        return psicologos;
    }

    public List<Paciente> getPacientesDoPsicologo(Psicologo psicologo) {
        return pacientesPorPsicologo.getOrDefault(psicologo, new ArrayList<>());
    }
}
