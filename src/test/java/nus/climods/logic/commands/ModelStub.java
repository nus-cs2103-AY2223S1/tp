package nus.climods.logic.commands;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Predicate;

import org.openapitools.client.ApiException;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.model.Model;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.module.Module;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UserModule;


/**
 * Model that returns true or false for hasModule
 */
class ModelStub implements Model {
    private boolean hasModule;

    public ModelStub(boolean hasModule) {
        this.hasModule = hasModule;
    }


    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return null;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

    }

    @Override
    public GuiSettings getGuiSettings() {
        return null;
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {

    }

    @Override
    public ReadOnlyModuleList getModuleList() {
        return null;
    }

    @Override
    public Optional<Module> getListModule(String moduleCode) {
        return Optional.empty();
    }

    @Override
    public boolean hasUserModule(UserModule module) {
        return hasModule;
    }

    @Override
    public boolean filteredListHasUserModule(UserModule module) {
        return hasModule;
    }

    @Override
    public void deleteUserModule(UserModule target) {
    }

    @Override
    public void addUserModule(UserModule module) {

    }

    @Override
    public ObservableList<UserModule> getFilteredUserModuleList() {
        return null;
    }

    @Override
    public void updateFilteredUserModuleList(Predicate<UserModule> predicate) {

    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return null;
    }

    @Override
    public void setFilteredModuleList(Predicate<Module> predicate) {

    }

    @Override
    public void setFilteredModuleList(Predicate<Module> predicate, Comparator<Module> comparator) {

    }

    @Override
    public void setModuleInFocus(Module module) throws ApiException {

    }

    @Override
    public void clearModuleInFocus() {

    }
}
