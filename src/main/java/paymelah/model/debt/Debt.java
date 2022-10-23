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
    private final DebtDate date;
    private final DebtTime time;

    /**
     * Every field must be present and not null.
     *
     * @param description The description of the debt.
     * @param money The money amount of the debt.
     * @param date The date of the debt.
     * @param time The time of the debt.
     */
    public Debt(Description description, Money money, DebtDate date, DebtTime time) {
        requireAllNonNull(description, money, date, time);
        this.description = description;
        this.money = money;
        this.date = date;
        this.time = time;
    }

    public Description getDescription() {
        return description;
    }

    public Money getMoney() {
        return money;
    }

    public DebtDate getDate() {
        return date;
    }

    public DebtTime getTime() {
        return time;
    }

    /**
     * Returns a Debt with the given description, money, date and time.
     */
    public static Debt makeDebt(String description, String money, String date, String time) {
        requireAllNonNull(description, money, date, time);
        return new Debt(new Description(description), new Money(money), new DebtDate(date), new DebtTime(time));
    }

    /**
     * Returns a copy of the debt.
     *
     * @return Debt that is a copy of this debt.
     */
    public Debt copyDebt() {
        return new Debt(this.description, this.money);
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
        return otherDebt.getDescription().equals(this.getDescription())
                && otherDebt.getMoney().equals(this.getMoney())
                && otherDebt.getDate().equals(this.getDate())
                && otherDebt.getTime().equals(this.getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, money, date, time);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append(" ")
                .append(getTime())
                .append(": ")
                .append(getDescription())
                .append("; $")
                .append(getMoney());

        return builder.toString();
    }
}
