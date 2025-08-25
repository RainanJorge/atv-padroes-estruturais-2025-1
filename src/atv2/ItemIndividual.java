package atv2;

public class ItemIndividual implements CardapioItem {
    private String nome;
    private double preco;

    public ItemIndividual(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    @Override
    public String getDescricao(int indent) {
        String pad = " ".repeat(indent);
        return pad + "- " + nome + " (R$ " + String.format("%.2f", preco) + ")";
    }

    @Override
    public double getPreco() {
        return preco;
    }
}