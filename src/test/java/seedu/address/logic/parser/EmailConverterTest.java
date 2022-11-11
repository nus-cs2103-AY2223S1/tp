package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.person.Email;

class EmailConverterTest {
    private EmailConverter converter = new EmailConverter();

    @Test
    public void convert_validEmail_success() throws Exception {
        String validEmail = VALID_EMAIL_AMY;
        Email email = converter.convert(validEmail);
        assertEquals(new Email(validEmail), email);
    }

    @Test
    public void convert_invalidEmail_throwsTypeConversionException() {
        String inValidEmail = "question?mark";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidEmail));
    }
}
