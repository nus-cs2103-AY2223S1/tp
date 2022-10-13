package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.COMPANY_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.CONTACT_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.DATE_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_COMPANY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CONTACT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_POSITION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.POSITION_DESC_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_GOOGLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_POSITION_GOOGLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_APPLICATION;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_APPLICATION;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.address.model.application.Company;
import seedu.address.model.application.Contact;
import seedu.address.model.application.Date;
import seedu.address.model.application.Email;
import seedu.address.model.application.Position;
import seedu.address.testutil.EditApplicationDescriptorBuilder;

public class EditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_COMPANY_FACEBOOK, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + COMPANY_DESC_FACEBOOK, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + COMPANY_DESC_FACEBOOK, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC, Company.MESSAGE_CONSTRAINTS); // invalid company
        assertParseFailure(parser, "1" + INVALID_CONTACT_DESC, Contact.MESSAGE_CONSTRAINTS); // invalid contact
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, Date.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_POSITION_DESC, Position.MESSAGE_CONSTRAINTS); // invalid position

        // invalid contact followed by valid email
        assertParseFailure(parser, "1" + INVALID_CONTACT_DESC + EMAIL_DESC_FACEBOOK, Contact.MESSAGE_CONSTRAINTS);

        // valid contact followed by invalid contact. The test case for invalid contact followed by valid contact
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + CONTACT_DESC_GOOGLE + INVALID_CONTACT_DESC, Contact.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_COMPANY_DESC + INVALID_EMAIL_DESC + VALID_DATE_FACEBOOK
                + VALID_CONTACT_FACEBOOK + VALID_POSITION_FACEBOOK, Company.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + CONTACT_DESC_GOOGLE + POSITION_DESC_GOOGLE
                + EMAIL_DESC_FACEBOOK + DATE_DESC_FACEBOOK + COMPANY_DESC_FACEBOOK;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_FACEBOOK).withContact(VALID_CONTACT_GOOGLE)
                .withEmail(VALID_EMAIL_FACEBOOK).withDate(VALID_DATE_FACEBOOK)
                .withPosition(VALID_POSITION_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + CONTACT_DESC_GOOGLE + EMAIL_DESC_FACEBOOK;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withContact(VALID_CONTACT_GOOGLE)
                .withEmail(VALID_EMAIL_FACEBOOK).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // company
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + COMPANY_DESC_FACEBOOK;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withCompany(VALID_COMPANY_FACEBOOK).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // contact
        userInput = targetIndex.getOneBased() + CONTACT_DESC_FACEBOOK;
        descriptor = new EditApplicationDescriptorBuilder().withContact(VALID_CONTACT_FACEBOOK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_FACEBOOK;
        descriptor = new EditApplicationDescriptorBuilder().withDate(VALID_DATE_FACEBOOK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_FACEBOOK;
        descriptor = new EditApplicationDescriptorBuilder().withEmail(VALID_EMAIL_FACEBOOK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // position
        userInput = targetIndex.getOneBased() + POSITION_DESC_FACEBOOK;
        descriptor = new EditApplicationDescriptorBuilder().withPosition(VALID_POSITION_FACEBOOK).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + CONTACT_DESC_FACEBOOK + DATE_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + POSITION_DESC_FACEBOOK + CONTACT_DESC_FACEBOOK + DATE_DESC_FACEBOOK
                + EMAIL_DESC_FACEBOOK + POSITION_DESC_FACEBOOK + CONTACT_DESC_GOOGLE + DATE_DESC_GOOGLE
                + EMAIL_DESC_GOOGLE + POSITION_DESC_GOOGLE;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withContact(VALID_CONTACT_GOOGLE)
                .withEmail(VALID_EMAIL_GOOGLE).withDate(VALID_DATE_GOOGLE).withPosition(VALID_POSITION_GOOGLE)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_CONTACT_DESC + CONTACT_DESC_GOOGLE;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withContact(VALID_CONTACT_GOOGLE).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EMAIL_DESC_GOOGLE + INVALID_CONTACT_DESC + DATE_DESC_GOOGLE
                + CONTACT_DESC_GOOGLE;
        descriptor = new EditApplicationDescriptorBuilder().withContact(VALID_CONTACT_GOOGLE)
                .withEmail(VALID_EMAIL_GOOGLE).withDate(VALID_DATE_GOOGLE).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
