package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.appointment.Appointment.DATE_FORMAT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PastAppointmentBuilder;

class PastAppointmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PastAppointment(null, null, null));
    }

    @Test
    public void constructor_invalidDate_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () ->
                new PastAppointmentBuilder().withDate("01-01-2020 12:00").build());
    }

    @Test
    public void constructor_validDate_returnsPastAppointment() {
        assertEquals(LocalDate.parse("01-01-2020", DateTimeFormatter.ofPattern(DATE_FORMAT)),
                new PastAppointmentBuilder().withDate("01-01-2020").build().getDate());
    }

    @Test
    public void constructor_nullMedication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PastAppointmentBuilder().withMedication(null).build());
    }


    @Test
    public void isValidDate_emptyDate_returnsTrue() {
        assertTrue(PastAppointment.isValidDate(""));
    }

    @Test
    public void isValidDate_validDate_returnsTrue() {
        assertTrue(PastAppointment.isValidDate("01-01-2020"));
    }

    @Test
    public void isValidDate_invalidDate_returnsFalse() {
        assertFalse(PastAppointment.isValidDate("01-01-2020 12:00"));
    }

    // partition - future dates
    @Test
    public void isValidDate_futureDate_returnsFalse() {
        assertFalse(PastAppointment.isValidDate(LocalDate.now().plusDays(1)
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT))));
    }

    // boundary condition, partition - current date
    @Test
    public void isValidDate_today_returnsTrue() {
        assertTrue(PastAppointment.isValidDate(LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT))));
    }

    // partition - past dates
    @Test
    public void isValidDate_pastDate_returnsTrue() {
        assertTrue(PastAppointment.isValidDate(LocalDate.now().minusDays(1)
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT))));
    }

    @Test
    public void parseLocalDate_validDate_returnsLocalDate() {
        assertEquals(LocalDate.parse("01-01-2020", DateTimeFormatter.ofPattern(DATE_FORMAT)),
                PastAppointment.parseLocalDate("01-01-2020"));
    }

    @Test
    public void parseLocalDate_invalidDate_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () -> PastAppointment.parseLocalDate("01-01-2020 12:00"));
    }

    @Test
    public void parseLocalDate_pastDate_returnsLocalDate() {
        assertEquals(LocalDate.parse("01-01-2020", DateTimeFormatter.ofPattern(DATE_FORMAT)),
                PastAppointment.parseLocalDate("01-01-2020"));
    }

    @Test
    public void parseLocalDate_today_returnsLocalDate() {
        assertEquals(LocalDate.now(), PastAppointment.parseLocalDate(LocalDate.now()
                .format(DateTimeFormatter.ofPattern(DATE_FORMAT))));
    }

    @Test
    public void getMedication_validMedication_returnsMedication() {
        assertEquals("[[paracetamol]]", new PastAppointmentBuilder()
                .withMedication(new String[] { "Paracetamol" }).build().getMedication().toString());
    }

    @Test
    public void getMedication_nullMedication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PastAppointmentBuilder().withMedication(null).build());
    }

    @Test
    public void getMedication_emptyMedication_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PastAppointmentBuilder()
                .withMedication(new String[] { "" }).build());
    }

    @Test
    public void getMedication_multipleMedication_returnsMedication() {
        assertEquals("[[ibuprofen], [paracetamol]]", new PastAppointmentBuilder()
                .withMedication(new String[] { "Paracetamol", "Ibuprofen" }).build().getMedication().toString());
    }

    @Test
    public void getMedication_multipleMedicationWithEmpty_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PastAppointmentBuilder()
                .withMedication(new String[] { "Paracetamol", "" }).build());
    }

    @Test
    public void getDiagnosis_validDiagnosis_returnsDiagnosis() {
        assertEquals("Fever", new PastAppointmentBuilder()
                .withDiagnosis("Fever").build().getDiagnosis());
    }

    @Test
    public void getDiagnosis_nullDiagnosis_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PastAppointmentBuilder().withDiagnosis(null).build());
    }

    @Test
    public void getDiagnosis_emptyDiagnosis_returnsEmptyString() {
        assertEquals("", new PastAppointmentBuilder().withDiagnosis("").build().getDiagnosis());
    }

    @Test
    public void getMedicationString_validMedication_returnsMedicationString() {
        assertEquals("Medication: Paracetamol", new PastAppointmentBuilder()
                .withMedication(new String[] { "Paracetamol" }).build().getMedicationString());
    }

    @Test
    public void getMedicationString_multipleMedication_returnsMedicationString() {
        assertEquals("Medication: ibuprofen, paracetamol", new PastAppointmentBuilder()
                .withMedication(new String[] { "Paracetamol", "Ibuprofen" }).build().getMedicationString());
    }

    @Test
    public void equals() {
        PastAppointment pastAppointment;

        pastAppointment = new PastAppointmentBuilder().build();
        assertNotEquals(null, pastAppointment);

        pastAppointment = new PastAppointmentBuilder().build();
        assertEquals(pastAppointment, pastAppointment);

        pastAppointment = new PastAppointmentBuilder().build();
        PastAppointment sameDatePastAppointment = new PastAppointmentBuilder().withDate("06-12-2022").build();
        assertEquals(pastAppointment, sameDatePastAppointment);

        pastAppointment = new PastAppointmentBuilder().build();
        PastAppointment differentDatePastAppointment = new PastAppointmentBuilder().withDate("01-01-2021").build();
        assertNotEquals(pastAppointment, differentDatePastAppointment);

        pastAppointment = new PastAppointmentBuilder().build();
        PastAppointment differentMedicationPastAppointment = new PastAppointmentBuilder()
                .withMedication(new String[] { "Paracetamol" })
                .build();
        assertNotEquals(pastAppointment, differentMedicationPastAppointment);

        pastAppointment = new PastAppointmentBuilder().build();
        PastAppointment differentDiagnosisPastAppointment = new PastAppointmentBuilder()
                .withDiagnosis("Sick")
                .build();
        assertNotEquals(pastAppointment, differentDiagnosisPastAppointment);

    }

    @Test
    public void toString_validPastAppointment_returnsString() {
        assertEquals("On: 06 Dec 2022; Diagnosis: Fever; Prescribed Medication: [ibuprofen][paracetamol]",
                new PastAppointmentBuilder().build().toString());
    }
}
