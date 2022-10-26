package seedu.taassist.testutil;

import java.util.List;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * A utility class to help with building ModuleClass objects.
 */
public class ModuleClassBuilder {
    public static final String DEFAULT_NAME = "IS1103";
    public static final UniqueList<Session> DEFAULT_SESSIONS = new UniqueList<>();

    private String name;
    private UniqueList<Session> sessions;

    /**
     * Creates a {@code ModuleClassBuilder} with the default details.
     */
    public ModuleClassBuilder() {
        name = DEFAULT_NAME;
        sessions = DEFAULT_SESSIONS;
    }

    /**
     * Creates a {@code ModuleClassBuilder} with the provided {@code name} and {@code sessions}.
     */
    public ModuleClassBuilder(String name, UniqueList<Session> sessions) {
        this.name = name;
        this.sessions = sessions;
    }

    /**
     * Sets the {@code name} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code sessions} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withSessions(Session... sessions) {
        this.sessions = new UniqueList<>();
        this.sessions.addAll(List.of(sessions));
        return this;
    }

    public ModuleClass build() {
        return new ModuleClass(name, sessions);
    }
}
