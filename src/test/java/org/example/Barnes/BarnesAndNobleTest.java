package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BarnesAndNobleTest {

    @Test
    @DisplayName("specification-based: returns null when order is null")
    void testNullOrder() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);
        BarnesAndNoble bn = new BarnesAndNoble(db, process);
        assertNull(bn.getPriceForCart(null));
    }

    @Test
    @DisplayName("specification-based: calculates total price for available books")
    void testTotalPriceCalculation() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Book b1 = new Book("A", 10, 5);
        when(db.findByISBN("A")).thenReturn(b1);

        BarnesAndNoble bn = new BarnesAndNoble(db, process);
        var summary = bn.getPriceForCart(Map.of("A", 3));

        assertEquals(30, summary.getTotalPrice());
        verify(process).buyBook(b1, 3);
    }

    @Test
    @DisplayName("structural-based: handles partial availability and records unavailable quantity")
    void testPartialAvailability() {
        BookDatabase db = mock(BookDatabase.class);
        BuyBookProcess process = mock(BuyBookProcess.class);

        Book b1 = new Book("B", 10, 2);
        when(db.findByISBN("B")).thenReturn(b1);

        BarnesAndNoble bn = new BarnesAndNoble(db, process);
        var summary = bn.getPriceForCart(Map.of("B", 5));

        assertEquals(20, summary.getTotalPrice());
        assertTrue(summary.getUnavailable().containsKey(b1));
        assertEquals(3, summary.getUnavailable().get(b1));
        verify(process).buyBook(b1, 2);
    }
}