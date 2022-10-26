package seedu.address.model;

/**
 * Represents whether an entity is pinned.
 */
public class Pin {

    public static final String MESSAGE_CONSTRAINTS = "Pin can only take truth values";
    private boolean isPinned;

    public Pin(boolean isPinned) {
        this.isPinned = isPinned;
    }

    public boolean isPinned() {
        return this.isPinned;
    }

    public void togglePinned() {
        this.isPinned = !this.isPinned;
    }

    /**
     * Returns true if a given string is a valid pin.
     */
    public static boolean isValidPin(String pinString) {
        if (pinString.toUpperCase().equals("FALSE") || pinString.toUpperCase().equals("TRUE")) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Pin)) {
            return false;
        }

        return this.isPinned == ((Pin) other).isPinned;
    }
}
