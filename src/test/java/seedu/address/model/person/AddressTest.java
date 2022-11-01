package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> Address.isValidAddress(null));

        // invalid addresses
        assertFalse(Address.isValidAddress("")); // empty string
        assertFalse(Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        final Address standardAddress = new Address("23 Clementi Road, #51-32");

        // same values -> returns true
        Address addressWithSameName = new Address("23 Clementi Road, #51-32");
        assertTrue(standardAddress.equals(addressWithSameName));

        // same object -> returns true
        assertTrue(standardAddress.equals(standardAddress));

        // null -> returns false
        assertFalse(standardAddress.equals(null));

        // different types -> returns false
        assertFalse(standardAddress.equals(new Email("jimhalpert@gmail.com")));

        // different addresses -> returns false
        assertFalse(standardAddress.equals(new Address("88 West Coast Drive, #01-110")));
    }

    @Test
    public void hashcode() {
        final Address standardAddress = new Address("23 Clementi Road, #51-32");

        // same values -> returns same hashcode
        Address addressWithSameName = new Address("23 Clementi Road, #51-32");
        assertEquals(standardAddress.hashCode(), addressWithSameName.hashCode());

        // different addresses -> returns different hashcode
        assertNotEquals(standardAddress.hashCode(), (new Address("88 West Coast Drive, #01-110")).hashCode());
    }

}
