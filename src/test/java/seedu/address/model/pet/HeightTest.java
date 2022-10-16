package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class HeightTest {

    @Test
    public void getValue() {
        Height height = new Height(5.5);
        assertEquals(height.getValue(), 5.5);
    }

    @Test
    public void equals_sameObject() {
        Height height = new Height(5.5);
        assertEquals(height, height);
    }

    @Test
    public void equals_differentObjectSameValue() {
        Height height1 = new Height(5.5);
        Height height2 = new Height(5.5);
        assertEquals(height1, height2);
    }

    @Test
    public void equals_differentValues() {
        Height height1 = new Height(5.5);
        Height height2 = new Height(6.5);
        assertNotEquals(height1, height2);
    }

    @Test
    public void hashCodeTest() {
        Height height1 = new Height(5.5);
        Height height2 = new Height(6.5);
        assertEquals(height1.hashCode(), Double.hashCode(5.5));
        assertNotEquals(height2.hashCode(), height1.hashCode());
    }

    @Test
    public void toStringTest() {
        Height height = new Height(5.5);
        String expected = Double.valueOf(5.5) + " cm";
        assertEquals(height.toString(), expected);
    }
}
