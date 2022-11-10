package seedu.address.testutil;

import seedu.address.model.AddressBook;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private AddressBook addressBook;

    public AddressBookBuilder() {
        addressBook = new AddressBook();
    }

    public AddressBookBuilder(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Buyer} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withBuyer(Buyer buyer) {
        addressBook.addBuyer(buyer);
        return this;
    }

    /**
     * Adds a new {@code Supplier} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withSupplier(Supplier supplier) {
        addressBook.addSupplier(supplier);
        return this;
    }

    /**
     * Adds a new {@code Deliverer} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withDeliverer(Deliverer deliverer) {
        addressBook.addDeliverer(deliverer);
        return this;
    }

    /**
     * Adds a new {@code Pet} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withPet(Pet pet) {
        addressBook.addPet(pet);
        return this;
    }

    /**
     * Adds a new {@code Order} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withOrder(Order order) {
        addressBook.addOrder(order);
        return this;
    }

    public AddressBook build() {
        return addressBook;
    }
}
