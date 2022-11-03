package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DATE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_END_TIME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_START_TIME;

import java.util.List;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.event.Event;
import longtimenosee.model.event.exceptions.OverlapEventException;
import longtimenosee.model.event.exceptions.PersonNotFoundException;


/**
 * Adds an Event to the address book.
 */
public class AddEventCommand extends Command {
    public static final String COMMAND_WORD = "addEvent";
    public static final String MESSAGE_DUPLICATE_EVENT = "This event already exists in the LTNS";
    public static final String MESSAGE_OVERLAP_EVENT = "The event you would like to add overlaps with another event \n"
            + "Perhaps choose a different timing? ";
    public static final String MESSAGE_PERSON_NOT_FOUND = "The person you've selected currently does not "
            + "exist in our addressBook. \n Please choose another name!";

    public static final String MESSAGE_SUCCESS = "New Event added: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an Event to the LTNS. "
            + "Parameters: "
            + PREFIX_DESCRIPTION + "Description "
            + PREFIX_NAME + "Person Name "
            + PREFIX_DATE + "Date "
            + PREFIX_START_TIME + "Start time "
            + PREFIX_END_TIME + "End time "

            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DESCRIPTION + "Meet Clement at Noon "
            + PREFIX_NAME + "Clement Tan "
            + PREFIX_DATE + "2022-10-10 "
            + PREFIX_START_TIME + "12:00 "
            + PREFIX_END_TIME + "13:00 ";

    private final Event toAdd;
    /**
     * Creates an AddEventCommand to add the specified {@code Event}
     *
     * @param event
     */
    public AddEventCommand(Event event) {
        requireNonNull(event);
        toAdd = event;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasEvent(toAdd)) { //TODO: duplicate events?
            throw new CommandException(MESSAGE_DUPLICATE_EVENT);
        }
        if (model.hasEventOverlap(toAdd)) { //TODO: overlapping events?
            List<Event> clashingEvents = model.listEventsOverlap(toAdd);
            List<Event> eventsOnTheSameDay = model.listEventsSameDay(toAdd);

            throw new CommandException(MESSAGE_OVERLAP_EVENT
                    + "\n List of events overlapping: "
                    + Event.viewEvents(clashingEvents)
                    + "List of events on the same day : "
                    + Event.viewEvents(eventsOnTheSameDay));
        }
        try {
            model.addEvent(toAdd, toAdd.getPersonName().fullName);
        } catch (PersonNotFoundException e) {
            throw new CommandException(MESSAGE_PERSON_NOT_FOUND);
        } catch (OverlapEventException e) {
            throw new CommandException(MESSAGE_OVERLAP_EVENT);
        }
        //TODO: Incorporate into GUI by adding a new field: showEvents?
        //TODO: showEvents could immediately show the next 7 days, up to implementation
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, false, true);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
