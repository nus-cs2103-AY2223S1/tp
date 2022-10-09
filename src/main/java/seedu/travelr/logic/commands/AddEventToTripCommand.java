package seedu.travelr.logic.commands;

import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.AddressBook;
import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.Trip;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.logic.parser.CliSyntax.*;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_TAG;

public class AddEventToTripCommand extends Command{

    public static final String COMMAND_WORD = "add-et";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an event to specified trip. "
            + "Parameters: "
            + PREFIX_TITLE + "Event TITLE "
            + PREFIX_TRIP + "TRIP "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "Swim "
            + PREFIX_TRIP + "Honeymoon ";

    public static final String MESSAGE_SUCCESS = "Event added to trip: %1$s";
    public static final String MESSAGE_DUPLICATE_TRIP = "This event already exists in The specified trip";

    private final Event toAdd;
    private final Trip toAddInto;

    /**
     * Creates an AddCommand to add the specified {@code Trip}
     */
    public AddEventToTripCommand(Event event, Trip trip) {
        requireNonNull(event);
        toAdd = event;
        toAddInto = trip;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        AddressBook.bucketList.removeEvent(toAdd);
        toAddInto.addEvent(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventToTripCommand // instanceof handles nulls
                && toAdd.equals(((AddEventToTripCommand) other).toAdd));
    }
}
