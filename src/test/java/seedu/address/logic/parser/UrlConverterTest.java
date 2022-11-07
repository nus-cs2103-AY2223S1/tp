package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_GOOGLE;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import picocli.CommandLine;
import seedu.address.model.team.Url;

class UrlConverterTest {
    private UrlConverter converter = new UrlConverter();

    @Test
    public void convert_validUrl_success() throws Exception {
        String validUrl = VALID_URL_GOOGLE;
        Url url = converter.convert(validUrl);
        assertEquals(new Url(validUrl), url);
    }

    @Test
    public void convert_invalidUrl_throwsTypeConversionException() {
        String invalidUrl = "unknownUrl";
        assertThrows(CommandLine.TypeConversionException.class, () -> converter.convert(invalidUrl));
    }
}