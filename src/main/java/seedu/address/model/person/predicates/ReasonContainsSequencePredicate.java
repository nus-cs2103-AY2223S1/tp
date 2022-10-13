package seedu.address.model.person.predicates;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.person.Appointment;

/**
 * Tests that an {@code Appointment}'s {@code reason} contains the sequence given; case-insensitive.
 */
public class ReasonContainsSequencePredicate implements Predicate<Appointment> {
    private final String sequence;

    public ReasonContainsSequencePredicate(String sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean test(Appointment appointment) {
        return StringUtil.containsIgnoreCase(appointment.getReason(), sequence);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReasonContainsSequencePredicate // instanceof handles nulls
                && sequence.equals(((ReasonContainsSequencePredicate) other).sequence)); // state check
    }
}
