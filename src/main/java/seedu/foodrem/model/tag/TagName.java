package seedu.foodrem.model.tag;

import static java.util.Objects.requireNonNull;
import static seedu.foodrem.commons.util.AppUtil.checkArgument;

import seedu.foodrem.commons.util.StringUtil;

/**
 * Represents a Tag's name in FoodRem.
 * Guarantees: immutable; is valid as declared in {@link StringUtil#isValidString(String)}
 */
public class TagName {
    private static final String MESSAGE_FOR_NAME_IS_BLANK =
            "The tag name should not be blank.";

    // Validation for name length
    private static final int MAX_LENGTH = 20;
    private static final String MESSAGE_FOR_NAME_TOO_LONG =
            String.format("The tag name should not exceed %d characters", MAX_LENGTH);

    private final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public TagName(String name) {
        requireNonNull(name);

        boolean isNamePresent = !name.isBlank();
        checkArgument(isNamePresent, MESSAGE_FOR_NAME_IS_BLANK);
        boolean isNameLengthLessThanEqualMaxLength = name.length() <= MAX_LENGTH;
        checkArgument(isNameLengthLessThanEqualMaxLength, MESSAGE_FOR_NAME_TOO_LONG);

        checkArgument(StringUtil.isValidString(name), StringUtil.getInvalidCharactersMessage("tag name"));
        fullName = name;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return fullName;
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TagName
                && fullName.equals(((TagName) other).fullName));
    }

    /**
     * @inheritDoc
     */
    @Override
    public int hashCode() {
        return fullName.hashCode();
    }
}
