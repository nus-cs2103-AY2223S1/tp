package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.BILL_DATE_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.BILL_DATE_DESC_8;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BILL_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalBills.BILL_7;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BILL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddBillCommand;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.Bill;
import seedu.address.model.bill.BillDate;
import seedu.address.testutil.BillBuilder;

public class AddBillCommandParserTest {
    private AddBillCommandParser parser = new AddBillCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Index targetIndex = INDEX_FIRST_BILL;
        Bill expectedBill = new BillBuilder(BILL_7).build();
        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + targetIndex.getOneBased() + BILL_DATE_DESC_7
                + AMOUNT_DESC_7, new AddBillCommand(targetIndex, expectedBill.getBillDate(), expectedBill.getAmount()));

        // multiple bill date - last bill date accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + BILL_DATE_DESC_8 + BILL_DATE_DESC_7
                + AMOUNT_DESC_7, new AddBillCommand(targetIndex, expectedBill.getBillDate(), expectedBill.getAmount()));

        // multiple amounts - last amount accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + BILL_DATE_DESC_7 + AMOUNT_DESC_7
                + AMOUNT_DESC_7, new AddBillCommand(targetIndex, expectedBill.getBillDate(), expectedBill.getAmount()));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBillCommand.MESSAGE_USAGE);

        // missing bill date prefix
        assertParseFailure(parser, AMOUNT_DESC_7, expectedMessage);

        // missing amount prefix
        assertParseFailure(parser, BILL_DATE_DESC_7, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        Index targetIndex = INDEX_FIRST_BILL;
        // invalid bill date
        assertParseFailure(parser, targetIndex.getOneBased() + INVALID_BILL_DATE_DESC + AMOUNT_DESC_7,
                BillDate.MESSAGE_CONSTRAINTS);

        // invalid amount
        assertParseFailure(parser, targetIndex.getOneBased() + BILL_DATE_DESC_7 + INVALID_AMOUNT_DESC,
                Amount.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, targetIndex.getOneBased() + INVALID_BILL_DATE_DESC + INVALID_AMOUNT_DESC,
                BillDate.MESSAGE_CONSTRAINTS);

        //non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + targetIndex.getOneBased() + BILL_DATE_DESC_7
                + AMOUNT_DESC_7, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddBillCommand.MESSAGE_USAGE));
    }
}
