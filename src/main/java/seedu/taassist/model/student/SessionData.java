package seedu.taassist.model.student;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a session data of student in a module.
 */
public class SessionData implements Identity<SessionData>, Comparable<SessionData> {

    public static final Double GRADE_MIN = 0.0;
    public static final Double GRADE_MAX = 1000.0;
    public static final String MESSAGE_CONSTRAINTS =
        String.format("Grades should be a number between %.2f and %.2f", GRADE_MIN, GRADE_MAX);

    private final Session session;
    private final Double grade;

    /**
     * Constructs a {@code SessionData} with the given session and grade.
     * The grade is rounded to 2 decimal places.
     *
     * @param session Session to keep track.
     * @param grade Grade given to {@code session}.
     */
    public SessionData(Session session, Double grade) {
        requireAllNonNull(session, grade);
        /*
         * The following line is to remove references to date from the session.
         * In a SessionData, only the name of the session matters. Also in the
         * JSON file, only the name is saved.
         */
        this.session = new Session(session.getSessionName());
        this.grade = Math.round(grade * 100.0) / 100.0;
    }

    /**
     * Checks if a given double a valid grade.
     *
     * @param grade Grade to check against.
     * @return True if {@code grade} is a valid grade.
     */
    public static boolean isValidGrade(Double grade) {
        return GRADE_MIN <= grade && grade <= GRADE_MAX;
    }

    public Session getSession() {
        return session;
    }

    public String getSessionName() {
        return session.getSessionName();
    }

    public Double getGrade() {
        return grade;
    }

    @Override
    public boolean isSame(SessionData other) {
        return other == this
                || (other != null && other.session.isSame(this.session));
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, grade);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SessionData
                && this.session.equals(((SessionData) other).session))
                && this.grade.equals(((SessionData) other).grade);
    }

    @Override
    public String toString() {
        return session.toString() + ": " + grade;
    }

    @Override
    public int compareTo(SessionData other) {
        return session.compareTo(other.session);
    }
}
