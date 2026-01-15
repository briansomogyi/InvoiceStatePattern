
package com.example.invoice;

public class Main {
    public static void main(String[] args) {
        Invoice inv = new Invoice();
        System.out.println(inv.status()); // DRAFT
        inv.edit("Laptop purchase", 1200.0);

        inv.submit();
        System.out.println(inv.status()); // SUBMITTED

        inv.approve();
        System.out.println(inv.status()); // APPROVED

        inv.pay();
        System.out.println(inv.status()); // PAID
    }
}
