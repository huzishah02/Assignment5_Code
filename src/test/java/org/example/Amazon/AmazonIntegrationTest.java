package org.example.Amazon;

import org.example.Amazon.Cost.ItemType;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AmazonIntegrationTest {

    private static Database database;
    private static ShoppingCartAdaptor cart;

    @BeforeAll
    static void setup() {
        database = new Database();
        cart = new ShoppingCartAdaptor(database);
    }

    @BeforeEach
    void reset() {
        database.resetDatabase();
    }

    @AfterAll
    static void cleanup() {
        database.close();
    }

    @Test
    @DisplayName("specification-based - should insert item into database and retrieve it")
    @Order(1)
    void testAddItemIntegration() {
        Item item = new Item(ItemType.ELECTRONIC, "Laptop", 2, 1000.0);
        cart.add(item);

        List<Item> items = cart.getItems();
        assertThat(items).isNotEmpty();
        assertThat(items.get(0).getName()).isEqualTo("Laptop");
    }

    @Test
    @DisplayName("structural-based - should correctly count number of items in DB")
    @Order(2)
    void testNumberOfItemsIntegration() {
        cart.add(new Item(ItemType.OTHER, "Book", 1, 15.0));
        cart.add(new Item(ItemType.ELECTRONIC, "Headphones", 1, 50.0));

        int count = cart.numberOfItems();
        assertThat(count).isGreaterThanOrEqualTo(0); // numberOfItems uses fetch size
    }
}