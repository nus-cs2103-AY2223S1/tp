package seedu.address.model.company;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class CompanyAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyAddress(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyAddress(invalidAddress));
    }

    @Test
    public void isValidCompanyAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> CompanyAddress.isValidCompanyAddress(null));

        // invalid address
        assertFalse(CompanyAddress.isValidCompanyAddress("")); // empty string
        assertFalse(CompanyAddress.isValidCompanyAddress(" ")); // empty string

        // valid address
        assertTrue(CompanyAddress.isValidCompanyAddress("bedok mall")); // alphabets only
        assertTrue(CompanyAddress.isValidCompanyAddress("12345")); // numbers only
        assertTrue(CompanyAddress.isValidCompanyAddress("21 toh drive")); // alphanumeric characters
        assertTrue(CompanyAddress.isValidCompanyAddress("Clem Mall")); // with capital letters
        assertTrue(CompanyAddress.isValidCompanyAddress("Block 111 Bedok Reservoir Road #01-1234,"
                + " S470111")); // long names
    }

    @Test
    public void toString_email_returnsValueInName() {
        String value = "Bedok Mall";
        CompanyAddress name = new CompanyAddress(value);
        assertEquals(name.toString(), value);
    }

}
