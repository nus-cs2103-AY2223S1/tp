package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.commons.util.CollectionUtil;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.logic.parser.CliSyntax;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Description;
import hobbylist.model.activity.Name;
import hobbylist.model.activity.Status;
import hobbylist.model.date.Date;
import hobbylist.model.tag.Tag;

/**
 * Edits the details of an existing activity in the HobbyList.
 */
public class EditCommand extends Command {
    public static final String MESSAGE_EDIT_ACTIVITY_SUCCESS = "Edited Activity: %1$s";
    public static final String MESSAGE_EDIT_ACTIVITY_NO_CHANGE = "No change to Activity: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in the HobbyList.";

    private static String commandWord = "edit";

    public static final String MESSAGE_USAGE = commandWord + ": Edits the details of the activity identified "
            + "by the index number used in the displayed activity list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_NAME + "NAME] "
            + "[" + CliSyntax.PREFIX_DESCRIPTION + "DESCRIPTION] "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "[" + CliSyntax.PREFIX_DATE + "DATE]...\n"
            + "Example: " + commandWord + " 1 "
            + CliSyntax.PREFIX_DESCRIPTION + "Exercised for 2 hours";

    private final Index index;
    private final EditActivityDescriptor editActivityDescriptor;

    /**
     * @param index of the activity in the filtered activity list to edit
     * @param editActivityDescriptor details to edit the activity with
     */
    public EditCommand(Index index, EditActivityDescriptor editActivityDescriptor) {
        requireNonNull(index);
        requireNonNull(editActivityDescriptor);

        this.index = index;
        this.editActivityDescriptor = new EditActivityDescriptor(editActivityDescriptor);
    }

    /**
     * Sets the command word for the command.
     * @param word Word to set command to.
     */
    public static void setCommandWord(String word) {
        commandWord = word;
    }

    /**
     * Gets the command word for the command.
     * @return Command word.
     */
    public static String getCommandWord() {
        return commandWord;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Activity> lastShownList = model.getFilteredActivityList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToEdit = lastShownList.get(index.getZeroBased());
        Activity editedActivity = createEditedActivity(activityToEdit, editActivityDescriptor);

        if (!activityToEdit.isSameActivity(editedActivity) && model.hasActivity(editedActivity)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        if (!activityToEdit.equals(editedActivity)) {
            model.setActivity(activityToEdit, editedActivity);
            model.updateFilteredActivityList(Model.PREDICATE_SHOW_ALL_ACTIVITIES);
            return new CommandResult(String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, editedActivity));
        } else {
            model.updateFilteredActivityList(Model.PREDICATE_SHOW_ALL_ACTIVITIES);
            return new CommandResult(String.format(MESSAGE_EDIT_ACTIVITY_NO_CHANGE, editedActivity));
        }
    }

    /**
     * Creates and returns an {@code Activity} with the details of {@code activityToEdit}
     * edited with {@code editActivityDescriptor}.
     */
    private static Activity createEditedActivity(Activity activityToEdit,
                                                 EditActivityDescriptor editActivityDescriptor) {
        assert activityToEdit != null;

        Name updatedName = editActivityDescriptor.getName().orElse(activityToEdit.getName());
        Description updatedDescription = editActivityDescriptor.getDescription()
                .orElse(activityToEdit.getDescription());
        Set<Tag> updatedTags = editActivityDescriptor.getTags().orElse(activityToEdit.getTags());
        Optional<Date> date = null;
        if (editActivityDescriptor.checkDate() != null) {
            date = editActivityDescriptor.checkDate();
        } else {
            date = activityToEdit.getDate();
        }
        Status updatedStatus = editActivityDescriptor.getStatus().orElse(activityToEdit.getStatus());
        return new Activity(updatedName, updatedDescription, updatedTags, date, activityToEdit.getRating(),
                updatedStatus, activityToEdit.getReview());
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
                && editActivityDescriptor.equals(e.editActivityDescriptor);
    }

    /**
     * Stores the details to edit the activity with. Each non-empty field value will replace the
     * corresponding field value of the activity.
     */
    public static class EditActivityDescriptor {
        private Name name;
        private Description description;
        private Set<Tag> tags;
        private Optional<Date> date;
        private Status status;

        public EditActivityDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditActivityDescriptor(EditActivityDescriptor toCopy) {
            setName(toCopy.name);
            setDescription(toCopy.description);
            setTags(toCopy.tags);
            setDate(toCopy.date);
            setStatus(toCopy.status);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {

            return CollectionUtil.isAnyNonNull(name, description, tags, date, status);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }
        public void setDate(Optional<Date> optionalDate) {
            this.date = optionalDate;
        }
        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }
        public Optional<Date> getDate() {
            if (this.date == null) {
                return Optional.empty();
            }
            return this.date;
        }
        public Optional<Date> checkDate() {
            return this.date;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Optional<Status> getStatus() {
            return Optional.ofNullable(status);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditActivityDescriptor)) {
                return false;
            }

            // state check
            EditActivityDescriptor e = (EditActivityDescriptor) other;

            return getName().equals(e.getName())
                    && getDescription().equals(e.getDescription())
                    && getTags().equals(e.getTags())
                    && getDate().equals(e.getDate())
                    && getStatus().equals(e.getStatus());
        }
    }
}
