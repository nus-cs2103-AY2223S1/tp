package nus.climods.logic.commands;

import javafx.collections.ObservableList;
import nus.climods.commons.core.GuiSettings;
import nus.climods.model.Model;
import nus.climods.model.ReadOnlyUserPrefs;
import nus.climods.model.module.ReadOnlyModuleList;
import nus.climods.model.module.UserModule;

import java.util.function.Predicate;

/**
 * Model that returns true or false for hasModule
 */
class ModelStub implements Model {
    private boolean hasModule = false;

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
        System.out.println("invoked");
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
}
