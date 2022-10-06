package paymelah.testutil;

import static paymelah.testutil.TypicalDebts.CHICKEN_RICE;

import java.util.ArrayList;
import java.util.List;

import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;

/**
 * A utility class to help with building DebtList objects.
 */
public class DebtListBuilder {
    public static final Debt DEFAULT_DEBT = CHICKEN_RICE;

    private List<Debt> debtList = new ArrayList<>();

    /**
     * Creates a {@code DebtListBuilder} with the default details.
     */
    public DebtListBuilder() {
        debtList.add(DEFAULT_DEBT);
    }

    /**
     * Initializes the {@code DebtListBuilder} with the data of {@code debtToCopy}.
     */
    public DebtListBuilder(DebtList debtToCopy) {
        debtList.addAll(debtToCopy.asList());
    }

    /**
     * Adds a {@link Debt} to the {@code DebtList} that we are building.
     */
    public DebtListBuilder withDebt(Debt debt) {
        debtList.add(debt);
        return this;
    }

    public DebtList build() {
        return DebtList.fromList(debtList);
    }
}
