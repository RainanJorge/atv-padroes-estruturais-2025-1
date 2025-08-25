package atv2;

import java.util.ArrayList;
import java.util.List;

public class Cardapio {
    private List<CardapioItem> itens = new ArrayList<>();

    public void adicionarItem(CardapioItem item) {
        itens.add(item);
    }

    public void exibirCardapio() {
        System.out.println("=== CARDÁPIO DO RESTAURANTE ===");
        for (CardapioItem item : itens) {
            System.out.println(item.getDescricao(0));
            System.out.println();
        }
    }
}