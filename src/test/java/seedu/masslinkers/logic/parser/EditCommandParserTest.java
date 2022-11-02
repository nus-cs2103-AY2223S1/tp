package seedu.masslinkers.logic.parser;

import static seedu.masslinkers.commons.core.Messages.MESSAGE_MISSING_ARGUMENTS;
import static seedu.masslinkers.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INTEREST_DESC_AI;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INTEREST_DESC_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_INTEREST_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.INVALID_TELEGRAM_DESC;
import static seedu.masslinkers.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.TELEGRAM_DESC_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.TELEGRAM_DESC_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_AI;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_INTEREST_SWE;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_AMY;
import static seedu.masslinkers.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_INTEREST;
import static seedu.masslinkers.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.masslinkers.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.masslinkers.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.masslinkers.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.masslinkers.logic.parser.ParserUtil.MESSAGE_UNEXPECTED_CHARACTERS;
import static seedu.masslinkers.logic.parser.ParserUtil.MESSAGE_UNEXPECTED_PREFIX;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_SECOND_STUDENT;
import static seedu.masslinkers.testutil.TypicalIndexes.INDEX_THIRD_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.EditCommand;
import seedu.masslinkers.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.masslinkers.model.interest.Interest;
import seedu.masslinkers.model.student.Email;
import seedu.masslinkers.model.student.Name;
import seedu.masslinkers.model.student.Phone;
import seedu.masslinkers.model.student.Telegram;
import seedu.masslinkers.testutil.EditStudentDescriptorBuilder;

public class EditCommandParserTest {

    private static final String INTEREST_EMPTY = " " + PREFIX_INTEREST;
    private static final String MOD_EMPTY = " " + PREFIX_MOD;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_MISSING_ARGUMENTS, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_UNEXPECTED_CHARACTERS);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_MISSING_ARGUMENTS, EditCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_UNEXPECTED_CHARACTERS);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 o/ string", String.format(MESSAGE_UNEXPECTED_PREFIX, "o/"));
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_TELEGRAM_DESC, Telegram.MESSAGE_CONSTRAINTS); //invalid address
        assertParseFailure(parser, "1" + INVALID_INTEREST_DESC, Interest.MESSAGE_CONSTRAINTS); //invalid interest

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_INTEREST} alone will reset the interests of the {@code Student} being edited,
        // parsing it together with a valid interest results in error
        assertParseFailure(parser, "1" + INTEREST_DESC_AI + INTEREST_DESC_SWE + INTEREST_EMPTY,
                Interest.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INTEREST_DESC_AI + INTEREST_EMPTY + INTEREST_DESC_SWE,
                Interest.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INTEREST_EMPTY + INTEREST_DESC_AI + INTEREST_DESC_SWE,
                Interest.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_TELEGRAM_AMY + VALID_PHONE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + INTEREST_DESC_SWE
                + EMAIL_DESC_AMY + TELEGRAM_DESC_AMY + NAME_DESC_AMY + INTEREST_DESC_AI;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withTelegram(VALID_TELEGRAM_AMY)
                .withInterests(VALID_INTEREST_SWE, VALID_INTEREST_AI).build();
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

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + TELEGRAM_DESC_AMY;
        descriptor = new EditStudentDescriptorBuilder().withTelegram(VALID_TELEGRAM_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interests
        userInput = targetIndex.getOneBased() + INTEREST_DESC_AI;
        descriptor = new EditStudentDescriptorBuilder().withInterests(VALID_INTEREST_AI).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_STUDENT;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY
                + INTEREST_DESC_AI + PHONE_DESC_AMY + TELEGRAM_DESC_AMY + EMAIL_DESC_AMY + INTEREST_DESC_AI
                + PHONE_DESC_BOB + TELEGRAM_DESC_BOB + EMAIL_DESC_BOB + INTEREST_DESC_SWE;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withTelegram(VALID_TELEGRAM_BOB)
                .withInterests(VALID_INTEREST_AI, VALID_INTEREST_SWE)
                .build();
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
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + TELEGRAM_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTelegram(VALID_TELEGRAM_BOB).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetInterests_success() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + INTEREST_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withInterests().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetMods_failure() {
        Index targetIndex = INDEX_THIRD_STUDENT;
        String userInput = targetIndex.getOneBased() + MOD_EMPTY;

        EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder().withMods().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_MISSING_ARGUMENTS, EditCommand.MODS_PASSED_TO_EDIT));
    }
}
