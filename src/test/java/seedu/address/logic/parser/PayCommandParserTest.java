package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.PayCommand;
import seedu.address.model.student.Money;

public class PayCommandParserTest {

    private static final Money VALID_MONEY = new Money(300);
    private PayCommandParser parser = new PayCommandParser();

    @Test
    public void parse_validArgs_returnsPayCommand() {
        assertParseSuccess(parser, "1 300", new PayCommand(INDEX_FIRST_STUDENT, VALID_MONEY));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "3 a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "a 3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "3",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, PayCommand.MESSAGE_USAGE));
    }
}
