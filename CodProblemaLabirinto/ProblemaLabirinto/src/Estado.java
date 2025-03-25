package busca;

public interface Estado {
    public boolean ehMeta();
    public int custo();
    public List<Estado> sucessores();
    public String getDescricao();
}