package seedu.phu.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;




public class GuiSettingsTest {
    @Test
    public void constructor() {
        GuiSettings guiSettings = new GuiSettings(920, 650, 0, 0);
        assertEquals(guiSettings.getWindowWidth(), 920);
        assertEquals(guiSettings.getWindowHeight(), 650);
        assertEquals(guiSettings.getWindowCoordinates(), new Point(0, 0));
    }
    @Test
    public void equals() {
        GuiSettings guiSettings = new GuiSettings(920, 650, 0, 0);
        assertTrue(new GuiSettings().equals(new GuiSettings()));
        assertFalse(guiSettings.equals(new GuiSettings(920, 650, 10, 10)));
        assertFalse(guiSettings.equals(null));
    }
}
