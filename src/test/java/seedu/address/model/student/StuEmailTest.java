package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StuEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new StuEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new StuEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> StuEmail.isValidEmail(null));

        // blank email
        assertFalse(StuEmail.isValidEmail("")); // empty string
        assertFalse(StuEmail.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(StuEmail.isValidEmail("@example.com")); // missing local part
        assertFalse(StuEmail.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(StuEmail.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(StuEmail.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(StuEmail.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(StuEmail.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(StuEmail.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(StuEmail.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(StuEmail.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(StuEmail.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(StuEmail.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(StuEmail.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(StuEmail.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(StuEmail.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(StuEmail.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(StuEmail.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(StuEmail.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(StuEmail.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(StuEmail.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(StuEmail.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(StuEmail.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(StuEmail.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(StuEmail.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(StuEmail.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(StuEmail.isValidEmail("a@bc")); // minimal
        assertTrue(StuEmail.isValidEmail("test@localhost")); // alphabets only
        assertTrue(StuEmail.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(StuEmail.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(StuEmail.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(StuEmail.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(StuEmail.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }
}
