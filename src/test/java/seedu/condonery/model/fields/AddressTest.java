package seedu.condonery.model.fields;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.condonery.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.condonery.model.person.Address;

public class AddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new seedu.condonery.model.person.Address(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new seedu.condonery.model.person.Address(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> seedu.condonery.model.person.Address.isValidAddress(null));

        // invalid addresses
        assertFalse(seedu.condonery.model.person.Address.isValidAddress("")); // empty string
        assertFalse(seedu.condonery.model.person.Address.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(seedu.condonery.model.person.Address.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(seedu.condonery.model.person.Address.isValidAddress("-")); // one character
        assertTrue(Address.isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
