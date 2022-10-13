package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonData;

/**
 * Marks a person identified using its displayed index from the address book as having attended a class or tutorial.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance for person identified by the index number used in the displayed"
            + " person list.\n Marks attendance for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) OPTION (must be absent/present) "
            + PREFIX_CLASS + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 present " + PREFIX_CLASS + " T01";

    public static final String MESSAGE_MARK_SUCCESS = "Marked Person as %1$s: %2$s";

    private final Index index;
    private final Attendance attendance;

    /**
     * @param index Index of the person in the filtered person list to edit the attendance
     * @param attendance Attendance of the person to be added
     */
    public MarkCommand(Index index, Attendance attendance) {
        this.index = index;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        PersonData personData = personToEdit.getPersonData();

        Set<Attendance> newAttendance = new HashSet<>(personToEdit.getAttendances());
        newAttendance.add(attendance);
        personData.setAttendances(newAttendance);
        Person editedPerson = new Person(personData);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(
                String.format(MESSAGE_MARK_SUCCESS, attendance.getAttendance(), editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkCommand)) {
            return false;
        }

        // state check
        MarkCommand e = (MarkCommand) other;
        return index.equals(e.index)
                && attendance.equals(e.attendance);
    }
}
