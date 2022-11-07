package seedu.condonery.logic.parser.property;

import static seedu.condonery.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.condonery.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.condonery.logic.commands.property.StatusPropertyCommand;
import seedu.condonery.model.property.PropertyStatusContainsKeywordsPredicate;

public class StatusPropertyCommandParserTest {
    private final StatusPropertyCommandParser parser = new StatusPropertyCommandParser();
    private final String userInput = "AVAILABLE";
    private final PropertyStatusContainsKeywordsPredicate predicate =
            new PropertyStatusContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));

    @Test
    public void parse_validArgs_returnsStatusPropertyCommand() {
        assertParseSuccess(parser, "AVAILABLE", new StatusPropertyCommand(predicate));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusPropertyCommand.MESSAGE_USAGE));
    }
}
