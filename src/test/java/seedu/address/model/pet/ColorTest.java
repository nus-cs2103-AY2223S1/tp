package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ColorTest {

    @Test
    public void constructor_null_emptyString() {
        Color nullColor = new Color(null);
        Color emptyString = new Color("");
        assertEquals(nullColor, emptyString);
    }

    @Test
    public void equals_different_fails() {
        Color red = new Color("Red");
        Color blue = new Color("Blue");
        assertFalse(red.equals(blue));
    }

    @Test
    public void equals_same_success() {
        Color red1 = new Color("Red");
        Color red2 = new Color("Red");
        assertTrue(red1.equals(red2));
    }

    @Test
    public void hashcode_sameColor() {
        Color red1 = new Color("Red");
        Color red2 = new Color("Red");
        assertEquals(red1.hashCode(), red2.hashCode());
    }

    @Test
    public void hashcode_differentColor() {
        Color red = new Color("Red");
        Color blue = new Color("Blue");
        assertNotEquals(red.hashCode(), blue.hashCode());
    }

    @Test
    public void getColor() {
        String expected = "Red";
        Color red = new Color(expected);
        assertEquals(red.getColor(), expected);
    }

}
