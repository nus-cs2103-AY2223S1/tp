package seedu.foodrem.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, immutable.
 */
public abstract class ItemDate {
    // Remember to change relevant messages displayed to users when changing the regex.
    public static final String DATE_PATTERN_REGEX = "dd-MM-uuuu";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter
            .ofPattern(DATE_PATTERN_REGEX);

    private final LocalDate date;

    protected ItemDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public int compareTo(ItemDate other) {
        return date.compareTo(other.date);
    }

    /**
     * Returns true if the given datetime object is after or on the same date.
     *
     * @param datetime The datetime object to compare against
     */
    public boolean isAfterOrOnDate(LocalDate datetime) {
        return date.isAfter(datetime.minusDays(1));
    }

    /**
     * Returns true if the given ItemDate object is after the date.
     *
     * @param itemDate The ItemDate object to compare against
     */
    public boolean isAfterDate(ItemDate itemDate) {
        return date.isAfter(itemDate.date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                && other instanceof ItemDate
                && date.equals(((ItemDate) other).date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return date.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return date.format(DATE_FORMATTER);
    }
}
