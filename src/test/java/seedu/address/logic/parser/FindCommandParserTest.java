package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_KEYWORDS_WITHOUT_PREFIX;
import static seedu.address.commons.core.Messages.MESSAGE_NO_PREFIX_SPECIFIED;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT_OR_SCHOOL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Model;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser(Model.ListType.STUDENT_LIST);

    @Test
    public void validateArgs_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_NO_PREFIX_SPECIFIED, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void validateArgs_noSpecifiedPrefix_throwsParseException() {
        assertParseFailure(parser, "someone",
                String.format(MESSAGE_KEYWORDS_WITHOUT_PREFIX, FindCommand.MESSAGE_USAGE));
    }
    /*
    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        HashMap<Prefix, String> keywords = new HashMap<>();
        keywords.put(PREFIX_NAME, "Alice");
        keywords.put(PREFIX_ADDRESS, "");
        keywords.put(PREFIX_EMAIL, "alice@example.com");
        keywords.put(PREFIX_PHONE, "");
        keywords.put(PREFIX_SUBJECT_OR_SCHOOL, "");
        keywords.put(PREFIX_LEVEL, "");
        keywords.put(PREFIX_TAG, "");
        FindCommand expectedFindCommand = new FindCommand(keywords);
        assertParseSuccess(parser, "n/Alice", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/ \n Alice \n e/ \t alice@example.com  \t", expectedFindCommand);
    }
    */
}
