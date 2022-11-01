package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_NON_POSITIVE_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BIRTHDAY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_AMOUNT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_REASON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.REASON_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMOUNT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTHDAY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REASON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditLoanCommand;
import seedu.address.model.ModelManager;
import seedu.address.model.person.Loan;
import seedu.address.model.person.LoanHistory;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Reason;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditLoanCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditLoanCommand.MESSAGE_USAGE);

    private EditLoanCommandParser parser = new EditLoanCommandParser(new ModelManager());

    @Test
    public void parse_missingParts_failure() {
        // deleted, ability added, previously: no index specified
        // assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", Messages.AMOUNT_NOT_SPECIFIED);
        assertParseFailure(parser, "1 amt/3", Messages.REASON_NOT_SPECIFIED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + AMOUNT_DESC + REASON_DESC,
                MESSAGE_INVALID_NON_POSITIVE_INDEX);

        // zero index
        assertParseFailure(parser, "0" + AMOUNT_DESC + REASON_DESC,
                MESSAGE_INVALID_NON_POSITIVE_INDEX);

        // invalid preamble
        assertParseFailure(parser, "(&^(" + AMOUNT_DESC + REASON_DESC,
                MESSAGE_INVALID_FORMAT);

        // invalid preamble
        assertParseFailure(parser, "313-" + VALID_AMOUNT + VALID_REASON,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid amount, valid reason
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC + REASON_DESC, Loan.MESSAGE_CONSTRAINTS);
        // valid amount, invalid reason
        assertParseFailure(parser, "1" + AMOUNT_DESC + INVALID_REASON_DESC, Reason.MESSAGE_CONSTRAINTS);

        // loan is always captured first if invalid
        assertParseFailure(parser, "1" + INVALID_AMOUNT_DESC + INVALID_REASON_DESC,
                Loan.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_REASON_DESC + INVALID_AMOUNT_DESC,
                Loan.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + AMOUNT_DESC + REASON_DESC;
        String userInputFlipped = targetIndex.getOneBased() + REASON_DESC + AMOUNT_DESC;

        EditLoanCommand.EditLoanDescriptor descriptor =
                new EditLoanCommand.EditLoanDescriptor(new Loan(VALID_AMOUNT),
                        new LoanHistory(new Loan(VALID_AMOUNT), new Reason(VALID_REASON)));

        EditLoanCommand expectedCommand = new EditLoanCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
        assertParseSuccess(parser, userInputFlipped, expectedCommand);
    }

    @Test
    public void parse_loanTooLarge_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " amt/1000000000001" + REASON_DESC;

        assertParseFailure(parser, userInput, Loan.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_loanTooSmall_failure() {
        String userInput = INDEX_FIRST_PERSON.getOneBased() + " amt/-1000000000000.1" + REASON_DESC;

        assertParseFailure(parser, userInput, Loan.MESSAGE_CONSTRAINTS);
    }
}
