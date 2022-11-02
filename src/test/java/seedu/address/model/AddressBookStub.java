package seedu.address.model;

import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;

import java.util.ArrayList;

public class AddressBookStub extends AddressBook {
    private ArrayList<Commission> allCommissions = new ArrayList<>();

    @Override
    public void addCommission(Customer customer, Commission commission) {
        customer.addCommission(commission);
        allCommissions.add(commission);
    }
}
