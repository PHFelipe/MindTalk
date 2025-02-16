package org.MindTalk;
import org.MindTalk.CoresTerminal;

import java.sql.SQLOutput;


public class MindTalkMenu {
    public static void exibirMenuInicialPsicologo() {
        System.out.println("\n===== MindTalk - Menu Inicial =====");
        System.out.println(CoresTerminal.AZUL + "1 - Cadastrar Psicólogo" );
        System.out.println("2 - Realizar Login" );
        System.out.println("0 - Sair" + CoresTerminal.RESET);
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuInicialPaciente() {
        System.out.println("\n===== MindTalk - Menu Inicial =====");
        System.out.println(CoresTerminal.AZUL + "1 - Realizar Login" );
        System.out.println("0 - Sair" + CoresTerminal.RESET);
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuPrincipalPsicologo(){
        System.out.println("\n===== MindTalk - Menu Principal =====");
        System.out.println(CoresTerminal.AZUL + "1 - Iniciar Sala Virtual");
        System.out.println("2 - Listar Pacientes");
        System.out.println("3 - Cadastrar Paciente");
        System.out.println("0 - Sair" + CoresTerminal.RESET);
        System.out.print("Escolha uma opção: ");
    }

    public static void exibirMenuPrincipalPaciente(int opcao){
        System.out.println("\n===== MindTalk - Menu Principal =====");
        if(opcao == 1){
            System.out.println(CoresTerminal.VERMELHO+ "Voce nao possui atendimento em andamento no momento. Atualize o sistema." + CoresTerminal.RESET);
            System.out.println(CoresTerminal.AZUL + "1 - Atualizar Sistema");
        }else {
            System.out.println(CoresTerminal.AZUL + "1 - Entrar na Sala Virtual");
        }
        System.out.println("0 - Sair" + CoresTerminal.RESET);
        System.out.print("Escolha uma opção: ");
    }

}