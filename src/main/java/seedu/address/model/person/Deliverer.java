package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.order.Order;
import seedu.address.model.tag.Tag;

public class Deliverer extends Person {

    private final ArrayList<Order> orders;

    /**
     * Constructs a {@code Deliverer}.
     *
     * @param personCategory Category the deliverer belongs to.
     * @param name Name of the deliverer.
     * @param phone Phone of the deliverer.
     * @param email Email of the deliverer.
     * @param address Address of the deliverer.
     * @param tags Tags that the deliverer has.
     * @param orders Orders that the deliverer is in charge of.
     */
    public Deliverer(PersonCategory personCategory, Name name, Phone phone, Email email, Address address,
                     Set<Tag> tags, ArrayList<Order> orders) {
        super(personCategory, name, phone, email, address, tags);
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Deliverer) {
            Deliverer otherDeliverer = (Deliverer) other;
            return super.equals(otherDeliverer) && orders.equals(otherDeliverer.orders);
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
                .append("Orders received").append(System.lineSeparator());

        if (orders != null) {
            for (Order order : orders) {
                builder.append("======== Order ").append(i).append(" ========").append(System.lineSeparator())
                        .append("Buyer: ").append(order.getBuyer().getName()).append(System.lineSeparator())
                        .append(order.toString()).append(System.lineSeparator());
                i++;
            }
        }

        return builder.toString();
    }

}
