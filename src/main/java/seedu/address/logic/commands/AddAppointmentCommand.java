package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_LOCATION;
import static seedu.address.logic.parser.EditPersonDescriptor.createEditedPersonByAddingAppointment;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.model.person.Person.MAXIMUM_NUM_OF_APPOINTMENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds appointment(s) for a particular client.
 */
public class AddAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "aa";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add appointment/s with a specific client "
            + "by the index number used in the displayed person list \n"
            + "Parameters: [INDEX] "
            + "[" + PREFIX_APPOINTMENT_DATE + "DATE AND TIME] "
            + "[" + PREFIX_APPOINTMENT_LOCATION + "LOCATION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_APPOINTMENT_DATE + "21-Jan-2023 12:30 PM "
            + PREFIX_APPOINTMENT_LOCATION + "Jurong Point, Starbucks";

    public static final String MESSAGE_SUCCESS = "New appointment added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "You have already scheduled this "
                                                                + "appointment for the client";
<<<<<<< HEAD

=======
    public static final String MESSAGE_DATE_FIELD_NOT_INCLUDED = "Date field must be provided.";
    public static final String MESSAGE_MAXIMUM_NUMBER_OF_APPOINTMENTS = "You have already reached the "
            + "maximum number of appointments (" + MAXIMUM_NUM_OF_APPOINTMENTS + ") for this client";
>>>>>>> musab_max_appointmnet
    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddAppointmentCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);
        assert(editPersonDescriptor.getName().isEmpty());
        assert(editPersonDescriptor.getAddress().isEmpty());
        assert(editPersonDescriptor.getPhone().isEmpty());
        assert(editPersonDescriptor.getEmail().isEmpty());
        assert(editPersonDescriptor.getTags().isEmpty());
        this.index = index;
        this.editPersonDescriptor = editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
<<<<<<< HEAD
        Person editedPerson = createEditedPersonByAddingAppointment(personToEdit, editPersonDescriptor);
=======
        Person editedPerson;

        try {
            editedPerson = createEditedPersonByAddingAppointments(personToEdit, editPersonDescriptor);
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }
>>>>>>> musab_max_appointmnet

        if (!personToEdit.isSamePerson(editedPerson) && model.hasPerson(editedPerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPOINTMENT);
        }

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAppointmentCommand // instanceof handles nulls
                && index.equals(((AddAppointmentCommand) other).index))
                && editPersonDescriptor.equals(((AddAppointmentCommand) other).editPersonDescriptor);
    }
}
