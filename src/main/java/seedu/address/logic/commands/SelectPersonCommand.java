package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Select the given person and filter appointment list by given person.
 */
public class SelectPersonCommand extends Command {
    public static final CommandWord COMMAND_WORD = new CommandWord("select", "sel");

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Selects the indicated patient and filter "
            + "the appointments list only containing selected person\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Selected Patient: %1$s";
    public static final String MESSAGE_NOT_SELECTED = "Index of person to select must be provided.";

    private final Index index;

    /**
     * @param index of the person in the filtered person list to select
     */
    public SelectPersonCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Person personToSelect = lastShownList.get(index.getZeroBased());

        model.updateFilteredAppointmentList(
                appointment -> appointment.getName().equals(personToSelect.getName()));
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToSelect));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SelectPersonCommand)) {
            return false;
        }

        SelectPersonCommand e = (SelectPersonCommand) other;
        return index.equals(e.index);
    }
}
