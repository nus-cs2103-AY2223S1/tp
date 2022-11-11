package seedu.pennywise.model.entry;

/**
 * Represents an income entry.
 */
public class Income extends Entry {
    /**
     * Every field must be present and not null. This condition is verified in the parent {@code Entry} constructor.
     */
    public Income(Description description, Date date, Amount amount, Tag tag) {
        super(description, date, amount, tag);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Date: ")
                .append(getDate())
                .append("; Amount: ")
                .append(getAmount())
                .append("; Tag: ")
                .append(getTag());

        return builder.toString();
    }
}
