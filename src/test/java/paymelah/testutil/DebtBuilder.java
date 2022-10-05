package paymelah.testutil;

import paymelah.model.debt.Debt;
import paymelah.model.debt.Description;
import paymelah.model.debt.Money;

/**
 * A utility class to help with building Debt objects.
 */
public class DebtBuilder {
    public static final String DEFAULT_DESCRIPTION = "Manicure";
    public static final String DEFAULT_MONEY = "100";

    private Description description;
    private Money money;

    /**
     * Creates a {@code DebtBuilder} with the default details.
     */
    public DebtBuilder() {
        description = new Description(DEFAULT_DESCRIPTION);
        money = new Money(DEFAULT_MONEY);
    }

    /**
     * Initializes the DebtBuilder with the data of {@code debtToCopy}.
     */
    public DebtBuilder(Debt debtToCopy) {
        description = debtToCopy.getDescription();
        money = debtToCopy.getMoney();
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

    public Debt build() {
        return new Debt(description, money);
    }
}
