package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.foodrem.model.item.itemvalidators.ItemBoughtDateValidator;

/**
 * Represents an item date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemBoughtDate {

    private static final String DATE_OUTPUT_PATTERN_REGEX = "yyyy-mm-dd";

    private final LocalDate itemDate;

    /**
     * Constructs an itemDate.
     *
     * @param dateString a string that represents the itemDate of the format
     */
    public ItemBoughtDate(String dateString) {
        requireNonNull(dateString);
        if (dateString.isEmpty()) {
            itemDate = LocalDate.now();
            return;
        }
        ItemBoughtDateValidator.validate(dateString);
        itemDate = LocalDate.parse(dateString);
    }

    /**
     * Returns true if both {@link ItemBoughtDate#itemDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemBoughtDate // instanceof handles nulls
                && itemDate.equals(((ItemBoughtDate) other).itemDate)); // state check
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
        return itemDate.compareTo(other.itemDate);
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
