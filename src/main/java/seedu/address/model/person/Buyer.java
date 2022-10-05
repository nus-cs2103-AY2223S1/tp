package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.tag.Tag;

public class Buyer extends Person {

    ArrayList<Order> orders = new ArrayList<>();

    public Buyer(Name name, Phone phone, Email email, Address address, Set<Tag> tags, ArrayList<Order> orders) {
        super(name, phone, email, address, tags);
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
    public String toString() {

    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orders);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Buyer) {
            Buyer otherBuyer = (Buyer) other;
            return super.equals(otherBuyer) && orders.equals(otherBuyer.orders);
        } else {
            return false;
        }
    }


}
