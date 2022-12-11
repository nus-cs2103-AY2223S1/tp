package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.foodrem.model.item.itemvalidators.ItemBoughtDateValidator;

/**
 * Represents an item bought date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemBoughtDate extends ItemDate {

    private static final ItemBoughtDate NOT_SET_BOUGHT_DATE = new ItemBoughtDate(LocalDate.MIN);

    /**
     * Constructs an boughtDate.
     *
     * @param date a localDate that represents the boughtDate of the
     *             format {@link ItemDate#DATE_PATTERN_REGEX}
     */
    private ItemBoughtDate(LocalDate date) {
        super(date);
    }

    /**
     * Produces a boughtDate object.
     *
     * @param dateString a string that represents the boughtDate of the
     *                   format {@link ItemDate#DATE_PATTERN_REGEX}
     */
    public static ItemBoughtDate of(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            return NOT_SET_BOUGHT_DATE;
        }
        ItemBoughtDateValidator.validate(dateString);
        return new ItemBoughtDate(LocalDate.parse(dateString, ItemDate.DATE_FORMATTER));
    }

    /**
     * Returns true if the bought date is not set, false otherwise.
     */
    public boolean isNotSet() {
        return this == NOT_SET_BOUGHT_DATE;
    }

    /**
     * Returns {@code true} if both {@link ItemBoughtDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other != NOT_SET_BOUGHT_DATE
                && other instanceof ItemBoughtDate
                && getDate().equals(((ItemBoughtDate) other).getDate()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this == NOT_SET_BOUGHT_DATE ? "" : super.toString();
    }
}
