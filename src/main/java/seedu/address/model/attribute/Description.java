package seedu.address.model.attribute;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.parser.Prefix;

/**
 * Creates an attribute to represent a description of a display Item
 */
public class Description extends AbstractAttribute<String> implements PrefixedAttribute {

    public static final String TYPE = "Description";

    public Description(String string) {
        super(TYPE, string);
    }

    @Override
    public Prefix getPrefix() {
        return PREFIX_DESCRIPTION;
    }
}
