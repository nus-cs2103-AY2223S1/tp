package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;

/**
 * Edits the details of an existing eatery in the food guide.
 */
public class FavouriteCommand extends Command {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the eatery identified by the index number used in the displayed eatery list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_FAV_EATERY_SUCCESS = "Favourite Eatery: %1$s";

    private final Index index;

    /**
     * @param index of the eatery in the filtered eatery list to edit
     */
    public FavouriteCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_FAV_EATERY_SUCCESS));
    }
}
