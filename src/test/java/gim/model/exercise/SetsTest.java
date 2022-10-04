package gim.model.exercise;

import static gim.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class SetsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Sets(null));
    }

    @Test
    public void constructor_invalidSets_throwsIllegalArgumentException() {
        String invalidSets = "";
        assertThrows(IllegalArgumentException.class, () -> new Sets(invalidSets));
    }

    @Test
    public void isValidSets() {
        // null sets
        assertThrows(NullPointerException.class, () -> Sets.isValidSets(null));

        // blank sets
        assertFalse(Sets.isValidSets("")); // empty string
        assertFalse(Sets.isValidSets(" ")); // spaces only

        // missing parts
        assertFalse(Sets.isValidSets("@example.com")); // missing local part
        assertFalse(Sets.isValidSets("peterjackexample.com")); // missing '@' symbol
        assertFalse(Sets.isValidSets("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Sets.isValidSets("peterjack@-")); // invalid domain name
        assertFalse(Sets.isValidSets("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Sets.isValidSets("peter jack@example.com")); // spaces in local part
        assertFalse(Sets.isValidSets("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Sets.isValidSets(" peterjack@example.com")); // leading space
        assertFalse(Sets.isValidSets("peterjack@example.com ")); // trailing space
        assertFalse(Sets.isValidSets("peterjack@@example.com")); // double '@' symbol
        assertFalse(Sets.isValidSets("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Sets.isValidSets("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Sets.isValidSets("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Sets.isValidSets("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Sets.isValidSets("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Sets.isValidSets("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Sets.isValidSets("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Sets.isValidSets("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Sets.isValidSets("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Sets.isValidSets("peterjack@example.c")); // top level domain has less than two chars

        // valid sets
        assertTrue(Sets.isValidSets("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Sets.isValidSets("PeterJack.1190@example.com")); // period in local part
        assertTrue(Sets.isValidSets("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Sets.isValidSets("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Sets.isValidSets("a@bc")); // minimal
        assertTrue(Sets.isValidSets("test@localhost")); // alphabets only
        assertTrue(Sets.isValidSets("123@145")); // numeric local part and domain name
        assertTrue(Sets.isValidSets("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Sets.isValidSets("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Sets.isValidSets("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Sets.isValidSets("e1234567@u.nus.edu")); // more than one period in domain
    }
}
