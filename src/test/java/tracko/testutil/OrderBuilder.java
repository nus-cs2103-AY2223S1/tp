package tracko.testutil;

import static tracko.testutil.TypicalItems.INVENTORY_ITEM_1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tracko.model.item.InventoryItem;
import tracko.model.item.Quantity;
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
    public static final LocalDateTime DEFAULT_TIME_CREATED =
            LocalDateTime.of(2022, 10, 18, 23, 54, 44);
    public static final InventoryItem DEFAULT_INVENTORY_ITEM = INVENTORY_ITEM_1;
    public static final Integer DEFAULT_QUANTITY = 2;
    public static final boolean DEFAULT_PAID_STATUS = false;
    public static final boolean DEFAULT_DELIVERY_STATUS = false;

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private LocalDateTime timeCreated;
    private List<ItemQuantityPair> itemList;
    private boolean isPaid;
    private boolean isDelivered;

    /**
     * Creates a {@code OrderBuilder} with the default details.
     */
    public OrderBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        timeCreated = DEFAULT_TIME_CREATED;
        itemList = new ArrayList<>();
        itemList.add(new ItemQuantityPair(DEFAULT_INVENTORY_ITEM, new Quantity(DEFAULT_QUANTITY)));
        isPaid = DEFAULT_PAID_STATUS;
        isDelivered = DEFAULT_DELIVERY_STATUS;
    }

    /**
     * Initializes the OrderBuilder with the data of {@code orderToCopy}.
     */
    public OrderBuilder(Order orderToCopy) {
        name = orderToCopy.getName();
        phone = orderToCopy.getPhone();
        email = orderToCopy.getEmail();
        address = orderToCopy.getAddress();
        timeCreated = orderToCopy.getTimeCreated();
        itemList = orderToCopy.getItemList();
        isPaid = orderToCopy.getPaidStatus();
        isDelivered = orderToCopy.getDeliveryStatus();
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
     * Sets the {@code timeCreated} of the {@code Order} that we are building.
     */
    public OrderBuilder withTimeCreated(LocalDateTime timeCreated) {
        this.timeCreated = timeCreated;
        return this;
    }

    /**
     * Sets the default {@code timeCreated} of the {@code Order} that we are building.
     */
    public OrderBuilder withDefaultTimeCreated() {
        this.timeCreated = DEFAULT_TIME_CREATED;
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
     * Sets the list of ordered items of the {@code Order} that we are building.
     */
    public OrderBuilder withItemList(List<ItemQuantityPair> itemList) {
        this.itemList = itemList;
        return this;
    }

    /**
     * Sets the list of ordered items of the {@code Order} that we are building.
     */
    public OrderBuilder withRecordedItemList(List<ItemQuantityPair> itemList) {
        List<ItemQuantityPair> recordedItems = this.itemList.stream()
                .map(ItemQuantityPair::getImmutableItemCopy).collect(Collectors.toList());
        this.itemList = recordedItems;
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
     * Sets the delivery status of the {@code Order} that we are building.
     */

    public OrderBuilder withPaidStatus(Boolean status) {
        this.isPaid = status;
        return this;
    }

    /**
     * Sets the payment status of the {@code Order} that we are building.
     */
    public OrderBuilder withDeliveredStatus(Boolean status) {
        this.isDelivered = status;
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
        return new Order(name, phone, email, address, timeCreated, itemList, isPaid, isDelivered);
    }
}
