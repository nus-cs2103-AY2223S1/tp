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
    public void isValidStuEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> StuEmail.isValidStuEmail(null));

        // blank email
        assertFalse(StuEmail.isValidStuEmail("")); // empty string
        assertFalse(StuEmail.isValidStuEmail(" ")); // spaces only

        // missing parts
        assertFalse(StuEmail.isValidStuEmail("@example.com")); // missing local part
        assertFalse(StuEmail.isValidStuEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(StuEmail.isValidStuEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(StuEmail.isValidStuEmail("peterjack@-")); // invalid domain name
        assertFalse(StuEmail.isValidStuEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(StuEmail.isValidStuEmail("peter jack@example.com")); // spaces in local part
        assertFalse(StuEmail.isValidStuEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(StuEmail.isValidStuEmail(" peterjack@example.com")); // leading space
        assertFalse(StuEmail.isValidStuEmail("peterjack@example.com ")); // trailing space
        assertFalse(StuEmail.isValidStuEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(StuEmail.isValidStuEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(StuEmail.isValidStuEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(StuEmail.isValidStuEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(StuEmail.isValidStuEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(StuEmail.isValidStuEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(StuEmail.isValidStuEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(StuEmail.isValidStuEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(StuEmail.isValidStuEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(StuEmail.isValidStuEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(StuEmail.isValidStuEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(StuEmail.isValidStuEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(StuEmail.isValidStuEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(StuEmail.isValidStuEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(StuEmail.isValidStuEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(StuEmail.isValidStuEmail("a@bc")); // minimal
        assertTrue(StuEmail.isValidStuEmail("test@localhost")); // alphabets only
        assertTrue(StuEmail.isValidStuEmail("123@145")); // numeric local part and domain name
        assertTrue(StuEmail.isValidStuEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(StuEmail.isValidStuEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(StuEmail.isValidStuEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(StuEmail.isValidStuEmail("e1234567@u.nus.edu")); // more than one period in domain
    }
}
