package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.FindPropertiesCommand;
import seedu.address.model.property.PropertyNameContainsSubstringPredicate;

public class FindPropertiesCommandParserTest {

    private FindPropertiesCommandParser parser = new FindPropertiesCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPropertiesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindPropertiesCommand expectedFindPropertiesCommand =
                new FindPropertiesCommand(new PropertyNameContainsSubstringPredicate("tembu"));
        assertParseSuccess(parser, "tembu", expectedFindPropertiesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n tembu \n \t \t", expectedFindPropertiesCommand);
    }

}
