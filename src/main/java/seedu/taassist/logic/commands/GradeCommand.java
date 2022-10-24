package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;

/**
 * Gives a grade to student for a session.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gives a grade to a student for a session. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_SESSION + "SESSION (must be a valid session) "
            + PREFIX_GRADE + "GRADE (must be a non-negative number)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SESSION + "Tutorial1 "
            + PREFIX_GRADE + "100";


    public static final String MESSAGE_SUCCESS = "Grade(s) given to student(s):\n%1$s";
    public static final String MESSAGE_INVALID_SESSION = "The session %1$s does not exist in class %2$s.";

    private final List<Index> indices;
    private final Session session;
    private final double grade;

    /**
     * Creates a GradeCommand to give the specified {@code grade} to the student at the specified {@code index}
     * for the specified {@code session}.
     */
    public GradeCommand(List<Index> indices, Session session, double grade) {
        requireAllNonNull(indices, session);
        this.indices = indices;
        this.session = session;
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass focusedClass = model.getFocusedClass();
        if (!focusedClass.hasSession(session)) {
            throw new CommandException(String.format(MESSAGE_INVALID_SESSION, session, focusedClass));
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> oldStudents;
        try {
            oldStudents = ParserStudentIndexUtil.parseStudentsFromIndices(indices, lastShownList);
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }

        for (Student oldStudent : oldStudents) {
            Student newStudent = Student.getUpdatedStudent(oldStudent, focusedClass, session, grade);
            model.setStudent(oldStudent, newStudent);
        }

        String message = getSuccessMessage(oldStudents);

        return new CommandResult(message);
    }

    private static String getSuccessMessage(List<Student> students) {
        return String.format(MESSAGE_SUCCESS, students.stream().map(student ->
                student.getName().toString()).collect(Collectors.joining("\n")));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeCommand // instanceof handles nulls
                && indices.equals(((GradeCommand) other).indices)
                && grade == ((GradeCommand) other).grade
                && session.equals(((GradeCommand) other).session)); // state check
    }

}
