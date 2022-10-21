package seedu.address.model.appointment;

import java.util.function.Predicate;

/**
 * Tests that a {@code Appointment}'s {@code Slot} matches the number given.
 */
public class SlotContainsNumberPredicateAppointment implements Predicate<Appointment> {
    private final String number;

    public SlotContainsNumberPredicateAppointment(String number) {
        this.number = number;
    }

    @Override
    public boolean test(Appointment appointment) {
        return appointment.getSlot().toString().contains(number);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SlotContainsNumberPredicateAppointment // instanceof handles nulls
                && number.equals(((SlotContainsNumberPredicateAppointment) other).number)); // state check
    }
}
