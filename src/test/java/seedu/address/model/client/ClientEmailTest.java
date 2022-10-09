package seedu.address.model.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ClientEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientEmail(null));
    }

    @Test
    public void constructor_invalidClientEmail_throwsIllegalArgumentException() {

        //null email
        String invalidEmptyEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidEmptyEmail));

        //only domain provided
        String invalidNoNameEmail = "@gmail.com";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidNoNameEmail));

        //only name provided
        String invalidNoDomainEmail = "john";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidNoDomainEmail));

        //illegal characters in name
        String invalidNameEmail = "harry++@gmail.com";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidNameEmail));

        //domain not separated by @
        String invalidUnseparatedDomainEmail = "harrygmail.com";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidUnseparatedDomainEmail));

        //name starting with illegal characters
        String invalidNamePrefixEmail = "_hermione@gmail.com";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidNamePrefixEmail));

        //incomplete domain argument
        String invalidIncompleteDomainEmail = "harry@gmail.";
        assertThrows(IllegalArgumentException.class, () -> new ClientEmail(invalidIncompleteDomainEmail));

    }

    @Test
    public void method_isValidEmail_invalidEmails() {

        // null email
        assertThrows(NullPointerException.class, () -> ClientEmail.isValidClientEmail(null));

        // blank email
        assertFalse(ClientEmail.isValidClientEmail("")); // empty string
        assertFalse(ClientEmail.isValidClientEmail(" ")); // spaces only

        // missing parts
        assertFalse(ClientEmail.isValidClientEmail("@abc.com")); // missing local part
        assertFalse(ClientEmail.isValidClientEmail("harry.com")); // missing '@' symbol
        assertFalse(ClientEmail.isValidClientEmail("hermione@")); // missing domain name
        assertFalse(ClientEmail.isValidClientEmail("ron")); //only local name
        assertFalse(ClientEmail.isValidClientEmail("harry@gmail.")); //only local name

        // invalid parts
        assertFalse(ClientEmail.isValidClientEmail("george@%")); // invalid domain name
        assertFalse(ClientEmail.isValidClientEmail("_hermione@gmail.com")); // invalid domain name
        assertFalse(ClientEmail.isValidClientEmail("e0888@some_domain.com")); // underscore in domain name
        assertFalse(ClientEmail.isValidClientEmail("harry++@gmail.com")); //invalid character leading local name
        assertFalse(ClientEmail.isValidClientEmail("harry potter@outlook.com")); // spaces in local part
        assertFalse(ClientEmail.isValidClientEmail("john@exam ple.com")); // spaces in domain name
        assertFalse(ClientEmail.isValidClientEmail(" mary@example.com")); // leading space
        assertFalse(ClientEmail.isValidClientEmail("sherlock@example.com ")); // trailing space
        assertFalse(ClientEmail.isValidClientEmail("$sherlock@xyz.com ")); // invalid character leading local name
        assertFalse(ClientEmail.isValidClientEmail("sherlock@exam#ple.com ")); // invalid character in domain name

    }

    @Test
    public void isValidEmail() {

        // valid email
        assertTrue(ClientEmail.isValidClientEmail("hermione_Grange@gmail.com")); // underscore in local part
        assertTrue(ClientEmail.isValidClientEmail("user.123@xyz.com")); // period in local part
        assertTrue(ClientEmail.isValidClientEmail("grr+martin@example.com")); // '+' symbol in local part
        assertTrue(ClientEmail.isValidClientEmail("harry-potter@example.com")); // hyphen in local part
        assertTrue(ClientEmail.isValidClientEmail("a@bc")); // minimal
        assertTrue(ClientEmail.isValidClientEmail("test@localhost")); // alphabets only
        assertTrue(ClientEmail.isValidClientEmail("abc@xyz")); // numeric local part and domain name
    }
}
