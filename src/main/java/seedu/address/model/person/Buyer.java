package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.tag.Tag;

/**
 * A class that represents a buyer that is also a person.
 */
public class Buyer extends Person {

    private final List<Order> orders = new ArrayList<>();

    /**
     * Constructs a Buyer object.
     *
     * @param personCategory By default, it should be PersonCategory.Buyer
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param tags The tags of this person.
     * @param orders The orders that this buyer requests.
     */
    public Buyer(PersonCategory personCategory,
                 Name name,
                 Phone phone,
                 Email email,
                 Address address,
                 Set<Tag> tags,
                 List<Order> orders) {
        super(PersonCategory.BUYER, name, phone, email, address, tags);
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    /**
     * Constructs a Buyer object.
     * By default, it should be PersonCategory.Buyer
     *
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param tags The tags of this person.
     * @param orders The orders that this buyer requests.
     */
    public Buyer(Name name,
                 Phone phone,
                 Email email,
                 Address address,
                 Set<Tag> tags,
                 List<Order> orders) {
        super(PersonCategory.BUYER, name, phone, email, address, tags);
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Adds all orders in a List.
     *
     * @param orders The list of orders to be added.
     */
    public void addOrder(List<Order> orders) {
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    public void deleteOrder(Order order) {
        // TODO: implement this method
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Buyer) {
            Buyer otherBuyer = (Buyer) other;
            return super.equals(otherBuyer) && orders.equals(otherBuyer.getOrders());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPersonCategory(), getName(), getPhone(), getEmail(), getAddress(), getTags(), orders);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        builder.append(super.toString()).append(System.lineSeparator()).append(System.lineSeparator())
                .append("Order summary").append(System.lineSeparator());

        if (orders != null) {
            for (Order order : orders) {
                builder.append("======== Order ").append(i).append(" ========").append(System.lineSeparator())
                        .append(order.toString()).append(System.lineSeparator());
                i++;
            }
        }

        return builder.toString();
    }

}
