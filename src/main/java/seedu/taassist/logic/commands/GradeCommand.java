package seedu.taassist.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.taassist.commons.core.Messages.MESSAGE_NOT_IN_FOCUS_MODE;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.taassist.logic.parser.CliSyntax.PREFIX_SESSION;

import java.util.List;

import seedu.taassist.commons.core.index.Index;
import seedu.taassist.logic.commands.exceptions.CommandException;
import seedu.taassist.logic.parser.ParserStudentIndexUtil;
import seedu.taassist.logic.parser.exceptions.ParseException;
import seedu.taassist.model.Model;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.student.StudentModuleData;
import seedu.taassist.model.student.StudentSessionData;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Gives a grade to student for a session.
 */
public class GradeCommand extends Command {

    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gives a grade to a student. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_SESSION + "SESSION (must be a valid session)\n"
            + PREFIX_GRADE + "GRADE (must be a valid grade) "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_SESSION + "Tutorial1 "
            + PREFIX_GRADE + "100";


    public static final String MESSAGE_SUCCESS = "Grade given to student: %1$s";
    public static final String MESSAGE_INVALID_SESSION = "The session provided is invalid: %1$s";

    private final Index index;
    private final Session session;
    private final double grade;

    /**
     * Creates a GradeCommand to give the specified {@code grade} to the student at the specified {@code index}
     * for the specified {@code session}.
     */
    public GradeCommand(Index index, Session session, double grade) {
        requireAllNonNull(index, session);
        this.index = index;
        this.session = session;
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.isInFocusMode()) {
            throw new CommandException(String.format(MESSAGE_NOT_IN_FOCUS_MODE, COMMAND_WORD));
        }

        ModuleClass moduleClass = model.getFocusedClass();
        if (!moduleClass.hasSession(session)) {
            throw new CommandException(String.format(MESSAGE_INVALID_SESSION, session));
        }

        List<Student> lastShownList = model.getFilteredStudentList();
        Student oldStudent;
        try {
            oldStudent = ParserStudentIndexUtil.parseStudentFromIndex(index, lastShownList);
        } catch (ParseException e) {
            throw new CommandException(String.format(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX));
        }

        Student newStudent = updatedStudent(oldStudent, moduleClass, session, grade);

        model.setStudent(oldStudent, newStudent);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newStudent));
    }

    private Student updatedStudent(Student oldStudent, ModuleClass moduleClass, Session session, double grade) {
        List<StudentModuleData> oldModuleData = oldStudent.getModuleData();
        List<StudentModuleData> newModuleData = updatedModuleData(oldModuleData, moduleClass, session, grade);
        return new Student(
                oldStudent.getName(),
                oldStudent.getPhone(),
                oldStudent.getEmail(),
                oldStudent.getAddress(),
                newModuleData);
    }

    private List<StudentModuleData> updatedModuleData(List<StudentModuleData> oldModuleData,
            ModuleClass moduleClass, Session session, double grade) {
        StudentModuleData oldStudentModuleData = oldModuleData.stream()
                .filter(studentModuleData -> studentModuleData.getModuleClass().isSame(moduleClass))
                .findFirst().get(); // must exist since we are in focus mode
        StudentModuleData newStudentModuleData = updateStudentModuleData(oldStudentModuleData, session, grade);
        UniqueList<StudentModuleData> newModuleData = new UniqueList<>();
        newModuleData.setElements(oldModuleData);
        newModuleData.setElement(oldStudentModuleData, newStudentModuleData);
        return newModuleData.asUnmodifiableObservableList();

    }

    private StudentModuleData updateStudentModuleData(StudentModuleData oldStudentModuleData,
            Session session, double grade) {
        List<SessionData> oldSessions = oldStudentModuleData.getSessionData();
        List<SessionData> newSessions = updatedSessions(oldSessions, session, grade);
        return new StudentModuleData(oldStudentModuleData.getModuleClass(), newSessions);
    }

    /**
     * Updates the session data of the student with the new grade.
     * If the session does not exist then a new entry is created.
     */
    private List<SessionData> updatedSessions(List<SessionData> oldSessions, Session session, double grade) {
        SessionData oldSessionData = oldSessions.stream()
                .filter(sessionData -> sessionData.getSession().isSame(session))
                .findFirst().orElse(null);

        // TODO: Unroll this one more level and copy fields other than grade from StudentSessionData
        SessionData newSessionData = new SessionData(session, new StudentSessionData(grade));

        UniqueList<SessionData> newSessionDataList = new UniqueList<>();
        newSessionDataList.setElements(oldSessions);

        if (oldSessionData == null) {
            newSessionDataList.add(newSessionData);
        } else {
            newSessionDataList.setElement(oldSessionData, newSessionData);
        }

        return newSessionDataList.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GradeCommand // instanceof handles nulls
                && index.equals(((GradeCommand) other).index)
                && grade == ((GradeCommand) other).grade
                && session.equals(((GradeCommand) other).session)); // state check
    }

}
