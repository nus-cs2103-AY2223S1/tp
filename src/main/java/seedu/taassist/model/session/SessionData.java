package seedu.taassist.model.session;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.taassist.model.student.StudentSessionData;
import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a session data of student in a module.
 */
public class SessionData implements Identity<SessionData> {

    private final Session session;
    private final StudentSessionData data;

    /**
     * Constructs a {@code SessionData} with the given session and student session data.
     */
    public SessionData(Session session, StudentSessionData data) {
        requireAllNonNull(session, data);
        this.session = session;
        this.data = data;
    }

    @Override
    public boolean isSame(SessionData other) {
        return other == this
                || (other != null && other.session.equals(this.session));
    }

    @Override
    public int hashCode() {
        return Objects.hash(session, data);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SessionData
                && this.session.equals(((SessionData) other).session))
                && this.data.equals(((SessionData) other).data);
    }

}
