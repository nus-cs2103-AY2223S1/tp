package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.person.Reminder;

/**
 * Gets reminders of all upcoming birthdays for the user.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sets a reminder for a given contact\n"
            + "Example: " + COMMAND_WORD + " 1 r/update client information d/20-10-2022";
    public static final String MESSAGE_DATE_CONSTRAINTS = "Date should be in the format 'D-MM-YYYY'";
    public static final String MESSAGE_INVALID_DATE = "The date provided is invalid!";
    public static final String MESSAGE_EMPTY_REMINDER = "The reminder should not be empty!";

    public static final String MESSAGE_ADD_REMINDER_SUCCESS = "Added reminder to Person: %1$s";
    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Removed reminder from Person: %1$s";

    private final Index index;
    private final Reminder reminder;

    /**
     * @param index of the person in the filtered person list to edit
     * @param reminder to associate with the person
     */
    public ReminderCommand(Index index, Reminder reminder) {
        requireAllNonNull(index, reminder);
        this.index = index;
        this.reminder = reminder;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        personToEdit.getReminders().add(reminder);
        model.addReminder(personToEdit, reminder);

        return new CommandResult(generateSuccessMessage(personToEdit));
    }

    /**
     * Generates a command execution success message based on whether
     * the reminder is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !reminder.task.isEmpty() ? MESSAGE_ADD_REMINDER_SUCCESS : MESSAGE_DELETE_REMINDER_SUCCESS;
        return String.format(message, personToEdit);
    }
}
