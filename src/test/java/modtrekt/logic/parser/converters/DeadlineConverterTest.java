package modtrekt.logic.parser.converters;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.beust.jcommander.ParameterException;

class DeadlineConverterTest {

    private final DeadlineConverter converter = new DeadlineConverter();

    // Out of bounds - Does not follow YYYY-MM-DD format
    @Test
    public void convert_outOfBounds_throws() {
        assertThrows(ParameterException.class, () -> converter.convert("-2022-01-01"));
        assertThrows(ParameterException.class, () -> converter.convert("99999-01-01"));
        assertThrows(ParameterException.class, () -> converter.convert("2022-112-01"));
        assertThrows(ParameterException.class, () -> converter.convert("2022-01-112"));
    }

    // Valid format, dates do not exist
    @Test
    public void convert_invalidDates_throws() {
        // 31 dates that do not exist
        assertThrows(ParameterException.class, () -> converter.convert("2022-11-31"));
        assertThrows(ParameterException.class, () -> converter.convert("2022-09-31"));
        // Non-leap year >28 Feb
        assertThrows(ParameterException.class, () -> converter.convert("2021-02-29"));
        assertThrows(ParameterException.class, () -> converter.convert("2022-02-29"));
        assertThrows(ParameterException.class, () -> converter.convert("2023-02-29"));
    }

    @Test
    public void convert_edgeCasesValidDates_returnsDate() throws ParameterException {
        // Leap year >28 Feb
        LocalDate date = converter.convert("2016-02-29");
        assertEquals(date.getDayOfMonth(), 29);
        LocalDate date2 = converter.convert("2020-02-29");
        assertEquals(date2.getDayOfMonth(), 29);
        LocalDate date3 = converter.convert("2024-02-29");
        assertEquals(date3.getDayOfMonth(), 29);
    }

}
