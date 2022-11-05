package paymelah.model.debt;

import static paymelah.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Debt in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Debt implements Comparable<Debt> {
    private final Description description;
    private final Money money;
    private final DebtDate date;
    private final DebtTime time;
    private final boolean isPaid;

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
        this.isPaid = false;
    }

    /**
     * Every field must be present and not null.
     *
     * @param description The description of the debt.
     * @param money The money amount of the debt.
     * @param date The date of the debt.
     * @param time The time of the debt.
     * @param isPaid Whether the debt has been paid.
     */
    public Debt(Description description, Money money, DebtDate date, DebtTime time, boolean isPaid) {
        requireAllNonNull(description, money, date, time, isPaid);
        this.description = description;
        this.money = money;
        this.date = date;
        this.time = time;
        this.isPaid = isPaid;
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

    public boolean isPaid() {
        return isPaid;
    }

    public Debt setPaid(boolean isPaid) {
        return new Debt(description, money, date, time, isPaid);
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
        return new Debt(description, money, date, time, isPaid);
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

    /**
     * Compares with another Debt using date and time.
     */
    @Override
    public int compareTo(Debt o) {
        int dateComparison = this.date.compareTo(o.date);
        int timeComparison = this.time.compareTo(o.time);
        int descriptionComparison = this.description.compareTo(o.description);
        int moneyComparison = this.money.compareTo(o.money);
        if (dateComparison != 0) {
            return dateComparison;
        }
        if (timeComparison != 0) {
            return timeComparison;
        }
        if (descriptionComparison != 0) {
            return descriptionComparison;
        }
        return moneyComparison;
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
                .append(getMoney())
                .append(isPaid() ? " (paid)" : " (unpaid)");

        return builder.toString();
    }
}
