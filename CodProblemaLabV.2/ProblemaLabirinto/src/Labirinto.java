import java.util.Random;

public class Labirinto {
        int tamanho;
        int[] saida;
        int[] entrada1;
        int[] entrada2;
        char[][] labirintoVS;
        int obstaculos;

        public Labirinto() {
            this.tamanho = tamanho;
            this.labirintoVS = new char[tamanho][tamanho];
            iniciarLabirinto();
            gerarObstaculos(obstaculos);
            gerarSaidaeEntradas();
        }

        void iniciarLabirinto(){
            for(int i = 0; i < tamanho; i++){
                for(int j = 0; j < tamanho; j++){
                    labirintoVS[i][j] = ' ';
                }
            }
        }

        void exibirLabirinto(){
            for(int i = 0; i < tamanho; i++){
                for(int j = 0; j < tamanho; j++){
                    System.out.print(labirintoVS[i][j] + " ");
                }
                System.out.println();
            }
        }

        void gerarObstaculos(int obstaculos){
            Random rand = new Random();
            int n = 0;
            while (n < obstaculos) {
                int x = rand.nextInt(tamanho);
                int y = rand.nextInt(tamanho);
                if(labirintoVS[x][y] == ' '){
                    labirintoVS[x][y] = '#'; // obstáculos
                    n ++;
                }
            }
        }

    void gerarSaidaeEntradas(){
        Random rand = new Random();

        saida = new int[]{rand.nextInt(tamanho), rand.nextInt(tamanho)};
        labirintoVS[saida[0]][saida[1]] = '=';

        do {
            entrada1 = new int[]{rand.nextInt(tamanho), rand.nextInt(tamanho)};
        } while (labirintoVS[entrada1[0]][entrada1[1]] != ' ');
        labirintoVS[entrada1[0]][entrada1[1]] = 'T';

        do {
            entrada2 = new int[]{rand.nextInt(tamanho), rand.nextInt(tamanho)};
        } while (labirintoVS[entrada2[0]][entrada2[1]] != ' ');
        labirintoVS[entrada2[0]][entrada2[1]] = 'T'; // Ou outro símbolo para a segunda entrada
    }

    public int[] getEntrada1() {
            return entrada1;
        }

    public int[] getEntrada2() {
            return entrada2;
        }

    public int[] getSaida() {
            return saida;
        }

    public char[][] getLabirinto() {
            return labirintoVS;
        }


}
