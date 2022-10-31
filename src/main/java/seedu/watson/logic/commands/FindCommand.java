package seedu.watson.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.watson.commons.core.Messages;
import seedu.watson.logic.commands.exceptions.CommandException;
import seedu.watson.model.Model;
import seedu.watson.model.student.FindCommandPredicate;

/**
 * Finds and lists all persons in watson book whose name contains any of the argument keywords.
 * Parameters such as "watson/", "phone/", "class/" etc. can be added to further filter results.
 * Keyword matching is case-insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons matching the specified criteria "
                                               + "the specified keywords (case-insensitive) and displays them"
                                               + " as a list with index numbers.\n"
                                               + "Parameters: n/NAMES (OPTIONAL) "
                                               + "c/CLASS (OPTIONAL) s/SUBJECT (OPTIONAL)\n"
                                               + "However, the command should not be blank.\n"
                                               + "Example: " + COMMAND_WORD + " n/alice bob charlie\n"
                                               + "Example: " + COMMAND_WORD + " n/alice bob charlie c/1A s/English";

    private final FindCommandPredicate predicate;

    /**
     * Creates a FindCommand to find the specified {@code Student} with a given {@code FindCommandPredicate}.
     * @param predicate The predicate to filter the list of persons with.
     */
    public FindCommand(FindCommandPredicate predicate) {
        // Defensive programming - enforcing compulsory associations
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
               || (other instanceof FindCommand // instanceof handles nulls
                   && this.predicate.equals(((FindCommand) other).predicate)); // state check
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
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }
}
