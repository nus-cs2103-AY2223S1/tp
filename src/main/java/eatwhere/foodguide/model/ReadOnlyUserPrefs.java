package eatwhere.foodguide.model;

import java.nio.file.Path;

import eatwhere.foodguide.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getFoodGuideFilePath();

}
