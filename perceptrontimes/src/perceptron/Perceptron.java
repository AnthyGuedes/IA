package perceptron;

import java.util.List;

public class Perceptron {
    private List<Ponto> amostras;
    private List<Integer> saidas;
    private double taxaAprendizado;
    private int geracoes;
    private int limiar;

    private double[] pesos;

    public Perceptron(List<Ponto> amostras, List<Integer> saidas, double taxaAprendizado, int geracoes, int limiar) {
        this.amostras = amostras;
        this.saidas = saidas;
        this.taxaAprendizado = taxaAprendizado;
        this.geracoes = geracoes;
        this.limiar = limiar;
        this.pesos = new double[3]; // x, y, bias
    }

    private int funcaoAtivacaoSignal(double soma) {
        return soma >= 0 ? 1 : -1;
    }

    public void treinar() {
        for (int g = 0; g < geracoes; g++) {
            for (int i = 0; i < amostras.size(); i++) {
                Ponto atual = amostras.get(i);
                int esperado = saidas.get(i);

                double soma = atual.x * pesos[0] + atual.y * pesos[1] + 1 * pesos[2]; // bias
                int saida = funcaoAtivacaoSignal(soma);

                int erro = esperado - saida;

                pesos[0] += taxaAprendizado * erro * atual.x;
                pesos[1] += taxaAprendizado * erro * atual.y;
                pesos[2] += taxaAprendizado * erro * 1; // bias
            }
        }
    }

    public void exibirPesos() {
        System.out.println("Pesos finais aprendidos pelo Perceptron:");
        System.out.printf("Peso x: %.4f\n", pesos[0]);
        System.out.printf("Peso y: %.4f\n", pesos[1]);
        System.out.printf("Peso bias: %.4f\n", pesos[2]);
    }
}