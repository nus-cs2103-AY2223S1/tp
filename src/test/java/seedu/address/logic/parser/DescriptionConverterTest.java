package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_FIRST_TEAM;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.team.Description;

class DescriptionConverterTest {
    private DescriptionConverter converter = new DescriptionConverter();

    @Test
    public void convert_validDescription_success() throws Exception {
        String validDescription = VALID_DESCRIPTION_FIRST_TEAM;
        Description description = converter.convert(validDescription);
        assertEquals(new Description(validDescription), description);
    }

    @Test
    public void convert_invalidDescription_throwsTypeConversionException() {
        String inValidDescription = "question?mark";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidDescription));
    }
}
