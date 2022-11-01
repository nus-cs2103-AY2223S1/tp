package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Adds an appointment to a person in the patient list.
 */
public class AddAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "appt";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an appointment to the patient specified.\n"
            + "Parameters: "
            + "INDEX "
            + PREFIX_DATE + "APPOINTMENT_DATE_TIME "
            + "Example: " + COMMAND_WORD + " "
            + "INDEX "
            + PREFIX_DATE + "01-01-2023 1200 (must be formatted in dd-MM-yyyy HHmm)";

    public static final String MESSAGE_SUCCESS = "New appointment added for %1$s";
    public static final String DATE_MISSING = "No date & time given! "
            + "Appointments must have date & time, formatted in d/dd-MM-yyyy HHmm.";

    private final Index index;
    private final String appointmentDate;

    /**
     * Constructs an AddAppointmentCommand to add an appointment for a patient.
     *
     * @param index           Index of the patient.
     * @param appointmentDate Date of the appointment.
     */
    public AddAppointmentCommand(Index index, String appointmentDate) {
        requireAllNonNull(index, appointmentDate);

        this.index = index;
        this.appointmentDate = appointmentDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = CommandUtil.prepareFilteredList(model, index);

        if (!Appointment.isFutureDate(appointmentDate)) {
            throw new CommandException(Appointment.MESSAGE_DATE_PAST);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getBirthdate(),
                personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(),
                personToEdit.getRecordList(), Appointment.of(appointmentDate));

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                personToEdit.getName().toString()) + ": " + appointmentDate);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && index.equals(((AddAppointmentCommand) other).index)
                && appointmentDate.equals(((AddAppointmentCommand) other).appointmentDate));
    }
}
