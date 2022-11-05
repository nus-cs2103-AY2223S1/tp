package seedu.address.model;

import java.util.ArrayList;

import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

/**
 * An AddressBook stub.
 */
public class AddressBookStub extends AddressBook {
    private ArrayList<Commission> allCommissions = new ArrayList<>();

    @Override
    public void addCommission(Customer customer, Commission commission) {
        customer.addCommission(commission);
        allCommissions.add(commission);
    }
}
