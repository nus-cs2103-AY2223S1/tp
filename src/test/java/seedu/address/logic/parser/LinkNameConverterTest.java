package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_GOOGLE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.team.LinkName;

class LinkNameConverterTest {
    private LinkNameConverter converter = new LinkNameConverter();

    @Test
    public void convert_validLinkName_success() throws Exception {
        String validLinkName = VALID_NAME_GOOGLE;
        LinkName linkName = converter.convert(validLinkName);
        assertEquals(new LinkName(validLinkName), linkName);
    }

    @Test
    public void convert_invalidLinkName_throwsTypeConversionException() {
        String inValidLinkName = "question?mark";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(inValidLinkName));
    }
}
