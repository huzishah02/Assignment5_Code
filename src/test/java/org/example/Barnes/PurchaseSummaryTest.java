package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PurchaseSummaryTest {

    @Test
    @DisplayName("specification-based: addToTotalPrice correctly accumulates values")
    void testAddToTotalPrice() {
        PurchaseSummary ps = new PurchaseSummary();
        ps.addToTotalPrice(50);
        ps.addToTotalPrice(20);
        assertEquals(70, ps.getTotalPrice());
    }

    @Test
    @DisplayName("structural-based: unavailable map is unmodifiable and records data")
    void testUnavailableMap() {
        PurchaseSummary ps = new PurchaseSummary();
        Book book = new Book("123", 10, 2);
        ps.addUnavailable(book, 3);

        Map<Book,Integer> map = ps.getUnavailable();
        assertTrue(map.containsKey(book));
        assertEquals(3, map.get(book));
        assertThrows(UnsupportedOperationException.class,
                () -> map.put(book, 10));
    }
}