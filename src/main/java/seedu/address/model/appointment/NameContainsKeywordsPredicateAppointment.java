package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class NameContainsKeywordsPredicateAppointment implements Predicate<Appointment> {
    private final String keywords;

    public NameContainsKeywordsPredicateAppointment(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getName().fullName.toLowerCase().contains(keywords.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicateAppointment // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicateAppointment) other).keywords)); // state check
    }

}

