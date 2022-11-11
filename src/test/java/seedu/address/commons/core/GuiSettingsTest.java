package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.GuiSettings.DARK_THEME_STRING;
import static seedu.address.commons.core.GuiSettings.LIGHT_THEME_STRING;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {
    @Test
    public void constructor() {
        GuiSettings guiSettings = new GuiSettings();
    }

    @Test
    public void constructorWithValues() {
        GuiSettings guiSettings = new GuiSettings(
                100, 100, 0, 0, LIGHT_THEME_STRING);
    }

    @Test
    public void getWindowWidth_windowWidth_equals() {
        double windowWidth = 100;
        GuiSettings guiSettings = new GuiSettings(
                windowWidth, 100, 0, 0, LIGHT_THEME_STRING);
        assertEquals(windowWidth, guiSettings.getWindowWidth());
    }

    @Test
    public void getWindowHeight_windowHeight_equals() {
        double windowHeight = 100;
        GuiSettings guiSettings = new GuiSettings(
                100, windowHeight, 0, 0, LIGHT_THEME_STRING);
        assertEquals(windowHeight, guiSettings.getWindowWidth());
    }

    @Test
    public void getWindowCoordinates_windowCoordinates_equals() {
        int windowCoordinateX = 0;
        int windowCoordinateY = 0;
        GuiSettings guiSettings = new GuiSettings(100, 100,
                windowCoordinateX, windowCoordinateY, LIGHT_THEME_STRING);
        assertEquals(new Point(windowCoordinateX, windowCoordinateY), guiSettings.getWindowCoordinates());
    }

    @Test
    public void getWindowCoordinates_null_equals() {
        GuiSettings guiSettings = new GuiSettings();
        assertEquals(null, guiSettings.getWindowCoordinates());
    }

    @Test
    public void getTheme_lightTheme_equals() {
        GuiSettings guiSettings = new GuiSettings(
                100, 100, 0, 0, LIGHT_THEME_STRING);
        assertEquals(LIGHT_THEME_STRING, guiSettings.getTheme());
    }

    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings(
                100, 100, 0, 0, LIGHT_THEME_STRING);
        assertTrue(guiSettings.equals(guiSettings));

        assertTrue(guiSettings.equals(
                new GuiSettings(100, 100, 0, 0, LIGHT_THEME_STRING)));

        assertFalse(guiSettings.equals(
                new GuiSettings(50, 100, 0, 0, LIGHT_THEME_STRING)));
        assertFalse(guiSettings.equals(
                new GuiSettings(100, 50, 0, 0, LIGHT_THEME_STRING)));
        assertFalse(guiSettings.equals(
                new GuiSettings(100, 100, 1, 0, LIGHT_THEME_STRING)));
        assertFalse(guiSettings.equals(
                new GuiSettings(100, 100, 0, 0, DARK_THEME_STRING)));
    }
}
