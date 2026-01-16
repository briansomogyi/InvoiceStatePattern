package com.example.invoice;

// InvoiceState.java
public interface InvoiceState {
    void edit(Invoice context, String desc, double amount);

    void submit(Invoice context);

    void approve(Invoice context);

    void hold(Invoice context); // The new requirement

    void reject(Invoice context, String reason);

    void pay(Invoice context);

    String getStatusName();
}

