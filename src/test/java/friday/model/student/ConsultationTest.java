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
    public void isValidConsultation() {
        assertFalse(Consultation.isValidConsultation("09-01-2022")); // wrong order
        assertFalse(Consultation.isValidConsultation("20220901")); // missing '-' symbols
        assertFalse(Consultation.isValidConsultation("01-01")); // missing year
        assertFalse(Consultation.isValidConsultation("0000-12-05")); // invalid year
        assertFalse(Consultation.isValidConsultation("2022-13-05")); // invalid month
        assertFalse(Consultation.isValidConsultation("0000-12-32")); // invalid day
        assertFalse(Consultation.isValidConsultation("2022/09/01")); // '/' instead of '-'
        assertFalse(Consultation.isValidConsultation("20 22-09-01")); // space in year
        assertFalse(Consultation.isValidConsultation("2022- 09 -01")); // spaces in month
        assertFalse(Consultation.isValidConsultation(" 2022-09-01")); // leading space
        assertFalse(Consultation.isValidConsultation("2022-09-01 ")); // trailing space
        assertFalse(Consultation.isValidConsultation("2022--09--01")); // double '-' symbol
        assertFalse(Consultation.isValidConsultation("2022-09-0-1")); // extra '-' symbol

        assertFalse(Consultation.isValidConsultation("1899-12-31")); // only years 1900 - 2999 are valid
        assertFalse(Consultation.isValidConsultation("3000-01-01")); // only years 1900 - 2999 are valid

        // valid dates
        assertTrue(Consultation.isValidConsultation("2022-09-01"));
        assertTrue(Consultation.isValidConsultation("2020-02-28"));

        assertTrue(Consultation.isValidConsultation("1900-01-01")); // only years 1900 - 2999 are valid
        assertTrue(Consultation.isValidConsultation("2999-12-31")); // only years 1900 - 2999 are valid
    }

    @Test
    public void isEmpty() {
        // not the empty instance
        assertFalse(new Consultation(LocalDate.of(2001, 01, 01)).isEmpty());

        // the empty instance
        assertTrue(Consultation.EMPTY_CONSULTATION.isEmpty());
    }


    @Test
    public void compareTo() {
        // 2020 is smaller than 2025
        assertTrue(new MasteryCheck(LocalDate.of(2021, 01, 01))
                .compareTo(new MasteryCheck(LocalDate.of(2025, 01, 01))) < 0);

        // September is not larger than October
        assertFalse(new MasteryCheck(LocalDate.of(2021, 9, 01))
                .compareTo(new MasteryCheck(LocalDate.of(2021, 10, 01))) > 0);


        // 30 is not smaller than 20
        assertFalse(new MasteryCheck(LocalDate.of(2021, 11, 30))
                .compareTo(new MasteryCheck(LocalDate.of(2021, 11, 20))) < 0);

        // Same date
        assertTrue(new MasteryCheck(LocalDate.of(2020, 12, 31))
                .compareTo(new MasteryCheck(LocalDate.of(2020, 12, 31))) == 0);
    }
}
