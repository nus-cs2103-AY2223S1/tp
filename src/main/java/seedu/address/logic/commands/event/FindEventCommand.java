package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.event.StartDateTimeContainsDatePredicate;
import seedu.address.model.event.TitleContainsKeywordsPredicate;

/**
 * Finds and lists all events in NUScheduler whose title contains any of the argument keywords, or if
 * inputs are dates, it will match the start dates equal to the input dates.
 * Keyword matching is case-insensitive.
 */
public class FindEventCommand extends EventCommand {

    public static final String COMMAND_OPTION = "f";
    public static final String MESSAGE_EVENT_LISTED_OVERVIEW = "1 event listed!";
    public static final String MESSAGE_NO_MATCH = "There are no matching results!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Finds all events whose start dates contain any of "
            + "the specified dates (if dates are provided) or events whose title "
            + "contains any of the specified keywords (case-insensitive) "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD_OR_DATE [MORE]...\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 11/10 12/10";

    public static final String MESSAGE_HELP = "Finds all events matching the dates or keywords.\n"
            + "Keywords are case-insensitive and will return partial matches.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " KEYWORDS_OR_DATE [MORE]";

    private final Predicate<Event> predicate;

    public FindEventCommand(TitleContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    public FindEventCommand(StartDateTimeContainsDatePredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredEventList(predicate);
        int size = model.getFilteredEventList().size();
        switch (size) {
        case 0:
            return new CommandResult(MESSAGE_NO_MATCH);
        case 1:
            return new CommandResult(MESSAGE_EVENT_LISTED_OVERVIEW);
        default:
            return new CommandResult(String.format(Messages.MESSAGE_EVENTS_LISTED_OVERVIEW, size));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindEventCommand // instanceof handles nulls
                && predicate.equals(((FindEventCommand) other).predicate)); // state check
    }
}
