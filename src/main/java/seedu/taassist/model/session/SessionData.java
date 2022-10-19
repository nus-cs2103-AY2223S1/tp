package seedu.taassist.model.session;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a session data of student in a module.
 */
public class SessionData implements Identity<SessionData> {

    private final Session session;
    private final double grade;

    /**
     * Constructs a {@code SessionData} with the given session and grade.
     */
    public SessionData(Session session, double grade) {
        requireAllNonNull(session);
        this.session = session;
        this.grade = grade;
    }

    public Session getSession() {
        return session;
    }

    public double getGrade() {
        return grade;
    }

    /**
     * Returns a list of session data by updating {@code oldSessionDataList} where the
     * specified {@code session} has specified {@code grade}.
     * If the {@code session} is not found, a new session with the {@code grade} is created.
     */
    public static List<SessionData> getUpdatedSessionDataList(List<SessionData> oldSessionDataList,
            Session session, double grade) {
        requireAllNonNull(oldSessionDataList, session);
        List<SessionData> newSessionDataList = oldSessionDataList.stream()
                .filter(sessionData -> !sessionData.getSession().equals(session))
                .collect(Collectors.toList());
        newSessionDataList.add(new SessionData(session, grade));
        return newSessionDataList;
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
}
