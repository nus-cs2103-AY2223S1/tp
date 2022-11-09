package seedu.nutrigoals.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TipTest {
    @Test
    public void isValidTipTest() {
        assertTrue(Tip.isValidTip(TipList.generateTip().toString()));
        assertTrue(Tip.isValidTip(new Tip("Dummy Tip #1").toString()));
        assertTrue(Tip.isValidTip(("Dummy Tip #2")));
        assertFalse(Tip.isValidTip(""));
    }

    @Test
    public void toStringTest() {
        assertEquals(new Tip("Dummy Tip #3").toString(), "Dummy Tip #3");
        assertEquals(new Tip("Dummy Tip #4").toString(), new Tip("Dummy Tip #4").toString());
        assertNotEquals(new Tip("Dummy Tip #5").toString(), "Dummy Tip #NAN");
    }

    @Test
    public void equalsTest() {
        assertEquals(new Tip("Dummy Tip #6"), new Tip("Dummy Tip #6"));
        assertNotEquals(new Tip("Dummy Tip #8"), new Tip("Dummy Tip #9"));
        assertTrue(new Tip("Dummy Tip #10").equals(new Tip("Dummy Tip #10")));
        assertFalse(new Tip("Dummy Tip #11").equals(new Tip("Dummy Tip #12")));
    }
}
