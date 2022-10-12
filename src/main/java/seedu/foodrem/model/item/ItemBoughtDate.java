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

    public static final String BOUGHT_DATE_PATTERN_REGEX = "dd-MM-yyyy";
    public static final DateTimeFormatter BOUGHT_DATE_FORMATTER = DateTimeFormatter
            .ofPattern(BOUGHT_DATE_PATTERN_REGEX);
    private final LocalDate boughtDate;

    /**
     * Constructs an boughtDate.
     *
     * @param dateString a string that represents the boughtDate of the
     *                   format in ItemBoughtDateValidator.
     */
    public ItemBoughtDate(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            boughtDate = null;
            return;
        }
        ItemBoughtDateValidator.validate(dateString);
        boughtDate = LocalDate.parse(dateString, BOUGHT_DATE_FORMATTER);
    }

    /**
     * Returns true if both {@link ItemBoughtDate#boughtDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ItemBoughtDate)) {
            return false;
        }

        ItemBoughtDate date = (ItemBoughtDate) other;

        if (date.boughtDate == null && boughtDate == null) {
            return true;
        }

        return boughtDate.equals(((ItemBoughtDate) other).boughtDate);
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
        return boughtDate == null ? "" : boughtDate.format(BOUGHT_DATE_FORMATTER);
    }
}
