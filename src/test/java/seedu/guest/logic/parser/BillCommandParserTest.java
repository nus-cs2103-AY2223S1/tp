package seedu.guest.logic.parser;

import static seedu.guest.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.guest.logic.commands.CommandTestUtil.BILL_DESC_NEGATIVE;
import static seedu.guest.logic.commands.CommandTestUtil.BILL_DESC_POSITIVE;
import static seedu.guest.logic.commands.CommandTestUtil.BILL_DESC_ZERO;
import static seedu.guest.logic.commands.CommandTestUtil.INVALID_BILL_DESC;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_BILL_NEGATIVE;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_BILL_POSITIVE;
import static seedu.guest.logic.commands.CommandTestUtil.VALID_BILL_ZERO;
import static seedu.guest.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.guest.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.guest.testutil.TypicalIndexes.INDEX_FIRST_GUEST;
import static seedu.guest.testutil.TypicalIndexes.INDEX_SECOND_GUEST;

import org.junit.jupiter.api.Test;

import seedu.guest.commons.core.index.Index;
import seedu.guest.logic.commands.BillCommand;
import seedu.guest.model.guest.Bill;

public class BillCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, BillCommand.MESSAGE_USAGE);

    private BillCommandParser parser = new BillCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, BILL_DESC_POSITIVE, MESSAGE_INVALID_FORMAT);

        // no value specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no value specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + BILL_DESC_POSITIVE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + BILL_DESC_POSITIVE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_BILL_DESC, Bill.MESSAGE_CONSTRAINTS); // invalid bill

        // valid bill followed by invalid bill. The test case for invalid bill followed by valid bill
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + BILL_DESC_POSITIVE + INVALID_BILL_DESC, Bill.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_GUEST;

        String userInput = targetIndex.getOneBased() + BILL_DESC_NEGATIVE;

        BillCommand expectedCommand = new BillCommand(targetIndex, new Bill(VALID_BILL_NEGATIVE));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_GUEST;

        String userInput = targetIndex.getOneBased() + BILL_DESC_NEGATIVE + BILL_DESC_ZERO + BILL_DESC_POSITIVE;

        BillCommand expectedCommand = new BillCommand(targetIndex, new Bill(VALID_BILL_POSITIVE));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_GUEST;
        String userInput = targetIndex.getOneBased() + INVALID_BILL_DESC + BILL_DESC_NEGATIVE;
        BillCommand expectedCommand = new BillCommand(targetIndex, new Bill(VALID_BILL_NEGATIVE));
        assertParseSuccess(parser, userInput, expectedCommand);

        // multiple invalid and valid values specified
        userInput = targetIndex.getOneBased() + INVALID_BILL_DESC + BILL_DESC_NEGATIVE + INVALID_BILL_DESC
                + BILL_DESC_ZERO;

        expectedCommand = new BillCommand(targetIndex, new Bill(VALID_BILL_ZERO));
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
