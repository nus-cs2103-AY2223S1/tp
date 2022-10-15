package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import java.util.List;

import seedu.address.commons.core.Messages;
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
            + PREFIX_DATE + "appointment_date\n"
            + "Example: " + COMMAND_WORD + " "
            + "INDEX "
            + PREFIX_DATE + "01-01-2000 1200 (must be formatted in dd-MM-yyyy HHmm)";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String DATE_MISSING = "No date given! "
            + "Appointments must have dates, formatted in d/ dd-MM-yyyy HHmm.";

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
        requireNonNull(model);

        if (model.isRecordListDisplayed()) {
            throw new CommandException(MESSAGE_ADDRESS_BOOK_COMMAND_PREREQUISITE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();

        // Check if index given is out of bounds
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(),
                personToEdit.getRecordList(), new Appointment(appointmentDate));

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && index.equals(((AddAppointmentCommand) other).index)
                && appointmentDate.equals(((AddAppointmentCommand) other).appointmentDate));
    }
}
