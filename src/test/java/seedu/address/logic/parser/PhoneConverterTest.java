package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.person.Phone;

class PhoneConverterTest {
    private PhoneConverter converter = new PhoneConverter();

    @Test
    public void convert_validPhoneNumber_success() throws Exception {
        String validPhoneNumber = "123456";
        Phone phoneNumber = converter.convert(validPhoneNumber);
        assertEquals(new Phone(validPhoneNumber), phoneNumber);
    }

    @Test
    public void convert_invalidPhoneNumber_throwsTypeConversionException() {
        String invalidPhoneNumber = "+651234";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(invalidPhoneNumber));
    }
}
