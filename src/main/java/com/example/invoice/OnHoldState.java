package com.example.invoice;

// OnHoldState.java
public class OnHoldState implements InvoiceState {
    private final InvoiceState previousState;

    // Constructor accepts the state we came from (Draft, Submitted, or Approved)
    public OnHoldState(InvoiceState previousState) {
        this.previousState = previousState;
    }

    @Override
    public void submit(Invoice context) {
        // Only allow "submit" (Resume) if we came from Submitted
        if (previousState instanceof SubmittedState) {
            context.setState(previousState);
        } else {
            throw new IllegalStateException("Cannot submit. Previous state was " + previousState.getStatusName());
        }
    }

    @Override
    public void approve(Invoice context) {
        // Only allow "approve" (Resume) if we came from Approved
        if (previousState instanceof ApprovedState) {
            context.setState(previousState);
        } else {
            throw new IllegalStateException("Cannot approve. Previous state was " + previousState.getStatusName());
        }
    }

    @Override
    public void edit(Invoice context, String desc, double amount) {
        // If we want to "Edit" from OnHold, we essentially reset to Draft.
        // This covers the diagram connection "OnHold -> ... -> Draft" (via edit logic)
        context.setDescription(desc);
        context.setAmount(amount);
        context.setState(new DraftState());
    }

    @Override
    public void reject(Invoice context, String reason) {
        // Diagram: OnHold -> Rejected
        context.setState(new RejectedState());
    }

    @Override
    public void pay(Invoice context) {
        throw new IllegalStateException("Cannot pay an invoice while it is On Hold.");
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
