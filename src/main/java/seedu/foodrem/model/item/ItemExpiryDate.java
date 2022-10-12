package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.foodrem.model.item.itemvalidator.ItemExpiryDateValidator;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemExpiryDate {

    public static final String EXPIRY_DATE_PATTERN_REGEX = "dd-MM-yyyy";
    public static final DateTimeFormatter EXPIRY_DATE_FORMATTER = DateTimeFormatter
            .ofPattern(EXPIRY_DATE_PATTERN_REGEX);
    private final LocalDate expiryDate;

    /**
     * Constructs an expiryDate.
     *
     * @param dateString a string that represents the expiryDate of the
     *                   format {@link ItemExpiryDate#EXPIRY_DATE_FORMATTER}
     */
    public ItemExpiryDate(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            expiryDate = null;
            return;
        }
        ItemExpiryDateValidator.validate(dateString);
        expiryDate = LocalDate.parse(dateString, EXPIRY_DATE_FORMATTER);
    }

    /**
     * Returns true if both {@link ItemExpiryDate#expiryDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItemExpiryDate)) {
            return false;
        }

        ItemExpiryDate date = (ItemExpiryDate) other;

        if (date.expiryDate == null && expiryDate == null) {
            return true;
        }

        return expiryDate.equals(((ItemExpiryDate) other).expiryDate);
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
        return expiryDate == null ? "" : expiryDate.format(EXPIRY_DATE_FORMATTER);
    }
}
