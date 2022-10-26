package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.foodrem.model.item.itemvalidators.ItemBoughtDateValidator;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemBoughtDate {
    // Remember to change relevant messages when changing the regex.
    public static final String BOUGHT_DATE_PATTERN_REGEX = "dd-MM-uuuu";
    public static final DateTimeFormatter BOUGHT_DATE_FORMATTER = DateTimeFormatter
            .ofPattern(BOUGHT_DATE_PATTERN_REGEX);

    private static final ItemBoughtDate NOT_SET_BOUGHT_DATE = new ItemBoughtDate(LocalDate.MIN);

    private final LocalDate boughtDate;

    /**
     * Constructs an boughtDate.
     *
     * @param date a localDate that represents the boughtDate of the
     *             format {@link ItemBoughtDate#BOUGHT_DATE_FORMATTER}
     */
    private ItemBoughtDate(LocalDate date) {
        boughtDate = date;
    }

    /**
     * Produces a boughtDate object.
     *
     * @param dateString a string that represents the boughtDate of the
     *                   format {@link ItemBoughtDate#BOUGHT_DATE_FORMATTER}
     */
    public static ItemBoughtDate of(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            return NOT_SET_BOUGHT_DATE;
        }
        ItemBoughtDateValidator.validate(dateString);
        return new ItemBoughtDate(LocalDate.parse(dateString, BOUGHT_DATE_FORMATTER));
    }

    /**
     * Returns true if the bought date is not set, false otherwise.
     */
    public boolean isNotSet() {
        return this == NOT_SET_BOUGHT_DATE;
    }

    /**
     * Returns {@code true} if both {@link ItemBoughtDate#boughtDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other != NOT_SET_BOUGHT_DATE
                && other instanceof ItemBoughtDate
                && boughtDate.equals(((ItemBoughtDate) other).boughtDate));
    }

    /**
     * Compares two item bought dates. The method returns 0 if the bought date is equal to the other
     * bought date.
     * A value less than 0 is returned if the bought date is less than the other bought date (earlier) and
     * a value greater than 0 if the bought date is greater than the other bought date (later).
     *
     * @param other The ItemBoughtDate to compare this ItemBoughtDate against.
     */
    public int compareTo(ItemBoughtDate other) {
        return boughtDate.compareTo(other.boughtDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return boughtDate.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this == NOT_SET_BOUGHT_DATE ? "" : boughtDate.format(BOUGHT_DATE_FORMATTER);
    }
}
