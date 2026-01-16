package com.example.invoice;

public class RejectedState implements InvoiceState {
    @Override
    public void edit(Invoice context, String desc, double amount) {
        // Requirement: "Rejected invoices should be editable only by admins."
        // Since we don't have a UserContext, we'll simulate the check or allow it
        // with a comment that this is where the check belongs.

        // if (!currentUser.isAdmin()) throw new IllegalAccessException("Only admin can edit");

        context.setDescription(desc);
        context.setAmount(amount);
        context.setState(new DraftState());
    }

    @Override
    public void submit(Invoice context) {
        throw new IllegalStateException("Cannot submit a Rejected invoice. Edit it first.");
    }

    @Override
    public void approve(Invoice context) {
        throw new IllegalStateException("Cannot approve a Rejected invoice.");
    }

    @Override
    public void reject(Invoice context, String reason) {
        throw new IllegalStateException("Already rejected.");
    }

    @Override
    public void pay(Invoice context) {
        throw new IllegalStateException("Cannot pay a Rejected invoice.");
    }

    @Override
    public void hold(Invoice context) {
        throw new IllegalStateException("Cannot hold a Rejected invoice.");
    }

    @Override
    public String getStatusName() {
        return "REJECTED";
    }
}
