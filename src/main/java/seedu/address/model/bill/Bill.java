package seedu.address.model.bill;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.patient.Name;

/**
 * Represents a bill of a patient in the HealthConnect
 */
public class Bill {

    private final Name name;
    private final Amount amount;
    private final BillDate billDate;
    private final Paid paid;

    /**
     * Every field must be present and not null.
     */
    public Bill(Name name, Amount amount, BillDate billDate, Paid paid) {
        requireAllNonNull(name, amount, billDate, paid);
        this.name = name;
        this.amount = amount;
        this.billDate = billDate;
        this.paid = paid;
    }

    public Name getName() {
        return name;
    }

    public Amount getAmount() {
        return amount;
    }

    public BillDate getBillDate() {
        return billDate;
    }

    public Paid getIsPaid() {
        return paid;
    }

    public boolean isSameBill(Bill bill) {
        return false;
    } //There could be two same bills.

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Bill) {
            Bill other = (Bill) o;
            return amount.equals(other.getAmount())
                    && billDate.equals(other.getBillDate())
                    && paid.equals(other.getIsPaid())
                    && name.equals(other.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Name: ")
                .append(getName())
                .append("; Amount: ")
                .append(getAmount())
                .append("; Bill Date: ")
                .append(getBillDate())
                .append("; Is paid: ")
                .append(getIsPaid());

        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, billDate, paid);
    }
}
