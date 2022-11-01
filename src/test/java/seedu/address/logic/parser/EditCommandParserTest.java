package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDITIONAL_NOTE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CLASS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CLASS_DATE_TIME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONEY_OWED_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MONEY_PAID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NOK_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATES_PER_CLASS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.MONEY_OWED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MONEY_OWED_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MONEY_PAID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MONEY_PAID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOK_PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NOK_PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.RATES_PER_CLASS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_BEGINNER;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_INTERMEDIATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDITIONAL_NOTES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONEY_OWED_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONEY_OWED_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONEY_PAID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MONEY_PAID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOK_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NOK_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATES_PER_CLASS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BEGINNER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INTERMEDIATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Address;
import seedu.address.model.student.Class;
import seedu.address.model.student.Email;
import seedu.address.model.student.Money;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

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
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_NOK_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid nok phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_CLASS_DATE_TIME_DESC, Class.MESSAGE_CONSTRAINTS); // invalid class
        assertParseFailure(parser, "1" + INVALID_MONEY_OWED_DESC, Money.MESSAGE_CONSTRAINTS); // invalid money owed
        assertParseFailure(parser, "1" + INVALID_MONEY_PAID_DESC, Money.MESSAGE_CONSTRAINTS); // invalid money paid
        assertParseFailure(parser,
                "1" + INVALID_RATES_PER_CLASS_DESC, Money.MESSAGE_CONSTRAINTS); // invalid rates per class
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // invalid next of kin phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_NOK_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // valid next of kin phone followed by invalid next of kin phone.
        assertParseFailure(
                parser, "1" + NOK_PHONE_DESC_BOB + INVALID_NOK_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Student} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(
                parser, "1" + TAG_DESC_BEGINNER + TAG_DESC_INTERMEDIATE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, "1" + TAG_DESC_BEGINNER + TAG_EMPTY + TAG_DESC_INTERMEDIATE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(
                parser, "1" + TAG_EMPTY + TAG_DESC_BEGINNER + TAG_DESC_INTERMEDIATE, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY + VALID_NOK_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + TAG_DESC_INTERMEDIATE
                + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY + CLASS_DESC_AMY
                + MONEY_OWED_DESC_BOB + MONEY_PAID_DESC_BOB
                + RATES_PER_CLASS_DESC_AMY + ADDITIONAL_NOTE_DESC_AMY + TAG_DESC_BEGINNER;

        EditStudentDescriptor descriptor;
        try {
            descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                    .withPhone(VALID_PHONE_BOB).withNokPhone(VALID_NOK_PHONE_BOB)
                    .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withClass(VALID_CLASS_AMY)
                    .withMoneyOwed(VALID_MONEY_OWED_BOB).withMoneyPaid(VALID_MONEY_PAID_BOB)
                    .withRatesPerClass(VALID_RATES_PER_CLASS_AMY)
                    .withAdditionalNotes(VALID_ADDITIONAL_NOTES_AMY)
                    .withTags(VALID_TAG_INTERMEDIATE, VALID_TAG_BEGINNER).build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // next of kin phone
        userInput = targetIndex.getOneBased() + NOK_PHONE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withNokPhone(VALID_NOK_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // money owed
        userInput = targetIndex.getOneBased() + MONEY_OWED_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withMoneyOwed(VALID_MONEY_OWED_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // money paid
        userInput = targetIndex.getOneBased() + MONEY_PAID_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withMoneyPaid(VALID_MONEY_PAID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // additional notes
        userInput = targetIndex.getOneBased() + ADDITIONAL_NOTE_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withAdditionalNotes(VALID_ADDITIONAL_NOTES_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_BEGINNER;
        descriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_BEGINNER).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + NOK_PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_BEGINNER + PHONE_DESC_AMY + NOK_PHONE_DESC_AMY + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY + TAG_DESC_BEGINNER + MONEY_OWED_DESC_AMY + MONEY_PAID_DESC_AMY
                + PHONE_DESC_BOB + NOK_PHONE_DESC_BOB + ADDRESS_DESC_BOB + EMAIL_DESC_BOB
                + MONEY_OWED_DESC_BOB + MONEY_PAID_DESC_BOB + TAG_DESC_INTERMEDIATE;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withNokPhone(VALID_NOK_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withMoneyOwed(VALID_MONEY_OWED_BOB).withMoneyPaid(VALID_MONEY_PAID_BOB)
                .withTags(VALID_TAG_BEGINNER, VALID_TAG_INTERMEDIATE).build();

        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + NOK_PHONE_DESC_BOB
                + ADDRESS_DESC_BOB + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withNokPhone(VALID_NOK_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_validClassDateTime() throws ParseException {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_CLASS_DATE_TIME + "2022-05-05 1200-1500";
        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withClass("2022-05-05 1200-1500").build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidClassDateTime() {
        assertParseFailure(parser, "1 " + PREFIX_CLASS_DATE_TIME + "2022-05-05 1200 1500", Class.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_CLASS_DATE_TIME + "2022-20-05 1200-1500", Class.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_CLASS_DATE_TIME + "2022-05-05 1200-1100",
                Class.INVALID_DURATION_ERROR_MESSAGE);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
