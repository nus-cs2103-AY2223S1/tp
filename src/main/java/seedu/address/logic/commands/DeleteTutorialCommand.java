package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class DeleteTutorialCommand extends Command{
    public static final String COMMAND_WORD = "deletetut";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tutorial identified by the index number used in the displayed tutorial list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Tutorial: %1$s";

    private final Index targetIndex;

    public DeleteTutorialCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
//        requireNonNull(model);
//        List<Person> lastShownList = model.getFilteredPersonList();
//
//        if (targetIndex.getZeroBased() >= lastShownList.size()) {
//            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
//        }
//
//        Person personToDelete = lastShownList.get(targetIndex.getZeroBased());
//        model.deletePerson(personToDelete);
//        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
        return new CommandResult("hello");
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTutorialCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTutorialCommand) other).targetIndex)); // state check
    }
}
