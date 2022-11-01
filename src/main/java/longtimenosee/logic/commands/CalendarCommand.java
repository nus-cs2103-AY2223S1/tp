package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.event.predicate.WithinNextSevenDays;

/**
 * Displays the user's upcoming events in the next 7 days
 */
public class CalendarCommand extends Command {

    public static final String COMMAND_WORD = "calendar";

    public static final String MESSAGE_SUCCESS = "Here are your events in the next 7 days";

    /**
     * Creates a CalendarCommand object, no attributes required
     */
    public CalendarCommand() {}

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.calendarView(); //TODO: This doesn't actually change the list I think?
        model.updateFilteredEventList(new WithinNextSevenDays()); //Change: Added new predicate
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CalendarCommand)) {
            return false;
        }

        return false;
        // no other object should be same, should always create new calendar command object
    }
}
