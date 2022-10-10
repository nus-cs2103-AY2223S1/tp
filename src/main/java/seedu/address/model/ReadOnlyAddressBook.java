package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Buyer> getBuyerList();

    ObservableList<Supplier> getSupplierList();

    ObservableList<Deliverer> getDelivererList();

    ObservableList<Pet> getPetList();

    ObservableList<Order> getOrderList();


}
