package busca;

import java.util.List;

public interface Estado {
    public boolean ehMeta();
    public int custo();
    public List<Estado> sucessores();
    public String getDescricao();
}