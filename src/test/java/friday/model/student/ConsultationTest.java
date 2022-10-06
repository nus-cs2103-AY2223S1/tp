package friday.model.student;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ConsultationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Consultation(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        LocalDate invalidDate = LocalDate.parse("2022/12/03");
        assertThrows(IllegalArgumentException.class, () -> new Consultation(invalidDate));
    }

    @Test
    public void isValidConsultation() {
        // null email
        assertThrows(NullPointerException.class, () -> Consultation.isValidConsultation(null));

        // blank email
        assertFalse(Consultation.isValidConsultation("")); // empty string
        assertFalse(Consultation.isValidConsultation(" ")); // spaces only

        // missing parts
        assertFalse(Consultation.isValidConsultation("@example.com")); // missing local part
        assertFalse(Consultation.isValidConsultation("peterjackexample.com")); // missing '@' symbol
        assertFalse(Consultation.isValidConsultation("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(Consultation.isValidConsultation("peterjack@-")); // invalid domain name
        assertFalse(Consultation.isValidConsultation("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(Consultation.isValidConsultation("peter jack@example.com")); // spaces in local part
        assertFalse(Consultation.isValidConsultation("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(Consultation.isValidConsultation(" peterjack@example.com")); // leading space
        assertFalse(Consultation.isValidConsultation("peterjack@example.com ")); // trailing space
        assertFalse(Consultation.isValidConsultation("peterjack@@example.com")); // double '@' symbol
        assertFalse(Consultation.isValidConsultation("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(Consultation.isValidConsultation("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(Consultation.isValidConsultation("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(Consultation.isValidConsultation("peter..jack@example.com"));
        assertFalse(Consultation.isValidConsultation("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(Consultation.isValidConsultation("peterjack@.example.com")); // domain name starts with a period
        assertFalse(Consultation.isValidConsultation("peterjack@example.com.")); // domain name ends with a period
        assertFalse(Consultation.isValidConsultation("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(Consultation.isValidConsultation("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(Consultation.isValidConsultation("peterjack@example.c"));

        // valid email
        assertTrue(Consultation.isValidConsultation("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(Consultation.isValidConsultation("PeterJack.1190@example.com")); // period in local part
        assertTrue(Consultation.isValidConsultation("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(Consultation.isValidConsultation("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(Consultation.isValidConsultation("a@bc")); // minimal
        assertTrue(Consultation.isValidConsultation("test@localhost")); // alphabets only
        assertTrue(Consultation.isValidConsultation("123@145")); // numeric local part and domain name
        assertTrue(Consultation.isValidConsultation("a1+be.d@example1.com"));
        assertTrue(Consultation.isValidConsultation("peter_jack@very-very-very-long-example.com"));
        assertTrue(Consultation.isValidConsultation("if.you.dream.it_you.can.do.it@example.com"));
        assertTrue(Consultation.isValidConsultation("e1234567@u.nus.edu")); // more than one period in domain
    }
}
