package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;

import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Attendance;
import seedu.address.model.person.AttendanceList;
import seedu.address.model.person.NameIsKeywordsPredicate;
import seedu.address.model.person.Person;

/**
 * Adds attendance to an existing person in the address book.
 */
public class AttendanceCommand extends Command {
    public static final String COMMAND_WORD = "attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds attendance to the person identified "
            + "by the index number used in the last person listing. "
            + "Existing attendance will not be modified.\n"
            + "Parameters: INDEX (must be a positive integer)"
            + "[" + PREFIX_ATTENDANCE + "ATTENDANCE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "a/ 2022-08-08.";

    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "Added attendance to Person: %1$s";
    public static final String MESSAGE_DELETE_ATTENDANCE_SUCCESS = "Removed attendance from Person: %1$s";

    public static final String MESSAGE_IN_DAY_MODE = "You need to be in list or view mode to add an attendance.";

    private final Index index;
    private final Attendance attendance;

    /**
     * @param index of the person in the filtered person list to edit the attendance
     * @param attendance of the person to add
     */
    public AttendanceCommand(Index index, Attendance attendance) {
        requireAllNonNull(index, attendance);

        this.index = index;
        this.attendance = attendance;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        if (model.isDayView()) {
            throw new CommandException(MESSAGE_IN_DAY_MODE);
        }

        List<Person> lastShownList = model.getFilteredPersonList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        AttendanceList attendanceList = personToEdit.getAttendanceList();
        attendanceList.addAttendance(attendance);
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getLessonPlan(),
                personToEdit.getHomeworkList(), personToEdit.getAttendanceList(),
                personToEdit.getSessionList(),
                personToEdit.getGradeProgressList(), personToEdit.getTags());

        model.setPerson(personToEdit, editedPerson);
        if (model.isFullView()) {
            editedPerson.setFullView();
            String[] newNameKeywords = {personToEdit.getName().fullName};
            model.updateFilteredPersonList(new NameIsKeywordsPredicate(Arrays.asList(newNameKeywords)));
        }
        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    /**
     * Generates a command execution success message based on
     * whether the attendance is added or removed
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !attendance.toString().isEmpty() ? MESSAGE_ADD_ATTENDANCE_SUCCESS
                                                            : MESSAGE_DELETE_ATTENDANCE_SUCCESS;
        return String.format(message, personToEdit);
    }

    @Override
    public boolean equals(Object other) {
        //short circuit if same object
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof AttendanceCommand)) {
            return false;
        }

        //state check
        AttendanceCommand temp = (AttendanceCommand) other;
        return index.equals(temp.index) && attendance.equals(temp.attendance);
    }

}
