package seedu.uninurse.logic.parser;

import static seedu.uninurse.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.uninurse.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.uninurse.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.uninurse.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.uninurse.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.uninurse.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.EditPatientCommand;
import seedu.uninurse.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.uninurse.model.person.Address;
import seedu.uninurse.model.person.Email;
import seedu.uninurse.model.person.Name;
import seedu.uninurse.model.person.Phone;
import seedu.uninurse.testutil.EditPatientDescriptorBuilder;

public class EditPatientCommandParserTest {
    private EditPatientCommandParser parser = new EditPatientCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_INDEX);

        // no field specified
        assertParseFailure(parser, "1", EditPatientCommand.MESSAGE_FAILURE);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY
                        + VALID_PHONE_AMY, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + NAME_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditPatientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditPatientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPatientDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditPatientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY
                + PHONE_DESC_AMY + ADDRESS_DESC_AMY + EMAIL_DESC_AMY + PHONE_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB;

        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditPatientCommand expectedCommand = new EditPatientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB + INVALID_PHONE_DESC + ADDRESS_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPatientDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditPatientCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
