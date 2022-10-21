package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Finds and lists all appointments according to the prefix input by the user.
 */
public class FindAppointmentCommand extends Command {
    public static final CommandWord COMMAND_WORD =
            new CommandWord("findappointment", "fa");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all appointments whose names/medical test/"
            + "slot/doctor contains any of the specified keywords/number and displays them as a list with index"
            + " numbers.\n"
            + "Parameters: prefix, KEYWORD/Number [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " n/alice \n"
            + "Example: " + COMMAND_WORD + " t/x-ray \n"
            + "Example: " + COMMAND_WORD + " s/2022 \n"
            + "Example: " + COMMAND_WORD + " d/Dr. Tan";

    private final Predicate<Appointment> predicate;

    public FindAppointmentCommand(Predicate<Appointment> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredAppointmentList(this.predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENTS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAppointmentCommand // instanceof handles nulls
                && predicate.equals(((FindAppointmentCommand) other).predicate)); // state check
    }
}
