package seedu.taassist.model.student;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a session data of student in a module.
 */
public class SessionData implements Identity<SessionData>, Comparable<SessionData> {

    private final Session session;
    private final double grade;

    /**
     * Constructs a {@code SessionData} with the given session and grade.
     */
    public SessionData(Session session, double grade) {
        requireAllNonNull(session);
        /*
         * The following line is to remove references to date from the session.
         * In a SessionData, only the name of the session matters. Also in the
         * JSON file, only the name is saved.
         */
        this.session = new Session(session.getSessionName());
        this.grade = grade;
    }

    /**
     * Returns true if the given string is a valid grade, false otherwise.
     */
    public static boolean isValidGrade(String grade) {
        try {
            double gradeDouble = Double.parseDouble(grade);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public Session getSession() {
        return session;
    }

    public String getSessionName() {
        return session.getSessionName();
    }

    public double getGrade() {
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
                && this.grade == ((SessionData) other).grade;
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
