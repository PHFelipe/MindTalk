import java.util.Scanner;

public class SalaVirtual {
    private boolean chamadaAtiva = true;
    private final HeartbeatAdapter wearable;

    public SalaVirtual(HeartbeatAdapter wearable) {
        this.wearable = wearable;
    }

    public void iniciarChamada() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("📞 Videochamada iniciada...");
        System.out.println("🎥 Pressione ENTER para encerrar.");

        Thread leitura = new Thread(() -> {
            while (chamadaAtiva) {
                int bpm = wearable.getHeartRate();
                System.out.println("💓 Batimentos Cardíacos: " + bpm + " BPM");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        leitura.start();
        scanner.nextLine(); // Aguarda o psicólogo encerrar
        chamadaAtiva = false;
        System.out.println("🚪 Videochamada encerrada.");
        scanner.close();
    }
}
