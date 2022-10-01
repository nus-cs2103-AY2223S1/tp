package seedu.address.model.customer;

/**
 * Factory for handling creation of various {@code NullableAddress} instances
 * given a {@code String} or {@code Optional<String>}.
 */
public class AddressFactory {
    public static final NullableAddress EMPTY_ADDRESS = new EmptyAddress();

    /**
     * Returns an Address from a String.
     *
     * @param address A valid address.
     * @return The address as a NullableAddress.
     */
    public static Address fromString(String address) {
        return new Address(address);
    }

}
