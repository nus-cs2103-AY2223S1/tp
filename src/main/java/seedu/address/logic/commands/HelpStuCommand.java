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
 * Adds a help tag to an existing student in SETA.
 */
public class HelpStuCommand extends Command {

    public static final String COMMAND_WORD = "helpstu";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a help tag to a student"
            + " by the index number used in the last person listing.\n"
            + "Example: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_HELP_STU_SUCCESS = "Added help tag to %1$s";

    private final Index index;

    public HelpStuCommand(Index index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof HelpStuCommand)) {
            return false;
        }

        HelpStuCommand e = (HelpStuCommand) other;
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
        Student editedStudent = new Student(
                studentToEdit.getName(),
                studentToEdit.getTelegram(),
                studentToEdit.getEmail(),
                studentToEdit.getResponse(),
                studentToEdit.getAttendance(),
                new HelpTag(true));

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_HELP_STU_SUCCESS, editedStudent));
    }
}
