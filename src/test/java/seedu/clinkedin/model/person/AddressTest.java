package seedu.clinkedin.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;

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
    public void address_toString() {
        String addressString = "singapore";
        Address address1 = new Address("singapore");
        Address address2 = new Address("singapore");
        assertTrue(address1.toString().equals(address2.toString()));
        assertTrue(address1.toString().equals(addressString));
    }

    @Test
    public void equalityTests() {
        Address address1 = new Address("singapore");
        Address address2 = new Address("singapore");
        assertTrue(address1.equals(address2));
        assertTrue(address1.equals(address1));
        assertFalse(address1.equals(null));
        assertFalse(address1.equals(5));
    }

    @Test
    public void hashcodeTests() {
        Address address1 = new Address("singapore");
        Address address2 = new Address("singapore");
        int hashcode1 = address1.hashCode();
        int hashcode2 = address2.hashCode();
        assertTrue(hashcode1 == hashcode1);
        assertTrue(hashcode1 == hashcode2);
    }
}
