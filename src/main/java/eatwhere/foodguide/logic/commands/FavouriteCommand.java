package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;

import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Tags an eatery with "<3" to mark it as a favourite.
 */
public class FavouriteCommand extends TagCommand {

    public static final String COMMAND_WORD = "fav";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Favourites the eatery identified by the index number used in the displayed eatery list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    private static final Tag[] FAVTAGINIT = {new Tag("<3")};
    public static final HashSet<Tag> FAVTAGSET = new HashSet<>(List.of(FAVTAGINIT));
    public static final Tag FAVTAG = FAVTAGINIT[0];

    /**
     * @param index of the eatery in the filtered eatery list to edit
     */
    public FavouriteCommand(Index index) {
        super(requireNonNull(index), requireNonNull(FAVTAGSET));
        assert super.isContaining(FAVTAG);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return super.execute(model);
    }
}
