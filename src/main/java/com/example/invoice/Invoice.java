package com.example.invoice;

public class Invoice {
    private InvoiceState state;
    private double amount;
    private String description;

    public Invoice() {
        this.state = new DraftState(); // Initial state
    }

    // Setters for data (used by States to update the context)
    public void setState(InvoiceState state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String status() {
        return state.getStatusName();
    }

    // --- Delegate methods ---
    public void edit(String desc, double amt) {
        state.edit(this, desc, amt);
    }

    public void submit() {
        state.submit(this);
    }

    public void approve() {
        state.approve(this);
    }

    public void reject(String reason) {
        state.reject(this, reason);
    }

    public void pay() {
        state.pay(this);
    }

    public void hold() {
        state.hold(this);
    }
}
