package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameMatchesKeywordPredicate;

/**
 * Finds the exact persons in address book whose name is the argument keywords.
 * Keyword matching is case insensitive.
 */
public class GoToCommand extends Command {

    public static final String COMMAND_WORD = "goto";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Go to specific person specified.\n"
            + "Parameters: KEYWORD keyword\n"
            + "Example: " + COMMAND_WORD + " Alex Yeoh";

    private final NameMatchesKeywordPredicate predicate;

    public GoToCommand(NameMatchesKeywordPredicate predicate) {
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
                || (other instanceof GoToCommand // instanceof handles nulls
                && predicate.equals(((GoToCommand) other).predicate)); // state check
    }
}
