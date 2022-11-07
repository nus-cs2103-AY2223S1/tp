package nus.climods.model.module;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of modules.
 */
public class ModuleList implements ReadOnlyModuleList {

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Constructor for ModuleList class.
     *
     * @param modules list of modules
     */
    public ModuleList(List<Module> modules) {
        requireNonNull(modules);
        internalList.setAll(modules);
    }

    public ModuleList(ReadOnlyModuleList toBeCopied) {
        this.internalList.setAll(toBeCopied.getModules());
    }

    @Override
    public ObservableList<Module> getModules() {
        return internalUnmodifiableList;
    }

    @Override
    public Optional<Module> getListModule(String moduleCode) {
        return internalUnmodifiableList.stream()
            .filter(mod -> mod.getCode().equalsIgnoreCase(moduleCode))
            .findFirst();
    }

    @Override
    public boolean isEmpty() {
        return internalUnmodifiableList.isEmpty();
    }

    @Override
    public boolean hasModule(String moduleCode) {
        return internalUnmodifiableList.stream().anyMatch(mod -> mod.getCode().equalsIgnoreCase(moduleCode));
    }

    @Override
    public Optional<Module> getModule(String moduleCode) {
        return internalUnmodifiableList.stream()
            .filter(mod -> mod.getCode().equalsIgnoreCase(moduleCode))
            .findFirst();
    }

    @Override
    public String toString() {
        return String.format("ModulesList<%s>", internalList.size());
    }
}
