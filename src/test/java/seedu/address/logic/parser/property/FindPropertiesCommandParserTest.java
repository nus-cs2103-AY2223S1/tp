package seedu.address.logic.parser.property;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.property.FindPropertiesCommand;
import seedu.address.model.property.PropertyNameContainsKeywordsPredicate;

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
                new FindPropertiesCommand(new PropertyNameContainsKeywordsPredicate(Arrays.asList("tembu", "rc4")));
        assertParseSuccess(parser, "tembu rc4", expectedFindPropertiesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n tembu \n \t rc4  \t", expectedFindPropertiesCommand);
    }

}
