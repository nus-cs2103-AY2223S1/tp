package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.model.module.ModuleCodeStartsWithKeywordPredicate;


public class FindModuleCommandParserTest {

    private FindModuleCommandParser parser = new FindModuleCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModuleCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindModuleCommand() {
        // no leading and trailing whitespaces
        FindModuleCommand expectedFindModuleCommand =
                new FindModuleCommand(new ModuleCodeStartsWithKeywordPredicate("CS"));
        assertParseSuccess(parser, "CS", expectedFindModuleCommand);

        // leading and trailing whitespaces
        assertParseSuccess(parser, "  CS  ", expectedFindModuleCommand);
    }

}
