package com.example.invoice;

// OnHoldState.java
public class OnHoldState implements InvoiceState {
    @Override
    public void edit(Invoice context, String desc, double amount) {
        // Requirement: Editing moves it back to Draft
        context.setDescription(desc);
        context.setAmount(amount);
        context.setState(new DraftState());
    }

    @Override
    public void submit(Invoice context) {
        // Requirement: OnHold -> Submitted
        context.setState(new SubmittedState());
    }

    @Override
    public void approve(Invoice context) {
        // Requirement: OnHold -> Approved
        context.setState(new ApprovedState());
    }

    @Override
    public void reject(Invoice context, String reason) {
        // Requirement: OnHold -> Rejected
        context.setState(new RejectedState());
    }

    @Override
    public void pay(Invoice context) {
        throw new IllegalStateException("Cannot pay an invoice that is On Hold.");
    }

    @Override
    public void hold(Invoice context) {
        throw new IllegalStateException("Already On Hold.");
    }

    @Override
    public String getStatusName() {
        return "ON_HOLD";
    }
}
