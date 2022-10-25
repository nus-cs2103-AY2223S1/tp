package seedu.nutrigoals.model;

/**
 * Represents a tip
 */
public class Tip {
    private final String tip;

    public Tip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return tip;
    }
}
