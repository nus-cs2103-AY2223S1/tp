package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_SESSION;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.commaSeparate;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.List;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.moduleclass.exceptions.SessionNotFoundException;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.student.Student;

/**
 * Gives a grade to student for a session.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = "> Gives a grade to student(s) for a session.\n"
            + "Parameters: INDEX... "
            + PREFIX_SESSION + "SESSION "
            + PREFIX_GRADE + "GRADE\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_SESSION + "Tutorial1 "
            + PREFIX_GRADE + "100";

    public static final String MESSAGE_SUCCESS = "Grade [ %1$s ] for [ %2$s ] given to these student(s):\n[ %3$s ]";

    private final List<Index> indices;
    private final Session session;
    private final Double grade;

    /**
     * Creates a GradeCommand to give the specified {@code grade} to the students at the specified {@code indices}
     * for the specified {@code session}.
     *
     * @param indices List of {@code Index} objects specifying the Student objects to give grades to.
     * @param session Session to grade.
     * @param grade Numerical grade for the {@code session}.
     */
    public GradeCommand(List<Index> indices, Session session, Double grade) {
        requireAllNonNull(indices, session, grade);
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
        Session existingSession;

        try {
            existingSession = focusedClass.getSessionWithSameName(session);
        } catch (SessionNotFoundException snfe) {
            throw new CommandException(String.format(MESSAGE_INVALID_SESSION, session.getSessionName(), focusedClass));
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        List<Student> studentsToGrade;
        try {
            studentsToGrade = ParserStudentIndexUtil.parseStudentsFromIndices(indices, lastShownList);
        } catch (ParseException e) {
            throw new CommandException(e.getMessage());
        }

        studentsToGrade.forEach(s -> model.setStudent(s, s.updateGrade(focusedClass, existingSession, grade)));

        String message = getCommandMessage(studentsToGrade, existingSession, grade);
        return new CommandResult(message);
    }

    /**
     * Returns the command message on successful execution of the command.
     *
     * @param students Student objects that were graded.
     * @param session Session that was graded.
     * @param grade Numerical grade given to {@code session}.
     * @return Command message showing which Student objects were given {@code grade} for {@code session}.
     */
    public static String getCommandMessage(List<Student> students, Session session, Double grade) {
        String studentNames = commaSeparate(students, student -> student.getName().toString());
        return String.format(MESSAGE_SUCCESS, grade, session.getSessionName(), studentNames);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeCommand // instanceof handles nulls
                && indices.equals(((GradeCommand) other).indices)
                && session.equals(((GradeCommand) other).session))
                && grade.equals(((GradeCommand) other).grade);
    }
}
