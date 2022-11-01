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
 * Represents the DeleteEventFromTripCommand.
 */
public class DeleteEventFromTripCommand extends Command {

    public static final String COMMAND_WORD = "delete-et";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes an event from specified trip. "
            + "Parameters: "
            + PREFIX_TITLE + "EVENTTITLE "
            + PREFIX_TRIP + "TRIP "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Swim "
            + PREFIX_TRIP + "Honeymoon ";

    public static final String MESSAGE_SUCCESS = "Event %s removed from Trip %s.\n"
            + "The specified event has been returned "
            + "to the bucket list.";

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


        if (!model.hasTrip(
                new Trip(tripToDeleteFrom,
                        new Description("random"),
                        new HashSet<>(),
                        new Location("random"),
                        new DateField("01-01-2000")))) {
            throw new CommandException("Please enter a valid Trip");
        }

        Trip toDeleteFrom = model.getTrip(
                new Trip(tripToDeleteFrom,
                        new Description("random"),
                        new HashSet<>(),
                        new Location("random"),
                        new DateField("01-01-2000")));


        if (!toDeleteFrom.containsEvent(new Event((eventToDelete)))) {
            throw new CommandException("Please enter a valid Event");
        }

        Event event = toDeleteFrom.getEvent(new Event(eventToDelete));

        toDeleteFrom.removeEvent(event);
        model.returnToBucketList(event);
        model.resetView();

        return new CommandResult(String.format(
                MESSAGE_SUCCESS + "\n" + MESSAGE_RESET_VIEW, event.getTitle(), toDeleteFrom.getTitle()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteEventFromTripCommand // instanceof handles nulls
                && eventToDelete.equals(((DeleteEventFromTripCommand) other).eventToDelete)
                && tripToDeleteFrom.equals(((DeleteEventFromTripCommand) other).tripToDeleteFrom));
    }
}
