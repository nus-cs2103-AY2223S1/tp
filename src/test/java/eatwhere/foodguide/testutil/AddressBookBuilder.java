package eatwhere.foodguide.testutil;

import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.eatery.Eatery;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code FoodGuide ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private FoodGuide addressBook;

    public AddressBookBuilder() {
        addressBook = new FoodGuide();
    }

    public AddressBookBuilder(FoodGuide addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Eatery} to the {@code FoodGuide} that we are building.
     */
    public AddressBookBuilder withPerson(Eatery eatery) {
        addressBook.addEatery(eatery);
        return this;
    }

    public FoodGuide build() {
        return addressBook;
    }
}
