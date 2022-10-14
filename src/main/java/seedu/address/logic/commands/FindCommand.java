package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommand extends Command{

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons matching the specified criteria "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]... subj/SUBJECT (OPTIONAL)\n"
            + "Example: " + COMMAND_WORD + " names/alice bob charlie\n"
            + "Example: " + COMMAND_WORD + " names/alice bob charlie subj/English";

    private final NameContainsKeywordsPredicate predicate;

    public FindCommand(NameContainsKeywordsPredicate predicate) {
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
        //return new CommandResult(getMessageForPersonListShownSummary(model.getFilteredPersonList().size()));
        return null;
    }
}
