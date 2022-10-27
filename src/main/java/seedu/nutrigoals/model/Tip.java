package seedu.nutrigoals.model;

import static java.util.Objects.requireNonNull;
import static seedu.nutrigoals.commons.util.AppUtil.checkArgument;

/**
 * Represents a tip, that will be shown to the user.
 * Developer-use only, to create tips that will be stored internally.
 */
public class Tip {
    public static final String MESSAGE_CONSTRAINTS =
            "There is an error in the input tip, please fix it";
    private final String tip;

    /**
     * Constructor for a tip object.
     * @param tip the input tip string.
     */
    public Tip(String tip) {
        requireNonNull(tip);
        checkArgument(isValidTip(tip), MESSAGE_CONSTRAINTS);
        this.tip = tip;
    }

    /**
     * Returns true if given String is valid tip input
     * @param tip
     * @return
     */
    public static boolean isValidTip(String tip) {
        if (!tip.isEmpty() && (tip instanceof String)) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return tip;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Tip) {
            return this.tip.equals(((Tip) other).tip);
        }
        return false;
    }
}
