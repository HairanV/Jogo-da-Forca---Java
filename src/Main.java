import model.Jogo;
import model.Jogador;
import repository.LeitorPalavras;
import view.InterfaceCLI;

public class Main {

    public static void main(String[] args) {

        String caminhoArquivo = "src/palavras_jogo_forca.txt";

        InterfaceCLI ui = new InterfaceCLI();
        LeitorPalavras leitor = new LeitorPalavras(caminhoArquivo);

        ui.exibirBoasVindas();

        String nome = ui.pedirNomeJogador();
        Jogador jogador = new Jogador(nome);

        boolean continuarJogando = true;

        while (continuarJogando) {

            String palavra = leitor.sortearPalavra();
            Jogo jogo = new Jogo(palavra, jogador);

            while (!jogo.isJogoEncerrado()) {
                ui.exibirEstadoJogo(jogo);

                char letra = ui.pedirLetra();

                if (jogo.letraJaTentada(letra)) {
                    ui.exibirLetraJaTentada(letra);
                    continue;
                }

                boolean acertou = jogo.tentarLetra(letra);
                ui.exibirFeedbackLetra(letra, acertou);
            }

            if (jogo.verificarVitoria()) {
                ui.exibirVitoria(jogo);
            } else {
                ui.exibirDerrota(jogo);
            }

            continuarJogando = ui.perguntarNovoJogo();
        }

        ui.exibirEncerramento();
        ui.fechar();
    }
}