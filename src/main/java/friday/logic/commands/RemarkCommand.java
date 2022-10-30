package friday.logic.commands;

import static friday.commons.util.CollectionUtil.requireAllNonNull;
import static friday.logic.parser.CliSyntax.PREFIX_REMARK;

import java.util.List;

import friday.commons.core.Messages;
import friday.commons.core.index.Index;
import friday.logic.commands.exceptions.CommandException;
import friday.model.Model;
import friday.model.student.Remark;
import friday.model.student.Student;

/**
 * Changes the remark of an existing student in FRIDAY.
 */
public class RemarkCommand extends Command {
    public static final String COMMAND_WORD = "remark";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the student identified "
            + "by the index number used in the last student listing. \n"
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "[REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_REMARK + "Likes to swim.";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Student: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Student: %1$s";

    private final Index index;
    private final Remark remark;

    /**
     * @param index of the student in the filtered student list to edit the remark
     * @param remark of the student to be updated to
     */
    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(studentToEdit.getName(), studentToEdit.getTelegramHandle(),
                studentToEdit.getConsultation(), studentToEdit.getMasteryCheck(), remark, studentToEdit.getTags(),
                studentToEdit.getGradesList());

        model.setStudent(studentToEdit, editedStudent);
        return new CommandResult(generateSuccessMessage(editedStudent));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code studentToEdit}.
     */
    private String generateSuccessMessage(Student studentToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, studentToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand e = (RemarkCommand) other;
        return index.equals(e.index)
                && remark.equals(e.remark);
    }
}
