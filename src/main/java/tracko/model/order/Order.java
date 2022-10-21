package tracko.model.order;

import static tracko.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import tracko.commons.util.DateTimeUtil;

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
    private boolean isPaid;
    private boolean isDelivered;

    /**
     * Every field must be present and not null.
     */
    public Order(Name name, Phone phone, Email email, Address address, List<ItemQuantityPair> itemList,
                 boolean isPaid, boolean isDelivered) {
        requireAllNonNull(name, phone, email, address, itemList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.itemList = itemList;
        this.timeCreated = LocalDateTime.now();
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
    }

    /**
     * Every field must be present and not null.
     * To be used for reading order data from Storage
     */
    public Order(Name name, Phone phone, Email email, Address address, LocalDateTime timeCreated,
                 List<ItemQuantityPair> itemList, boolean isPaid, boolean isDelivered) {
        requireAllNonNull(name, phone, email, address, timeCreated, itemList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.timeCreated = timeCreated;
        this.itemList = itemList;
        this.isPaid = isPaid;
        this.isDelivered = isDelivered;
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

    public boolean getPaidStatus() {
        return isPaid;
    }

    public boolean getDeliveryStatus() {
        return isDelivered;
    }

    /**
     * Adds the given {@code ItemQuantityPair} to the order's item list. If a pair already exists with the same item,
     * updates the {@code Quantity} instead.
     * @param itemQuantityPair The given {@code ItemQuantityPair}.
     */
    public void addToItemList(ItemQuantityPair itemQuantityPair) {
        for (int i = 0; i < itemList.size(); i++) {
            ItemQuantityPair currentPair = itemList.get(i);
            if (currentPair.hasSameItem(itemQuantityPair)) {
                currentPair.setQuantity(itemQuantityPair.getQuantity());
                return;
            }
        }
        this.itemList.add(itemQuantityPair);
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public boolean isCompleted() {
        return isPaid && isDelivered;
    }

    public void setPaid() {
        this.isPaid = true;
    }

    public void setDelivered() {
        this.isDelivered = true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, timeCreated, itemList, isPaid, isDelivered);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Order)) {
            return false;
        }

        Order otherOrder = (Order) other;
        return otherOrder.getName().equals(getName())
            && otherOrder.getPhone().equals(getPhone())
            && otherOrder.getEmail().equals(getEmail())
            && otherOrder.getAddress().equals(getAddress())
            && otherOrder.getItemList().equals(getItemList())
            && otherOrder.getPaidStatus() == getPaidStatus()
            && otherOrder.getDeliveryStatus() == getDeliveryStatus();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + getName().fullName + "\n");
        sb.append("Phone: " + getPhone().value + "\n");
        sb.append("Email: " + getEmail().value + "\n");
        sb.append("Address: " + getAddress().value + "\n");
        sb.append("Time Created: " + getTimeCreated().format(DateTimeUtil.getFormat()) + "\n");
        sb.append("Item List: \n");
        for (ItemQuantityPair itemQuantityPair : getItemList()) {
            sb.append("\u2022 " + itemQuantityPair.toString() + "\n");
        }
        sb.append("Paid status: " + getPaidStatus() + "\n");
        sb.append("Delivery status: " + getDeliveryStatus() + "\n");
        return sb.toString();
    }

}
