package seedu.address.logic.commands.commission;

import seedu.address.commons.core.ObservableObject;
import seedu.address.model.AddressBookStub;
import seedu.address.model.ModelStub;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

import java.util.ArrayList;

import static java.util.Objects.requireNonNull;

public class ModelStubWithCustomer extends ModelStub {
    private final ObservableObject<Customer> selectedCustomer;

    ModelStubWithCustomer(Customer customer) {
        requireNonNull(customer);
        this.selectedCustomer = new ObservableObject<>(customer);
    }

    @Override
    public boolean hasSelectedCustomer() {
        return true;
    }

    @Override
    public void addCommission(Customer customer, Commission commission) {
        customer.addCommission(commission);
    }

    @Override
    public ObservableObject<Customer> getSelectedCustomer() {
        return selectedCustomer;
    }

    @Override
    public void selectCommission(Commission commission) {
        // Do nothing.
    }
}
