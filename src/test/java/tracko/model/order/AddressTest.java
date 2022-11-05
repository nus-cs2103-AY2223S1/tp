package tracko.model.order;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tracko.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AddressTest {
    @Test
    public void equals() {
        Address address1 = new Address("75 Clementi Street, Blk 990, #45-09");
        Address address2 = new Address("23 King Albert Park Street, 632093");

        // same object -> returns true
        assertTrue(address1.equals(address1));

        //same values -> return true
        Address address1Copy = new Address("75 Clementi Street, Blk 990, #45-09");
        assertTrue(address1.equals(address1Copy));

        // different types -> returns false
        assertFalse(address1.equals(1));

        // null -> returns false
        assertFalse(address1.equals(null));

        // different predicate -> returns false
        assertFalse(address1.equals(address2));
    }

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
}
