package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TeachingNominationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TeachingNomination(null));
    }

    @Test
    public void constructor_invalidTeachingNomination_throwsIllegalArgumentException() {
        String invalidTeachingNomination = "-1";
        assertThrows(IllegalArgumentException.class, () -> new TeachingNomination(invalidTeachingNomination));
    }

    @Test
    public void isValidTeachingNomination() {
        // null teachingNomination
        assertThrows(NullPointerException.class, () -> TeachingNomination.isValidTeachingNomination(null));

        // blank teachingNomination
        assertFalse(TeachingNomination.isValidTeachingNomination("")); // empty string
        assertFalse(TeachingNomination.isValidTeachingNomination(" ")); // spaces only

        // invalid parts
        assertFalse(TeachingNomination.isValidTeachingNomination("peterjack@-")); // invalid domain name
        assertFalse(TeachingNomination.isValidTeachingNomination("peterjack@exam_ple.com")); // underscore in domain name

        // valid TeachingNomination
        assertTrue(TeachingNomination.isValidTeachingNomination("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(TeachingNomination.isValidTeachingNomination("PeterJack.1190@example.com")); // period in local part
        assertTrue(TeachingNomination.isValidTeachingNomination("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(TeachingNomination.isValidTeachingNomination("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(TeachingNomination.isValidTeachingNomination("a@bc")); // minimal
        assertTrue(TeachingNomination.isValidTeachingNomination("test@localhost")); // alphabets only
        assertTrue(TeachingNomination.isValidTeachingNomination("123@145")); // numeric local part and domain name
        assertTrue(TeachingNomination.isValidTeachingNomination("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(TeachingNomination.isValidTeachingNomination("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(TeachingNomination.isValidTeachingNomination("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(TeachingNomination.isValidTeachingNomination("e1234567@u.nus.edu")); // more than one period in domain
    }
}
