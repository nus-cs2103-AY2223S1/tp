package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindModuleCommand;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;
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
                new FindModuleCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList("CS2100", "CS2103T")));
        assertParseSuccess(parser, "CS2100 CS2103T", expectedFindModuleCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n CS2100 \n \t CS2103T  \t", expectedFindModuleCommand);
    }

}
