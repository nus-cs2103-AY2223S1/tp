package foodwhere.logic.commands;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static foodwhere.model.Model.PREDICATE_SHOW_ALL_REVIEWS;
import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import foodwhere.commons.core.index.Index;
import foodwhere.commons.util.CollectionUtil;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
import foodwhere.model.review.Review;

/**
 * Edits the details of an existing review in FoodWhere.
 */
public class REditCommand extends Command {

    public static final String COMMAND_WORD = "redit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the review identified "
            + "by the index number used in the displayed review list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_DATE + "DATE] "
            + "[" + CliSyntax.PREFIX_CONTENT + "CONTENT] "
            + "[" + CliSyntax.PREFIX_RATING + "RATING] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_REVIEW_SUCCESS = "Edited Review: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_REVIEW = "This review already exists in FoodWhere.";

    public static final String MESSAGE_INVALID_INDEX_ERROR =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX)
                    + REditCommand.MESSAGE_USAGE;

    private final Index index;
    private final EditReviewDescriptor editReviewDescriptor;

    /**
     * @param index of the review in the filtered review list to edit
     * @param editReviewDescriptor details to edit the review with
     */
    public REditCommand(Index index, EditReviewDescriptor editReviewDescriptor) {
        requireNonNull(index);
        requireNonNull(editReviewDescriptor);

        this.index = index;
        this.editReviewDescriptor = new EditReviewDescriptor(editReviewDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Review> lastShownList = model.getFilteredReviewList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX_ERROR);
        }

        if (!editReviewDescriptor.isAnyFieldEdited()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        Review reviewToEdit = lastShownList.get(index.getZeroBased());
        Review editedReview = createEditedReview(reviewToEdit, editReviewDescriptor);

        if (!reviewToEdit.isSameReview(editedReview) && model.hasReview(editedReview)) {
            throw new CommandException(MESSAGE_DUPLICATE_REVIEW);
        }

        model.setReview(reviewToEdit, editedReview);
        model.updateFilteredReviewList(PREDICATE_SHOW_ALL_REVIEWS);
        return new CommandResult(String.format(MESSAGE_EDIT_REVIEW_SUCCESS, editedReview));
    }

    /**
     * Creates and returns a {@code Review} with the details of {@code reviewToEdit}
     * edited with {@code editReviewDescriptor}.
     */
    private static Review createEditedReview(Review reviewToEdit, EditReviewDescriptor editReviewDescriptor) {
        assert reviewToEdit != null;

        Name name = reviewToEdit.getName();
        Address address = reviewToEdit.getAddress();
        Date updatedDate = editReviewDescriptor.getDate().orElse(reviewToEdit.getDate());
        Content updatedContent = editReviewDescriptor.getContent().orElse(reviewToEdit.getContent());
        Rating updatedRating = editReviewDescriptor.getRating().orElse(reviewToEdit.getRating());
        Set<Tag> updatedTags = editReviewDescriptor.getTags().orElse(reviewToEdit.getTags());

        return new Review(name, address, updatedDate, updatedContent, updatedRating, updatedTags);
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
        REditCommand e = (REditCommand) other;
        return index.equals(e.index)
                && editReviewDescriptor.equals(e.editReviewDescriptor);
    }

    /**
     * Stores the details to edit the review with. Each non-empty field value will replace the
     * corresponding field value of the review.
     */
    public static class EditReviewDescriptor {
        private Date date;
        private Content content;
        private Rating rating;
        private Set<Tag> tags;

        public EditReviewDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditReviewDescriptor(EditReviewDescriptor toCopy) {
            setDate(toCopy.date);
            setContent(toCopy.content);
            setRating(toCopy.rating);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(date, content, rating, tags);
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(date);
        }

        public void setContent(Content content) {
            this.content = content;
        }

        public Optional<Content> getContent() {
            return Optional.ofNullable(content);
        }

        public void setRating(Rating rating) {
            this.rating = rating;
        }

        public Optional<Rating> getRating() {
            return Optional.ofNullable(rating);
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
            if (!(other instanceof EditReviewDescriptor)) {
                return false;
            }

            // state check
            EditReviewDescriptor e = (EditReviewDescriptor) other;

            return getDate().equals(e.getDate())
                    && getContent().equals(e.getContent())
                    && getRating().equals(e.getRating())
                    && getTags().equals(e.getTags());
        }
    }
}
