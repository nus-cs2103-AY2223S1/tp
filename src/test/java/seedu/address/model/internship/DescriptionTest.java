package seedu.address.model.internship;

// import static org.junit.jupiter.api.Assertions.assertFalse;
// import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidEmail));
    }
    */

    /*
    // Removed for now as there are no constraints on the inputs
    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // blank email
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // missing parts
        assertFalse(Description.isValidDescription("@example.com")); // missing local part
        assertFalse(Description.isValidDescription("peterjackexample.com")); // missing '@' symbol
        assertFalse(Description.isValidDescription("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Description.isValidDescription("peterjack@-")); // invalid domain name
        assertFalse(Description.isValidDescription("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Description.isValidDescription("peter jack@example.com")); // spaces in local part
        assertFalse(Description.isValidDescription("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Description.isValidDescription(" peterjack@example.com")); // leading space
        assertFalse(Description.isValidDescription("peterjack@example.com ")); // trailing space
        assertFalse(Description.isValidDescription("peterjack@@example.com")); // double '@' symbol
        assertFalse(Description.isValidDescription("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Description.isValidDescription("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Description.isValidDescription("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Description.isValidDescription("peter..jack@example.com")); // local part has consecutive periods
        assertFalse(Description.isValidDescription("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Description.isValidDescription("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Description.isValidDescription("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Description.isValidDescription("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Description.isValidDescription("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Description.isValidDescription("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Description.isValidDescription("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Description.isValidDescription("PeterJack.1190@example.com")); // period in local part
        assertTrue(Description.isValidDescription("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Description.isValidDescription("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Description.isValidDescription("a@bc")); // minimal
        assertTrue(Description.isValidDescription("test@localhost")); // alphabets only
        assertTrue(Description.isValidDescription("123@145")); // numeric local part and domain name
        assertTrue(Description.isValidDescription("a1+be.d@example1.com")); // mixture of alphanumeric and special
        assertTrue(Description.isValidDescription("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Description.isValidDescription("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Description.isValidDescription("e1234567@u.nus.edu")); // more than one period in domain
    }
    */
}
