package seedu.address.model.customer;

/**
 * Represents a Customer's address in the address book.
 * It can only be null when the user creates a new customer entry without the address field.
 */
public abstract class NullableAddress {
    public abstract String getValue();

    public abstract boolean isEmpty();

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }
}
