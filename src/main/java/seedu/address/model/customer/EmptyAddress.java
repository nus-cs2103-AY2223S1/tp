package seedu.address.model.customer;

/**
 * Represents an empty Customer's address in the address book.
 * Only used as a placeholder value when a new entry is created.
 */
public class EmptyAddress extends NullableAddress {
    public final String value = "";

    public EmptyAddress() {
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof EmptyAddress;
    }


    @Override
    public String toString() {
        return "No address specified.";
    }
}
