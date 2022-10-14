package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.AttendanceCommandParser.ATTENDANCE_COMMAND_WORD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.AttendanceCommandParser;
import seedu.address.model.Model;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.Student;

public class AttendanceDeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_DELETE_ATTENDANCE_SUCCESS = "Removed attendance from Student: %1$s";

    public static final String MESSAGE_USAGE = ATTENDANCE_COMMAND_WORD + " "
            + COMMAND_WORD
            + ": Deletes the person identified "
            + "by the index number used in the last person listing. "
            + "Existing attendance will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLASS_GROUP + "[ATTENDANCE]\n"
            + "Example: " + ATTENDANCE_COMMAND_WORD
            + " " + COMMAND_WORD + " 1 ";

    private final Index index;

    public AttendanceDeleteCommand(Index index) {
        requireAllNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }
        Student studentToEdit = lastShownList.get(index.getZeroBased());

        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getClassGroup(), studentToEdit.getStudentId(), studentToEdit.getTags(),
                new AttendanceList());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the attendance is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = MESSAGE_DELETE_ATTENDANCE_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceDeleteCommand)) {
            return false;
        }

        // state check
        AttendanceDeleteCommand e = (AttendanceDeleteCommand) other;
        return index.equals(e.index);
    }
}
