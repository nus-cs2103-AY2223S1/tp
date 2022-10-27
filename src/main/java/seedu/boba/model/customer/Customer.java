package seedu.boba.model.customer;

import static seedu.boba.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.boba.model.tag.Tag;

/**
 * Represents a Customer in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Customer {

    public static final Tag BIRTHDAY_TAG = new Tag("BDAY");

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final BirthdayMonth birthdayMonth;
    private final Reward reward;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Customer(Name name, Phone phone, Email email, BirthdayMonth birthdayMonth, Reward reward, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, reward, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthdayMonth = birthdayMonth;
        this.reward = reward;
        this.tags.addAll(tags);
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

    public BirthdayMonth getBirthdayMonth() {
        return birthdayMonth;
    }

    public Reward getReward() {
        return reward;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds a birthday tag to a Customer.
     */
    public void addBirthdayTag() {
        if (!tags.contains(BIRTHDAY_TAG)) {
            this.tags.add(BIRTHDAY_TAG);
        }
    }

    /**
     * Removes the birthday tag from a Customer (if any).
     */
    public void removeBirthdayTag() {
        this.tags.remove(BIRTHDAY_TAG);
    }

    /**
     * Returns true if both persons have the same phone number or email address.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Customer otherCustomer) {
        if (otherCustomer == this) {
            return true;
        }

        return otherCustomer != null
                && (otherCustomer.getPhone().equals(getPhone())
                || otherCustomer.getEmail().equals(getEmail()));
    }

    public String getAllInfo() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(getPhone())
                .append(getEmail())
                .append(getBirthdayMonth())
                .append(getReward());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns true if both persons have the same identity and phone number/ email address.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Customer)) {
            return false;
        }

        Customer otherCustomer = (Customer) other;
        return otherCustomer.getPhone().equals(getPhone())
                || otherCustomer.getEmail().equals(getEmail());
    }

    /**
     * Compares if two Customers are strictly equals
     * two customers are strictly equals if all of their attributes are the same
     * @param other
     * @return
     */
    public boolean strictlyEquals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Customer)) {
            return false;
        }
        Customer otherCustomer = (Customer) other;
        return otherCustomer.getAllInfo().equals(getAllInfo());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, birthdayMonth, reward, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nBirthday Month: ")
                .append(getBirthdayMonth())
                .append("\nReward: ")
                .append(getReward());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("\nTags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
