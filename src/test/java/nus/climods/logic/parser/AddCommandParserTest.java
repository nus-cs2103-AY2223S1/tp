package nus.climods.logic.parser;

import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;
import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.commands.AddCommand;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.logic.parser.parameters.SemesterTypeParameter;

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

    @Test
    public void parse_invalidFormatSemesterType_throwsParseException() {
        String input = "CS2100 k1";

        assertParseFailure(parser, input, String.format(SemesterTypeParameter.PARSE_EXCEPTION_MESSAGE, "k1"));
    }

    @Test
    public void parse_addCommandSuccess() {
        AddCommand expectedAddCommand = new AddCommand("CS1010J", SemestersEnum.S1);

        String input = "CS1010J s1";
        assertParseSuccess(parser, input, expectedAddCommand);
    }
}
