package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TRIP;

import java.util.HashSet;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.trip.Trip;


/**
 * Represents the DeleteEventFromTripCommand.
 */
public class DeleteEventFromTripCommand extends Command {

    public static final String COMMAND_WORD = "delete-et";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to specified trip. "
            + "Parameters: "
            + PREFIX_TITLE + "Event TITLE "
            + PREFIX_TRIP + "TRIP "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Swim "
            + PREFIX_TRIP + "Honeymoon ";

    public static final String MESSAGE_SUCCESS = "Event removed to trip: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This event doesn't exists in the specified trip";

    private Title eventToDelete;
    private Title tripToDeleteFrom;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public DeleteEventFromTripCommand(Title event, Title trip) {
        requireNonNull(event);
        requireNonNull(trip);
        eventToDelete = event;
        tripToDeleteFrom = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);


        if (!model.hasTrip(new Trip(tripToDeleteFrom, new Description("random"), new HashSet<>()))) {
            throw new CommandException("Please enter a valid List");
        }

        Trip toDeleteFrom = model.getTrip(new Trip(tripToDeleteFrom, new Description("random"), new HashSet<>()));


        if (!toDeleteFrom.contains(new Event((eventToDelete)))) {
            throw new CommandException("Please enter a valid Event");
        }

        Event event = toDeleteFrom.getEvent(new Event(eventToDelete));

        toDeleteFrom.removeEvent(event);
        model.addEvent(event);

        return new CommandResult(String.format(MESSAGE_SUCCESS, event));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventFromTripCommand // instanceof handles nulls
                && eventToDelete.equals(((DeleteEventFromTripCommand) other).eventToDelete)
                && tripToDeleteFrom.equals(((DeleteEventFromTripCommand) other).tripToDeleteFrom));
    }
}
