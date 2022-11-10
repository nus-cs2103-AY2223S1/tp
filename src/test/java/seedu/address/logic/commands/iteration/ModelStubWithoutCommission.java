package seedu.address.logic.commands.iteration;

import seedu.address.model.ModelStub;

/**
 * A Model stub that does not have a commission.
 */
class ModelStubWithoutCommission extends ModelStub {
    @Override
    public boolean hasSelectedCommission() {
        return false;
    }
}
