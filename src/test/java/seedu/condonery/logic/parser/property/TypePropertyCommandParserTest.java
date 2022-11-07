package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.TypePropertyCommand;
import seedu.condonery.model.property.PropertyTypeContainsKeywordsPredicate;

public class TypePropertyCommandParserTest {
    private final TypePropertyCommandParser parser = new TypePropertyCommandParser();
    private final String userInput = "HDB";
    private final PropertyTypeContainsKeywordsPredicate predicate =
            new PropertyTypeContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));

    @Test
    public void parse_validArgs_returnsStatusPropertyCommand() {
        assertParseSuccess(parser, "HDB", new TypePropertyCommand(predicate));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TypePropertyCommand.MESSAGE_USAGE));
    }
}
