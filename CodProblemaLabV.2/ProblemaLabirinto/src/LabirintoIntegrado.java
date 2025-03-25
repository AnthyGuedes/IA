import java.util.LinkedList;
import java.util.List;
import busca.Estado;
import busca.Heuristica;

public class LabirintoIntegrado implements Estado, Heuristica{
     char[][] labIntegrado;
     int[] entrada1;
     int[] entrada2;
     int[] saida;
     String operacao;

    public LabirintoIntegrado(Labirinto labBase, String op) {
        this.labIntegrado = copiarLab(labBase.getLabirinto());  // Usa o grid original diretamente
        this.entrada1 = labBase.getEntrada1();
        this.entrada2 = labBase.getEntrada2();
        this.saida = labBase.getSaida();
        this.operacao = op;
    }

     char[][] converterSimbolos(char[][] original) {
        char[][] novo = new char[original.length][original.length];
        for(int i = 0; i < original.length; i++) {
            for(int j = 0; j < original.length; j++) {
                switch(original[i][j]) {
                    case '1': novo[i][j] = '¹'; break;
                    case '2': novo[i][j] = '²'; break;
                    case '=': novo[i][j] = 'S'; break;
                    case '#': novo[i][j] = '@'; break;
                    case ' ': novo[i][j] = 'O'; break;
                    default: novo[i][j] = original[i][j];
                }
            }
        }
        return novo;
    }

    @Override
    public boolean ehMeta() {
        // Meta: qualquer entrada alcançar a saída ('=')
        return (entrada1[0] == saida[0] && entrada1[1] == saida[1]) ||
                (entrada2[0] == saida[0] && entrada2[1] == saida[1]);
    }

    @Override
    public int custo() {
        return 0;
    }

    @Override
    public List<Estado> sucessores() {
        List<Estado> visitados = new LinkedList<>();
        moverEntrada(visitados, entrada1, '1');  // Move entrada 1
        moverEntrada(visitados, entrada2, '2');  // Move entrada 2
        return visitados;
    }

    @Override
    public String getDescricao() {
        return "";
    }

    private void moverEntrada(List<Estado> visitados, int[] entrada, char simboloEntrada) {
        int x = entrada[0], y = entrada[1];
        int[][] movimentos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};  // Cima, baixo, esquerda, direita

        for (int[] mov : movimentos) {
            int novoX = x + mov[0], novoY = y + mov[1];
            if (novoX >= 0 && novoX < labIntegrado.length && novoY >= 0 && novoY < labIntegrado.length &&
                    labIntegrado[novoX][novoY] != '#') {  // Obstáculo é '#'

                char[][] novoGrid = copiarLab();
                novoGrid[x][y] = ' ';  // Volta para vazio
                novoGrid[novoX][novoY] = simboloEntrada;

                LabirintoIntegrado novoEstado = sucessores(
                        novoGrid,
                        (simboloEntrada == '1') ? new int[]{novoX, novoY} : entrada1,
                        (simboloEntrada == '2') ? new int[]{novoX, novoY} : entrada2,
                        "Mover '" + simboloEntrada + "' para " + direcaoToString(mov)
                );

                if (!visitados.contains(novoEstado)) {
                    visitados.add(novoEstado);
                }
            }
        }
    }
    
    private char[][]copiarLab(char[][]labIntegrado) {
        char[][] copia = new char[labIntegrado.length][labIntegrado.length];
        for(int i = 0; i < labIntegrado.length; i++) {
            System.arraycopy(labIntegrado[i], 0, copia[i], 0, labIntegrado.length);
        }
        return copia;
    }

    // Construtor auxiliar para estados sucessores
    private LabirintoIntegrado(char[][] labIntegrado, int[] entrada1, int[] entrada2, int[] saida, String operacao) {
        this.labIntegrado = labIntegrado;
        this.entrada1 = entrada1;
        this.entrada2 = entrada2;
        this.saida = saida;
        this.operacao = operacao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LabirintoIntegrado outro = (LabirintoIntegrado) obj;
        for(int i = 0; i < labIntegrado.length; i++) {
            for(int j = 0; j < labIntegrado.length; j++) {
                if(labIntegrado[i][j] != outro.labIntegrado[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        for(int i = 0; i < labIntegrado.length; i++) {
            for(int j = 0; j < labIntegrado.length; j++) {
                hash = 31 * hash + labIntegrado[i][j];
            }
        }
        return hash;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append(operacao).append("\n");
        for(int i = 0; i < labIntegrado.length; i++) {
            for(int j = 0; j < labIntegrado.length; j++) {
                sb.append(labIntegrado[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("E1: (").append(entrada1[0]).append(",").append(entrada1[1]).append(") ");
        sb.append("E2: (").append(entrada2[0]).append(",").append(entrada2[1]).append(") ");
        sb.append("S: (").append(saida[0]).append(",").append(saida[1]).append(")\n");
        return sb.toString();
    }

    @Override
    public int h() {
        return 0;
    }
}
