package seedu.foodrem.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Represents a Tag's name in the FoodRem.
 * Guarantees: immutable; is valid as declared in {@link StringUtil#isValidString(String)}
 */
public class TagName {
    private final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TagName(String name) {
        requireNonNull(name);
        checkArgument(StringUtil.isValidString(name), StringUtil.getInvalidCharactersMessage("tag name"));
        fullName = name;
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagName
                && fullName.equals(((TagName) other).fullName));
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
