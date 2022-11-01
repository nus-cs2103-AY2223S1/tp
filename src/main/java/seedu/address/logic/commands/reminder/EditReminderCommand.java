package seedu.address.logic.commands.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_UNCHANGED_FIELD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE_DAY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_REMINDERS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.datetime.Datetime;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderName;
import seedu.address.model.reminder.ReminderPriority;
import seedu.address.model.reminder.ReminderStatus;


/**
 * Parses input arguments and creates a new EditReminderCommand object
 */
public class EditReminderCommand extends Command {

    public static final String COMMAND_WORD = "edit reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the reminder identified "
            + "by the index number used in the displayed reminder list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_DATE_DAY + "DATE] "
            + "[" + PREFIX_TIME + "TIME] "
            + "[" + PREFIX_PRIORITY + "PRIORITY] "
            + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DATE_DAY + "2022-10-10 "
            + PREFIX_TIME + "14:00 "
            + PREFIX_PRIORITY + "HIGH";

    public static final String MESSAGE_EDIT_REMINDER_SUCCESS = "Edited Reminder: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_REMINDER = "This reminder already exists in ModQuik.";
    public static final String MESSAGE_DATETIME_REMINDER = "Both new date and new timeslot must be given.";

    private final Index index;
    private final EditReminderDescriptor editReminderDescriptor;

    /**
     * @param index of the person in the filtered reminder list to edit
     * @param editReminderDescriptor details to edit the reminder with
     */
    public EditReminderCommand(Index index, EditReminderDescriptor editReminderDescriptor) {
        requireNonNull(index);
        requireNonNull(editReminderDescriptor);

        this.index = index;
        this.editReminderDescriptor = new EditReminderDescriptor(editReminderDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Reminder> lastShownList = model.getFilteredReminderList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REMINDER_DISPLAYED_INDEX);
        }

        Reminder reminderToEdit = lastShownList.get(index.getZeroBased());
        Reminder editedReminder = createEditedReminder(reminderToEdit, editReminderDescriptor);

        if (!reminderToEdit.isSameReminder(editedReminder) && model.hasReminder(editedReminder)) {
            throw new CommandException(MESSAGE_DUPLICATE_REMINDER);
        }

        model.setReminder(reminderToEdit, editedReminder);
        model.updateFilteredReminderList(PREDICATE_SHOW_ALL_REMINDERS);
        return new CommandResult(String.format(MESSAGE_EDIT_REMINDER_SUCCESS, editedReminder));
    }

    /**
     * Creates and returns a {@code Reminder} with the details of {@code reminderToEdit}
     * edited with {@code editReminderDescriptor}.
     */
    private static Reminder createEditedReminder(Reminder reminderToEdit,
                                                 EditReminderDescriptor editReminderDescriptor)
            throws CommandException {
        assert reminderToEdit != null;

        ReminderName updatedName = editReminderDescriptor.getName().orElse(reminderToEdit.getName());
        Datetime updatedDeadline = editReminderDescriptor.getDeadline().orElse(reminderToEdit.getDeadline());
        ReminderPriority updatedPriority = editReminderDescriptor.getPriority().orElse(reminderToEdit.getPriority());
        ReminderDescription updatedDescription = editReminderDescriptor.getDescription()
                .orElse(reminderToEdit.getDescription());
        ReminderStatus sameStatus = reminderToEdit.getStatus();

        Reminder editedReminder = new Reminder(updatedName, updatedDeadline,
                updatedPriority, updatedDescription, sameStatus);

        if (editedReminder.equals(reminderToEdit)) {
            throw new CommandException(MESSAGE_UNCHANGED_FIELD);
        }
        return editedReminder;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditReminderCommand)) {
            return false;
        }

        // state check
        EditReminderCommand e = (EditReminderCommand) other;
        return index.equals(e.index)
                && editReminderDescriptor.equals(e.editReminderDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditReminderDescriptor {
        private ReminderName name;
        private Datetime deadline;
        private ReminderDescription description;
        private ReminderPriority priority;

        public EditReminderDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditReminderDescriptor(EditReminderDescriptor toCopy) {
            setName(toCopy.name);
            setDeadline(toCopy.deadline);
            setPriority(toCopy.priority);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, deadline, priority, description);
        }

        public void setName(ReminderName name) {
            this.name = name;
        }

        public Optional<ReminderName> getName() {
            return Optional.ofNullable(name);
        }

        public void setDeadline(Datetime deadline) {
            this.deadline = deadline;
        }

        public Optional<Datetime> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setPriority(ReminderPriority priority) {
            this.priority = priority;
        }

        public Optional<ReminderPriority> getPriority() {
            return Optional.ofNullable(priority);
        }

        public void setDescription(ReminderDescription description) {
            this.description = description;
        }

        public Optional<ReminderDescription> getDescription() {
            return Optional.ofNullable(description);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditReminderDescriptor)) {
                return false;
            }

            // state check
            EditReminderDescriptor e = (EditReminderDescriptor) other;

            return getName().equals(e.getName())
                    && getDeadline().equals(e.getDeadline())
                    && getDescription().equals(e.getDescription())
                    && getPriority().equals(e.getPriority());
        }
    }
}
