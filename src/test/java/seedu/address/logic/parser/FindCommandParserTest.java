package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.AlwaysTruePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_returnsAllContacts() {
        FindCommand expectedFindCommand = new FindCommand(new AlwaysTruePredicate());
        assertParseSuccess(parser, "", expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy", "Bee")));
        assertParseSuccess(parser, NAME_DESC_AMY, expectedFindCommand);
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        assertParseFailure(parser, " /g", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

}
