package seedu.taassist.model.stubs;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.model.moduleclass.ModuleClass;
import seedu.taassist.model.student.Student;
import seedu.taassist.model.uniquelist.UniqueList;

/**
 * A ModelStub that will always have no module class.
 */
public class ModelStubWithNoModuleClass extends ModelStub {

    @Override
    public void removeModuleClasses(Collection<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
    }

    @Override
    public void removeModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
    }

    @Override
    public boolean hasModuleClasses(Collection<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        return false;
    }

    @Override
    public boolean hasModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        return false;
    }

    @Override
    public ObservableList<ModuleClass> getModuleClassList() {
        return new UniqueList<ModuleClass>().asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return new UniqueList<Student>().asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return FXCollections.emptyObservableList();
    }

    @Override
    public boolean isInFocusMode() {
        return false;
    }
}
