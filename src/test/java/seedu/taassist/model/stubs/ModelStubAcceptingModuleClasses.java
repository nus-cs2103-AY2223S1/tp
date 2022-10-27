package seedu.taassist.model.stubs;

import static java.util.Objects.requireNonNull;
import static seedu.taassist.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.taassist.model.ReadOnlyTaAssist;
import seedu.taassist.model.TaAssist;
import seedu.taassist.model.moduleclass.ModuleClass;

/**
 * A Model stub that always accepts the module class being added.
 */
public class ModelStubAcceptingModuleClasses extends ModelStub {
    private final ObservableList<ModuleClass> moduleClassesAdded = FXCollections.observableArrayList();

    @Override
    public boolean hasModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        return false;
    }

    @Override
    public ObservableList<ModuleClass> getModuleClassList() {
        return moduleClassesAdded;
    }

    @Override
    public void addModuleClass(ModuleClass moduleClass) {
        requireNonNull(moduleClass);
        moduleClassesAdded.add(moduleClass);
    }

    @Override
    public void addModuleClasses(Set<ModuleClass> moduleClasses) {
        requireAllNonNull(moduleClasses);
        moduleClassesAdded.addAll(moduleClasses);
    }

    @Override
    public ReadOnlyTaAssist getTaAssist() {
        return new TaAssist();
    }
}
