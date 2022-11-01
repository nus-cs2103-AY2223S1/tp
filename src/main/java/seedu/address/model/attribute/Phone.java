package seedu.address.model.attribute;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Person's phone number in the address book. Guarantees: immutable; is valid as
 * declared in {@link #isValidPhone(String)}
 */
public class Phone extends AbstractAttribute<String> implements PrefixedAttribute {

    public static final String TYPE = "Phone";
    public static final String MESSAGE_CONSTRAINTS = "Phone numbers should only contain numbers,"
        + " and it should be at least 3 digits long";
    public static final String VALIDATION_REGEX = "\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        super(TYPE, phone);
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public Prefix getPrefix() {
        return PREFIX_PHONE;
    }
}
