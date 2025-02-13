package org.MindTalk;
import org.MindTalk.CoresTerminal;


public class MindTalkMenu {
    public static void exibirMenuInicial() {
        System.out.println("\n===== MindTalk - Menu Inicial =====");
        System.out.println(CoresTerminal.AZUL + "1 - Cadastrar Psicólogo" );
        System.out.println("2 - Realizar Login" );
        System.out.println("0 - Sair" + CoresTerminal.RESET);
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuPrincipal(){
        System.out.println("\n===== MindTalk - Menu Principal =====");
        System.out.println(CoresTerminal.AZUL + "1 - Iniciar Sala Virtual");
        System.out.println("2 - Listar Pacientes");
        System.out.println("0 - Sair" + CoresTerminal.RESET);
        System.out.print("Escolha uma opção: ");
    }

}