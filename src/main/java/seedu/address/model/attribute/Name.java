package seedu.address.model.attribute;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.parser.Prefix;

/**
 * Represents a Attribute's name in the address book. Guarantees: immutable; is valid as declared in
 * {@link #isValidName(String)}
 */
public class Name extends AbstractAttribute<String> implements PrefixedAttribute {

    public static final String TYPE = "Name";
    public static final String MESSAGE_CONSTRAINTS = "Names should only contain alphanumeric "
        + "characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace, otherwise " " (a blank string)
     * becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[a-zA-Z][a-zA-Z0-9_\\- ]*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public Name(String name) {
        super(TYPE, name);
        checkArgument(isValidName(name), MESSAGE_CONSTRAINTS);
        fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public Prefix getPrefix() {
        return PREFIX_NAME;
    }
}
