package nus.climods.logic.commands;

import java.util.Optional;
import java.util.function.Predicate;

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
    public boolean hasUserModule(UserModule module) {
        return hasModule;
    }

    @Override
    public boolean filteredListhasUserModule(UserModule module) {
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
    public void updateFilteredModuleList(Optional<String> facultyCode, Optional<Boolean> hasUser) {

    }

    @Override
    public ObservableList<Module> getFilteredModuleList() {
        return null;
    }

    @Override
    public void setFilteredModuleList(Predicate<Module> predicate) {

    }
}
