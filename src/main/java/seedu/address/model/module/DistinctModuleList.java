package seedu.address.model.module;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.module.exceptions.DuplicateModuleException;

import java.util.Iterator;
import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class DistinctModuleList implements Iterable<Module> {

    private final ObservableList<Module> moduleList = FXCollections.observableArrayList();
    private final ObservableList<Module> unmodifiableModuleList = FXCollections
            .unmodifiableObservableList(moduleList);

    public boolean containsModule(Module module) {
        requireNonNull(module);
        return moduleList.stream().anyMatch(module::isSameModuleCode);
    }

    public void addModule(Module moduleAdded) {
        requireNonNull(moduleAdded);
        if (containsModule(moduleAdded)) {
            throw new DuplicateModuleException();
        }
        moduleList.add(moduleAdded);
    }

    public void setModules(List<Module> modules) {
        requireAllNonNull(modules);
        moduleList.setAll(modules);
    }

    @Override
    public Iterator<Module> iterator() {
        return moduleList.iterator();
    }

    @Override
    public int hashCode() {
        return moduleList.hashCode();
    }

    @Override
    public boolean equals(Object otherMod) {
        return otherMod == this
                || (otherMod instanceof DistinctModuleList
                && moduleList.equals(((DistinctModuleList) otherMod).moduleList));
    }

    public ObservableList<Module> getUnmodifiableModuleList() {
        return unmodifiableModuleList;
    }
}
