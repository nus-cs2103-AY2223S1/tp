package foodwhere.logic.commands;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static foodwhere.model.Model.PREDICATE_SHOW_ALL_STALLS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import foodwhere.commons.core.index.Index;
import foodwhere.commons.util.CollectionUtil;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.model.stall.Stall;

/**
 * Edits the details of an existing stall in FoodWhere.
 */
public class SEditCommand extends Command {

    public static final String COMMAND_WORD = "sedit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the stall identified "
            + "by the index number used in the displayed stall list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_ADDRESS + "ADDRESS] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_STALL_SUCCESS = "Edited Stall: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STALL = "This stall already exists in FoodWhere.";

    public static final String MESSAGE_INVALID_INDEX_ERROR =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX)
                    + SEditCommand.MESSAGE_USAGE;

    private final Index index;
    private final EditStallDescriptor editStallDescriptor;

    /**
     * @param index of the stall in the filtered stall list to edit
     * @param editStallDescriptor details to edit the stall with
     */
    public SEditCommand(Index index, EditStallDescriptor editStallDescriptor) {
        requireNonNull(index);
        requireNonNull(editStallDescriptor);

        this.index = index;
        this.editStallDescriptor = new EditStallDescriptor(editStallDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX_ERROR);
        }

        if (!editStallDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Stall stallToEdit = lastShownList.get(index.getZeroBased());
        Stall editedStall = createEditedStall(stallToEdit, editStallDescriptor);

        if (!stallToEdit.isSameStall(editedStall) && model.hasStall(editedStall)) {
            throw new CommandException(MESSAGE_DUPLICATE_STALL);
        }

        model.setStall(stallToEdit, editedStall);
        model.updateFilteredStallList(PREDICATE_SHOW_ALL_STALLS);
        return new CommandResult(String.format(MESSAGE_EDIT_STALL_SUCCESS, editedStall));
    }

    /**
     * Creates and returns a {@code Stall} with the details of {@code stallToEdit}
     * edited with {@code editStallDescriptor}.
     */
    private static Stall createEditedStall(Stall stallToEdit, EditStallDescriptor editStallDescriptor) {
        assert stallToEdit != null;

        Name updatedName = editStallDescriptor.getName().orElse(stallToEdit.getName());
        Address updatedAddress = editStallDescriptor.getAddress().orElse(stallToEdit.getAddress());
        Set<Tag> updatedTags = editStallDescriptor.getTags().orElse(stallToEdit.getTags());
        Set<Review> updatedReviews = editStallDescriptor.getReviews()
                .orElse(stallToEdit.getReviews())
                .stream()
                .map(review -> new ReviewBuilder(review)
                        .withName(updatedName.fullName)
                        .withAddress(updatedAddress.value)
                        .build())
                .collect(Collectors.toSet());
        return new Stall(updatedName, updatedAddress, updatedTags, updatedReviews);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SEditCommand)) {
            return false;
        }

        // state check
        SEditCommand e = (SEditCommand) other;
        return index.equals(e.index)
                && editStallDescriptor.equals(e.editStallDescriptor);
    }

    /**
     * Stores the details to edit the stall with. Each non-empty field value will replace the
     * corresponding field value of the stall.
     */
    public static class EditStallDescriptor {
        private Name name;
        private Address address;
        private Set<Tag> tags;
        private Set<Review> reviews;

        public EditStallDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStallDescriptor(EditStallDescriptor toCopy) {
            setName(toCopy.name);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setReviews(toCopy.reviews);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, address, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
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

        /**
         * Sets {@code reviews} to this object's {@code reviews}.
         * A defensive copy of {@code reviews} is used internally.
         */
        public void setReviews(Set<Review> reviews) {
            this.reviews = (reviews != null) ? new HashSet<>(reviews) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Review>> getReviews() {
            return (reviews != null) ? Optional.of(Collections.unmodifiableSet(reviews)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStallDescriptor)) {
                return false;
            }

            // state check
            EditStallDescriptor e = (EditStallDescriptor) other;

            return getName().equals(e.getName())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags());
        }
    }
}
