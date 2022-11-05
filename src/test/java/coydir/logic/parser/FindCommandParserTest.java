package coydir.logic.parser;

import static coydir.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static coydir.logic.parser.CommandParserTestUtil.assertParseFailure;
import static coydir.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import coydir.logic.commands.FindCommand;
import coydir.model.person.Name;
import coydir.model.person.PersonMatchesKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "n/Prittam",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        assertParseFailure(parser, " n/ j/ d/", ParserUtil.MESSAGE_INVALID_ARGUMENT);
        assertParseFailure(parser, " n/$$$ j/Intern d/Board", Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, " e/prittam@email.com",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new PersonMatchesKeywordsPredicate("Prittam", "Intern", "Board"));
        assertParseSuccess(parser, " n/Prittam j/Intern d/Board", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n n/Prittam \n j/Intern \t d/Board  \t", expectedFindCommand);

        // not all args necessary
        expectedFindCommand = new FindCommand(new PersonMatchesKeywordsPredicate("Prittam", "", ""));
        assertParseSuccess(parser, " n/Prittam", expectedFindCommand);
        expectedFindCommand = new FindCommand(new PersonMatchesKeywordsPredicate("", "Intern", ""));
        assertParseSuccess(parser, " j/Intern", expectedFindCommand);
        expectedFindCommand = new FindCommand(new PersonMatchesKeywordsPredicate("", "", "Board"));
        assertParseSuccess(parser, " d/Board", expectedFindCommand);
        expectedFindCommand = new FindCommand(new PersonMatchesKeywordsPredicate("Prittam", "Intern", ""));
        assertParseSuccess(parser, " j/Intern n/Prittam", expectedFindCommand);
        expectedFindCommand = new FindCommand(new PersonMatchesKeywordsPredicate("Prittam", "", "Board"));
        assertParseSuccess(parser, " d/Board n/Prittam", expectedFindCommand);
        expectedFindCommand = new FindCommand(new PersonMatchesKeywordsPredicate("", "Intern", "Board"));
        assertParseSuccess(parser, " d/Board j/Intern", expectedFindCommand);
    }

}
