package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Price;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Removes tags of an existing eatery in the food guide.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Removes tags of the eatery identified "
            + "by the index number used in the displayed eatery list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + " halal";

    /**
     *  %1$s - s if multiple tags, else nothing (i.e. tags or tag)
     *  %2$s - The names of removed tags
     *  %3$s - Eatery name
     */
    public static final String MESSAGE_UNTAG_EATERY_SUCCESS = "Removed tag%1$s %2$s\nfrom %3$s";
    public static final String MESSAGE_NOT_UNTAGGED = "At least one tag must be provided";
    public static final String MESSAGE_DUPLICATE_EATERY = "This eatery already exists in the food guide";
    public static final String MESSAGE_TAG_NOT_FOUND = "Didn't find tag %1$s at eatery %2$s";

    private final Index index;
    private final Set<Tag> tagsToRemove;

    /**
     * @param index of the eatery in the filtered eatery list to untag
     * @param tagsToRemove tags to remove from the eatery
     */
    public UntagCommand(Index index, Set<Tag> tagsToRemove) {
        requireNonNull(index);
        requireNonNull(tagsToRemove);

        this.index = index;
        this.tagsToRemove = new HashSet<>(tagsToRemove);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Eatery> lastShownList = model.getFilteredEateryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
        }

        Eatery eateryToUntag = lastShownList.get(index.getZeroBased());
        Eatery untaggedEatery = createUntaggedEatery(eateryToUntag, tagsToRemove);

        if (!eateryToUntag.isSameEatery(untaggedEatery) && model.hasEatery(untaggedEatery)) {
            throw new CommandException(MESSAGE_DUPLICATE_EATERY);
        }

        model.setEatery(eateryToUntag, untaggedEatery);
        model.updateFilteredEateryList(Model.PREDICATE_SHOW_ALL_EATERIES);

        String pluralForTagWord = tagsToRemove.size() == 1 ? "" : "s";
        StringBuilder removedTags = new StringBuilder();
        for (Tag tag : tagsToRemove) {
            removedTags.append(tag).append(", ");
        }

        return new CommandResult(String.format(MESSAGE_UNTAG_EATERY_SUCCESS,
                pluralForTagWord,
                removedTags.substring(0, removedTags.length() - 2),
                untaggedEatery.getName()));
    }

    /**
     * Creates and returns a {@code Eatery} that's a copy of {@code eateryToUntag}
     * with {@code tagsToRemove} excluded.
     */
    private static Eatery createUntaggedEatery(Eatery eateryToUntag, Set<Tag> tagsToRemove) throws CommandException {
        assert eateryToUntag != null;

        Name name = eateryToUntag.getName();
        Price price = eateryToUntag.getPrice();
        Cuisine email = eateryToUntag.getCuisine();
        Location location = eateryToUntag.getLocation();
        Set<Tag> tags = new HashSet<>(eateryToUntag.getTags()); //hashset supports removeAll()
        for (Tag tagToRemove : tagsToRemove) {
            if (!tags.contains(tagToRemove)) {
                throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND, tagToRemove, eateryToUntag.getName()));
            }
        }
        tags.removeAll(tagsToRemove);

        return new Eatery(name, price, email, location, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UntagCommand)) {
            return false;
        }

        // state check
        UntagCommand e = (UntagCommand) other;
        return index.equals(e.index)
                && tagsToRemove.equals(e.tagsToRemove);
    }

}
