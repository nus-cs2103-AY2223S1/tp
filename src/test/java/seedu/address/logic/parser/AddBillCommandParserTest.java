package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.BILL_DATE_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BILL_DATE_7;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddBillCommand;


public class AddBillCommandParserTest {
    private AddBillCommandParser parser = new AddBillCommandParser();


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBillCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, PREAMBLE_WHITESPACE + BILL_DATE_DESC_7 + AMOUNT_DESC_7,
                expectedMessage);

        // missing date prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + 1 + AMOUNT_DESC_7,
                expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + 1 + BILL_DATE_DESC_7,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, PREAMBLE_WHITESPACE + 1 + VALID_BILL_DATE_7 + VALID_AMOUNT_7,
                expectedMessage);
    }

}
