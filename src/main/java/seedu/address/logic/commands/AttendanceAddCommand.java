package seedu.address.logic.commands;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_GROUP;
import static seedu.address.logic.parser.AttendanceCommandParser.ATTENDANCE_COMMAND_WORD;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.AttendanceList;
import seedu.address.model.student.Student;

public class AttendanceAddCommand extends Command {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_ADD_ATTENDANCE_SUCCESS = "Added attendance to Student: %1$s";

    public static final String MESSAGE_USAGE = ATTENDANCE_COMMAND_WORD + " "
            + COMMAND_WORD
            + ": Adds the person identified "
            + "by the index number used in the last person listing. "
            + "Existing attendance will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_CLASS_GROUP + "[ATTENDANCE]\n"
            + "Example: " + ATTENDANCE_COMMAND_WORD
            + " " + COMMAND_WORD + " 1 "
            + PREFIX_CLASS_GROUP + "CS2103T";

    private final Index index;
    private final String mod;

    private final String size;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param mod belonging to the attendance list
     * @param size of the attendance list
     */
    public AttendanceAddCommand(Index index, String mod, String size) {
        requireAllNonNull(index, mod, size);
        this.index = index;
        this.mod = mod;
        this.size = size;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        if (!AttendanceList.isValidSize(size)) {
            throw new CommandException(Messages.MESSAGE_INVALID_ATTENDANCE_LIST_INDEX);
        }

        AttendanceList attendanceList = new AttendanceList(mod, size);
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(),
                studentToEdit.getClassGroup(), studentToEdit.getStudentId(), studentToEdit.getTags(),
                attendanceList);

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
        String message = MESSAGE_ADD_ATTENDANCE_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendanceAddCommand)) {
            return false;
        }

        // state check
        AttendanceAddCommand e = (AttendanceAddCommand) other;
        return index.equals(e.index)
                && mod.equals(e.mod)
                && size.equals(e.size);
    }
}
