package seedu.uninurse.model.condition;

import static java.util.Objects.requireNonNull;

// basic Condition stub
public class Condition {

    public final String condition;

    /**
     * Constructs a {@code Condition}.
     *
     * @param condition A valid condition.
     */
    public Condition(String condition) {
        requireNonNull(condition);
        this.condition = condition;
    }
}
