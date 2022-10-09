package seedu.foodrem.testutil;

import seedu.foodrem.model.FoodRem;
import seedu.foodrem.model.item.Item;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code AddressBook ab = new AddressBookBuilder().withItem("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private final FoodRem addressBook;

    public AddressBookBuilder() {
        addressBook = new FoodRem();
    }

    public AddressBookBuilder(FoodRem addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Item} to the {@code AddressBook} that we are building.
     */
    public AddressBookBuilder withItem(Item item) {
        addressBook.addItem(item);
        return this;
    }

    public FoodRem build() {
        return addressBook;
    }
}
