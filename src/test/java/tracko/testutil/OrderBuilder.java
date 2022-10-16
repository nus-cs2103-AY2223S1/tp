package tracko.testutil;

import java.util.ArrayList;
import java.util.List;

import tracko.model.order.Address;
import tracko.model.order.Email;
import tracko.model.order.ItemQuantityPair;
import tracko.model.order.Name;
import tracko.model.order.Order;
import tracko.model.order.Phone;

/**
 * A utility class to help with building Order objects.
 */
public class OrderBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_ITEM = "Keychain";
    public static final Integer DEFAULT_QUANTITY = 2;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private List<ItemQuantityPair> itemList;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        itemList = new ArrayList<>();
        itemList.add(new ItemQuantityPair(DEFAULT_ITEM, DEFAULT_QUANTITY));
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        name = orderToCopy.getName();
        phone = orderToCopy.getPhone();
        email = orderToCopy.getEmail();
        address = orderToCopy.getAddress();
        itemList = orderToCopy.getItemList();
    }

    /**
     * Sets the {@code Name} of the {@code Order} that we are building.
     */
    public OrderBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Order} that we are building.
     */
    public OrderBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Order} that we are building.
     */
    public OrderBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Order} that we are building.
     */
    public OrderBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Adds an item and associated quantity to the list of items ordered
     */
    public OrderBuilder withItemQuantityPair(ItemQuantityPair itemQuantityPair) {
        itemList.add(itemQuantityPair);
        return this;
    }

    /**
     * Clears the item list.
     */
    public OrderBuilder withEmptyItemList() {
        itemList = new ArrayList<>();
        return this;
    }

    public Order build() {
        return new Order(name, phone, email, address, itemList);
    }
}
