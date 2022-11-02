package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Select the given appointment and filter appointment list by given appointment.
 */
public class SelectAppointmentCommand extends Command {
    public static final CommandWord COMMAND_WORD =
            new CommandWord("selectappointment", "sla");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects the indicated appointment and shows the "
            + "bill for that appointment, if any.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Selected Appointment: %1$s";

    private final Index index;

    /**
     * @param index of the appointment in the filtered appointment list to select
     */
    public SelectAppointmentCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
        }

        Appointment appointmentToSelect = lastShownList.get(index.getZeroBased());

        model.selectAppointment(appointmentToSelect);
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointmentToSelect));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SelectAppointmentCommand)) {
            return false;
        }

        SelectAppointmentCommand e = (SelectAppointmentCommand) other;
        return index.equals(e.index);
    }
}
