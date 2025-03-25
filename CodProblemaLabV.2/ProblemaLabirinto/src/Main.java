import javax.swing.JOptionPane;

import busca.BuscaProfundidade;
import busca.MostrarStatusConsole;
import busca.Nodo;

 class Main {
    public static void main(String[] args) {
        try {
            // Configuração inicial
            int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Tamanho do labirinto (NxN):"));
            int obstaculos = Integer.parseInt(JOptionPane.showInputDialog("Número de obstáculos:"));

            // Criação do labirinto
            Labirinto labBase = new Labirinto();
            System.out.println("Labirinto gerado:");
            labBase.exibirLabirinto();

            // Preparação para busca
            LabirintoIntegrado estadoInicial = new LabirintoIntegrado(labBase, "Estado Inicial") {
                @Override
                public int h() {
                    return 0;
                }

                @Override
                public int custo() {
                    return 0;
                }

                @Override
                public String getDescricao() {
                    return "";
                }
            };

            // Execução das buscas
            System.out.println("\n--- Busca em Profundidade ---");
            Nodo solucaoProfundidade = new BuscaProfundidade(new MostrarStatusConsole()).busca(estadoInicial);
            mostrarResultado("Profundidade", solucaoProfundidade);

            System.out.println("\n--- Busca em Largura ---");
            Nodo solucaoLargura = new busca.BuscaLargura(new MostrarStatusConsole()).busca(estadoInicial);
            mostrarResultado("Largura", solucaoLargura);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
    }

    private static void mostrarResultado(String tipo, Nodo resultado) {
        System.out.println("\nResultado (" + tipo + "):");
        if (resultado == null) {
            System.out.println("Nenhuma solução encontrada!");
        } else {
            System.out.println("Solução em " + resultado.getProfundidade() + " passos:");
            System.out.println(resultado.montaCaminho());
        }
    }
}
