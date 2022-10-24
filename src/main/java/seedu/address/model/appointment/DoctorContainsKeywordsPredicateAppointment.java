package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code Doctor} matches any of the keywords given.
 */
public class DoctorContainsKeywordsPredicateAppointment implements Predicate<Appointment> {
    private final String keywords;

    public DoctorContainsKeywordsPredicateAppointment(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getDoctor().doctorName.toLowerCase().contains(keywords.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoctorContainsKeywordsPredicateAppointment // instanceof handles nulls
                && keywords.equals(((DoctorContainsKeywordsPredicateAppointment) other).keywords)); // state check
    }
}
