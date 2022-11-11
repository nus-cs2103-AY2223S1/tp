package coydir.logic.parser;

import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import coydir.commons.core.Messages;
import coydir.logic.commands.RateCommand;
import coydir.model.person.EmployeeId;
import coydir.model.person.Rating;

public class RateCommandParserTest {
    private RateCommandParser parser = new RateCommandParser();
    private String invalidFormat = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
        RateCommand.MESSAGE_USAGE);

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, RateCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        // missing employee id
        assertParseFailure(parser, "3" , invalidFormat);

        // missing rating
        assertParseFailure(parser, "1" , invalidFormat);

    }

    @Test
    public void parse_allFieldsPresent_success() {
        RateCommand expectedRateCommand = new RateCommand(new EmployeeId("1"), new Rating("3"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, " id/1 r/3", expectedRateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n id/1 \n r/3  \t", expectedRateCommand);
    }
}
