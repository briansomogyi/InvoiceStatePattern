package com.example.invoice;

public class PaidState implements InvoiceState {
    @Override
    public void edit(Invoice context, String desc, double amount) {
        throw new IllegalStateException("Cannot edit a Paid invoice.");
    }

    @Override
    public void submit(Invoice context) {
        throw new IllegalStateException("Already Paid.");
    }

    @Override
    public void approve(Invoice context) {
        throw new IllegalStateException("Already Paid.");
    }

    @Override
    public void reject(Invoice context, String reason) {
        throw new IllegalStateException("Cannot reject a Paid invoice.");
    }

    @Override
    public void pay(Invoice context) {
        throw new IllegalStateException("Already Paid.");
    }

    @Override
    public void hold(Invoice context) {
        throw new IllegalStateException("Cannot put a Paid invoice On Hold.");
    }

    @Override
    public String getStatusName() {
        return "PAID";
    }
}
