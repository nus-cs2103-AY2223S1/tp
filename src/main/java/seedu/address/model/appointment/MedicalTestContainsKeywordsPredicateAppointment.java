package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code MedicalTest} matches any of the keywords given.
 */
public class MedicalTestContainsKeywordsPredicateAppointment implements Predicate<Appointment> {
    private final String keywords;

    public MedicalTestContainsKeywordsPredicateAppointment(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getMedicalTest().medicalTestName.toLowerCase().contains(keywords.toLowerCase());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MedicalTestContainsKeywordsPredicateAppointment // instanceof handles nulls
                && keywords.equals(((MedicalTestContainsKeywordsPredicateAppointment) other).keywords)); // state check
    }
}
