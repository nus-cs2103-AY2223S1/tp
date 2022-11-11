package seedu.studmap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.studmap.commons.core.Messages.MESSAGE_DUPLICATE_STUDENT;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import seedu.studmap.commons.core.Messages;
import seedu.studmap.commons.core.index.Index;
import seedu.studmap.commons.core.index.IndexListGenerator;
import seedu.studmap.logic.commands.commons.StudentEditor;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.model.Model;
import seedu.studmap.model.student.Student;

/**
 * Abstract class for any command that wants to edit student fields. Provides some basic abstractions to reduce
 * duplicated code, such as the main execution of the command (retrieval of StudentData etc.)
 *
 * @param <T> StudentEditor that this command uses to edit each specified Student.
 */
public abstract class EditStudentCommand<T extends StudentEditor> extends Command {

    protected T studentEditor;
    protected Function<Model, List<Index>> indicesToEdit;

    /**
     * @param indexListGenerator Function that produces list of indices of students
     *                           in the filtered student list to edit
     * @param studentEditor      details to edit the student(s) with
     */
    protected EditStudentCommand(IndexListGenerator indexListGenerator, T studentEditor) {
        this.indicesToEdit = indexListGenerator;
        this.studentEditor = studentEditor;
    }

    public abstract String getSingleEditSuccessMessage(Student editedStudent);

    public abstract String getMultiEditSuccessMessage(List<Student> editedStudents);

    public abstract String getSingleUneditedMessage(Student uneditedStudent);

    public abstract String getMultiUneditedMessage(List<Student> uneditedStudents);

    public abstract String getNoEditMessage();

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return executeNoRefresh(model);
    }

    /**
     * Edits the student without returning to the full students list.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    public CommandResult executeNoRefresh(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        ArrayList<Student> studentsToEdit = new ArrayList<>();
        ArrayList<Student> editedStudents = new ArrayList<>();
        ArrayList<Student> uneditedStudents = new ArrayList<>();

        if (!studentEditor.hasEdits()) {
            return new CommandResult(getNoEditMessage());
        }

        for (Index index : indicesToEdit.apply(model)) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }
            studentsToEdit.add(lastShownList.get(index.getZeroBased()));
        }

        for (Student studentToEdit : studentsToEdit) {

            StudentEditor.EditResult editResult = studentEditor.editStudent(studentToEdit);
            Student editedStudent = editResult.editedStudent;

            if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
                throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
            }

            if (editResult.isEdited) {
                editedStudents.add(editedStudent);
                model.setStudent(studentToEdit, editedStudent);
            } else {
                uneditedStudents.add(studentToEdit);
            }

        }

        StringBuilder commandOutput = new StringBuilder();

        if (editedStudents.size() == 1) {
            commandOutput.append(getSingleEditSuccessMessage(editedStudents.get(0)));
        } else if (editedStudents.size() > 1) {
            commandOutput.append(getMultiEditSuccessMessage(editedStudents));
        }

        if (uneditedStudents.size() == 1) {
            commandOutput.append("\n").append(getSingleUneditedMessage(uneditedStudents.get(0)));
        } else if (uneditedStudents.size() > 1) {
            commandOutput.append("\n").append(getMultiUneditedMessage(uneditedStudents));
        }

        return new CommandResult(commandOutput.toString().trim());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditStudentCommand)) {
            return false;
        }

        // state check
        EditStudentCommand<?> e = (EditStudentCommand<?>) other;

        return indicesToEdit.equals(e.indicesToEdit) && studentEditor.equals(e.studentEditor);
    }

    /**
     * Returns true if this command will make edits when executed.
     */
    public boolean hasEdits() {
        return studentEditor.hasEdits();
    }

}
