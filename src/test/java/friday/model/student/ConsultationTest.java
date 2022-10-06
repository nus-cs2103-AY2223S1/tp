package friday.model.student;

import static friday.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ConsultationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Consultation(null));
    }

    @Test
    public void isValidConsultation() {
        /*
         missing parts
        assertFalse(Consultation.isValidConsultation("@example.com")); // missing local part
        assertFalse(Consultation.isValidConsultation("peterjackexample.com")); // missing '@' symbol
        assertFalse(Consultation.isValidConsultation("peterjack@")); // missing domain name

         */

        /*
        invalid
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

         */

        // valid email
        assertTrue(Consultation.isValidConsultation("2022-09-01"));
    }
}
