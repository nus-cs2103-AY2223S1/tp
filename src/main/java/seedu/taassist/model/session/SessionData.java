package seedu.taassist.model.session;

import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a session data of student in a module.
 */
public class SessionData {

    private Session session;
    private StudentSessionData data;

    /**
     * Constructs a {@code SessionData} with the given session and student session data.
     */
    public SessionData(Session session, StudentSessionData data) {
        requireAllNonNull(session, data);
        this.session = session;
        this.data = data;
    }

}
