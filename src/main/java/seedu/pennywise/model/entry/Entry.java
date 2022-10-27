package seedu.pennywise.model.entry;

import static seedu.pennywise.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents an {@code Entry} in the PennyWise application.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Entry {
    // Identity fields
    private final Description description;
    // Data fields
    private final Date date;
    private final Amount amount;
    private final Tag tag;

    /**
     * Every field must be present and not null.
     */
    public Entry(Description description, Date date, Amount amount, Tag tag) {
        // Check the preconditions
        requireAllNonNull(date, amount, description, tag);
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.tag = tag;
    }

    // Getters for Entries
    public Description getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public LocalDate getLocalDate() {
        return date.getLocalDate();
    }

    public YearMonth getYearMonth() {
        return date.getYearMonth();
    }

    public String getFormattedDate(DateTimeFormatter formatter) {
        return date.getLocalDate().format(formatter);
    }

    public Amount getAmount() {
        return amount;
    }

    public Double getAmountValue() {
        return amount.getValue();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(description, date, amount, tag);
    }


    /**
     * Returns true if both Entries have the same description.
     * This defines a weaker notion of equality between two descriptions.
     */
    public boolean isSameEntry(Entry otherEntry) {
        if (otherEntry == this) {
            return true;
        }
        if (!(otherEntry instanceof Entry)) {
            return false;
        }

        Entry otherEntryCopy = (Entry) otherEntry;
        return otherEntryCopy.getDescription().equals(getDescription())
                && otherEntryCopy.getDate().equals(getDate())
                && otherEntryCopy.getAmount().equals(getAmount())
                && otherEntryCopy.getTag().equals(getTag());
    }

    /**
     * Returns true if both entries have the same identity and data fields.
     * This defines a stronger notion of equality between two entries.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Entry)) {
            return false;
        }

        Entry otherEntry = (Entry) other;
        return otherEntry.getDescription().equals(getDescription())
                && otherEntry.getDate().equals(getDate())
                && otherEntry.getAmount().equals(getAmount())
                && otherEntry.getTag().equals(getTag());
    }
}
