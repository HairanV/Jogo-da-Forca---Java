package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LeitorPalavras {

    private String caminhoArquivo;
    private List<String> palavras;
    private Random random;

    public LeitorPalavras(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        this.palavras = new ArrayList<>();
        this.random = new Random();
        carregarPalavras();
    }

    private void carregarPalavras() {
        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    palavras.add(linha.toUpperCase());
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar arquivo de palavras: " + e.getMessage());
            System.err.println("Verifique se o arquivo existe em: " + caminhoArquivo);
            // Palavras padrão caso o arquivo não seja encontrado
            palavras.add("JAVA");
            palavras.add("FORCA");
            palavras.add("PROGRAMACAO");
        }
    }

    public String sortearPalavra() {
        if (palavras.isEmpty()) {
            return "JAVA";
        }
        int indice = random.nextInt(palavras.size());
        return palavras.get(indice);
    }

    public List<String> getPalavras() {
        return palavras;
    }

    public int getTotalPalavras() {
        return palavras.size();
    }
}
