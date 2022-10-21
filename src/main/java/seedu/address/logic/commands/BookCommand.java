package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REASON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECURRING_PERIOD;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Books an appointment for the given patient.
 */
public class BookCommand extends Command {

    public static final String COMMAND_WORD = "book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Books an appointment for the patient. "
            + "Parameters: INDEX (must be a positive integer)\n"
            + PREFIX_REASON + "REASON "
            + PREFIX_DATE + "DATE "
            + PREFIX_RECURRING_PERIOD + "PERIOD "
            + "Example: " + COMMAND_WORD + " 3 "
            + PREFIX_REASON + "Sore Throat "
            + PREFIX_DATE + "2022-10-12 16:30 "
            + PREFIX_RECURRING_PERIOD + "1Y2M3D";

    public static final String MESSAGE_BOOK_APPOINTMENT_SUCCESS = "Booked Appointment for Person: %1$s";

    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "This person already booked an appointment at this time";
    private final Appointment appointment;
    private final Index targetIndex;

    /**
     * Creates a BookCommand with the given index and appointment.
     *
     * @param targetIndex The given index to retrieve the patient.
     * @param appointment The given appointment to book for the patient.
     */
    public BookCommand(Index targetIndex, Appointment appointment) {
        requireNonNull(appointment);
        this.targetIndex = targetIndex;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToBookFor = lastShownList.get(targetIndex.getZeroBased());
        List<Appointment> appointments = personToBookFor.getAppointments();
        bookAppointment(appointments, appointment);
        appointment.setPatient(personToBookFor);

        model.addAppointment(appointment);
        return new CommandResult(String.format(MESSAGE_BOOK_APPOINTMENT_SUCCESS, personToBookFor));
    }

    private boolean hasSameAppointment(List<Appointment> appointments, Appointment appointment) {
        return appointments.stream().anyMatch(x -> x.isSameTime(appointment));
    }

    private void bookAppointment(List<Appointment> appointments, Appointment appointment) throws CommandException {
        if (hasSameAppointment(appointments, appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }
        appointments.add(appointment);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof BookCommand // instanceof handles nulls
                && targetIndex.equals(((BookCommand) other).targetIndex)
                && appointment.isSameAppointment(((BookCommand) other).appointment)); // state check
    }
}
