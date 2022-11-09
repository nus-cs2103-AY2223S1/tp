package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class HeightTest {
    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Height(null));
    }

    @Test
    public void constructor_invalidThrowsIllegalArgumentException() {
        String invalidHeight = " ";
        assertThrows(IllegalArgumentException.class, () -> new Height(invalidHeight));

        // EP: [min_int...99]
        assertThrows(IllegalArgumentException.class, () -> new Height("-100"));
        assertThrows(IllegalArgumentException.class, () -> new Height("99"));

        // EP: [220...max_int]
        assertThrows(IllegalArgumentException.class, () -> new Height("220"));
        assertThrows(IllegalArgumentException.class, () -> new Height("500"));
    }

    @Test
    public void isValidHeight() {
        assertTrue(Height.isValidHeight("170"));
        assertTrue(Height.isValidHeight("120"));
        assertTrue(Height.isValidHeight("190"));
        assertFalse(Height.isValidHeight("-123"));
        assertFalse(Height.isValidHeight("123456"));
        assertFalse(Height.isValidHeight("abcdefg"));
        assertFalse(Height.isValidHeight("1.0"));
        assertFalse(Height.isValidHeight("0"));
    }

    @Test
    public void equals() {
        Height h1 = new Height("123");
        Height h2 = new Height("100");

        assertTrue(h1.equals(h1));
        assertTrue(h2.equals(h2));
        assertTrue(h1.equals(new Height("123")));
        assertFalse(h1.equals(h2));
        assertFalse(h1.equals(null));
        assertFalse(h1.equals("123"));
    }
}
