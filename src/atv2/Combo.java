package atv2;

import java.util.ArrayList;
import java.util.List;

public class Combo implements CardapioItem {
    private String nome;
    private List<CardapioItem> itens = new ArrayList<>();
    private double desconto; // percentual, ex: 0.10 para 10%

    public Combo(String nome, double desconto) {
        this.nome = nome;
        this.desconto = desconto;
    }

    public void adicionarItem(CardapioItem item) {
        itens.add(item);
    }

    @Override
    public String getDescricao(int indent) {
        String pad = " ".repeat(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(pad).append("+ Combo: ").append(nome)
          .append(" (Desconto: ").append((int)(desconto*100)).append("%)\n");
        for (CardapioItem item : itens) {
            sb.append(item.getDescricao(indent + 2)).append("\n");
        }
        sb.append(pad).append("  Total: R$ ").append(String.format("%.2f", getPreco()));
        return sb.toString();
    }

    @Override
    public double getPreco() {
        double total = 0;
        for (CardapioItem item : itens) {
            total += item.getPreco();
        }
        return total * (1 - desconto);
    }
}