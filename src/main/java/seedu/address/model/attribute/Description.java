package seedu.address.model.attribute;

/**
 * Creates an attribute to represent a description of a display Item
 */
public class Description extends AbstractAttribute<String> {

    public static final String TYPE = "Description";

    public Description(String string) {
        super(TYPE, string);
    }
}
