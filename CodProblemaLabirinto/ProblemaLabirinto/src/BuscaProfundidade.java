package busca;

import java.util.*;

public class BuscaProfundidade {
    private MostraStatusConsole status;

    public BuscaProfundidade(MostraStatusConsole status) {
        this.status = status;
    }

    public Nodo busca(Estado inicial) {
        Deque<Nodo> borda = new ArrayDeque<>();
        Set<Estado> visitados = new HashSet<>();

        borda.push(new Nodo(inicial, null));

        while (!borda.isEmpty()) {
            Nodo atual = borda.pop();
            status.mostraStatus(atual);

            if (atual.estado.ehMeta()) {
                return atual;
            }

            for (Estado suc : atual.estado.sucessores()) {
                if (!visitados.contains(suc)) {
                    visitados.add(suc);
                    borda.push(new Nodo(suc, atual));
                }
            }
        }
        return null;
    }
}