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
 * Adds tags to an existing eatery in the food guide.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to the eatery identified "
            + "by the index number used in the displayed eatery list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + " halal";

    /**
     *  %1$s - s if multiple tags, else nothing (i.e. tags or tag)
     *  %2$s - The names of added tags
     *  %3$s - Eatery name
     */
    public static final String MESSAGE_TAG_EATERY_SUCCESS = "Added tag%1$s %2$s\nto %3$s";
    public static final String MESSAGE_NOT_TAGGED = "At least one tag must be provided";
    public static final String MESSAGE_DUPLICATE_EATERY = "This eatery already exists in the food guide";
    public static final String MESSAGE_DUPLICATE_TAGS_HEADER = "Duplicate tags not allowed:";
    private final Index index;
    private final Set<Tag> tagsToAdd;

    /**
     * @param index of the eatery in the filtered eatery list to tag
     * @param tagsToAdd tags to add to the eatery
     */
    public TagCommand(Index index, Set<Tag> tagsToAdd) {
        requireNonNull(index);
        requireNonNull(tagsToAdd);

        this.index = index;
        this.tagsToAdd = new HashSet<>(tagsToAdd);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Eatery> lastShownList = model.getFilteredEateryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
        }

        Eatery eateryToTag = lastShownList.get(index.getZeroBased());
        Eatery taggedEatery = createTaggedEatery(eateryToTag, tagsToAdd);

        if (!eateryToTag.isSameEatery(taggedEatery) && model.hasEatery(taggedEatery)) {
            throw new CommandException(MESSAGE_DUPLICATE_EATERY);
        }

        model.setEatery(eateryToTag, taggedEatery);
        model.updateFilteredEateryList(Model.PREDICATE_SHOW_ALL_EATERIES);

        String pluralForTagWord = tagsToAdd.size() == 1 ? "" : "s";
        StringBuilder addedTags = new StringBuilder();
        for (Tag tag : tagsToAdd) {
            addedTags.append(tag).append(", ");
        }

        return new CommandResult(String.format(MESSAGE_TAG_EATERY_SUCCESS,
                pluralForTagWord,
                addedTags.substring(0, addedTags.length() - 2),
                taggedEatery.getName()));
    }

    /**
     * Creates and returns an {@code Eatery} that's a copy of {@code eateryToTag}
     * with {@code tagsToAdd} included.
     */
    private static Eatery createTaggedEatery(Eatery eateryToTag, Set<Tag> tagsToAdd) throws CommandException {
        assert eateryToTag != null;

        Name name = eateryToTag.getName();
        Price price = eateryToTag.getPrice();
        Cuisine email = eateryToTag.getCuisine();
        Location location = eateryToTag.getLocation();
        Set<Tag> tags = new HashSet<>(eateryToTag.getTags()); //hashset supports addAll()

        Set<Tag> duplicateTags = new HashSet<>(Set.copyOf(tagsToAdd));
        duplicateTags.retainAll(tags);
        if (!duplicateTags.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder(MESSAGE_DUPLICATE_TAGS_HEADER);
            for (Tag tag : duplicateTags) {
                errorMessage.append(" ").append(tag);
            }
            throw new CommandException(errorMessage.toString());
        }

        tags.addAll(tagsToAdd);
        return new Eatery(name, price, email, location, tags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && tagsToAdd.equals(e.tagsToAdd);
    }
}
