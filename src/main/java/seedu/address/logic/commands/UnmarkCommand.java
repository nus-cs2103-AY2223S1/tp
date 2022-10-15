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
import seedu.address.model.student.Attendance;
import seedu.address.model.student.Person;
import seedu.address.model.student.PersonData;

/**
 * Unmarks the specified attendance record from the student identified using its displayed index.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Unmarks the attendance for student identified by the index number used in the displayed"
            + " student list.\n Removes attendance record for the class or tutorial specified in the parameter.\n"
            + "Parameters: INDEX (must be positive integer) "
            + PREFIX_CLASS + " [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_CLASS + " T01";

    public static final String MESSAGE_UNMARK_SUCCESS = "Removed Class %1$s from Person: %2$s";
    public static final String MESSAGE_UNMARK_NOTFOUND = "Class %1$s not found in Person: %2$s";

    private final Index index;
    private final Attendance attendance;

    /**
     * @param index Index of the student in the filtered student list to remove the attendance
     * @param attendance Attendance of the student to be removed
     */
    public UnmarkCommand(Index index, Attendance attendance) {
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
        boolean isRemoved = newAttendance.remove(attendance);
        personData.setAttendances(newAttendance);
        Person editedPerson = new Person(personData);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(
                String.format(isRemoved ? MESSAGE_UNMARK_SUCCESS : MESSAGE_UNMARK_NOTFOUND,
                        attendance.className, editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UnmarkCommand)) {
            return false;
        }

        // state check
        UnmarkCommand e = (UnmarkCommand) other;
        return index.equals(e.index)
                && attendance.equals(e.attendance);
    }
}
