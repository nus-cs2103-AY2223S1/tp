package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.DateTime;
import seedu.address.model.reminder.Reminder;

/**
 *  Creates a tag in the address book.
 */
public class ReminderCreateCommand extends ReminderCommandGroup {
    public static final String COMMAND_WORD = COMMAND_GROUP;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates reminders for a person "
            + "with the specified description and time.\n"
            + "Parameters: [INDEX] "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATETIME + "DATETIME\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + "Meeting" + " " + PREFIX_DATETIME + "2022-12-28 11:00";

    public static final String MESSAGE_SUCCESS = "New reminder created: \n%1$s";

    private final Optional<Index> index;
    private final String description;
    private final DateTime dateTime;

    /**
     * @param index of the person in the filtered person list to set reminder for
     * @param description of the reminder
     * @param dateTime of the reminder
     */
    public ReminderCreateCommand(Index index, String description, DateTime dateTime) {
        requireAllNonNull(index, description, dateTime);
        this.index = Optional.of(index);
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * @param description of the reminder
     * @param dateTime of the reminder
     */
    public ReminderCreateCommand(String description, DateTime dateTime) {
        requireAllNonNull(description, dateTime);
        this.index = Optional.empty();
        this.description = description;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.isPresent() && index.get().getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } else if (!index.isPresent() && !model.hasTargetPerson()) {
            throw new CommandException(Messages.MESSAGE_NO_TARGET_PERSON);
        }

        Person person = index.map(i -> lastShownList.get(i.getZeroBased())).orElseGet(() -> model.getTargetPerson());
        Name name = person.getName();
        Phone phone = person.getPhone();
        Reminder reminder = new Reminder(description, dateTime, name, phone);
        if (model.reminderExists(reminder)) {
            throw new CommandException(String.format(Messages.MESSAGE_REMINDER_ALREADY_EXIST, reminder));
        }
        model.addReminder(reminder);

        return new CommandResult(String.format(MESSAGE_SUCCESS, reminder));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ReminderCreateCommand)) {
            return false;
        }

        // state check
        ReminderCreateCommand o = (ReminderCreateCommand) other;
        return description.equals(o.description) && dateTime.equals(o.dateTime);
    }
}
