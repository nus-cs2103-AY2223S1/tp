package seedu.address.model.pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ColorPatternTest {

    @Test
    public void constructor_null() {
        ColorPattern nullColorPattern = new ColorPattern(null);
        assertEquals(nullColorPattern.getColorPattern(), "");
    }

    @Test
    public void getColorPattern() {
        ColorPattern blueAndWhite = new ColorPattern("blue and white");
        ColorPattern brownAndWhite = new ColorPattern("brown and white");
        assertEquals(blueAndWhite.getColorPattern(), "blue and white");
        assertEquals(brownAndWhite.getColorPattern(), "brown and white");
    }

    @Test
    public void equals_sameObject() {
        ColorPattern blueAndWhite = new ColorPattern("blue and white");
        assertEquals(blueAndWhite, blueAndWhite);
    }

    @Test
    public void equals_samePattern() {
        ColorPattern blueAndWhite1 = new ColorPattern("blue and white");
        ColorPattern blueAndWhite2 = new ColorPattern("blue and white");
        assertEquals(blueAndWhite1, blueAndWhite2);
    }

    @Test
    public void equals_diffPattern() {
        ColorPattern blueAndWhite = new ColorPattern("blue and white");
        ColorPattern brownAndWhite = new ColorPattern("brown and white");
        assertNotEquals(blueAndWhite, brownAndWhite);
    }

    @Test
    public void hashcode_test() {
        ColorPattern blueAndWhite = new ColorPattern("blue and white");
        ColorPattern brownAndWhite = new ColorPattern("brown and white");
        assertEquals(blueAndWhite.hashCode(), "blue and white".hashCode());
        assertNotEquals(blueAndWhite.hashCode(), brownAndWhite.hashCode());
    }
}
