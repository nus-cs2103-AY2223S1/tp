package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;

import java.time.LocalDate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.StartDateWithinTimeFramePredicate;

/**
 * Shows a list of all Events in the scheduler that occur in the next specified days.
 */
public class ViewUpcomingEventsCommand extends EventCommand {
    public static final String COMMAND_OPTION = "u";

    public static final String MESSAGE_HELP = "Lists events in NUScheduler that starts in the next specified "
            + "number of days.\n"
            + "i.e. Events starting today will not be included.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " DAYS";
    public static final String MESSAGE_INVALID_EVENT_UPCOMING_DAYS =
            "The days provided is invalid.\n%1$s";
    public static final String MESSAGE_MISSING_DAYS = "Days must be specified!\n%1$s";

    public static final String MESSAGE_SUCCESS_MULTIPLE_EVENTS = "You have these upcoming events ";
    public static final String MESSAGE_SUCCESS_SINGLE_EVENT = "You have 1 event ";
    public static final String MESSAGE_SUCCESS_NO_EVENTS = "You have no upcoming events ";
    public static final String MESSAGE_SUCCESS_TOMORROW = "tomorrow!\n";
    public static final String MESSAGE_SUCCESS_UPCOMING_DAYS = "in the next %1$s days!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Lists the upcoming events that starts in the next specified number of days, "
            + "i.e. Events starting today will not be included.\n"
            + "Parameters: DAYS (must be a positive integer less than 10000)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 5";



    private final int days;
    private final LocalDate currentDate;
    private final LocalDate endDate;
    private final StartDateWithinTimeFramePredicate predicate;

    /**
     * Constructor for ViewUpcomingEventsCommand
     */
    public ViewUpcomingEventsCommand(int days, StartDateWithinTimeFramePredicate predicate) {
        this.days = days;
        this.currentDate = java.time.LocalDate.now();
        this.endDate = currentDate.plusDays(days);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredEventList(new StartDateWithinTimeFramePredicate(currentDate, endDate));

        int numberOfEvents = model.getFilteredEventList().size();
        String messageNumberOfEvents = MESSAGE_SUCCESS_NO_EVENTS;
        String messageDay = MESSAGE_SUCCESS_TOMORROW;

        if (numberOfEvents == 1) {
            messageNumberOfEvents = MESSAGE_SUCCESS_SINGLE_EVENT;
        }

        if (numberOfEvents > 1) {
            messageNumberOfEvents = MESSAGE_SUCCESS_MULTIPLE_EVENTS;
        }

        if (days > 1) {
            messageDay = String.format(MESSAGE_SUCCESS_UPCOMING_DAYS, days);
        }

        String displayedMessage = messageNumberOfEvents + messageDay;
        return new CommandResult(displayedMessage);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewUpcomingEventsCommand)) {
            return false;
        }

        // state check
        ViewUpcomingEventsCommand otherViewUpcomingEventCommand = (ViewUpcomingEventsCommand) other;

        return days == otherViewUpcomingEventCommand.days
                && currentDate.isEqual(otherViewUpcomingEventCommand.currentDate)
                && endDate.isEqual(otherViewUpcomingEventCommand.endDate);
    }
}
