package seedu.waddle.logic.commands;

import static seedu.waddle.commons.util.CollectionUtil.requireAllNonNull;

import seedu.waddle.commons.core.Messages;
import seedu.waddle.commons.core.index.Index;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.Stages;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.itinerary.Itinerary;

/**
 * Moves to the planning stage of the selected itinerary.
 */
public class SelectCommand extends Command {
    public static final String COMMAND_WORD = "select";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": selects an itinerary for planning "
            + "by the index number used in the last itineraries listing.\n"
            + "Parameters: INDEX (must exist in the itinerary list)\n"
            + "Example: " + COMMAND_WORD + " 1 ";
    public static final String MESSAGE_ARGUMENTS = "Index: %1$d";
    public static final String MESSAGE_SELECT_ITINERARY_SUCCESS = "Selected Itinerary: %1$s";
    private final Index index;

    /**
     * @param index of the itinerary to select
     */
    public SelectCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        StageManager stageManager = StageManager.getInstance();
        Itinerary selectedItinerary;

        // get the selected itinerary from the last shown list of itineraries
        try {
            selectedItinerary = model.getFilteredItineraryList().get(this.index.getZeroBased());
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
        }
        // change to wish stage in stage manager
        try {
            stageManager.setWishStage(selectedItinerary);
        } catch (NullPointerException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_ITINERARY_DISPLAYED_INDEX);
        }

        //TODO: allow users to directly select which planning stage
        // instead of going to wish stage by default

        // return command result with stage change to wish by default for now (refer above)
        return new CommandResult(String.format(MESSAGE_SELECT_ITINERARY_SUCCESS, selectedItinerary.getDescription()),
                Stages.WISH);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SelectCommand)) {
            return false;
        }

        // state check
        SelectCommand e = (SelectCommand) other;
        return index.equals(e.index);
    }
}
