package nus.climods.logic.parser;

import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import nus.climods.logic.parser.parameters.ModuleCodeParameter;


public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "", ModuleCodeParameter.INVALID_INPUT_MESSAGE);
    }

    @Test
    public void parse_blankInput_throwsParseException() {
        assertParseFailure(parser, "   ", ModuleCodeParameter.INVALID_INPUT_MESSAGE);
    }

    @Test
    public void parse_invalidFormatTextOnly_throwsParseException() {
        String input = "CSFASS";
        assertParseFailure(parser, input, String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, input));
    }

    @Test
    public void parse_invalidFormatNumFirst_throwsParseException() {
        String input = "210ergerg";
        assertParseFailure(parser, input, String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, input));
    }

    @Test
    public void parse_invalidFormatNotEnoughNumber_throwsParseException() {
        String input = "CS210";
        assertParseFailure(parser, input, String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, input));
    }
}
