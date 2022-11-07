package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.BILL_DATE_DESC_7;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BILL_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BILL_DATE_7;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPOINTMENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BILL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_BILL;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_BILL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditBillCommand;
import seedu.address.model.bill.Amount;
import seedu.address.model.bill.BillDate;
import seedu.address.testutil.EditBillDescriptorBuilder;

public class EditBillCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditBillCommand.MESSAGE_USAGE);

    private EditBillCommandParser parser = new EditBillCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditBillCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC, Amount.MESSAGE_CONSTRAINTS); // invalid amount
        assertParseFailure(parser, "1"
                + INVALID_BILL_DATE_DESC, BillDate.MESSAGE_CONSTRAINTS); // invalid bill date

        // invalid name followed by valid information
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC + VALID_BILL_DATE_7,
                Amount.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser, "1" + VALID_AMOUNT_7 + INVALID_AMOUNT_DESC + VALID_BILL_DATE_7,
                Amount.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_BILL;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_7 + BILL_DATE_DESC_7;

        EditBillCommand.EditBillDescriptor descriptor = new EditBillDescriptorBuilder()
                .withAmount(VALID_AMOUNT_7).withBillDate(VALID_BILL_DATE_7).build();
        EditBillCommand expectedCommand = new EditBillCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_BILL;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_7;

        EditBillCommand.EditBillDescriptor descriptor = new EditBillDescriptorBuilder()
                .withAmount(VALID_AMOUNT_7).build();
        EditBillCommand expectedCommand = new EditBillCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_BILL;
        String userInput = targetIndex.getOneBased() + BILL_DATE_DESC_7;
        EditBillCommand.EditBillDescriptor descriptor =
                new EditBillDescriptorBuilder().withBillDate(VALID_BILL_DATE_7).build();
        EditBillCommand expectedCommand = new EditBillCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC_7 + AMOUNT_DESC_7
                + BILL_DATE_DESC_7 + BILL_DATE_DESC_7;

        EditBillCommand.EditBillDescriptor descriptor = new EditBillDescriptorBuilder()
                .withAmount(VALID_AMOUNT_7).withBillDate(VALID_BILL_DATE_7).build();
        EditBillCommand expectedCommand = new EditBillCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_APPOINTMENT;
        String userInput = targetIndex.getOneBased() + INVALID_AMOUNT_DESC + AMOUNT_DESC_7;
        EditBillCommand.EditBillDescriptor descriptor =
                new EditBillDescriptorBuilder().withAmount(VALID_AMOUNT_7).build();
        EditBillCommand expectedCommand = new EditBillCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + AMOUNT_DESC_7 + INVALID_BILL_DATE_DESC + BILL_DATE_DESC_7;
        descriptor = new EditBillDescriptorBuilder().withAmount(VALID_AMOUNT_7).withBillDate(VALID_BILL_DATE_7).build();
        expectedCommand = new EditBillCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
