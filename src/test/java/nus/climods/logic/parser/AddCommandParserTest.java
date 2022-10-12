package nus.climods.logic.parser;

import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.AddCommand;
import nus.climods.logic.parser.exceptions.ParseException;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.model.module.UserModule;


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
    public void parse_invalidCode_throwsParseException() {
        String input = "CS9999";
        assertParseFailure(parser, input, UserModule.MESSAGE_MODULE_NOT_FOUND);
    }

    // TODO: replace with UserModule stub
    @Test
    public void parse_validModule_success() throws ParseException {
        String input = "CS2103";
        assertParseSuccess(parser, input, new AddCommand(new UserModule(input)));
    }
}
