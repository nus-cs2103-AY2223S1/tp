package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.travelr.model.Model.PREDICATE_SHOW_ALL_EVENTS;

import java.util.List;

import javafx.collections.transformation.FilteredList;
import seedu.travelr.commons.core.Messages;
import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.model.Model;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.event.EventInItineraryPredicate;
import seedu.travelr.model.list.Itineraries;
import seedu.travelr.model.trip.Trip;

/**
 * Selects a trip in Travelr and display it to the user.
 */
public class SelectCommand extends Command {

    public static final String COMMAND_WORD = "select";

    public static final String MESSAGE_SUCCESS = "Listed the itinerary for the trip: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the itinerary of the specified trip.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private EventInItineraryPredicate predicate;
    public SelectCommand(EventInItineraryPredicate predicate) {
        this.predicate = predicate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Index targetIndex = predicate.getIndex();
        List<Trip> lastShownList = model.getFilteredTripList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_DISPLAYED_TRIP_INDEX);
        }

        Trip tripToDisplay = lastShownList.get(targetIndex.getZeroBased());
        Itineraries tripItinerary = tripToDisplay.getItinerary();
        predicate.setItinerary(tripItinerary);
        model.updateFilteredEventList(predicate);
        model.updateSelectedTrip(tripToDisplay);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tripToDisplay.getTitle().toString()));
    }

    private FilteredList<Event> retrieveItinerariesAsList(Itineraries itinerary) {
        return itinerary.asUnmodifiableObservableList().filtered(PREDICATE_SHOW_ALL_EVENTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SelectCommand // instanceof handles nulls
                && predicate.equals(((SelectCommand) other).predicate)); // state check
    }

}
