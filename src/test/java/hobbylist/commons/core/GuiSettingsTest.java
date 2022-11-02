package hobbylist.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.awt.Point;

import org.junit.jupiter.api.Test;

public class GuiSettingsTest {

    private static final int WINDOW_WIDTH = 10;
    private static final int WINDOW_HEIGHT = 12;
    private static final int X_POSITION = 6;
    private static final int Y_POSITION = 7;

    private static final GuiSettings TEST = new GuiSettings();
    private static final GuiSettings OTHER = new GuiSettings(WINDOW_WIDTH, WINDOW_HEIGHT, X_POSITION, Y_POSITION);

    @Test
    public void equals() {
        // null -> return false
        assertNotEquals(TEST, null);

        // different types -> returns false
        assertNotEquals(TEST, new Object());

        // not equal values -> return false
        assertNotEquals(TEST, OTHER);

        // equal values -> return true
        GuiSettings another = new GuiSettings(10, 12, 6, 7);
        assertEquals(OTHER, another);

        // same object -> return true
        assertEquals(TEST, TEST);
    }

    @Test
    public void hashcode() {
        // same values -> return same hash code
        assertEquals(OTHER.hashCode(),
                new GuiSettings(WINDOW_WIDTH, WINDOW_HEIGHT, X_POSITION, Y_POSITION).hashCode());

        // different windowWidth -> different hashcode
        assertNotEquals(OTHER.hashCode(),
                new GuiSettings(0, WINDOW_HEIGHT, X_POSITION, Y_POSITION).hashCode());

        // different windowHeight -> different hashcode
        assertNotEquals(OTHER.hashCode(),
                new GuiSettings(WINDOW_WIDTH, 0, X_POSITION, Y_POSITION).hashCode());

        // different xPosition -> different hashcode
        assertNotEquals(OTHER.hashCode(),
                new GuiSettings(WINDOW_WIDTH, WINDOW_HEIGHT, 0, Y_POSITION).hashCode());

        // different windowPosition -> different hashcode
        assertNotEquals(OTHER.hashCode(),
                new GuiSettings(WINDOW_WIDTH, WINDOW_HEIGHT, X_POSITION, 0).hashCode());
    }

    @Test
    public void guiSettingsGetWindowWidth_correctParameters_valueAsExpected() {
        assertEquals(OTHER.getWindowWidth(), WINDOW_WIDTH);
    }

    @Test
    public void guiSettingsGetWindowHeight_correctParameters_valueAsExpected() {
        assertEquals(OTHER.getWindowHeight(), WINDOW_HEIGHT);
    }

    @Test
    public void guiSettingsGetWindowCoordinates_correctParameters_valueAsExpected() {
        assertEquals(OTHER.getWindowCoordinates(), new Point(X_POSITION, Y_POSITION));
    }
}
