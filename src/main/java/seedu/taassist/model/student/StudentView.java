package seedu.taassist.model.student;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.session.SessionData;

/**
 * Handles the view of a Student for UI, i.e. the grade of a Student along with their identity.
 * Guarantees: Immutable.
 */
public class StudentView {
    private final Student student;
    private final Optional<SessionData> sessionData;

    public StudentView(Student student) {
        this.student = student;
        sessionData = null;
    }

    private StudentView(Student student, SessionData sessionData) {
        this.student = student;
        this.sessionData = Optional.ofNullable(sessionData);
    }

    public Student getStudent() {
        return student;
    }

    public boolean isSessionQueried() {
        return sessionData != null;
    }

    public SessionData getSessionData() {
        return sessionData.get();
    }

    public StudentView withSession(ModuleClass targetClass, Session targetSession) {
        requireAllNonNull(targetClass, targetSession);
        return new StudentView(student, student.findNullableSessionData(targetClass, targetSession));
    }

    public StudentView withoutSession() {
        return new StudentView(student);
    }

    public String toString() {
        return String.format("[%s, %s]", student, sessionData);
    }
}
