package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.core.index.UniqueId;

/**
 * Represents a deliverer.
 */
public class Deliverer extends Person {

    private final List<UniqueId> orders = new ArrayList<>();

    /**
     * Constructs a deliverer object.
     *
     * @param personCategory By default, it should be PersonCategory.Deliverer
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param orders The orders that this deliverer is dispatched.
     */
    public Deliverer(PersonCategory personCategory,
                     Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     List<UniqueId> orders) {
        super(PersonCategory.DELIVERER, name, phone, email, address);
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    /**
     * Constructs a deliverer object.
     * By default, it should be PersonCategory.Deliverer
     *
     * @param name The name of this person.
     * @param phone The phone number in string.
     * @param email The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param orders The orders that this deliverer is dispatched.
     */
    public Deliverer(Name name,
                     Phone phone,
                     Email email,
                     Address address,
                     List<UniqueId> orders) {
        super(PersonCategory.DELIVERER, name, phone, email, address);
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    /**
     * Gets the list of unique ids of the orders.
     * @return The list of unique ids.
     */
    public List<UniqueId> getOrders() {
        return orders;
    }

    /**
     * Compares a deliverer with another deliverer in default way in terms of the number of orders that they have.
     * @param deliverer The other buyer being compared.
     * @return The method returns 0 if the deliverer and the other deliverer has the same number of orders.
     *      A value less than 0 is returned if the deliverer has less order than the other deliverer,
     *      and a value greater than 0 if the deliverer has more order than the other deliverer.
     */
    public int compareTo(Deliverer deliverer) {
        return this.orders.size() - deliverer.orders.size();
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
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), orders);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
