package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.ClassGroup;
import seedu.address.model.student.Student;

/**
 * Changes the class of an existing student in the address book.
 */
public class ClassGroupCommand extends Command {

    public static final String COMMAND_WORD = "class";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the class of the student identified "
            + "by the index number used in the last student listing. "
            + "Existing class will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "c/ [CLASS]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "c/ AY2223S1-CS2103T-W12";

    public static final String MESSAGE_ADD_CLASS_GROUP_SUCCESS = "Added class to Student: %1$s";
    public static final String MESSAGE_DELETE_CLASS_GROUP_SUCCESS = "Removed class from Student: %1$s";

    private final Index index;
    private final ClassGroup classGroup;

    /**
     * @param index of the student in the filtered student list to edit the class
     * @param classGroup of the student to be updated to
     */
    public ClassGroupCommand(Index index, ClassGroup classGroup) {
        requireAllNonNull(index, classGroup);

        this.index = index;
        this.classGroup = classGroup;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getPhone(), studentToEdit.getEmail(), classGroup,
                studentToEdit.getStudentId(), studentToEdit.getTags(), studentToEdit.getAttendance());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether
     * the class group is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !classGroup.value.isEmpty()
                ? MESSAGE_ADD_CLASS_GROUP_SUCCESS
                : MESSAGE_DELETE_CLASS_GROUP_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ClassGroupCommand)) {
            return false;
        }

        ClassGroupCommand e = (ClassGroupCommand) other;
        return index.equals(e.index)
                && classGroup.equals(e.classGroup);
    }
}
