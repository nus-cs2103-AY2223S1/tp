package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClientEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> ClientEmail.isValidEmail(null));

        // blank email
        assertFalse(ClientEmail.isValidEmail("")); // empty string
        assertFalse(ClientEmail.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(ClientEmail.isValidEmail("@example.com")); // missing local part
        assertFalse(ClientEmail.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(ClientEmail.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(ClientEmail.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(ClientEmail.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(ClientEmail.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(ClientEmail.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(ClientEmail.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(ClientEmail.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(ClientEmail.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(ClientEmail.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(ClientEmail.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(ClientEmail.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(ClientEmail.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(ClientEmail.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(ClientEmail.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(ClientEmail.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(ClientEmail.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(ClientEmail.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(ClientEmail.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(ClientEmail.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(ClientEmail.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(ClientEmail.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(ClientEmail.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(ClientEmail.isValidEmail("a@bc")); // minimal
        assertTrue(ClientEmail.isValidEmail("test@localhost")); // alphabets only
        assertTrue(ClientEmail.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(ClientEmail.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(ClientEmail.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(ClientEmail.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(ClientEmail.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void toString_email_returnsValueInEmail() {
        String value = "test@test.com";
        ClientEmail email = new ClientEmail(value);
        assertEquals(email.toString(), value);
    }
}
