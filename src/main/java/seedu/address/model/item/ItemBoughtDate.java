package seedu.address.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.item.itemvalidator.ItemBoughtDateValidator;

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
