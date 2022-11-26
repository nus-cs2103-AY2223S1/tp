package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PriorityTagBuilder;

public class PriorityTagTest {

    private final PriorityTag highPriorityTag = new PriorityTagBuilder().withStatus("HIGH").build();
    private final PriorityTag lowPriorityTag = new PriorityTagBuilder().withStatus("LOW").build();
    private final PriorityTag mediumPriorityTag = new PriorityTagBuilder().withStatus("MEDIUM").build();

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PriorityTag(null));
    }

    @Test
    public void constructor_invalidStatus_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PriorityTag("PRIORITY"));
    }

    @Test
    public void testIsValidTag() {

        //Valid input(LOW)
        assertTrue(PriorityTag.isValidTag("LOW"));

        //Valid input(HIGH)
        assertTrue(PriorityTag.isValidTag("HIGH"));

        //Valid input(MEDIUM)
        assertTrue(PriorityTag.isValidTag("MEDIUM"));

        //Another valid input(lOw) - checking if support case-insensitive status
        assertTrue(PriorityTag.isValidTag("lOw"));

        //Null input
        assertFalse(PriorityTag.isValidTag(null));

        //Different word given for status
        assertFalse(PriorityTag.isValidTag("Hello"));

    }

    @Test
    public void testCompareTo() {
        PriorityTag highPriorityTagCopy = new PriorityTagBuilder(highPriorityTag).build();
        PriorityTag mediumPriorityTagCopy = new PriorityTagBuilder(mediumPriorityTag).build();
        PriorityTag lowPriorityTagCopy = new PriorityTagBuilder(lowPriorityTag).build();

        //Comparing high priority tag with another high priority tag
        assertEquals(0, highPriorityTag.compareTo(highPriorityTagCopy));

        //Comparing high priority tag with low priority tag
        assertEquals(-1, highPriorityTag.compareTo(lowPriorityTag));

        //Comparing high priority tag with medium priority tag
        assertEquals(-1, highPriorityTag.compareTo(mediumPriorityTag));

        //Comparing medium priority tag with high priority tag
        assertEquals(1, mediumPriorityTag.compareTo(highPriorityTag));

        //Comparing medium priority tag with another medium priority tag
        assertEquals(0, mediumPriorityTag.compareTo(mediumPriorityTagCopy));

        //Comparing medium priority tag with low priority tag
        assertEquals(-1, mediumPriorityTag.compareTo(lowPriorityTag));

        //Comparing low priority tag with high priority tag
        assertEquals(1, lowPriorityTag.compareTo(highPriorityTag));

        //Comparing low priority tag with medium priority tag
        assertEquals(1, lowPriorityTag.compareTo(mediumPriorityTag));

        //Comparing low priority tag with another low priority tag
        assertEquals(0, lowPriorityTag.compareTo(lowPriorityTagCopy));
    }

    @Test
    public void testEquals() {
        PriorityTag highPriorityTagCopy = new PriorityTagBuilder(highPriorityTag).build();
        PriorityTag highPriorityTagDiffSpelling = new PriorityTagBuilder(highPriorityTag)
                .withStatus("hIgH").build();

        //Checks if equal with itself
        assertTrue(highPriorityTag.equals(highPriorityTag));

        //Checks if equal with another priority tag with same status and spelling
        assertTrue(highPriorityTag.equals(highPriorityTagCopy));

        //Checks if equal with another priority tag with same status but different spelling
        assertTrue(highPriorityTag.equals(highPriorityTagDiffSpelling));

        //Checks if equal with null
        assertFalse(highPriorityTag.equals(null));

        //Checks if equal with different status
        assertFalse(highPriorityTag.equals(lowPriorityTag));
        assertFalse(highPriorityTag.equals(mediumPriorityTag));

        //Checks if equal with different object type
        assertFalse(highPriorityTag.equals(79));
    }

    @Test
    public void testToString() {
        assertEquals("HIGH", highPriorityTag.toString());
    }
}
