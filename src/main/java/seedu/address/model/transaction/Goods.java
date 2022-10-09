package seedu.address.model.transaction;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Class to store the name of the goods transacted.
 */
public class Goods {
    public static final String MESSAGE_CONSTRAINTS =
            "Goods should only contain alphanumeric characters and spaces, and it should not be blank";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String goodsName;

    /**
     * Constructs a {@code Goods}.
     *
     * @param goodsName A valid goods name.
     */
    public Goods(String goodsName) {
        requireNonNull(goodsName);
        checkArgument(isValidName(goodsName), MESSAGE_CONSTRAINTS);
        this.goodsName = goodsName;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return goodsName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Goods // instanceof handles nulls
                && goodsName.equals(((Goods) other).goodsName)); // state check
    }
}
