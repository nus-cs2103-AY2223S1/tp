package seedu.address.testutil;

import seedu.address.model.attribute.AbstractAttribute;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.attribute.Email;
import seedu.address.model.attribute.Phone;

/**
 * A utility class containing a list of {@code Attribute} objects to be used in
 * tests.
 */
public class TypicalAttributes {

    public static final Attribute<?> PHONE = new Phone("95319531");
    public static final Attribute<?> EMAIL = new Email("example@gmail.com");
    public static final Attribute<?> AGE = new AbstractAttribute<>("Age", 20) { };
    public static final Attribute<?> POSITION = new AbstractAttribute<>("Position", "CEO") { };

}
