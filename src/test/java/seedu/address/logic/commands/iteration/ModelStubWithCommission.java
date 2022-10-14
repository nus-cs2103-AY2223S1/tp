package seedu.address.logic.commands.iteration;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.ObservableObject;
import seedu.address.model.ModelStub;
import seedu.address.model.commission.Commission;

/**
 * A Model stub that contains a single commission.
 */
class ModelStubWithCommission extends ModelStub {
    private final ObservableObject<CommissionStubWithIteration> commission;

    ModelStubWithCommission(CommissionStubWithIteration commission) {
        requireNonNull(commission);
        this.commission = new ObservableObject<>(commission);
    }

    CommissionStubWithIteration getSelectedCommissionStub() {
        return commission.getValue();
    }

    @Override
    public boolean hasSelectedCommission() {
        return true;
    }

    @Override
    public ObservableObject<Commission> getSelectedCommission() {
        return new ObservableObject<>(commission.getValue());
    }
}
