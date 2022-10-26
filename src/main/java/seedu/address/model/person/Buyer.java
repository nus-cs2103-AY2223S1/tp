package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.UniqueId;
import seedu.address.model.order.Order;
import seedu.address.model.tag.Tag;

/**
 * Represents a buyer that is also a person.
 */
public class Buyer extends Person {

    private final List<UniqueId> orders = new ArrayList<>();

    /**
     * Constructs a Buyer object.
     *
     * @param personCategory By default, it should be PersonCategory.Buyer
     * @param name           The name of this person.
     * @param phone          The phone number in string.
     * @param email          The email, which will be checked against regex.
     * @param address        The address of this person, which will be checked against the regex.
     * @param tags           The tags of this person.
     * @param orders         The orders that this buyer requests.
     */
    public Buyer(PersonCategory personCategory,
                 Name name,
                 Phone phone,
                 Email email,
                 Address address,
                 Set<Tag> tags,
                 Collection<? extends UniqueId> orders) {
        super(PersonCategory.BUYER, name, phone, email, address, tags);
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    /**
     * Constructs a Buyer object.
     * By default, it should be PersonCategory.Buyer
     *
     * @param name    The name of this person.
     * @param phone   The phone number in string.
     * @param email   The email, which will be checked against regex.
     * @param address The address of this person, which will be checked against the regex.
     * @param tags    The tags of this person.
     * @param orders  The orders that this buyer requests.
     */
    public Buyer(Name name,
                 Phone phone,
                 Email email,
                 Address address,
                 Set<Tag> tags,
                 Collection<? extends UniqueId> orders) {
        super(PersonCategory.BUYER, name, phone, email, address, tags);
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    public List<UniqueId> getOrderIds() {
        return orders;
    }

    /**
     * Adds all orders in a Collection.
     *
     * @param orders The collection of orders to be added.
     */
    public void addOrders(Collection<UniqueId> orders) {
        if (orders != null) {
            this.orders.addAll(orders);
        }
    }

    public void deleteOrder(Order order) {
        // TODO: implement this method
    }

    /**
     * Compares a buyer with another buyer in default way in terms of the number of orders that they have.
     * @param buyer The other buyer being compared.
     * @return The method returns 0 if the buyer and the other buyer has the same number of orders.
     *      A value less than 0 is returned if the buyer has less order than the other buyer,
     *      and a value greater than 0 if the buyer has more order than the other buyer.
     */
    public int compareTo(Buyer buyer) {
        return this.orders.size() - buyer.orders.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Buyer) {
            Buyer otherBuyer = (Buyer) other;
            return super.equals(otherBuyer) && orders.equals(otherBuyer.getOrderIds());
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
        return super.toString();
    }
}
