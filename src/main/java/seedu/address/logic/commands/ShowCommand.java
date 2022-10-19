package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.DayIsKeywordPredicate;

/**
 * Finds and lists all persons in address book whose session contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all students whose sessions contain any of "
            + "the specified day (case-insensitive) and displays them as a list sorted according to the session "
            + "timings with earliest at the top.\n"
            + "Parameters: Day (Mon, Tue, Wed, Thu, Fri, Sat, Sun)\n"
            + "Example: " + COMMAND_WORD + " Mon";

    private final DayIsKeywordPredicate predicate;
    private final String keyword;

    public ShowCommand(String keyword) {
        predicate = new DayIsKeywordPredicate(keyword);
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setDayView();
        model.updateFilteredPersonList(predicate);
        model.updateTimeSlots(keyword);
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
