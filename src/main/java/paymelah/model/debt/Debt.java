package paymelah.model.debt;

import static paymelah.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Debt in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Debt {
    private final Description description;
    private final Money money;
    private boolean isPaid;

    /**
     * Every field must be present and not null.
     *
     * @param description The description of the debt.
     * @param money The money amount of the debt.
     */
    public Debt(Description description, Money money) {
        requireAllNonNull(description, money);
        this.description = description;
        this.money = money;
        this.isPaid = false;
    }

    /**
     * Every field must be present and not null.
     *
     * @param description The description of the debt.
     * @param money The money amount of the debt.
     * @param isPaid Whether the debt has been paid.
     */
    public Debt(Description description, Money money, boolean isPaid) {
        requireAllNonNull(description, money, isPaid);
        this.description = description;
        this.money = money;
        this.isPaid = isPaid;
    }

    public Description getDescription() {
        return description;
    }

    public Money getMoney() {
        return money;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    /**
     * Returns a Debt with the given description and money.
     */
    public static Debt makeDebt(String description, String money) {
        return new Debt(new Description(description), new Money(money));
    }

    /**
     * Returns true only if both debts have the same data fields.
     *
     * @param other The other {@code Debt} object to check.
     * @return true only if both debts have the same data fields.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Debt)) {
            return false;
        }

        Debt otherDebt = (Debt) other;
        return otherDebt.getDescription().equals(getDescription())
                && otherDebt.getMoney().equals(getMoney());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, money);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append(": $")
                .append(getMoney());

        return builder.toString();
    }
}
