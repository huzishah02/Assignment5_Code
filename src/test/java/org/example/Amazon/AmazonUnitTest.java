package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.example.Amazon.Cost.PriceRule;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AmazonUnitTest {

    @Test
    @DisplayName("specification-based - should aggregate prices from all rules")
    void testCalculateWithMockRules() {
        ShoppingCart mockCart = Mockito.mock(ShoppingCart.class);
        List<Item> mockItems = List.of(new Item(ItemType.ELECTRONIC, "Phone", 1, 500.0));

        Mockito.when(mockCart.getItems()).thenReturn(mockItems);

        PriceRule rule1 = Mockito.mock(PriceRule.class);
        PriceRule rule2 = Mockito.mock(PriceRule.class);
        Mockito.when(rule1.priceToAggregate(mockItems)).thenReturn(500.0);
        Mockito.when(rule2.priceToAggregate(mockItems)).thenReturn(50.0);

        Amazon amazon = new Amazon(mockCart, List.of(rule1, rule2));
        double result = amazon.calculate();

        assertThat(result).isEqualTo(550.0);
        Mockito.verify(rule1).priceToAggregate(mockItems);
        Mockito.verify(rule2).priceToAggregate(mockItems);
    }

    @Test
    @DisplayName("structural-based - should call add method on ShoppingCart when adding item")
    void testAddToCartCallsShoppingCart() {
        ShoppingCart mockCart = Mockito.mock(ShoppingCart.class);
        PriceRule mockRule = Mockito.mock(PriceRule.class);
        Amazon amazon = new Amazon(mockCart, List.of(mockRule));

        Item item = new Item(ItemType.OTHER, "Charger", 1, 20.0);
        amazon.addToCart(item);

        Mockito.verify(mockCart, Mockito.times(1)).add(item);
    }
}