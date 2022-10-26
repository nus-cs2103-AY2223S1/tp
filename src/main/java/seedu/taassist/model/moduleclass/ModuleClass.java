package seedu.taassist.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Represents a Class in TA-Assist.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleClassName(String)}
 */
public class ModuleClass implements Identity<ModuleClass> {

    public static final String MESSAGE_CONSTRAINTS = "Class names should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String className;

    // TODO: Implement a more robust solution to check for session uniqueness within the list.
    private final UniqueList<Session> sessions;

    /**
     * Constructs a {@code ModuleClass}.
     *
     * @param className A valid class name.
     */
    public ModuleClass(String className) {
        requireNonNull(className);
        checkArgument(isValidModuleClassName(className), MESSAGE_CONSTRAINTS);
        this.className = className;
        sessions = new UniqueList<>();
    }

    /**
     * Constructs a {@code ModuleClass} with the provided list of {@code Session}-s.
     *
     * @param className A valid class name.
     * @param sessions A list of sessions.
     */
    public ModuleClass(String className, UniqueList<Session> sessions) {
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
        return sessions.asUnmodifiableObservableList();
    }

    /**
     * Returns a new {@code ModuleClass} by adding the provided {@code Session} to this {@code ModuleClass}.
     * If the session already exists, this {@code ModuleClass} is returned.
     */
    public ModuleClass addSession(Session session) {
        requireNonNull(session);
        sessions.add(session);
        return this;
    }

    /**
     * Returns a new {@code ModuleClass} by removing the {@code session}.
     */
    public ModuleClass removeSession(Session session) {
        requireNonNull(session);
        sessions.remove(session);
        return this;
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
        return sessions.contains(toCheck);
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

    @Override
    public int compareTo(ModuleClass other) {
        return className.compareTo(other.className);
    }

}
