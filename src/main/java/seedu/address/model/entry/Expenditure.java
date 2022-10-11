package seedu.address.model.entry;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents an Expenditure in the penny wise application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Expenditure extends Entry {
    /**
     * Every field must be present and not null.
     */
    public Expenditure(Description description, Date date, Amount amount, Set<Tag> tags) {
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
