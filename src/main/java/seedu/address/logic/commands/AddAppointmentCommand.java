package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;
import seedu.address.model.util.MaximumSortedList;

/**
 * Adds appointment(s) for a particular client.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "aa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add appointment/s with a specific client "
            + "by the index number used in the displayed person list \n"
            + "Parameters: INDEX "
            + PREFIX_APPOINTMENT_DATE + "DATE_AND_TIME "
            + PREFIX_APPOINTMENT_LOCATION + "LOCATION\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_DATE + "21-Jan-2023 12:30 PM "
            + PREFIX_APPOINTMENT_LOCATION + "Jurong Point, Starbucks";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME = "You already have an appointment "
                                                                        + "scheduled on this date and time";
    public static final String MESSAGE_DATE_FIELD_NOT_INCLUDED = "Date field must be provided.";
    public static final String MESSAGE_MAXIMUM_NUMBER_OF_APPOINTMENTS = "You have already reached the "
            + "maximum number of appointments (" + MAXIMUM_NUM_OF_APPOINTMENTS + ") for this client";
    private final Index index;
    private final Appointment appointment;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Index index, Appointment appointment) {
        requireNonNull(index);
        requireNonNull(appointment);
        this.index = index;
        this.appointment = appointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personWithAppointmentToAdd = lastShownList.get(index.getZeroBased());
        MaximumSortedList<Appointment> appointmentSet = personWithAppointmentToAdd.getAppointments();

        if (model.hasPersonWithSameAppointmentDateTime(appointment)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT_DATE_TIME);
        }

        if (appointmentSet.size() == MAXIMUM_NUM_OF_APPOINTMENTS) {
            throw new CommandException(MESSAGE_MAXIMUM_NUMBER_OF_APPOINTMENTS);
        }

        appointmentSet.add(appointment);

        model.updateCalendarEventList();
        return new CommandResult(String.format(MESSAGE_SUCCESS, appointment));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && index.equals(((AddAppointmentCommand) other).index))
                && appointment.equals(((AddAppointmentCommand) other).appointment);
    }
}
