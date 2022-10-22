package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.HelpTag;
import seedu.address.model.student.Student;

/**
 * Removes help tag of an existing student that needs help.
 */
public class UnhelpStuCommand extends Command {

    public static final String COMMAND_WORD = "unhelpstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Removes help tag from a student"
            + " by the index number used in the last person listing.\n"
            + "Example: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_UNHELP_STU_SUCCESS = "Removed help tag to %1$s";

    private final Index index;

    public UnhelpStuCommand(Index index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UnhelpStuCommand)) {
            return false;
        }

        UnhelpStuCommand e = (UnhelpStuCommand) other;
        return index.equals(e.index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        if (!studentToEdit.needsHelp()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_TO_UNHELP);
        }

        Student editedStudent = new Student(
                studentToEdit.getName(),
                studentToEdit.getTelegram(),
                studentToEdit.getEmail(),
                studentToEdit.getResponse(),
                studentToEdit.getAttendance(),
                new HelpTag(false));

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_UNHELP_STU_SUCCESS, editedStudent));
    }
}
