package nus.climods.logic.parser;

import static nus.climods.logic.parser.CommandParserTestUtil.assertParseFailure;
import static nus.climods.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.PickCommand;
import nus.climods.logic.parser.parameters.LessonTypeParameter;
import nus.climods.logic.parser.parameters.ModuleCodeParameter;
import nus.climods.model.module.LessonTypeEnum;

public class PickCommandParserTest {
    private PickCommandParser parser = new PickCommandParser();

    @Test
    public void parse_emptyInput_throwsParseException() {
        assertParseFailure(parser, "", "You need 3 arguments: <module-code> <lesson-type> <classNo>");
    }

    @Test
    public void parse_blankInput_throwsParseException() {
        assertParseFailure(parser, "   ", ModuleCodeParameter.INVALID_INPUT_MESSAGE);
    }

    @Test
    public void parse_invalidFormatTextOnly_throwsParseException() {
        String input = "CSFASS lol 999 ";

        assertParseFailure(parser, input, String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, "CSFASS"));
    }

    @Test
    public void parse_invalidFormatNumFirst_throwsParseException() {
        String input = "210ergerg lol 999 ";

        assertParseFailure(parser, input, String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, "210ergerg"));
    }

    @Test
    public void parse_invalidFormatNotEnoughNumber_throwsParseException() {
        String input = "CS210 lol 999 ";

        assertParseFailure(parser, input, String.format(ModuleCodeParameter.PARSE_EXCEPTION_MESSAGE, "CS210"));
    }

    @Test
    public void parse_invalidFormatLessonType_throwsParseException() {
        String input = "CS2100 lol 999 ";

        assertParseFailure(parser, input, String.format(LessonTypeParameter.PARSE_EXCEPTION_MESSAGE, "lol"));
    }

    @Test
    public void parse_insufficientArguments_throwsParseException() {
        String input = "CS2100 ";

        assertParseFailure(parser, input, "You need 3 arguments: <module-code> <lesson-type> <classNo>");
    }


    @Test
    public void parse_pickCommandSuccess() {
        PickCommand expectedPickCommand = new PickCommand("CS1010J", LessonTypeEnum.TUT, "02");

        String input = "CS1010J TUT 02 ";
        assertParseSuccess(parser, input, expectedPickCommand);
    }
}
