package seedu.address.model.expenditure;

import seedu.address.model.tag.Tag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Expenditure {
    // Identity fields
    private final Description description;

    // Data fields
    private final Date date;
    private final Amount amount;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Expenditure( Description description, Date date, Amount amount, Set<Tag> tags) {
        requireAllNonNull(date, amount, description, tags);
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.tags.addAll(tags);
    }

    // Getters for Income
    public Description getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public Amount getAmount() {
        return amount;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }


    /**
     * Returns true if both Expenditures have the same description.
     * This defines a weaker notion of equality between two descriptions.
     */
    public boolean isSameExpense(Expenditure otherExpense) {
        if (otherExpense == this) {
            return true;
        }

        return otherExpense != null
                && otherExpense.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both expenditures have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Expenditure)) {
            return false;
        }

        Expenditure otherExpense = (Expenditure) other;
        return otherExpense.getDescription().equals(getDescription())
                && otherExpense.getDate().equals(getDate())
                && otherExpense.getAmount().equals(getAmount())
                && otherExpense.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, date, amount, tags);
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
