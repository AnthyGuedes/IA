package perceptron;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Ponto> amostras = new ArrayList<>();
        List<Integer> saidas = new ArrayList<>();

        String caminhoCSV = "amostras_saidas_problemaTimes.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoCSV))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] partes = linha.trim().split(";");
                if (partes.length != 3) continue;

                double x = Double.parseDouble(partes[0]);
                double y = Double.parseDouble(partes[1]);
                int saida = Integer.parseInt(partes[2]);

                amostras.add(new Ponto(x, y));
                saidas.add(saida);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        // instanciando o perceptron com os dados
        Perceptron p = new Perceptron(amostras, saidas, 0.1, 100, 0);
        p.treinar();
        p.exibirPesos();
    }
}
