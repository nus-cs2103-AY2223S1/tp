package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import coydir.logic.commands.FindCommand;
import coydir.model.person.PersonMatchesKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonMatchesKeywordsPredicate("Prittam", "Intern", "Board"));
        assertParseSuccess(parser, "n/Prittam j/Intern d/Board", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Prittam \nj/Intern \t d/Board  \t", expectedFindCommand);
    }

}
