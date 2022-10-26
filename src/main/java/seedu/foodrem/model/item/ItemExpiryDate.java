package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.foodrem.model.item.itemvalidators.ItemExpiryDateValidator;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemExpiryDate {
    // Remember to change relevant messages displayed to users when changing the regex.
    public static final String EXPIRY_DATE_PATTERN_REGEX = "dd-MM-uuuu";
    public static final DateTimeFormatter EXPIRY_DATE_FORMATTER = DateTimeFormatter
            .ofPattern(EXPIRY_DATE_PATTERN_REGEX);

    private static final ItemExpiryDate NOT_SET_EXPIRY_DATE = new ItemExpiryDate(LocalDate.MIN);

    private final LocalDate expiryDate;

    /**
     * Constructs an expiryDate.
     *
     * @param date a string that represents the expiryDate of the
     *             format {@link ItemExpiryDate#EXPIRY_DATE_FORMATTER}
     */
    private ItemExpiryDate(LocalDate date) {
        expiryDate = date;
    }

    /**
     * Produces a expiryDate object.
     *
     * @param dateString a string that represents the expiryDate of the
     *                   format {@link ItemExpiryDate#EXPIRY_DATE_FORMATTER}
     */
    public static ItemExpiryDate of(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            return NOT_SET_EXPIRY_DATE;
        }
        ItemExpiryDateValidator.validate(dateString);
        return new ItemExpiryDate(LocalDate.parse(dateString, EXPIRY_DATE_FORMATTER));
    }

    /**
     * Returns true if the expiry date is not set, false otherwise.
     */
    public boolean isNotSet() {
        return this == NOT_SET_EXPIRY_DATE;
    }

    /**
     * Returns {@code true} if both {@link ItemExpiryDate#expiryDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other != NOT_SET_EXPIRY_DATE
                && other instanceof ItemExpiryDate
                && expiryDate.equals(((ItemExpiryDate) other).expiryDate));
    }

    /**
     * Compares two item expiry dates. The method returns 0 if the bought date is equal to the other
     * bought date.
     * A value less than 0 is returned if the bought date is less than the other bought date (earlier) and
     * a value greater than 0 if the bought date is greater than the other bought date (later).
     *
     * @param other The ItemExpiryDate to compare this ItemExpiryDate against.
     */
    public int compareTo(ItemExpiryDate other) {
        return expiryDate.compareTo(other.expiryDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return expiryDate.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this == NOT_SET_EXPIRY_DATE ? "" : expiryDate.format(EXPIRY_DATE_FORMATTER);
    }
}
