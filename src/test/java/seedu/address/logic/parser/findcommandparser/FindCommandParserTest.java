package seedu.address.logic.parser.findcommandparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.model.person.PersonCategory;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void countOccurrences_moreThanOnePrefix_success() {
        int result = parser.countOccurrences(PREFIX_ADDRESS.getPrefix(), PREFIX_ADDRESS.getPrefix()
                + PREFIX_ADDRESS.getPrefix());
        assertEquals(result, 2);
    }

    @Test
    public void countOccurrences_moreThanOnePrefixWithSpaces_success() {
        int result = parser.countOccurrences(PREFIX_ADDRESS.getPrefix(), PREFIX_ADDRESS.getPrefix()
                + "      " + PREFIX_ADDRESS.getPrefix());
        assertEquals(result, 2);
    }

    @Test
    public void countOccurrences_onePrefix_success() {
        int result = parser.countOccurrences(PREFIX_ADDRESS.getPrefix(), PREFIX_ADDRESS.getPrefix());
        assertEquals(result, 1);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException2() {
        assertParseFailure(parser, "b/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThanOnePrefix_throwsParseException() {
        assertParseFailure(parser, PREFIX_ADDRESS.getPrefix() + PREFIX_PHONE.getPrefix(),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, "More than 1 prefix present"));
    }

    @Test
    public void parse_validArgs_returnsFind() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Bob")),
                        new NameContainsKeywordsPredicate<>(Arrays.asList("Alice", "Bob")),
                        PersonCategory.BUYER);
        assertParseSuccess(parser, "n/Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/\n Alice Bob  \t", expectedFindCommand);
    }

}
