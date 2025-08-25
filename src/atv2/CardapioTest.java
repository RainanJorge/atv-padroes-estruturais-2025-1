package atv2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CardapioTest {

    @Test
    public void testComboPrecoEDescricao() {
        ItemIndividual prato = new ItemIndividual("Filé à Parmegiana", 35.0);
        ItemIndividual bebida = new ItemIndividual("Suco de Laranja", 8.0);
        ItemIndividual sobremesa = new ItemIndividual("Pudim", 12.0);

        Combo comboAlmoco = new Combo("Almoço Completo", 0.15); // 15% de desconto
        comboAlmoco.adicionarItem(prato);
        comboAlmoco.adicionarItem(bebida);
        comboAlmoco.adicionarItem(sobremesa);

        Combo comboDuplo = new Combo("Combo Duplo", 0.20); // 20% de desconto
        comboDuplo.adicionarItem(comboAlmoco);
        comboDuplo.adicionarItem(new ItemIndividual("Refrigerante", 7.0));

        // Testa preço do comboAlmoco
        double esperadoAlmoco = (35.0 + 8.0 + 12.0) * 0.85;
        assertEquals(esperadoAlmoco, comboAlmoco.getPreco(), 0.01);

        // Testa preço do comboDuplo
        double esperadoDuplo = (comboAlmoco.getPreco() + 7.0) * 0.80;
        assertEquals(esperadoDuplo, comboDuplo.getPreco(), 0.01);

        // Testa descrição não nula
        assertNotNull(comboDuplo.getDescricao(0));
    }
}