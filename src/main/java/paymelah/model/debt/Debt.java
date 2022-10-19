package paymelah.model.debt;

import static paymelah.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a Debt in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Debt {
    public static final String DATE_CONSTRAINTS =
            "Dates should be in yyyy-mm-dd format; where y is year, m is month and d is day.";
    public static final String TIME_CONSTRAINTS =
            "Time should be in hh:mm format; where h is hour in 24h clock and m is minute.";

    private final Description description;
    private final Money money;
    private final LocalDate date;
    private final LocalTime time;

    /**
     * Every field must be present and not null.
     *
     * @param description The description of the debt.
     * @param money The money amount of the debt.
     * @param date The date of the debt.
     * @param time The time of the debt.
     */
    public Debt(Description description, Money money, LocalDate date, LocalTime time) {
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

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    /**
     * Returns a Debt with the given description, money, date and time.
     */
    public static Debt makeDebt(String description, String money, String date, String time) {
        LocalDate localDate = null;
        LocalTime localTime = null;

        try {
            localDate = LocalDate.parse(date);
        } catch (DateTimeParseException dateException) {
            assert false : "makeDebt called with erroneous date format.";
        }

        try {
            localTime = LocalTime.parse(time);
        } catch (DateTimeParseException timeException) {
            assert false : "makeDebt called with erroneous time format.";
        }

        assert localDate != null && localTime != null;
        return new Debt(new Description(description), new Money(money), localDate, localTime);
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
        builder.append(getDate().format(DateTimeFormatter.ofPattern("dd/MM/yy")))
                .append(": ")
                .append(getDescription())
                .append("; $")
                .append(getMoney());

        return builder.toString();
    }
}
