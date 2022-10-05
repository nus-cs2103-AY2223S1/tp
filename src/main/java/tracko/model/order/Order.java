package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an Order in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Order {
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final LocalDateTime timeCreated;
    private final List<ItemQuantityPair> itemList;

    /**
     * Every field must be present and not null.
     */
    public Order(Name name, Phone phone, Email email, Address address, List<ItemQuantityPair> itemList) {
        requireAllNonNull(name, phone, email, address, itemList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.itemList = itemList;
        this.timeCreated = LocalDateTime.now();
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public List<ItemQuantityPair> getItemList() {
        return itemList;
    }

    public void addToItemList(ItemQuantityPair itemQuantityPair) {
        this.itemList.add(itemQuantityPair);
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }


}
