package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SlotTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Slot(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidSlot = "";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot1_throwsIllegalArgumentException() {
        String invalidSlot = "2022/01-12";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot2_throwsIllegalArgumentException() {
        String invalidSlot = "2022/01-12 12:34";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot3_throwsIllegalArgumentException() {
        String invalidSlot = "2022/01/12 12:61";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot4_throwsIllegalArgumentException() {
        String invalidSlot = "22/01/12 12:01";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot5_throwsIllegalArgumentException() {
        String invalidSlot = "1922/01/12 1201";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot6_throwsIllegalArgumentException() {
        String invalidSlot = "2022-02-29 10:00";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void constructor_invalidSlot7_throwsIllegalArgumentException() {
        String invalidSlot = "2022-02-28 24:00";
        assertThrows(IllegalArgumentException.class, () -> new Slot(invalidSlot));
    }

    @Test
    public void equals() {
        Slot slot1 = new Slot("2022-11-13 00:00");
        Slot slot2 = new Slot("2022-11-13 00:00");
        Slot slot3 = new Slot("2022-11-13 00:01");
        Slot slot4 = new Slot("2022-11-13 00:00");
        assertTrue(slot1.equals(slot1));
        assertTrue(slot1.equals(slot2));
        assertTrue(slot1.equals(slot4));
        assertFalse(slot2.equals(slot3));
        assertFalse(slot2.equals("12345"));
        assertFalse(slot2.equals(null));
    }

    @Test
    public void isValidDateTime() {
        assertThrows(NullPointerException.class, () -> Slot.isValidSlot(null));
        assertFalse(Slot.isValidSlot(""));
        assertTrue(Slot.isValidSlot("2012-12-31 01:02"));
        assertTrue(Slot.isValidSlot("2012-12-31 23:59"));
        assertTrue(Slot.isValidSlot("2023-06-30 23:59"));
        assertFalse(Slot.isValidSlot("2021-12-40 11:22"));
        assertFalse(Slot.isValidSlot("2021-13-01 11:22"));
        assertFalse(Slot.isValidSlot("9999-12-31 1:22"));
        assertFalse(Slot.isValidSlot("2021-12-10 01:0"));
        assertFalse(Slot.isValidSlot("2021-1-10 01:00"));
        assertFalse(Slot.isValidSlot("0000-12-10 00:00"));
    }

    @Test
    public void hashCodeTest() {
        Slot slot1 = new Slot("2022-11-13 00:00");
        Slot slot2 = new Slot("2022-11-13 00:00");
        Slot slot3 = new Slot("2022-11-13 00:01");
        Slot slot4 = new Slot("2022-11-12 23:59");
        assertTrue(slot1.hashCode() == slot1.hashCode());
        assertFalse(slot1.hashCode() == slot3.hashCode());
        assertFalse(slot1.hashCode() == slot4.hashCode());
    }
}
