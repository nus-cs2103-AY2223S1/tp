package seedu.address.logic.commands.commission;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.CustomerStub;
import seedu.address.model.commission.Commission;
import seedu.address.model.customer.Customer;
import seedu.address.model.customer.Name;

import java.util.ArrayList;

public class CustomerStubWithCommission extends CustomerStub {
    static final String CUSTOMER_NAME = "Dummy";
    final ArrayList<Commission> commissionsAdded = new ArrayList<>();

    CustomerStubWithCommission() {
        super(new seedu.address.testutil.CustomerBuilder()
                .withName(CUSTOMER_NAME).toCustomerBuilder());
    }

    @Override
    public Name getName() {
        return new Name(CUSTOMER_NAME);
    }

    @Override
    public boolean hasCommission(Commission toAdd) {
        return commissionsAdded.stream().anyMatch(toAdd::isSameCommission);
    }

    @Override
    public boolean isSameCustomer(Customer customer) {
        return this.getName().equals(customer.getName());
    }

    @Override
    public void addCommission(Commission toAdd) {
        commissionsAdded.add(toAdd);
    }

    @Override
    public void removeCommission(Commission toRemove) {
        commissionsAdded.remove(toRemove);
    }

    @Override
    public ObservableList<Commission> getCommissionList() {
        return FXCollections.observableList(commissionsAdded);
    }

    @Override
    public void setCommission(Commission target, Commission edited) {
        int targetIndex = commissionsAdded.indexOf(target);
        commissionsAdded.set(targetIndex, edited);
    }
}
