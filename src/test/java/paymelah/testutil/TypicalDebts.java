package paymelah.testutil;

import paymelah.model.debt.Debt;

/**
 * A utility class containing a list of {@code Debt} objects to be used in tests.
 */
public class TypicalDebts {
    public static final Debt MCDONALDS = new DebtBuilder().withDescription("McDonald's")
            .withMoney("10.80").build();
    public static final Debt CHICKEN_RICE = new DebtBuilder().withDescription("chicken rice")
            .withMoney("5.60").build();
    public static final Debt SUPPER = new DebtBuilder().withDescription("supper jio")
            .withMoney("$4.5").build();
    public static final Debt TAOBAO = new DebtBuilder().withDescription("Taobao group buy")
            .withMoney("34.52").build();
    public static final Debt BURGER = new DebtBuilder().withDescription("burger king")
            .withMoney("10.40").build();
    public static final Debt MEALS = new DebtBuilder().withDescription("Volunteer meal")
            .withMoney("5").build();
    public static final Debt KFC_BURGER = new DebtBuilder().withDescription("kfc zinger burger")
            .withMoney("$4.5").build();
    public static final Debt COOP = new DebtBuilder().withDescription("coop textbooks")
            .withMoney("$29.60").build();

    private TypicalDebts() {} // prevents instantiation
}
