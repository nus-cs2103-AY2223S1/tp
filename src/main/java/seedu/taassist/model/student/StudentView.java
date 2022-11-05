package seedu.taassist.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * Handles the view of a Student for UI, i.e. a Student's identity along with their grade.
 * Guarantees: Immutable.
 */
public class StudentView {

    private final Student student;

    /* There are three types of values:
       - null -> SessionData has not been queried
       - empty Optional -> SessionData has been queried, but doesn't exist in Student
       - non-empty Optional -> SessionData has been queried and exists in Student
    */
    private final Optional<SessionData> sessionData;

    /**
     * Constructs a {@code StudentView} with the provided {@code Student}.
     *
     * @param student the student.
     */
    public StudentView(Student student) {
        requireNonNull(student);
        this.student = student;
        sessionData = null;
    }

    /**
     * Constructs a {@code StudentView} with the provided {@code Student} and {@code Optional<SessionData>}.
     *
     * @param student the student.
     * @param sessionData the (optional) session data.
     */
    private StudentView(Student student, Optional<SessionData> sessionData) {
        requireAllNonNull(student, sessionData);
        this.student = student;
        this.sessionData = sessionData;
    }

    public Student getStudent() {
        return student;
    }

    /**
     * Returns true if {@code StudentView} has been queried with a session data.
     *
     * @return true if and only if session data was queried.
     */
    public boolean hasSession() {
        return sessionData != null;
    }

    /**
     * Returns an {@code Optional} value encapsulating the {@code SessionData}. The returned {@code Optional} is empty
     * if the queried session data doesn't exist in the encapsulated {@code Student}.
     *
     * @return an {@code Optional} value encapsulating the queried {@code SessionData}.
     * @throws IllegalStateException if {@code StudentView} has not been queried.
     */
    public Optional<SessionData> getSessionData() {
        if (!hasSession()) {
            throw new IllegalStateException("StudentView doesn't contain session data");
        }
        return sessionData;
    }

    /**
     * Creates a new {@StudentView} instance with the provided {@code ModuleClass} and {@code Session} being queried
     * from the encapsulated {@code Student}.
     *
     * @param targetClass the target {@code ModuleClass}.
     * @param targetSession the target {@code Session}.
     * @return a new {@StudentView} instance.
     */
    public StudentView withSession(ModuleClass targetClass, Session targetSession) {
        requireAllNonNull(targetClass, targetSession);
        return new StudentView(student, student.findSessionData(targetClass, targetSession));
    }

    /**
     * Creates a new {@StudentView} instance without any queried {@code SessionData}.
     *
     * @return a new {@StudentView} instace.
     */
    public StudentView withoutSession() {
        return new StudentView(student);
    }

    @Override
    public String toString() {
        return String.format("[%s, %s]", student, sessionData);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof StudentView)) {
            return false;
        }

        StudentView otherView = (StudentView) other;
        return otherView.student.equals(this.student)
                && Objects.equals(otherView.sessionData, this.sessionData);
    }
}
