package busca;

import java.util.*;

 public class BuscaLargura {
    private MostrarStatusConsole status;

    public BuscaLargura(MostrarStatusConsole status) {
        this.status = status;
    }

    public Nodo busca(Estado inicial) {
        Queue<Nodo> borda = new LinkedList<>();
        Set<Estado> visitados = new HashSet<>();

        borda.add(new Nodo(inicial, null));

        while (!borda.isEmpty()) {
            Nodo atual = borda.poll();
            status.mostraStatus(atual);

            if (atual.estado.ehMeta()) {
                return atual;
            }

            for (Estado suc : atual.estado.sucessores()) {
                if (!visitados.contains(suc)) {
                    visitados.add(suc);
                    borda.add(new Nodo(suc, atual));
                }
            }
        }
        return null;
    }
}