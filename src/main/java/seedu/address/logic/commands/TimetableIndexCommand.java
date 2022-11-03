package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Command to show timetable for a contact.
 */
public class TimetableIndexCommand extends TimetableCommand {
    public static final String MESSAGE_TIMETABLE_ACKNOWLEDGEMENT = "Showing contact at index %d timetable as requested";
    public static final String MESSAGE_NO_LESSONS = "No lessons added to contact at index %d!";

    private Index index;

    /**
     * constructor for new instance.
     *
     * @param index of contact to add lesson to.
     */
    public TimetableIndexCommand(Index index) {
        super();
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person person = lastShownList.get(index.getZeroBased());

        if (!model.setTimetable(person.getLessons())) {
            throw new CommandException(String.format(MESSAGE_NO_LESSONS, index.getOneBased()));
        }
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_TIMETABLE_ACKNOWLEDGEMENT, index.getOneBased()),
                false, false, true);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o == this) {
            return true;
        }

        if (o instanceof TimetableIndexCommand) {
            TimetableIndexCommand command = (TimetableIndexCommand) o;
            return command.index.equals(this.index);
        }

        return false;
    }
}
