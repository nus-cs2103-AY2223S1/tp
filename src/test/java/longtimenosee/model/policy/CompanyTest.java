package longtimenosee.model.policy;

import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Company(null));
    }


    @Test
    public void isValidCompany() {
        // EP: null
        assertThrows(NullPointerException.class, () -> Company.isValidCompany(null));

        //invalid companies
        assertFalse(Company.isValidCompany("")); //Empty string
        assertFalse(Company.isValidCompany("  ")); //Spaces
        assertFalse(Company.isValidCompany("0")); //Integer values
        assertFalse(Company.isValidCompany("0.00")); //Decimal values
        assertFalse(Company.isValidCompany("AKE")); //Uppercase invalid companies

        //Valid companies
        assertTrue(Company.isValidCompany("MNF")); //Manulife
        assertTrue(Company.isValidCompany("PRU"));
        assertTrue(Company.isValidCompany("AXA"));
        assertTrue(Company.isValidCompany("GEL"));
        assertTrue(Company.isValidCompany("NTU"));
        assertTrue(Company.isValidCompany("ETQ"));
        assertTrue(Company.isValidCompany("TML"));
        assertTrue(Company.isValidCompany("AIA"));
        assertTrue(Company.isValidCompany("AVI"));
        assertTrue(Company.isValidCompany("FWD"));

    }
}

