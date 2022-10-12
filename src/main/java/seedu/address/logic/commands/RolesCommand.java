package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLES;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.position.Professor;

/**
 * Changes the roles of an existing professor in the address book.
 */
public class RolesCommand extends Command {
    public static final String COMMAND_WORD = "roles";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the roles of the Professor identified "
            + "by the index number used in the last person listing.\n"
            + "Multiple roles may be added and must be separated by a comma.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_ROLES
            + "[ROLES]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_ROLES + "Coordinator, Lecturer, Advisor";
    public static final String MESSAGE_PERSON_NOT_PROFESSOR = "The person to edit is not a Professor, there are no "
            + "roles to be edited.";
    public static final String MESSAGE_EDIT_ROLES_SUCCESS = "Edited roles to Professor: %1$s";

    private final Index index;
    private final String roles;

    /**
     * @param index of the professor in the filtered person list to edit the roles
     * @param roles of the professor to be updated to
     */
    public RolesCommand(Index index, String roles) {
        requireAllNonNull(index, roles);

        this.index = index;
        this.roles = roles;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        if (!(personToEdit.getPosition() instanceof Professor)) {
            throw new CommandException(MESSAGE_PERSON_NOT_PROFESSOR);
        }
        Professor currPosition = (Professor) personToEdit.getPosition();
        currPosition.setDetails(roles);
        Person editedPerson = personToEdit;

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on whether the roles are edited for
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        return String.format(MESSAGE_EDIT_ROLES_SUCCESS, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RolesCommand)) {
            return false;
        }

        // state check
        RolesCommand e = (RolesCommand) other;
        return index.equals(e.index)
                && roles.equals(e.roles);
    }
}
