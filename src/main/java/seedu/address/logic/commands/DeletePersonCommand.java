package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.FullNamePredicate;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using the displayed name from the address book.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "deleteperson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the specified person from the address book.\n"
        + "Parameters: NAME (must be exactly the same as person's name)\n"
        + "Example: " + COMMAND_WORD + " John Doe";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public static final String MESSAGE_INVALID_PERSON_NAME = "The person to delete cannot be found!";

    public static final String MESSAGE_PERSON_IN_EXISITING_GROUP =
        "The person specified is still in one or more groups!";

    private final FullNamePredicate predicate;

    public DeletePersonCommand(FullNamePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        List<Person> lastShownList = model.getFilteredPersonList();

        int targetIndex = -1;
        for (int i = 0; i < lastShownList.size(); i++) {
            Person currentPerson = lastShownList.get(i);
            if (predicate.test(currentPerson)) {
                targetIndex = i;
                break;
            }
        }
        if (targetIndex == -1) {
            throw new CommandException(MESSAGE_INVALID_PERSON_NAME);
        }

        Person personToDelete = lastShownList.get(targetIndex);

        if (personToDelete.getPersonGroups().size() > 0) {
            throw new CommandException(MESSAGE_PERSON_IN_EXISITING_GROUP);
        }

        model.deletePerson(personToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonCommand)) {
            return false;
        }

        // state check
        DeletePersonCommand e = (DeletePersonCommand) other;
        return predicate.equals((e.predicate));

    }







}
