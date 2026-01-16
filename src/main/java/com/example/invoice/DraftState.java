package com.example.invoice;

public class DraftState implements InvoiceState {
    // ... existing edit/submit code ...

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

    // ... existing error throws for approve/reject/pay ...
    @Override
    public void approve(Invoice c) {
        throw new IllegalStateException("Submit first");
    }

    @Override
    public void reject(Invoice c, String r) {
        throw new IllegalStateException("Cannot reject Draft");
    }

    @Override
    public void pay(Invoice c) {
        throw new IllegalStateException("Cannot pay Draft");
    }

    @Override
    public void hold(Invoice context) {
        // Diagram: Draft -> OnHold
        // We pass 'this' so OnHoldState knows to come back to Draft if needed
        context.setState(new OnHoldState(this));
    }

    @Override
    public String getStatusName() {
        return "DRAFT";
    }
}
