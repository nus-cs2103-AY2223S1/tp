package seedu.address.logic.commands.commission;

import seedu.address.model.ModelStub;

public class ModelStubWithoutCustomer extends ModelStub {
    @Override
    public boolean hasSelectedCustomer() {
        return false;
    }
}
