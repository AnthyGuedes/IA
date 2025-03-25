package busca;

public class MostraStatusConsole {
    public void mostraStatus(Nodo n) {
        System.out.print("\rNível: " + n.getProfundidade());
    }
}