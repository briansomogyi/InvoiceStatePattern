package com.example.invoice;

public class ApprovedState implements InvoiceState {
    @Override
    public void edit(Invoice context, String desc, double amount) {
        // Consistent behavior: Editing an Approved invoice returns it to Draft for re-approval
        context.setDescription(desc);
        context.setAmount(amount);
        context.setState(new DraftState());
    }

    @Override
    public void submit(Invoice context) {
        throw new IllegalStateException("Already approved.");
    }

    @Override
    public void approve(Invoice context) {
        throw new IllegalStateException("Already approved.");
    }

    @Override
    public void reject(Invoice context, String reason) {
        // Usually can't reject once approved, unless un-approved first,
        // but simple implementation might allow it or throw exception.
        throw new IllegalStateException("Cannot reject an Approved invoice.");
    }

    @Override
    public void pay(Invoice context) {
        context.setState(new PaidState());
    }

    @Override
    public void hold(Invoice context) {
        // Requirement: Approved -> OnHold
        context.setState(new OnHoldState());
    }

    @Override
    public String getStatusName() {
        return "APPROVED";
    }
}
