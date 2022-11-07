package paymelah.testutil;

import java.util.ArrayList;
import java.util.List;

import paymelah.model.debt.Debt;
import paymelah.model.debt.DebtList;

/**
 * A utility class to help with building DebtList objects.
 */
public class DebtListBuilder {
    private List<Debt> debtList = new ArrayList<>();

    /**
     * Creates an empty {@code DebtListBuilder}.
     */
    public DebtListBuilder() {}

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
        if (!debtList.contains(debt)) {
            debtList.add(debt);
        }
        return this;
    }

    public DebtList build() {
        return DebtList.fromList(debtList);
    }
}
