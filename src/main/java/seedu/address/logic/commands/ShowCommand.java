package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book whose session contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all students whose sessions contain any of "
            + "the specified day (case-insensitive) and displays them as a list sorted according to the session "
            + "timings with earliest at the top.\n"
            + "Parameters: Day (Mon, Tues, Wed, Thurs, Fri, Sat, Sun)\n"
            + "Example: " + COMMAND_WORD + " Mon";


    // NameContainsKeywordsPredicate to be changed to DayContainsKeywordsPredicate
    private final NameContainsKeywordsPredicate predicate;

    // NameContainsKeywordsPredicate to be changed to DayContainsKeywordsPredicate
    public ShowCommand(NameContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                // Assumption: Each student has only one session on that day.
                String.format(Messages.MESSAGE_PERSONS_LISTED_ACCORDING_TO_DAY, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && predicate.equals(((ShowCommand) other).predicate)); // state check
    }
}
