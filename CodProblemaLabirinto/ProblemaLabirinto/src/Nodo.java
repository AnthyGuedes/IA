package busca;

import java.util.*;

public class Nodo {
    public final Estado estado;
    public final Nodo pai;
    private int profundidade;

    public Nodo(Estado estado, Nodo pai) {
        this.estado = estado;
        this.pai = pai;
        this.profundidade = (pai == null) ? 0 : pai.profundidade + 1;
    }

    public int getProfundidade() {
        return profundidade;
    }

    public String montaCaminho() {
        StringBuilder caminho = new StringBuilder();
        montaCaminhoRecursivo(this, caminho);
        return caminho.toString();
    }

    private void montaCaminhoRecursivo(Nodo nodo, StringBuilder caminho) {
        if (nodo.pai != null) {
            montaCaminhoRecursivo(nodo.pai, caminho);
        }
        caminho.append(nodo.estado.toString()).append("\n");
    }
}