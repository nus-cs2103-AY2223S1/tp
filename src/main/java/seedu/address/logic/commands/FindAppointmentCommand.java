package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

/**
 * Finds and lists all appointments according to the prefix input(s) by the user.
 */
public class FindAppointmentCommand extends Command {
    public static final CommandWord COMMAND_WORD =
            new CommandWord("findappointment", "fa");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments whose names/medical test/"
            + "slot/doctor contains any of the specified keywords/number and displays them as a list with index"
            + " numbers.\n"
            + "Multiple prefixes can be used at once to filter appointments.\n"
            + "Parameters: prefix(s), KEYWORD/Number [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice \n"
            + "Example: " + COMMAND_WORD + " t/x-ray \n"
            + "Example: " + COMMAND_WORD + " s/2022 \n"
            + "Example: " + COMMAND_WORD + " d/Dr. Tan\n"
            + "Example: " + COMMAND_WORD + " n/alice t/x-ray s/2022";

    private final Predicate<Appointment> predicate;

    /**
     * Creates a FindAppointmentCommand to find appointment(s) according to the prefix input(s).
     *
     * @param namePredicate Optional predicate to filter appointments by name.
     * @param testPredicate Optional predicate to filter appointments by medical test.
     * @param slotPredicate Optional predicate to filter appointments by slot.
     * @param doctorPredicate Optional predicate to filter appointments by doctor.
     */
    public FindAppointmentCommand(Optional<Predicate<Name>> namePredicate, Optional<Predicate<MedicalTest>>
            testPredicate, Optional<Predicate<Slot>> slotPredicate, Optional<Predicate<Doctor>> doctorPredicate) {
        this.predicate = appointment -> namePredicate.orElse(x -> true).test(appointment.getName())
                && testPredicate.orElse(x -> true).test(appointment.getMedicalTest())
                && slotPredicate.orElse(x -> true).test(appointment.getSlot())
                && doctorPredicate.orElse(x -> true).test(appointment.getDoctor());
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(this.predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, model.getFilteredAppointmentList()
                        .size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAppointmentCommand // instanceof handles nulls
                && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}
