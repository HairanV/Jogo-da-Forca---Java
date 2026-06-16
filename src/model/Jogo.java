package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contém toda a lógica e estado do Jogo da Forca.
 */
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

    /**
     * Tenta adivinhar uma letra. Retorna true se a letra está na palavra.
     */
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

    /**
     * Verifica se a letra já foi tentada anteriormente.
     */
    public boolean letraJaTentada(char letra) {
        letra = Character.toUpperCase(letra);
        return letrasCorretas.contains(letra) || letrasErradas.contains(letra);
    }

    /**
     * Retorna a palavra mascarada com _ para letras não descobertas.
     */
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

    /**
     * Verifica se o jogador venceu (todas as letras descobertas).
     */
    public boolean verificarVitoria() {
        for (char c : palavraSecreta.toCharArray()) {
            if (!letrasCorretas.contains(c)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Verifica se o jogador perdeu (sem tentativas restantes).
     */
    public boolean verificarDerrota() {
        return tentativasRestantes <= 0;
    }

    public boolean isJogoEncerrado() {
        return verificarVitoria() || verificarDerrota();
    }

    // Getters
    public String getPalavraSecreta() { return palavraSecreta; }
    public Jogador getJogador() { return jogador; }
    public List<Character> getLetrasCorretas() { return letrasCorretas; }
    public List<Character> getLetrasErradas() { return letrasErradas; }
    public int getTentativasRestantes() { return tentativasRestantes; }
    public int getMaxTentativas() { return MAX_TENTATIVAS; }
    public int getTentativasUsadas() { return MAX_TENTATIVAS - tentativasRestantes; }
}
