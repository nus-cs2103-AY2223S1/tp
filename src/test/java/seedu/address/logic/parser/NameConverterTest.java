package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ALICE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.person.Name;

class NameConverterTest {
    private NameConverter converter = new NameConverter();

    @Test
    public void convert_validName_success() throws Exception {
        String validName = VALID_NAME_ALICE;
        Name name = converter.convert(validName);
        assertEquals(new Name(validName), name);
    }

    @Test
    public void convert_invalidName_throwsTypeConversionException() {
        String inValidName = "question?mark";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidName));
    }
}