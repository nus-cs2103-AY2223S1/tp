package seedu.travelr.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.travelr.model.Model;
import seedu.travelr.model.event.AllInBucketListPredicate;

/**
 * Displays the list of Events in Travelr's Bucket List.
 */
public class EventListCommand extends Command {

    public static final String COMMAND_WORD = "list-e";

    public static final String MESSAGE_SUCCESS = "Listed all events in Bucket list";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        AllInBucketListPredicate predicate = model.getBucketPredicate();
        model.updateFilteredEventList(predicate);
        model.resetSelectedTrip();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
