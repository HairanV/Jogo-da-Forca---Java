package view;

import model.Jogo;
import java.util.Scanner;

public class InterfaceCLI {

    private Scanner scanner;

    public InterfaceCLI() {
        this.scanner = new Scanner(System.in);
    }

    public void exibirBoasVindas() {
        limparTela();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║         JOGO DA FORCA - Java         ║");
        System.out.println("║   UNOESC - Engenharia da Computação  ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
    }

    public String pedirNomeJogador() {
        System.out.print("Digite seu nome: ");
        return scanner.nextLine().trim();
    }

    public void exibirEstadoJogo(Jogo jogo) {
        limparTela();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           JOGO DA FORCA              ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();

        exibirForca(jogo.getTentativasUsadas());

        System.out.println();
        System.out.println("Jogador: " + jogo.getJogador().getNome());
        System.out.println("Tamanho da palavra: " + jogo.getPalavraSecreta().length() + " letras");
        System.out.println();
        System.out.println("Palavra: " + jogo.getPalavraVisivel());
        System.out.println();

        if (!jogo.getLetrasErradas().isEmpty()) {
            System.out.print("Letras erradas: ");
            for (char c : jogo.getLetrasErradas()) {
                System.out.print(c + " ");
            }
            System.out.println();
        } else {
            System.out.println("Letras erradas: (nenhuma ainda)");
        }

        System.out.println();
        System.out.println("Tentativas usadas : " + jogo.getTentativasUsadas() + " / " + jogo.getMaxTentativas());
        System.out.println("Tentativas restantes: " + jogo.getTentativasRestantes());
        System.out.println("─────────────────────────────────────");
    }

    public char pedirLetra() {
        while (true) {
            System.out.print("Digite uma letra: ");
            String entrada = scanner.nextLine().trim().toUpperCase();
            if (entrada.length() == 1 && Character.isLetter(entrada.charAt(0))) {
                return entrada.charAt(0);
            }
            System.out.println("⚠ Entrada inválida! Digite apenas uma letra.");
        }
    }

    public void exibirLetraJaTentada(char letra) {
        System.out.println("⚠ A letra '" + letra + "' já foi tentada! Tente outra.");
        pausar();
    }

    public void exibirFeedbackLetra(char letra, boolean acertou) {
        if (acertou) {
            System.out.println("✔ A letra '" + letra + "' está na palavra!");
        } else {
            System.out.println("✘ A letra '" + letra + "' não está na palavra.");
        }
        pausar();
    }

    public void exibirVitoria(Jogo jogo) {
        limparTela();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           🎉 VOCÊ VENCEU! 🎉         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.println("Parabéns, " + jogo.getJogador().getNome() + "!");
        System.out.println("A palavra era: " + jogo.getPalavraSecreta());
        System.out.println("Você usou " + jogo.getTentativasUsadas() + " tentativa(s).");
        System.out.println();
    }

    public void exibirDerrota(Jogo jogo) {
        limparTela();
        exibirForca(jogo.getMaxTentativas());
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           ☠ VOCÊ PERDEU! ☠           ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println();
        System.out.println("Que pena, " + jogo.getJogador().getNome() + "...");
        System.out.println("A palavra era: " + jogo.getPalavraSecreta());
        System.out.println();
    }

    public boolean perguntarNovoJogo() {
        while (true) {
            System.out.print("Deseja jogar novamente? (S/N): ");
            String resposta = scanner.nextLine().trim().toUpperCase();
            if (resposta.equals("S")) return true;
            if (resposta.equals("N")) return false;
            System.out.println("⚠ Digite S para sim ou N para não.");
        }
    }

    public void exibirEncerramento() {
        System.out.println();
        System.out.println("Obrigado por jogar! Até a próxima. 👋");
    }

    private void exibirForca(int erros) {
        String[] forca = {
                "  +---+  ",
                "  |   |  ",
                "      |  ",
                "      |  ",
                "      |  ",
                "      |  ",
                "=========",
        };

        String[] forcaComCorpo = switch (erros) {
            case 0 -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "      |  ",
                    "      |  ",
                    "      |  ",
                    "      |  ",
                    "========="
            };
            case 1 -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "  O   |  ",
                    "      |  ",
                    "      |  ",
                    "      |  ",
                    "========="
            };
            case 2 -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "  O   |  ",
                    "  |   |  ",
                    "      |  ",
                    "      |  ",
                    "========="
            };
            case 3 -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "  O   |  ",
                    " /|   |  ",
                    "      |  ",
                    "      |  ",
                    "========="
            };
            case 4 -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "  O   |  ",
                    " /|\\  |  ",
                    "      |  ",
                    "      |  ",
                    "========="
            };
            case 5 -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "  O   |  ",
                    " /|\\  |  ",
                    " /    |  ",
                    "      |  ",
                    "========="
            };
            default -> new String[]{
                    "  +---+  ",
                    "  |   |  ",
                    "  O   |  ",
                    " /|\\  |  ",
                    " / \\  |  ",
                    "      |  ",
                    "========="
            };
        };

        for (String linha : forcaComCorpo) {
            System.out.println(linha);
        }
    }

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pausar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void fechar() {
        scanner.close();
    }
}