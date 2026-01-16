package com.example.invoice;

public class DraftState implements InvoiceState {
    @Override
    public void edit(Invoice context, String desc, double amount) {
        // Edit is allowed in Draft, stays in Draft
        context.setDescription(desc);
        context.setAmount(amount);
    }

    @Override
    public void submit(Invoice context) {
        context.setState(new SubmittedState());
    }

    @Override
    public void approve(Invoice context) {
        throw new IllegalStateException("Cannot approve a Draft invoice. Submit it first.");
    }

    @Override
    public void reject(Invoice context, String reason) {
        throw new IllegalStateException("Cannot reject a Draft invoice.");
    }

    @Override
    public void pay(Invoice context) {
        throw new IllegalStateException("Cannot pay a Draft invoice.");
    }

    @Override
    public void hold(Invoice context) {
        throw new IllegalStateException("Cannot put a Draft invoice On Hold.");
    }

    @Override
    public String getStatusName() {
        return "DRAFT";
    }
}
