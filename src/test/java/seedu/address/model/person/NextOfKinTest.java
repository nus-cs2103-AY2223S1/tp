package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NextOfKinTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NextOfKin(null));
    }

    @Test
    public void constructor_emptyString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(""));
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(" "));
    }

    @Test
    public void constructor_garbledNextOfKin_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin(" , , ")); //missing all fields
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("carle")); //missing two fields without comma
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("carle, , ")); //missing two fields with comma
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("carle,mom ")); //missing one field
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("carle, mom, ")); //mising one field with comma
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("@@12, mom, 123456 ")); //invalid name
        //invalid relationship
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("carle, @@12, 123456 "));
        assertThrows(IllegalArgumentException.class, () -> new NextOfKin("carle, mom, mynumber")); //invalid contact
    }

    @Test
    public void isValidNextOfKinTest() {
        // null NextOfKin
        assertThrows(NullPointerException.class, () -> NextOfKin.isValidNextOfKin(null));

        // invalid NextOfKines
        assertFalse(NextOfKin.isValidNextOfKin("")); // empty string
        assertFalse(NextOfKin.isValidNextOfKin(" ")); // spaces only
        assertFalse(NextOfKin.isValidNextOfKin("-")); // one character
        assertFalse(NextOfKin.isValidNextOfKin(" , , ")); // blank fields with comma
        assertFalse(NextOfKin.isValidNextOfKin("1234, mom, 12345")); // invalid name
        assertFalse(NextOfKin.isValidNextOfKin("carls, 1234, 12345")); // invalid relationship
        assertFalse(NextOfKin.isValidNextOfKin("carls, mom, mynumber")); // invalid contact

        // valid NextOfKines
        assertTrue(NextOfKin.isValidNextOfKin("a, a, 81283745")); // short NextOfKin
        assertTrue(NextOfKin.isValidNextOfKin("Sugon Bee, Father, 92873764"));
    }

    @Test
    public void toStringTest() {
        String expectedMessage1 = "Next of Kin: Charles, Brother, 90989098";
        String expectedMessage2 = "Next of Kin: Mary, Aunt, 12341234";
        NextOfKin nextOfKin1 = new NextOfKin("Charles, Brother, 90989098");
        NextOfKin nextOfKin2 = new NextOfKin("Mary, Aunt, 12341234");

        // same next of kin
        assertEquals(expectedMessage1, nextOfKin1.toString());
        assertEquals(expectedMessage2, nextOfKin2.toString());

        // different next of kin
        assertNotEquals(expectedMessage1, nextOfKin2.toString());
        assertNotEquals(expectedMessage2, nextOfKin1.toString());
    }

    @Test
    public void getNextOfKinNameTest() {
        String expectedMessage1 = "Charles";
        String expectedMessage2 = "Mary";
        NextOfKin nextOfKin1 = new NextOfKin("Charles, Brother, 90989098");
        NextOfKin nextOfKin2 = new NextOfKin("Mary , Aunt, 12341234");

        // same next of kin
        assertEquals(expectedMessage1, nextOfKin1.getNextOfKinName());
        assertEquals(expectedMessage2, nextOfKin2.getNextOfKinName());

        // different next of kin
        assertNotEquals(expectedMessage1, nextOfKin2.getNextOfKinName());
        assertNotEquals(expectedMessage2, nextOfKin1.getNextOfKinName());

        //case sensitive
        assertNotEquals("mary", nextOfKin2.getNextOfKinName());
    }

    @Test
    public void getNextOfKinRelationshipTest() {
        String expectedMessage1 = "Brother";
        String expectedMessage2 = "Aunt";
        NextOfKin nextOfKin1 = new NextOfKin("Charles, Brother , 90989098");
        NextOfKin nextOfKin2 = new NextOfKin("Mary ,Aunt, 12341234");

        // same next of kin
        assertEquals(expectedMessage1, nextOfKin1.getNextOfKinRelationship());
        assertEquals(expectedMessage2, nextOfKin2.getNextOfKinRelationship());

        // different next of kin
        assertNotEquals(expectedMessage1, nextOfKin2.getNextOfKinRelationship());
        assertNotEquals(expectedMessage2, nextOfKin1.getNextOfKinRelationship());

        //case sensitive
        assertNotEquals("aunt", nextOfKin2.getNextOfKinRelationship());
    }

    @Test
    public void getNextOfKinContactTest() {
        String expectedMessage1 = "90989098";
        String expectedMessage2 = "12341234";
        NextOfKin nextOfKin1 = new NextOfKin("Charles, Brother, 90989098");
        NextOfKin nextOfKin2 = new NextOfKin("Mary , Aunt, 12341234 ");

        // same next of kin
        assertEquals(expectedMessage1, nextOfKin1.getNextOfKinContact());
        assertEquals(expectedMessage2, nextOfKin2.getNextOfKinContact());

        // different next of kin
        assertNotEquals(expectedMessage1, nextOfKin2.getNextOfKinContact());
        assertNotEquals(expectedMessage2, nextOfKin1.getNextOfKinContact());
    }

    @Test
    public void equalsTest() {
        NextOfKin nextOfKin = new NextOfKin("Charles, Brother, 90989098");

        // same object -> returns true
        assertTrue(nextOfKin.equals(nextOfKin));

        // same values -> returns true
        NextOfKin nextOfKinCopy = new NextOfKin("Charles, Brother, 90989098");
        assertTrue(nextOfKin.equals(nextOfKinCopy));

        // different types -> returns false
        assertFalse(nextOfKin.equals(1));

        // null -> returns false
        assertFalse(nextOfKin.equals(null));

        // different next of kin -> returns false
        NextOfKin differentNextOfKin = new NextOfKin("Mary , Aunt, 12341234 ");
        assertFalse(nextOfKin.equals(differentNextOfKin));
    }

    @Test
    public void hashCodeTest() {
        NextOfKin nextOfKin1 = new NextOfKin("Charles, Brother, 90989098");
        NextOfKin nextOfKin2 = new NextOfKin("Charles, Brother, 90989098");
        NextOfKin nextOfKin3 = new NextOfKin("Mary , Aunt, 12341234");

        // same object -> same hashcode
        assertEquals(nextOfKin1.hashCode(), nextOfKin1.hashCode());

        // same values -> same hashcode
        assertEquals(nextOfKin1.hashCode(), nextOfKin2.hashCode());

        // different values -> different hashcode
        assertNotEquals(nextOfKin1.hashCode(), nextOfKin3.hashCode());
    }
}
