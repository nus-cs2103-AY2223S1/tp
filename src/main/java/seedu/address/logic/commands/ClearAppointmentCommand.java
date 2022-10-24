package seedu.address.logic.commands;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * Clears the appointment field for a specified user.
 */
public class ClearAppointmentCommand extends Command {

    public static final String COMMAND_WORD = "apptcl";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Clears the appointment of the patient specified.\n"
            + "Parameters: INDEX "
            + "Example: apptcl 1";

    public static final String MESSAGE_SUCCESS = "Appointment cleared!";

    private final Index index;

    public ClearAppointmentCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = CommandUtil.prepareFilteredList(model, index);

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getBirthdate(),
                personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(),
                personToEdit.getRecordList(), Appointment.of(Appointment.NO_APPOINTMENT_SCHEDULED));

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ClearAppointmentCommand // instanceof handles nulls
                && index.equals(((ClearAppointmentCommand) other).index)); // state check
    }
}
