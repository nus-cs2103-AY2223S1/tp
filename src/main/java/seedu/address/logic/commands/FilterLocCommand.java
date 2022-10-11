package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.LocationContainsKeywordsPredicate;

/**
 * Filters and lists all persons in address book whose locations match the argument keyword.
 * Keyword matching is case-insensitive.
 */
public class FilterLocCommand extends Command {
    public static final String COMMAND_WORD = "filterLoc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all persons who are tagged: "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Singapore";

    private final LocationContainsKeywordsPredicate predicate;

    public FilterLocCommand(LocationContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 10)); //TODO Uncomment
//        model.updateFilteredPersonList(predicate);
//        return new CommandResult(
//                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterLocCommand // instanceof handles nulls
                && predicate.equals(((FilterLocCommand) other).predicate)); // state check
    }
}
