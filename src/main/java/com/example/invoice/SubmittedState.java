package com.example.invoice;

public class SubmittedState implements InvoiceState {
    @Override
    public void edit(Invoice context, String desc, double amount) {
        // Requirement: Editing a Submitted invoice puts it back to Draft
        context.setDescription(desc);
        context.setAmount(amount);
        context.setState(new DraftState());
    }

    @Override
    public void submit(Invoice context) {
        throw new IllegalStateException("Already submitted.");
    }

    @Override
    public void approve(Invoice context) {
        context.setState(new ApprovedState());
    }

    @Override
    public void reject(Invoice context, String reason) {
        context.setState(new RejectedState());
    }

    @Override
    public void pay(Invoice context) {
        throw new IllegalStateException("Cannot pay a Submitted invoice. It must be Approved first.");
    }

    @Override
    public void hold(Invoice context) {
        // Requirement: Submitted -> OnHold
        context.setState(new OnHoldState());
    }

    @Override
    public String getStatusName() {
        return "SUBMITTED";
    }
}
