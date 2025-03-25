import javax.swing.JOptionPane;
import busca.BuscaLargura;
import busca.BuscaProfundidade;
import busca.MostraStatusConsole;
import busca.Nodo;

public class MainLabirinto {
    public static void main(String[] args) {
        try {
            // Configuração inicial
            int tamanho = Integer.parseInt(JOptionPane.showInputDialog("Tamanho do labirinto (NxN):"));
            int obstaculos = Integer.parseInt(JOptionPane.showInputDialog("Número de obstáculos:"));

            // Criação do labirinto
            Labirinto labBase = new Labirinto(tamanho, obstaculos);
            System.out.println("Labirinto gerado:");
            labBase.exibirLabirinto();

            // Preparação para busca
            LabirintoIntegrado estadoInicial = new LabirintoIntegrado(labBase, "Estado Inicial");

            // Execução das buscas
            System.out.println("\n--- Busca em Profundidade ---");
            Nodo solucaoProfundidade = new BuscaProfundidade(new MostraStatusConsole()).busca(estadoInicial);
            mostrarResultado("Profundidade", solucaoProfundidade);

            System.out.println("\n--- Busca em Largura ---");
            Nodo solucaoLargura = new BuscaLargura(new MostraStatusConsole()).busca(estadoInicial);
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
