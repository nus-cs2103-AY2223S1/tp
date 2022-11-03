package seedu.taassist.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.session.Session;
import seedu.taassist.model.uniquelist.Identity;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Represents a student's data for a module.
 */
public class StudentModuleData implements Identity<StudentModuleData>, Comparable<StudentModuleData> {

    private static final double DUMMY_GRADE = 0.0;

    private final ModuleClass moduleClass;
    private final UniqueList<SessionData> sessionDataList = new UniqueList<>();

    /**
     * Constructs a {@code StudentModuleData} with an empty session data list for the given module class.
     */
    public StudentModuleData(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        /*
         * The following line is to remove references to session from the module class.
         * In a StudentModuleData, only the name of the module class is used. Also in the
         * JSON file, only the name is saved.
         */
        this.moduleClass = new ModuleClass(moduleClass.getClassName());
    }

    /**
     * Constructs a {@code StudentModuleData} with the given module class and list of session data.
     */
    public StudentModuleData(ModuleClass moduleClass, List<SessionData> sessionDataList) {
        this(moduleClass);
        requireAllNonNull(sessionDataList);
        this.sessionDataList.setElements(sessionDataList);
    }

    public ModuleClass getModuleClass() {
        return moduleClass;
    }

    /**
     * Returns an immutable list of session data.
     */
    public List<SessionData> getSessionDataList() {
        return sessionDataList.asUnmodifiableObservableList();
    }

    /**
     * Returns the {@code SessionData} within the module data for the given {@code Session}.
     */
    public Optional<SessionData> findSessionData(Session target) {
        requireNonNull(target);
        return sessionDataList.findElement(new SessionData(target, DUMMY_GRADE));
    }

    /**
     * Returns a new {@code StudentModuleData} by removing the given session from the list of session data.
     * If the session is not present, the original {@code StudentModuleData} is returned.
     */
    public StudentModuleData removeSession(Session session) {
        requireNonNull(session);
        List<SessionData> newSessionDataList = sessionDataList.asUnmodifiableObservableList().stream()
                .filter(sessionData -> !sessionData.getSession().isSame(session))
                .collect(Collectors.toList());
        return new StudentModuleData(moduleClass, newSessionDataList);
    }

    /**
     * Returns a new {@code StudentModuleData} by updating the grade in the given {@code session}.
     * If the session does not exist in the list of session data, a new session data is added.
     * Assumption: the session exists in the module class.
     */
    public StudentModuleData updateGrade(Session session, double grade) {
        requireAllNonNull(session);
        StudentModuleData newStudentModuleData = removeSession(session);
        newStudentModuleData.sessionDataList.add(new SessionData(session, grade));
        return newStudentModuleData;
    }

    @Override
    public boolean isSame(StudentModuleData other) {
        return this == other
                || (other != null && this.moduleClass.isSame(other.moduleClass));
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleClass, sessionDataList);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StudentModuleData
                && this.moduleClass.equals(((StudentModuleData) other).moduleClass))
                && this.sessionDataList.equals(((StudentModuleData) other).sessionDataList);
    }

    @Override
    public int compareTo(StudentModuleData other) {
        return moduleClass.compareTo(other.moduleClass);
    }

}
