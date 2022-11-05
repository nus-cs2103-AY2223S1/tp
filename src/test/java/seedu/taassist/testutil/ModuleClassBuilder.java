package seedu.taassist.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

/**
 * A utility class to help with building ModuleClass objects.
 */
public class ModuleClassBuilder {
    public static final String DEFAULT_NAME = "IS1103";
    public static final List<Session> DEFAULT_SESSIONS = new ArrayList<>();

    private String name;
    private List<Session> sessions;

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
    public ModuleClassBuilder(String name, List<Session> sessions) {
        this.name = name.toUpperCase();
        this.sessions = sessions;
    }

    /**
     * Sets the {@code name} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withName(String name) {
        this.name = name.toUpperCase();
        return this;
    }

    /**
     * Sets the {@code sessions} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withSessions(Session... sessions) {
        this.sessions = Arrays.stream(sessions)
                .collect(Collectors.toList());
        return this;
    }

    /**
     * Sets the {@code sessions} of the {@code ModuleClass} that we are building.
     */
    public ModuleClassBuilder withSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }


    public ModuleClass build() {
        return new ModuleClass(name, sessions);
    }
}
