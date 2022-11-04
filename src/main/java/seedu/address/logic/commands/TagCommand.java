package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Deadline;
import seedu.address.model.task.Module;
import seedu.address.model.task.Name;
import seedu.address.model.task.Task;

/**
 * Adds on tags to an existing task in the address book.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds tags to the task identified "
            + "by the index number used in the displayed task list. \n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG*]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TAG + "highPriority";

    public static final String MESSAGE_TAG_PERSON_SUCCESS = "Tagged Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one tag must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This task already exists in the task tracker.";
    private final Index index;
    private final EditPersonTags editPersonTags;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editPersonTags tag to tag the task with
     */
    public TagCommand(Index index, EditPersonTags editPersonTags) {
        requireNonNull(index);
        requireNonNull(editPersonTags);

        this.index = index;
        this.editPersonTags = editPersonTags;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToTag = lastShownList.get(index.getZeroBased());
        Task taggedTask = createTaggedPerson(taskToTag, editPersonTags);

        if (!taskToTag.isSameTask(taggedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setTask(taskToTag, taggedTask);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        CommandResult result = new CommandResult(String.format(MESSAGE_TAG_PERSON_SUCCESS, taggedTask));
        return result;
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createTaggedPerson(Task taskToEdit, EditPersonTags editPersonTags) {
        assert taskToEdit != null;

        Name updatedName = taskToEdit.getName();
        Module updatedModule = taskToEdit.getModule();
        Deadline updatedDeadline = taskToEdit.getDeadline();

        Set<Tag> updatedTags = new HashSet<>();
        if (editPersonTags.getTags().isPresent()) {
            updatedTags.addAll(editPersonTags.getTags().get());
        }
        updatedTags.addAll(taskToEdit.getTags());

        return new Task(updatedName, updatedModule, updatedDeadline, updatedTags, taskToEdit.isDone());
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
                && editPersonTags.equals(e.editPersonTags);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     *
     * The {@code isDone} field is not affected by the edit command, and thus is not part of
     * the descriptor.
     */
    public static class EditPersonTags {
        private Set<Tag> tags;

        public EditPersonTags() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonTags(EditPersonTags toCopy) {
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isTagEdited() {
            return CollectionUtil.isAnyNonNull(tags);
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
            if (!(other instanceof EditPersonTags)) {
                return false;
            }

            // state check
            EditPersonTags e = (EditPersonTags) other;

            return getTags().equals(e.getTags());
        }
    }
}
