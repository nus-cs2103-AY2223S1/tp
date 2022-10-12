package seedu.taassist.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;

/**
 * Represents a Class in TA-Assist.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleClassName(String)}
 */
public class ModuleClass implements Identity<ModuleClass> {

    public static final String MESSAGE_CONSTRAINTS = "Class names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String className;

    // TODO: Implement a more robust solution to check for session uniqueness within the list.
    private final List<Session> sessions;

    /**
     * Constructs a {@code ModuleClass}.
     *
     * @param className A valid class name.
     */
    public ModuleClass(String className) {
        requireNonNull(className);
        checkArgument(isValidModuleClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className;
        sessions = new ArrayList<Session>();
    }

    /**
     * Constructs a {@code ModuleClass} with the provided list of {@code Session}-s.
     *
     * @param className A valid class name.
     * @param sessions A list of sessions.
     */
    public ModuleClass(String className, List<Session> sessions) {
        requireAllNonNull(className, sessions);
        checkArgument(isValidModuleClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className;
        this.sessions = sessions;
    }

    /**
     * Returns true if a given string is a valid class name.
     */
    public static boolean isValidModuleClassName(String test) { // TODO: Ensure that class exists
        return test.matches(VALIDATION_REGEX);
    }

    public String getClassName() {
        return className;
    }

    /**
     * Returns an immutable sessions list, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    /**
     * Returns true if both modules have the same name and session list.
     * This defines a stronger notion of equality between two module classes.
     *
     * @param other the object to be compared to.
     * @return true if objects are equal.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleClass // instanceof handles nulls
                && className.equals(((ModuleClass) other).className)
                && sessions.equals(((ModuleClass) other).sessions));
    }

    /**
     * Returns true if both modules have the same name.
     * This defines a weaker notion of equality between two module classes.
     *
     * @param otherModule the module class to be compared to.
     * @return true if both modules have the same name.
     */
    @Override
    public boolean isSame(ModuleClass otherModule) {
        return otherModule == this
            || (otherModule != null && otherModule.className.equals(this.className));
    }

    public boolean hasSession(Session toCheck) {
        return sessions.stream().anyMatch(toCheck::isSameSession);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className, sessions);
    }

    /**
     * Formats state as text for viewing.
     */
    public String toString() {
        return '[' + className + ']';
    }
}
