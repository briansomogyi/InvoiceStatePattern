
package com.example.invoice;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InvoiceTest {
    @Test
    void happy_path() {
        Invoice inv = new Invoice();
        inv.edit("Laptop", 1200);
        inv.submit();
        inv.approve();
        inv.pay();
        assertEquals("PAID", inv.status());
    }

    @Test
    void illegal_transition_throws() {
        Invoice inv = new Invoice();
        assertThrows(IllegalStateException.class, inv::approve);
        assertThrows(IllegalStateException.class, inv::pay);
    }
}
