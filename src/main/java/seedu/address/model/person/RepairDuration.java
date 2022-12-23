package seedu.address.model.person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

//todo
// change to repairDuration
public class RepairDuration {
    public static final String MESSAGE_CONSTRAINTS =
            "Should be an integer from 1 - 99";
    // adapted from https://stackoverflow.com/questions/52165059/regex-for-singapore-vehicle-number
    public static final String VALIDATION_REGEX = "^[A-Za-z]{3}[\\d]{4}[A-Za-z]{1}$";
    public final Integer value;

    /**
     * Constructs a {@code repairDuration}.
     *
     * @param repairDuration A valid Singapore car repairDuration.
     */
    public RepairDuration(String repairDuration) {
        requireNonNull(repairDuration);
        checkArgument(isValidRepairDuration(repairDuration), MESSAGE_CONSTRAINTS);
        value = Integer.parseInt(repairDuration);
    }

    /**
     * Returns true if a given string is a valid car pickUpDate.
     */

    public static boolean isValidRepairDuration(String test) {
        return true;
//                test.matches(VALIDATION_REGEX);
    }

    public String getEndDate(Arrival arrDate){
        LocalDate endDate =  arrDate.value.plusDays(value);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return endDate.format(formatter);
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RepairDuration // instanceof handles nulls
                && value.equals(((RepairDuration) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

