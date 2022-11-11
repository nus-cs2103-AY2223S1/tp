package seedu.address.logic.commands.getcommands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.GetCommand;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in checkUp whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class GetNameCommand extends GetCommand {

    public static final String NAME_PREFIX = "/n";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all persons whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: "
            + NAME_PREFIX + " NAME\n"
            + "Example: "
            + COMMAND_WORD + " "
            + NAME_PREFIX
            + " alice bob charlie";

    private final NameContainsKeywordsPredicate predicate;

    public GetNameCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetNameCommand // instanceof handles nulls
                && predicate.equals(((GetNameCommand) other).predicate)); // state check
    }
}
