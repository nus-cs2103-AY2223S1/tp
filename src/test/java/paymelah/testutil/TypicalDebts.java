package paymelah.testutil;

import paymelah.model.debt.Debt;

/**
 * A utility class containing a list of {@code Debt} objects to be used in tests.
 */
public class TypicalDebts {
    public static final Debt MCDONALDS = new DebtBuilder().withDescription("Mcdonalds")
            .withMoney("10.80").build();

    public static final Debt CHICKEN_RICE = new DebtBuilder().withDescription("chicken rice")
            .withMoney("5.60").build();

    private TypicalDebts() {} // prevents instantiation
}
