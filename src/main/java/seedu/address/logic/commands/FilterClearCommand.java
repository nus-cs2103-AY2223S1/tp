package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Removes filter applied to address book based on arguments provided.
 */
public class FilterClearCommand extends FilterCommand {

    public static final String COMMAND_SPECIFIER = "clear";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes filter of the specified name"
            + "the specified keywords (case-insensitive) and displays the contacts without the filter applied \n"
            + "Parameters: " + PREFIX_NAME + "NAME ...\n" + "Example: " + COMMAND_WORD + COMMAND_SPECIFIER + "n=alice";

    public FilterClearCommand(NameContainsKeywordsPredicate predicate) {
        super(predicate);
    }

    public FilterClearCommand() {
        super(null);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.removeFilterFromFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterClearCommand // instanceof handles nulls
                        && predicate.equals(((FilterClearCommand) other).predicate)); // state check
    }
}
