package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.person.Person;

/**
 * Gets and displays all the previous appointments of a patient identified using it's displayed index from the address
 * book.
 */
public class GetPastAppointmentCommand extends GetCommand {

    public static final String PAST_APPOINTMENT_PREFIX = "/appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all past appointments of patient identified by "
            + "the index number and displays them from most recent to oldest.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PAST_APPOINTMENT_PREFIX + " 3";

    public static final String MESSAGE_OBTAIN_PAST_APPOINTMENTS_SUCCESS =
            "Obtained Past Appointments of Patient:\n%1$s";

    private final Index targetIndex;

    public GetPastAppointmentCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person selectedPatient = lastShownList.get(targetIndex.getZeroBased());
        List<PastAppointment> pastAppointments = selectedPatient.getPastAppointments();
        StringBuilder output = new StringBuilder();

        for (PastAppointment appointment : pastAppointments) {
            output.append(appointment.toString()).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_OBTAIN_PAST_APPOINTMENTS_SUCCESS, output));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetPastAppointmentCommand // instanceof handles nulls
                && targetIndex.equals(((GetPastAppointmentCommand) other).targetIndex)); // state check
    }
}
