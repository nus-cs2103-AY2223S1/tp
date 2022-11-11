package seedu.address.model.attribute;

import seedu.address.logic.parser.Prefix;

/**
 * An interface to mean that the attribute has a prefix
 */
public interface PrefixedAttribute {
    /**
     * Gets the prefix of this attribute
     */
    Prefix getPrefix();
}
