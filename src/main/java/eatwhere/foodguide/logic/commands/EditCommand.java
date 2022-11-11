package eatwhere.foodguide.logic.commands;

import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_CUISINE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_LOCATION;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_NAME;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_PRICE;
import static eatwhere.foodguide.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import eatwhere.foodguide.commons.core.Messages;
import eatwhere.foodguide.commons.core.index.Index;
import eatwhere.foodguide.commons.util.CollectionUtil;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.eatery.Cuisine;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.model.eatery.Location;
import eatwhere.foodguide.model.eatery.Name;
import eatwhere.foodguide.model.eatery.Price;
import eatwhere.foodguide.model.tag.Tag;

/**
 * Edits the details of an existing eatery in the food guide.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the eatery identified "
            + "by the index number used in the displayed eatery list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_CUISINE + "CUISINE] "
            + "[" + PREFIX_LOCATION + "LOCATION] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PRICE + "$$ "
            + PREFIX_CUISINE + "chinese";

    public static final String MESSAGE_EDIT_EATERY_SUCCESS = "Edited Eatery: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EATERY = "This eatery already exists in the food guide.";

    private final Index index;
    private final EditEateryDescriptor editEateryDescriptor;

    /**
     * @param index of the eatery in the filtered eatery list to edit
     * @param editEateryDescriptor details to edit the eatery with
     */
    public EditCommand(Index index, EditEateryDescriptor editEateryDescriptor) {
        requireNonNull(index);
        requireNonNull(editEateryDescriptor);

        this.index = index;
        this.editEateryDescriptor = new EditEateryDescriptor(editEateryDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Eatery> lastShownList = model.getFilteredEateryList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EATERY_DISPLAYED_INDEX);
        }

        Eatery eateryToEdit = lastShownList.get(index.getZeroBased());
        Eatery editedEatery = createEditedEatery(eateryToEdit, editEateryDescriptor);

        if (!eateryToEdit.isSameEatery(editedEatery) && model.hasEatery(editedEatery)) {
            throw new CommandException(MESSAGE_DUPLICATE_EATERY);
        }

        model.setEatery(eateryToEdit, editedEatery);
        model.updateFilteredEateryList(Model.PREDICATE_SHOW_ALL_EATERIES);
        return new CommandResult(String.format(MESSAGE_EDIT_EATERY_SUCCESS, editedEatery));
    }

    /**
     * Creates and returns a {@code Eatery} with the details of {@code eateryToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Eatery createEditedEatery(Eatery eateryToEdit, EditEateryDescriptor editEateryDescriptor) {
        assert eateryToEdit != null;

        Name updatedName = editEateryDescriptor.getName().orElse(eateryToEdit.getName());
        Price updatedPrice = editEateryDescriptor.getPhone().orElse(eateryToEdit.getPrice());
        Cuisine updatedCuisine = editEateryDescriptor.getEmail().orElse(eateryToEdit.getCuisine());
        Location updatedLocation = editEateryDescriptor.getAddress().orElse(eateryToEdit.getLocation());
        Set<Tag> updatedTags = editEateryDescriptor.getTags().orElse(eateryToEdit.getTags());

        return new Eatery(updatedName, updatedPrice, updatedCuisine, updatedLocation, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editEateryDescriptor.equals(e.editEateryDescriptor);
    }

    /**
     * Stores the details to edit the eatery with. Each non-empty field value will replace the
     * corresponding field value of the eatery.
     */
    public static class EditEateryDescriptor {
        private Name name;
        private Price price;
        private Cuisine cuisine;
        private Location location;
        private Set<Tag> tags;

        public EditEateryDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEateryDescriptor(EditEateryDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.price);
            setCuisine(toCopy.cuisine);
            setAddress(toCopy.location);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, price, cuisine, location, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Price price) {
            this.price = price;
        }

        public Optional<Price> getPhone() {
            return Optional.ofNullable(price);
        }

        public void setCuisine(Cuisine cuisine) {
            this.cuisine = cuisine;
        }

        public Optional<Cuisine> getEmail() {
            return Optional.ofNullable(cuisine);
        }

        public void setAddress(Location location) {
            this.location = location;
        }

        public Optional<Location> getAddress() {
            return Optional.ofNullable(location);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEateryDescriptor)) {
                return false;
            }

            // state check
            EditEateryDescriptor e = (EditEateryDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
