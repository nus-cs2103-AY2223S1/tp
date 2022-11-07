package seedu.address.logic.commands.commission;

import seedu.address.model.ModelStub;

/**
 * A Model stub that does not have a customer.
 */
public class ModelStubWithoutCustomer extends ModelStub {
    @Override
    public boolean hasSelectedCustomer() {
        return false;
    }
}
