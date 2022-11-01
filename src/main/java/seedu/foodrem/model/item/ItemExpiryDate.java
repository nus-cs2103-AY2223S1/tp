package seedu.foodrem.model.item;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.foodrem.model.item.itemvalidators.ItemExpiryDateValidator;

/**
 * Represents an item expiry date in an {@link Item}.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class ItemExpiryDate extends ItemDate {

    private static final ItemExpiryDate NOT_SET_EXPIRY_DATE = new ItemExpiryDate(LocalDate.MIN);

    /**
     * Constructs an expiryDate.
     *
     * @param date a localDate that represents the expiryDate of the
     *             format {@link ItemDate#DATE_PATTERN_REGEX}
     */
    private ItemExpiryDate(LocalDate date) {
        super(date);
    }

    /**
     * Produces a expiryDate object.
     *
     * @param dateString a string that represents the expiryDate of the
     *                   format {@link ItemDate#DATE_PATTERN_REGEX}
     */
    public static ItemExpiryDate of(String dateString) {
        requireNonNull(dateString);
        if (dateString.isBlank()) {
            return NOT_SET_EXPIRY_DATE;
        }
        ItemExpiryDateValidator.validate(dateString);
        return new ItemExpiryDate(LocalDate.parse(dateString, ItemDate.DATE_FORMATTER));
    }

    /**
     * Returns true if the expiry date is not set, false otherwise.
     */
    public boolean isNotSet() {
        return this == NOT_SET_EXPIRY_DATE;
    }

    /**
     * Returns {@code true} if both {@link ItemExpiryDate} have the same date by
     * {@link LocalDate#equals(Object)}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other != NOT_SET_EXPIRY_DATE
                && other instanceof ItemExpiryDate
                && getDate().equals(((ItemExpiryDate) other).getDate()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this == NOT_SET_EXPIRY_DATE ? "" : super.toString();
    }
}
