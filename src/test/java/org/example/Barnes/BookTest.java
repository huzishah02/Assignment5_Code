package org.example.Barnes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    @DisplayName("specification-based: constructor sets fields correctly")
    void testConstructorAndGetters() {
        Book b = new Book("123", 10, 5);
        assertEquals(10, b.getPrice());
        assertEquals(5, b.getQuantity());
    }

    @Test
    @DisplayName("structural-based: equals and hashCode depend only on ISBN")
    void testEqualsAndHashCode() {
        Book b1 = new Book("X1", 5, 2);
        Book b2 = new Book("X1", 9, 9);
        Book b3 = new Book("Y2", 5, 2);

        assertEquals(b1, b2);
        assertNotEquals(b1, b3);
        assertEquals(b1.hashCode(), b2.hashCode());
    }
}