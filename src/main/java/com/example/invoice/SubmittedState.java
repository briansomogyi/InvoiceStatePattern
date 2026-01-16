package com.example.invoice;

public class SubmittedState implements InvoiceState {
    @Override
    public void hold(Invoice context) {
        // Diagram: Submitted -> OnHold (Memorize 'Submitted')
        context.setState(new OnHoldState(this));
    }

    // ... rest of implementation (submit throws, approve -> Approved, etc) ...
    @Override
    public void edit(Invoice c, String d, double a) {
        // Requirement: Editing a Submitted invoice puts it back to Draft
        c.setDescription(d);
        c.setAmount(a);
        c.setState(new DraftState());
    }

    @Override
    public void submit(Invoice c) {
        throw new IllegalStateException("Already submitted");
    }

    @Override
    public void approve(Invoice c) {
        c.setState(new ApprovedState());
    }

    @Override
    public void reject(Invoice c, String r) {
        c.setState(new RejectedState());
    }

    @Override
    public void pay(Invoice c) {
        throw new IllegalStateException("Approve first");
    }

    @Override
    public String getStatusName() {
        return "SUBMITTED";
    }
}
