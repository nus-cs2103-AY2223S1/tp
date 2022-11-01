package seedu.address.model.attribute;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidAddress(String)}
 */
public class Address extends AbstractAttribute<String> implements PrefixedAttribute {

    public static final String TYPE = "Address";
    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace, otherwise " " (a blank string)
     * becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public Address(String address) {
        super(TYPE, address);
        checkArgument(isValidAddress(address), MESSAGE_CONSTRAINTS);
        value = address;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public Prefix getPrefix() {
        return PREFIX_ADDRESS;
    }
}
