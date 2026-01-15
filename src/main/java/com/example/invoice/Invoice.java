
package com.example.invoice;

public class Invoice {

    private Status status = Status.DRAFT;
    private double amount;
    private String description;

    public String status() { return status.name(); }

    public void edit(String desc, double amt) {
        switch (status) {
            case DRAFT, SUBMITTED, APPROVED -> {
                this.description = desc;
                this.amount = amt;
                // If edited after SUBMITTED or APPROVED, return to DRAFT for re-approval
                if (status != Status.DRAFT) status = Status.DRAFT;
            }
            default -> throw new IllegalStateException("edit not allowed in " + status);
        }
    }

    public void submit() {
        switch (status) {
            case DRAFT -> status = Status.SUBMITTED;
            default -> throw new IllegalStateException("submit not allowed in " + status);
        }
    }

    public void approve() {
        switch (status) {
            case SUBMITTED -> status = Status.APPROVED;
            default -> throw new IllegalStateException("approve not allowed in " + status);
        }
    }

    public void reject(String reason) {
        switch (status) {
            case SUBMITTED -> status = Status.REJECTED;
            default -> throw new IllegalStateException("reject not allowed in " + status);
        }
    }

    public void pay() {
        switch (status) {
            case APPROVED -> status = Status.PAID;
            default -> throw new IllegalStateException("pay not allowed in " + status);
        }
    }
}
