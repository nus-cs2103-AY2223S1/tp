package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

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
            + "Example: " + COMMAND_WORD + " r/update client information d/20-10-2022";

    public static final String MESSAGE_NOT_IMPLEMENTED_YET =
            "Remind command not implemented yet!";

    public static final String SHOWING_REMINDER_MESSAGE = "Displayed reminders.";

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
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getBirthday(), reminder, personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
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
