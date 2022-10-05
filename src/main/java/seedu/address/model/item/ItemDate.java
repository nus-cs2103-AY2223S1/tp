package seedu.address.model.item;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class ItemDate {
    public static final String MESSAGE_CONSTRAINTS =
            "Dates must follow the format yyyy-mm-dd or dd-mm-yyyy.";

    public static final String VALIDATION_REGEX = "yyyy-MM-dd";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(VALIDATION_REGEX);

    public static final String DATE_OUTPUT_PATTERN = "dd/MM/yyyy";

    public final LocalDate itemDate;

    public ItemDate(String unparsedDate) {
        requireNonNull(unparsedDate);
         checkArgument(isValidItemDatetime(unparsedDate), MESSAGE_CONSTRAINTS);
        this.itemDate = getDatetimeFormat(unparsedDate);
    }

    public static boolean isValidItemDatetime(String unparsedDatetime) {
        DateValidator validator = new DateValidator(formatter);
        return validator.isValid(unparsedDatetime);
    }

    public LocalDate getDatetimeFormat(String unparsedDatetime) {
        return LocalDate.parse(unparsedDatetime, formatter);
    }

    public String getStringFromDatetime(LocalDate date) {
        return date.format(
                DateTimeFormatter.ofPattern(DATE_OUTPUT_PATTERN));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ItemDate // instanceof handles nulls
                && itemDate.equals(((ItemDate) other).itemDate)); // state check
    }

    @Override
    public int hashCode() {
        return itemDate.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return getStringFromDatetime(this.itemDate);
    }
}
