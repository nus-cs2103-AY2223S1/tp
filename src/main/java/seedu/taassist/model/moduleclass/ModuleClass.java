package seedu.taassist.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.AppUtil.checkArgument;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.taassist.commons.util.StringUtil.caseInsensitiveEquals;

import java.util.List;
import java.util.Objects;

import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Represents a Class in TA-Assist.
 * Guarantees: immutable; name is valid as declared in {@link #isValidModuleClassName(String)}
 */
public class ModuleClass implements Identity<ModuleClass>, Comparable<ModuleClass> {

    public static final String MESSAGE_CONSTRAINTS = "Class name should be alphanumeric and"
            + " doesn't exceed 25 characters.";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private final String className;

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
        this.sessions = new UniqueList<>();
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
        this.sessions = new UniqueList<>();
        this.sessions.addAll(sessions);
    }

    /**
     * Constructs a {@code ModuleClass} with the provided unique list of {@code Session}-s.
     *
     * @param className A valid class name.
     * @param sessions A unique list of sessions.
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
    public static boolean isValidModuleClassName(String className) {
        return className.matches(VALIDATION_REGEX) && className.length() <= 25;
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
        if (hasSession(session)) {
            return this;
        }
        UniqueList<Session> newSessions = new UniqueList<>();
        newSessions.add(session);
        return new ModuleClass(className, newSessions);
    }

    /**
     * Returns a new {@code ModuleClass} by removing the {@code session}.
     */
    public ModuleClass removeSession(Session toRemove) {
        requireNonNull(toRemove);
        UniqueList<Session> newSessions = new UniqueList<>();
        for (Session session : sessions) {
            if (!session.isSame(toRemove)) {
                newSessions.add(session);
            }
        }
        return new ModuleClass(className, newSessions);
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
                && caseInsensitiveEquals(className, ((ModuleClass) other).className)
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
                || (otherModule != null && caseInsensitiveEquals(this.className, otherModule.className));
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
        return getClassName();
    }

    @Override
    public int compareTo(ModuleClass other) {
        return className.compareTo(other.className);
    }

}
