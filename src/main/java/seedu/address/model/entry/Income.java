package seedu.address.model.entry;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an income entry.
 */
public class Income extends Entry {
    /**
     * Every field must be present and not null.
     */
    public Income(Description description, Date date, Amount amount, Set<Tag> tags) {
        super(description, date, amount, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDescription())
                .append("; Date: ")
                .append(getDate())
                .append("; Amount: ")
                .append(getAmount());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }
}
