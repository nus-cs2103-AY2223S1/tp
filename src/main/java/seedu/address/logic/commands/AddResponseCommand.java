package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MESSAGE_COUNT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Response;
import seedu.address.model.student.Student;

/**
 * Adds the response count to the student.
 */
public class AddResponseCommand extends Command {

    public static final String COMMAND_WORD = "addresponse";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": edits response count of a student. "
            + "The student is identified by the index number used "
            + "in the last student listing.\n "
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_MESSAGE_COUNT + "7 ";
    public static final String MESSAGE_ADDRESPONSE_SUCCESS = "Edited response count for Student ";
    public static final String MESSAGE_MISSING_PREFIX = "Must include the prefix m/ and a number afterward"
        + " for a valid addresponse command";

    private final Index index;
    private final Response response;

    /**
     * Creates an AddResponseCommand to add the specified {@code Response}
     */
    public AddResponseCommand(Index index, Response response) {
        requireAllNonNull(index, response);

        this.index = index;
        this.response = response;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof AddResponseCommand)) {
            return false;
        }

        AddResponseCommand e = (AddResponseCommand) other;
        return index.equals(e.index)
                && (this.response.equals(e.response));
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());
        Student editedStudent = new Student(
                studentToEdit.getName(), studentToEdit.getTelegram(), studentToEdit.getEmail(),
                response, studentToEdit.getAttendance(), studentToEdit.getHelpTag());

        model.setStudent(studentToEdit, editedStudent);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(MESSAGE_ADDRESPONSE_SUCCESS + studentToEdit.getName().toString());
    }
}
