package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.commands.FavouriteCommand.FAVTAG;
import static eatwhere.foodguide.logic.commands.FavouriteCommand.FAVTAGSET;
import static java.util.Objects.requireNonNull;

import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;

/**
 * Removes a favourite tag from an eatery.
 */
public class UnfavouriteCommand extends UntagCommand {

    public static final String COMMAND_WORD = "unfav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unfavourites the eatery identified by the index number used in the displayed eatery list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    /**
     * @param index of the eatery in the filtered eatery list to edit
     */
    public UnfavouriteCommand(Index index) {
        super(requireNonNull(index), requireNonNull(FAVTAGSET));
        assert super.isContaining(FAVTAG);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return super.execute(model);
    }
}
