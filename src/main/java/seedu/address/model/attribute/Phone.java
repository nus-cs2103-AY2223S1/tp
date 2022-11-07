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
    public static final String MESSAGE_CONSTRAINTS =
        "Phone numbers can contain country code, region code and numbers.\n"
            + "Optional Country code can begin with a optional '+' and followed by at most 4 digits\n"
            + "Optional Area code can be at most 4 digits long\n"
            + "Compulsary Phone number must be minimally 3 digits long\n";
    public static final String VALIDATION_REGEX = "(?:\\+?\\d{1,4} )?(?:\\d{1,4} )?\\d{3,}";
    public final String value;

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public Phone(String phone) {
        super(TYPE, phone.replaceAll("\\s+", " ").trim());
        requireNonNull(phone);
        checkArgument(isValidPhone(phone), MESSAGE_CONSTRAINTS);
        value = phone;
    }

    /**
     * Returns true if a given string is a valid phone number.
     */
    public static boolean isValidPhone(String test) {
        test = test.replaceAll("\\s+", " ").trim();
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public Prefix getPrefix() {
        return PREFIX_PHONE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (o instanceof Phone) {
            Phone otherPhone = (Phone) o;
            return value.equals(otherPhone.value);
        }
        return false;
    }
}
