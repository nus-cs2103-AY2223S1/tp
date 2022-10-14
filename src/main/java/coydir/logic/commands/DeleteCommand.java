package coydir.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import coydir.commons.core.Messages;
import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the database.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private final EmployeeId targetId;

    public DeleteCommand(EmployeeId targetid) {
        this.targetId = targetid;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToDelete;

        for (Person person : lastShownList) {
            if (person.getEmployeeId().equals(targetId)) {
                model.deletePerson(person);
                personToDelete = person;
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
            }
        }
        throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetId.equals(((DeleteCommand) other).targetId)); // state check
    }
}
