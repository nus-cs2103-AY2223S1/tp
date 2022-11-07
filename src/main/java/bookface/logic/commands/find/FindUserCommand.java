package bookface.logic.commands.find;

import static java.util.Objects.requireNonNull;

import bookface.commons.core.Messages;
import bookface.logic.commands.CommandResult;
import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.ObjectContainsKeywordsPredicate;
import bookface.model.person.Person;

/**
 * Finds a user from the user list.
 */
public class FindUserCommand extends FindCommand {

    public static final String COMMAND_WORD = "user";

    public static final String MESSAGE_USAGE = FindCommand.generateMessage(COMMAND_WORD,
            COMMAND_WORD + " alice bob");

    private final ObjectContainsKeywordsPredicate<Person, String> predicate;

    public FindUserCommand(ObjectContainsKeywordsPredicate<Person, String> predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindUserCommand // instanceof handles nulls
                && predicate.equals(((FindUserCommand) other).predicate)); // state check
    }
}
