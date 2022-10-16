package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

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

    public static final String COMMAND_SPECIFIER = "create";
    public static final String COMMAND_WORD = COMMAND_GROUP + " " + COMMAND_SPECIFIER;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates tags with the names given. "
            + "Parameters: "
            + "[TAG1] [TAG2] ... \n"
            + "Example: " + COMMAND_WORD + " tag1 tag2 tag3";

    public static final String MESSAGE_SUCCESS = "New reminder created: %1$s";

    private final Optional<Index> index;
    private final String description;
    private final DateTime dateTime;

    /**
     * @param index of the person in the filtered person list to set reminder for
     * @param description of the reminder
     * @param dateTime of the reminder
     */
    public ReminderCreateCommand(Index index, String description, DateTime dateTime) {
        requireNonNull(index);
        requireNonNull(description);
        requireNonNull(dateTime);
        this.index = Optional.of(index);
        this.description = description;
        this.dateTime = dateTime;
    }

    /**
     * @param description of the reminder
     * @param dateTime of the reminder
     */
    public ReminderCreateCommand(String description, DateTime dateTime) {
        requireNonNull(description);
        requireNonNull(dateTime);
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


        Person person = index.isPresent() ? lastShownList.get(index.get().getZeroBased())
                : model.getTargetPerson();
        Name name = person.getName();
        Phone phone = person.getPhone();
        Reminder reminder = new Reminder(description, dateTime, name, phone);

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
