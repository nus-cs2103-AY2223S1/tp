package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindModulesCommand;
import seedu.address.model.module.ModuleCodeContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class FindModulesCommandParserTest {

    private FindModulesCommandParser parser = new FindModulesCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindModulesCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindModulesCommand() {
        // no leading and trailing whitespaces
        FindModulesCommand expectedFindModulesCommand =
                new FindModulesCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList("cs2030s")));
        assertParseSuccess(parser, "cs2030s", expectedFindModulesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \t \n cs2030s\n \t \t", expectedFindModulesCommand);
    }

    @Test
    public void parse_validAllCapsArgsPassToPredicateAsLowerCase_returnsFindModulesCommand() {
        // no leading and trailing whitespaces
        FindModulesCommand expectedFindModulesCommand =
                new FindModulesCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList("cs2030s")));
        assertParseSuccess(parser, "CS2030S", expectedFindModulesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \t \n CS2030s  \n \t \t", expectedFindModulesCommand);
    }

    @Test
    public void parse_validMixCaseArgsPassToPredicateAsLowerCase_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindModulesCommand expectedFindModulesCommand =
                new FindModulesCommand(new ModuleCodeContainsKeywordsPredicate(Arrays.asList("cs2030s")));
        assertParseSuccess(parser, "Cs2030S", expectedFindModulesCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \t \n cS2030s \n \t \t", expectedFindModulesCommand);
    }


}
