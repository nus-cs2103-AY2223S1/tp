package hobbylist.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ThemeSettingsTest {
    @Test
    public void themeSettingsConstructor_correctParameter_valueAsExpected() {
        ThemeSettings test = new ThemeSettings(ThemeSettings.Theme.LIGHT);
        assertEquals(test.getTheme(), ThemeSettings.Theme.LIGHT);
    }

    @Test
    public void equals() {
        ThemeSettings test = new ThemeSettings(ThemeSettings.Theme.TREE);
        ThemeSettings testSame = new ThemeSettings(ThemeSettings.Theme.TREE);
        ThemeSettings different = new ThemeSettings(ThemeSettings.Theme.DARK);

        // null -> return false
        assertNotEquals(test, null);

        // wrong type -> return false
        assertNotEquals(test, new Object());

        // wrong value -> return false
        assertNotEquals(test, different);

        // same value -> return true
        assertEquals(test, testSame);

        // same object -> return true
        assertEquals(test, test);
    }

    @Test
    public void hashcode() {
        ThemeSettings test = new ThemeSettings(ThemeSettings.Theme.TREE);
        ThemeSettings testSame = new ThemeSettings(ThemeSettings.Theme.TREE);
        ThemeSettings different = new ThemeSettings(ThemeSettings.Theme.DARK);

        // different value -> different hashcode
        assertNotEquals(test.hashCode(), different.hashCode());

        // same value -> same hashcode
        assertEquals(test.hashCode(), testSame.hashCode());

        // same object -> same hashcode
        assertEquals(test.hashCode(), test.hashCode());
    }

    @Test
    public void themeToString_correctOutput() {
        ThemeSettings test = new ThemeSettings(ThemeSettings.Theme.SKY);
        assertEquals(test.toString(), "Theme : SKY");
    }
}
