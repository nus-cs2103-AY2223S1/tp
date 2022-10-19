package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.time.LocalDate;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.StartDateWithinTimeFramePredicate;

public class ViewUpcomingEventCommand extends EventCommand {
    public static final String COMMAND_OPTION = "u";

    public static final String MESSAGE_INVALID_EVENT_UPCOMING_DAYS =
            "The days provided is invalid (must be a positive integer)\n%1$s";
    public static final String MESSAGE_MISSING_DAYS = "Days must be specified!\n%1$s";
    public static final String MESSAGE_SUCCESS = "You have these upcoming events in the next %1$s days!\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION
            + ": Lists the events that are occurring in the next specified days.\n"
            + "Parameters: DAYS (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION + COMMAND_OPTION + " 5";



    private final int days;
    private final LocalDate currentDate;
    private final LocalDate endDate;

    public ViewUpcomingEventCommand(int days) {
        this.days = days;
        this.currentDate = java.time.LocalDate.now();
        this.endDate = currentDate.plusDays(days);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredEventList(new StartDateWithinTimeFramePredicate(currentDate, endDate));
        return new CommandResult(String.format(MESSAGE_SUCCESS, days));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewUpcomingEventCommand)) {
            return false;
        }

        // state check
        ViewUpcomingEventCommand otherViewUpcomingEventCommand = (ViewUpcomingEventCommand) other;

        return days == otherViewUpcomingEventCommand.days
                && currentDate.isEqual(otherViewUpcomingEventCommand.currentDate)
                && endDate.isEqual(otherViewUpcomingEventCommand.endDate);
    }
}
