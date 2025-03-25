package busca;

public class MostrarStatusConsole {
    public void mostraStatus(Nodo n) {
        System.out.print("\rNível: " + n.getProfundidade());
    }
}