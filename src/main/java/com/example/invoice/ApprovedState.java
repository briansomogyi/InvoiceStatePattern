package com.example.invoice;

public class ApprovedState implements InvoiceState {
    @Override
    public void hold(Invoice context) {
        // Diagram: Approved -> OnHold (Memorize 'Approved')
        context.setState(new OnHoldState(this));
    }

    // ... rest of implementation ...
    @Override
    public void edit(Invoice c, String d, double a) {
        // Consistent behavior: Editing an Approved invoice returns it to Draft for re-approval
        c.setDescription(d);
        c.setAmount(a);
        c.setState(new DraftState());
    }

    @Override
    public void submit(Invoice c) {
        throw new IllegalStateException("Already approved");
    }

    @Override
    public void approve(Invoice c) {
        throw new IllegalStateException("Already approved");
    }

    @Override
    public void reject(Invoice c, String r) {
        // Usually can't reject once approved, unless unapproved first,
        // but simple implementation might allow it or throw exception.
        throw new IllegalStateException("Cannot reject Approved");
    }

    @Override
    public void pay(Invoice c) {
        c.setState(new PaidState());
    }

    @Override
    public String getStatusName() {
        return "APPROVED";
    }
}
