package foodwhere.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    @Test
    public void constructor_testDefaults_success() {
        GuiSettings defaultGui = new GuiSettings();
        assertEquals(defaultGui.getWindowWidth(), GuiSettings.DEFAULT_WIDTH);
        assertEquals(defaultGui.getWindowHeight(), GuiSettings.DEFAULT_HEIGHT);
        assertEquals(defaultGui.getWindowCoordinates(), null);
        assertTrue(defaultGui.equals(defaultGui));
        assertFalse(defaultGui.equals(null));
    }

    @Test
    public void constructor_testCustom_success() {
        double width = 200;
        double height = 100;
        int x = 300;
        int y = 400;
        GuiSettings customGui = new GuiSettings(width, height, x, y);
        GuiSettings customGuiCopy = new GuiSettings(width, height, x, y);
        Point guiCoords = new Point(x, y);
        assertEquals(customGui.getWindowCoordinates(), guiCoords);
        assertTrue(customGui.equals(customGui));
        assertTrue(customGui.equals(customGuiCopy));
        assertEquals(customGui.hashCode(), customGuiCopy.hashCode());
        assertFalse(customGui.equals(new GuiSettings()));
        assertFalse(customGui.equals(null));
        assertEquals(customGui.toString(), "Width : 200.0\nHeight : 100.0\nPosition : " + guiCoords);
    }
}
