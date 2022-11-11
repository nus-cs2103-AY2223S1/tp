package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.tag.Tag;

class TagConverterTest {
    private TagConverter converter = new TagConverter();

    @Test
    public void convert_validTagName_success() throws Exception {
        String validTagName = "friend";
        Tag tagName = converter.convert(validTagName);
        assertEquals(new Tag(validTagName), tagName);
    }

    @Test
    public void convert_invalidTagName_throwsTypeConversionException() {
        String invalidTagName = "#friend";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(invalidTagName));
    }
}
