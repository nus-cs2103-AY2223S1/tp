package eatwhere.foodguide.model.eatery;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.testutil.Assert;

public class CuisineTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Cuisine(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Cuisine(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        Assert.assertThrows(NullPointerException.class, () -> Cuisine.isValidEmail(null));

        // blank email
        assertFalse(Cuisine.isValidEmail("")); // empty string
        assertFalse(Cuisine.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(Cuisine.isValidEmail("@example.com")); // missing local part
        assertFalse(Cuisine.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(Cuisine.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Cuisine.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(Cuisine.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Cuisine.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(Cuisine.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Cuisine.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(Cuisine.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(Cuisine.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(Cuisine.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Cuisine.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Cuisine.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Cuisine.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(Cuisine.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Cuisine.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Cuisine.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Cuisine.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Cuisine.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Cuisine.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(Cuisine.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Cuisine.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(Cuisine.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Cuisine.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Cuisine.isValidEmail("a@bc")); // minimal
        assertTrue(Cuisine.isValidEmail("test@localhost")); // alphabets only
        assertTrue(Cuisine.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(Cuisine.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(Cuisine.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(Cuisine.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(Cuisine.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }
}
