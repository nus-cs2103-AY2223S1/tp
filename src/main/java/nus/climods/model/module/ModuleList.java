package nus.climods.model.module;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.stream.Collectors;

import org.openapitools.client.ApiException;
import org.openapitools.client.api.ModulesApi;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A list of modules.
 */
public class ModuleList implements ReadOnlyModuleList {

    private static final ModulesApi modulesApi = new ModulesApi();

    private final ObservableList<Module> internalList = FXCollections.observableArrayList();
    private final ObservableList<Module> internalUnmodifiableList =
        FXCollections.unmodifiableObservableList(internalList);

    /**
     * Constructor for ModuleList class.
     *
     * @param academicYear academic year of modules
     */
    public ModuleList(String academicYear) {
        try {
            internalList.setAll(
                modulesApi.acadYearModuleInfoJsonGet(academicYear).stream().map(Module::new)
                    .collect(Collectors.toList()));
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

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
    public String toString() {
        return String.format("ModulesList<%s>", internalList.size());
    }
}
