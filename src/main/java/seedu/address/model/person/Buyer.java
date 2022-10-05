package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.tag.Tag;

public class Buyer extends Person {

    private final ArrayList<Order> orders;


    public Buyer(Name name, Phone phone, Email email, Address address, Set<Tag> tags, ArrayList<Order> orders) {
        super(name, phone, email, address, tags);
        requireNonNull(orders);
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void deleteOrder() {
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
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getTags(), orders);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        builder.append(super.toString()).append(System.lineSeparator()).append(System.lineSeparator())
                .append("Order summary").append(System.lineSeparator());
        for (Order order : orders) {
            builder.append("======== Order ").append(i).append(" ========").append(System.lineSeparator())
                    .append(order.toString()).append(System.lineSeparator());
            i++;
        }
        return builder.toString();
    }

}
