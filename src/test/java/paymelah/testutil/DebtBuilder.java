package paymelah.testutil;

import java.time.format.DateTimeParseException;

import paymelah.logic.parser.AddDebtCommandParser;
import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtDate;
import paymelah.model.debt.DebtTime;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * A utility class to help with building Debt objects.
 */
public class DebtBuilder {
    public static final String DEFAULT_DESCRIPTION = "Manicure";
    public static final String DEFAULT_MONEY = "100";
    public static final String DEFAULT_DATE = "2022-10-12";
    public static final String DEFAULT_TIME = AddDebtCommandParser.DEFAULT_TIME;

    private Description description;
    private Money money;
    private DebtDate date;
    private DebtTime time;

    /**
     * Creates a {@code DebtBuilder} with the default details.
     */
    public DebtBuilder() {
        try {
            description = new Description(DEFAULT_DESCRIPTION);
            money = new Money(DEFAULT_MONEY);
            date = new DebtDate(DEFAULT_DATE);
            time = new DebtTime(DEFAULT_TIME);
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
        this.date = new DebtDate(date);
        return this;
    }

    /**
     * Sets the date of the {@code Debt} that we are building to the current date.
     */
    public DebtBuilder withCurrentDate() {
        this.date = new DebtDate();
        return this;
    }

    /**
     * Sets the time of the {@code Debt} that we are building.
     */
    public DebtBuilder withTime(String time) {
        this.time = new DebtTime(time);
        return this;
    }

    /**
     * Sets the time of the {@code Debt} that we are building to the current time.
     */
    public DebtBuilder withCurrentTime() {
        this.time = new DebtTime();
        return this;
    }

    public Debt build() {
        return new Debt(description, money, date, time);
    }
}
