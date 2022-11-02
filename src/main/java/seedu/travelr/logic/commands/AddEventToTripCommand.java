package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.commons.core.Messages.MESSAGE_RESET_VIEW;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TRIP;

import java.util.HashSet;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.component.DateField;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Location;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

/**
 * Represents the AddEventToTripCommand. Extends the Command class.
 */
public class AddEventToTripCommand extends Command {

    public static final String COMMAND_WORD = "add-et";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to specified trip. "
            + "Parameters: "
            + PREFIX_TITLE + "Event TITLE "
            + PREFIX_TRIP + "TRIP "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Swim "
            + PREFIX_TRIP + "Honeymoon ";

    public static final String MESSAGE_SUCCESS = "Event %s added to Trip %s."
            + "\nThe specified event has been removed "
            + "from the bucket list.";
    public static final String MESSAGE_DUPLICATE_EVENT_IN_TRIP = "This event already exists in the specified trip";

    private final Title eventToAdd;
    private final Title tripToAddInto;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddEventToTripCommand(Title event, Title trip) {
        requireNonNull(event);
        requireNonNull(trip);
        eventToAdd = event;
        tripToAddInto = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.bucketlistHasEvent(new Event(eventToAdd))) {
            throw new CommandException("Please enter a valid event");
        }

        if (!model.hasTrip(
                new Trip(tripToAddInto,
                        new Description("random"),
                        new HashSet<>(),
                        new Location("random"),
                        new DateField("01-01-2000")))) {
            throw new CommandException("Please enter a valid Trip");
        }

        if (!model.hasEventInBucketList(new Event(eventToAdd))) {
            throw new CommandException("This event is no longer in the bucket list!");
        }

        Event event = model.getEvent(new Event(eventToAdd));
        Trip toAddInto = model.getTrip(
                new Trip(tripToAddInto,
                        new Description("random"),
                        new HashSet<>(),
                        new Location("random"),
                        new DateField("01-01-2000")));

        if (toAddInto.containsEvent(event)) {
            throw new CommandException(MESSAGE_DUPLICATE_EVENT_IN_TRIP);
        }

        model.removeFromBucketList(event);
        toAddInto.addEvent(event);
        model.resetView();
        return new CommandResult(String.format(
                MESSAGE_SUCCESS + "\n" + MESSAGE_RESET_VIEW, event.getTitle(), toAddInto.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventToTripCommand // instanceof handles nulls
                && eventToAdd.equals(((AddEventToTripCommand) other).eventToAdd)
                && tripToAddInto.equals(((AddEventToTripCommand) other).tripToAddInto));
    }
}
