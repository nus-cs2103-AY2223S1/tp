package seedu.taassist.model.moduleclass;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Objects;

import seedu.taassist.model.session.SessionData;
import seedu.taassist.model.uniquelist.Identity;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * Represents a student's data for a module.
 */
public class StudentModuleData implements Identity<StudentModuleData> {

    private final ModuleClass moduleClass;
    private final UniqueList<SessionData> sessionDataList = new UniqueList<>();

    /**
     * Constructs a {@code StudentModuleData} with an empty session data list for the given module class.
     */
    public StudentModuleData(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        this.moduleClass = moduleClass;
    }

    /**
     * Constructs a {@code StudentModuleData} with the given module class and list of session data.
     */
    public StudentModuleData(ModuleClass moduleClass, List<SessionData> sessionDataList) {
        requireAllNonNull(moduleClass, sessionDataList);
        this.moduleClass = moduleClass;
        this.sessionDataList.setElements(sessionDataList);
    }

    public ModuleClass getModuleClass() {
        return moduleClass;
    }

    /**
     * Returns an immutable list of session data.
     */
    public List<SessionData> getSessionData() {
        return sessionDataList.asUnmodifiableObservableList();
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

}
