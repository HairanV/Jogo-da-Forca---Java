package model;

import java.util.ArrayList;
import java.util.List;

public class Jogo {

    private static final int MAX_TENTATIVAS = 6;

    private String palavraSecreta;
    private Jogador jogador;
    private List<Character> letrasCorretas;
    private List<Character> letrasErradas;
    private int tentativasRestantes;
    private boolean jogoEncerrado;

    public Jogo(String palavraSecreta, Jogador jogador) {
        this.palavraSecreta = palavraSecreta.toUpperCase();
        this.jogador = jogador;
        this.letrasCorretas = new ArrayList<>();
        this.letrasErradas = new ArrayList<>();
        this.tentativasRestantes = MAX_TENTATIVAS;
        this.jogoEncerrado = false;
    }

    public boolean tentarLetra(char letra) {
        letra = Character.toUpperCase(letra);

        // Letra já tentada — ignora
        if (letrasCorretas.contains(letra) || letrasErradas.contains(letra)) {
            return false;
        }

        if (palavraSecreta.indexOf(letra) >= 0) {
            letrasCorretas.add(letra);
            return true;
        } else {
            letrasErradas.add(letra);
            tentativasRestantes--;
            return false;
        }
    }

    public boolean letraJaTentada(char letra) {
        letra = Character.toUpperCase(letra);
        return letrasCorretas.contains(letra) || letrasErradas.contains(letra);
    }

    public String getPalavraVisivel() {
        StringBuilder sb = new StringBuilder();
        for (char c : palavraSecreta.toCharArray()) {
            if (letrasCorretas.contains(c)) {
                sb.append(c);
            } else {
                sb.append('_');
            }
            sb.append(' ');
        }
        return sb.toString().trim();
    }

    public boolean verificarVitoria() {
        for (char c : palavraSecreta.toCharArray()) {
            if (!letrasCorretas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public boolean verificarDerrota() {
        return tentativasRestantes <= 0;
    }

    public boolean isJogoEncerrado() {
        return verificarVitoria() || verificarDerrota();
    }

    public String getPalavraSecreta() { return palavraSecreta; }
    public Jogador getJogador() { return jogador; }
    public List<Character> getLetrasCorretas() { return letrasCorretas; }
    public List<Character> getLetrasErradas() { return letrasErradas; }
    public int getTentativasRestantes() { return tentativasRestantes; }
    public int getMaxTentativas() { return MAX_TENTATIVAS; }
    public int getTentativasUsadas() { return MAX_TENTATIVAS - tentativasRestantes; }
}
