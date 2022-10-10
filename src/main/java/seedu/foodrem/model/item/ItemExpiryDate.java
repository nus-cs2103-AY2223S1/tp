package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import seedu.foodrem.model.item.itemvalidator.ItemBoughtDateValidator;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemExpiryDate {

    private static final String DATE_OUTPUT_PATTERN_REGEX = "dd-MM-yyyy";
    private static final String EXPIRY_DATE_NOT_SET_PLACEHOLDER = "Not Set";

    private final LocalDate expiryDate;

    /**
     * Constructs an expiryDate.
     *
     * @param dateString a string that represents the expiryDate of the
     *                   format {@link ItemExpiryDate#DATE_OUTPUT_PATTERN_REGEX}
     */
    public ItemExpiryDate(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            expiryDate = null;
            return;
        }
        ItemBoughtDateValidator.validate(dateString);
        expiryDate = LocalDate.parse(dateString);
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
        return expiryDate == null ? "" : expiryDate.toString();
    }

    /**
     * {@inheritDoc}
     */
    public String toListView() {
        String date = EXPIRY_DATE_NOT_SET_PLACEHOLDER;
        if (expiryDate != null) {
            date = expiryDate.format(DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN_REGEX));
        }
        return String.format("(Expiry Date: %s)", date);
    }
}
