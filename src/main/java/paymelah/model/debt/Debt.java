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
    }

    public Description getDescription() {
        return description;
    }

    public Money getMoney() {
        return money;
    }

    /**
     * Returns true only if both debts have the same data fields.
     *
     * @param other The other {@link Debt} object to check.
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
                .append("; Money: $")
                .append(getMoney());

        return builder.toString();
    }
}
