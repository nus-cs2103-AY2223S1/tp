package paymelah.testutil;

import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * A utility class to help with building Debt objects.
 */
public class DebtBuilder {
    public static final String DEFAULT_DESCRIPTION = "Manicure";
    public static final String DEFAULT_MONEY = "100";
    public static final String DEFAULT_DATE = "2022-10-12";
    public static final String DEFAULT_TIME = "00:00";

    private Description description;
    private Money money;
    private LocalDate date;
    private LocalTime time;

    /**
     * Creates a {@code DebtBuilder} with the default details.
     */
    public DebtBuilder() {
        try {
            description = new Description(DEFAULT_DESCRIPTION);
            money = new Money(DEFAULT_MONEY);
            date = LocalDate.parse(DEFAULT_DATE);
            time = LocalTime.parse(DEFAULT_TIME);
        } catch (DateTimeParseException e) {
            assert false : "DebtBuilder constructor error that should not occur has occurred.";
        }
    }

    /**
     * Initializes the DebtBuilder with the data of {@code debtToCopy}.
     */
    public DebtBuilder(Debt debtToCopy) {
        description = debtToCopy.getDescription();
        money = debtToCopy.getMoney();
        date = debtToCopy.getDate();
        time = debtToCopy.getTime();
    }

    /**
     * Sets the {@code Description} of the {@code Debt} that we are building.
     */
    public DebtBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Money} of the {@code Debt} that we are building.
     */
    public DebtBuilder withMoney(String money) {
        this.money = new Money(money);
        return this;
    }

    /**
     * Sets the date of the {@code Debt} that we are building.
     */
    public DebtBuilder withDate(String date) {
        this.date = LocalDate.parse(date);
        return this;
    }

    /**
     * Sets the time of the {@code Debt} that we are building.
     */
    public DebtBuilder withTime(String time) {
        this.time = LocalTime.parse(time);
        return this;
    }

    public Debt build() {
        return new Debt(description, money, date, time);
    }
}
