package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.item.itemvalidator.ItemExpiryDateValidator;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemExpiryDate {

    private static final String DATE_OUTPUT_PATTERN_REGEX = "yyyy-mm-dd";

    private final LocalDate itemDate;

    /**
     * Constructs an itemDate.
     *
     * @param dateString a string that represents the itemDate of the format
     */
    public ItemExpiryDate(String dateString) {
        requireNonNull(dateString);
        ItemExpiryDateValidator.validate(dateString);
        itemDate = LocalDate.parse(dateString);
    }

    /**
     * Returns true if both {@link ItemExpiryDate#itemDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ItemExpiryDate // instanceof handles nulls
            && itemDate.equals(((ItemExpiryDate) other).itemDate)); // state check
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return itemDate.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return itemDate.toString();
    }
}
