package seedu.taassist.testutil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;

public class ModuleClassBuilder {
    public static final String DEFAULT_NAME = "IS1103";

    private String name;
    private List<Session> sessions;

    public ModuleClassBuilder() {
        name = DEFAULT_NAME;
    }

    public ModuleClassBuilder(String name, List<Session> sessions) {
        this.name = name;
        this.sessions = sessions;
    }

    public ModuleClassBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ModuleClassBuilder withSessions(String... sessions) {
        this.sessions = Arrays.stream(sessions)
                .map(Session::new)
                .collect(Collectors.toList());
        return this;
    }

    public ModuleClass build() {
        return new ModuleClass(name, sessions);
    }
}
