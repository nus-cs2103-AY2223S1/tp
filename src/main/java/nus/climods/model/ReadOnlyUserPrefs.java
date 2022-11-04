package nus.climods.model;

import java.nio.file.Path;

import nus.climods.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    String getAcademicYear();

    Path getUserModuleListFilePath();
}
