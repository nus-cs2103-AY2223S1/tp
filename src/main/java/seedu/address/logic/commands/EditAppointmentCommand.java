package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.EditPersonDescriptor.createEditedPersonByAddingAppointments;
import static seedu.address.logic.parser.EditPersonDescriptor.createEditedPersonByOverwritingAppointments;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.EditPersonDescriptor;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Edits the appointment details of an existing person in the address book.
 */
public class EditAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "ea";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Overwrites all appointment details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_DATE + "DATE]... \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE + "21-Jan-2023 12:30 PM ";

    public static final String MESSAGE_EDIT_APPOINTMENT_SUCCESS = "Edited Person's Appointment: %1$s";
    public static final String MESSAGE_NO_APPOINTMENT_TO_EDIT = "This client does not have an appointment to edit\n" +
                                                                "Use command \"aa\" to add appointment instead";
    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditAppointmentCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }


    public Index getIndex() {
        return index;
    }

    public EditPersonDescriptor getEditPersonDescriptor() {
        return editPersonDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = createEditedPersonByOverwritingAppointments(personToEdit, editPersonDescriptor);

        if (personToEdit.getAppointments().size() == 0){
            throw new CommandException(MESSAGE_NO_APPOINTMENT_TO_EDIT);
        }
        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_APPOINTMENT_SUCCESS, editedPerson));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditAppointmentCommand)) {
            return false;
        }

        // state check
        EditAppointmentCommand e = (EditAppointmentCommand) other;

        return index.equals(e.index)
               && editPersonDescriptor.equals(e.editPersonDescriptor);
    }
}
