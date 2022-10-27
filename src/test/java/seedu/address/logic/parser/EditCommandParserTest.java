package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.CAP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.CAP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GENDER_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.GRADUATION_DATE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.GRADUATION_DATE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_CAP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GENDER_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_GRADUATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_JOB_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MAJOR_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_UNIVERSITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.JOB_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_ID_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.JOB_TITLE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.MAJOR_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_KIV;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_REJECTED;
import static seedu.address.logic.commands.CommandTestUtil.UNIVERSITY_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.UNIVERSITY_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_VALUE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADUATION_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAJOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAXIMUM_CAP_VALUE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MAXIMUM_CAP_VALUE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_KIV;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_REJECTED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UNIVERSITY_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.job.Id;
import seedu.address.model.job.Title;
import seedu.address.model.person.Address;
import seedu.address.model.person.Cap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.GraduationDate;
import seedu.address.model.person.Major;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.University;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;

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
        assertParseFailure(parser, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1" + INVALID_GENDER_DESC, Gender.MESSAGE_CONSTRAINTS); // invalid gender
        assertParseFailure(parser, "1" + INVALID_GRADUATION_DESC,
                GraduationDate.MESSAGE_CONSTRAINTS); // invalid graduation date
        assertParseFailure(parser, "1" + INVALID_UNIVERSITY_DESC, University.MESSAGE_CONSTRAINTS); // invalid university
        assertParseFailure(parser, "1" + INVALID_MAJOR_DESC, Major.MESSAGE_CONSTRAINTS); // invalid major
        assertParseFailure(parser, "1" + INVALID_CAP_DESC, Cap.MESSAGE_CONSTRAINTS); // invalid CAP
        assertParseFailure(parser, "1" + INVALID_JOB_ID_DESC, Id.MESSAGE_CONSTRAINTS); // invalid job ID
        assertParseFailure(parser, "1" + INVALID_JOB_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid job title
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_REJECTED + TAG_DESC_KIV + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_REJECTED + TAG_EMPTY + TAG_DESC_KIV, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_REJECTED + TAG_DESC_KIV, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1"
                + INVALID_NAME_DESC
                + INVALID_EMAIL_DESC
                + VALID_ADDRESS_AMY
                + VALID_PHONE_AMY
                + VALID_GENDER_AMY
                + VALID_GRADUATION_DATE_AMY
                + VALID_UNIVERSITY_AMY
                + VALID_MAJOR_AMY
                + VALID_CAP_AMY
                + VALID_JOB_ID_AMY
                + VALID_JOB_TITLE_AMY,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased()
                + PHONE_DESC_BOB
                + TAG_DESC_KIV
                + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_AMY
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + CAP_DESC_BOB
                + NAME_DESC_AMY
                + JOB_ID_DESC_AMY
                + JOB_TITLE_DESC_AMY
                + TAG_DESC_REJECTED;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_AMY)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withId(VALID_JOB_ID_AMY)
                .withTitle(VALID_JOB_TITLE_AMY)
                .withTags(VALID_TAG_KIV, VALID_TAG_REJECTED)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {

        Index targetIndex = INDEX_THIRD_PERSON;

        // name
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // gender
        userInput = targetIndex.getOneBased() + GENDER_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGender(VALID_GENDER_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // graduation date
        userInput = targetIndex.getOneBased() + GRADUATION_DATE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withGraduationDate(VALID_GRADUATION_DATE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // university
        userInput = targetIndex.getOneBased() + UNIVERSITY_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withUniversity(VALID_UNIVERSITY_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // major
        userInput = targetIndex.getOneBased() + MAJOR_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withMajor(VALID_MAJOR_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // CAP
        userInput = targetIndex.getOneBased() + CAP_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder()
            .withCap(VALID_CAP_VALUE_AMY, VALID_MAXIMUM_CAP_VALUE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // job ID
        userInput = targetIndex.getOneBased() + JOB_ID_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withId(VALID_JOB_ID_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // job title
        userInput = targetIndex.getOneBased() + JOB_TITLE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withTitle(VALID_JOB_TITLE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_REJECTED;
        descriptor = new EditPersonDescriptorBuilder().withTags(VALID_TAG_REJECTED).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased()
                + PHONE_DESC_AMY
                + ADDRESS_DESC_AMY
                + EMAIL_DESC_AMY
                + GENDER_DESC_AMY
                + GRADUATION_DATE_DESC_AMY
                + UNIVERSITY_DESC_AMY
                + MAJOR_DESC_AMY
                + CAP_DESC_AMY
                + JOB_ID_DESC_AMY
                + JOB_TITLE_DESC_AMY
                + TAG_DESC_REJECTED
                + PHONE_DESC_BOB
                + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + CAP_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + TAG_DESC_KIV;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withId(VALID_JOB_ID_BOB)
                .withTitle(VALID_JOB_TITLE_BOB)
                .withTags(VALID_TAG_REJECTED, VALID_TAG_KIV)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_BOB;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased()
                + EMAIL_DESC_BOB
                + INVALID_PHONE_DESC
                + ADDRESS_DESC_BOB
                + GENDER_DESC_BOB
                + GRADUATION_DATE_DESC_BOB
                + UNIVERSITY_DESC_BOB
                + MAJOR_DESC_BOB
                + CAP_DESC_BOB
                + JOB_ID_DESC_BOB
                + JOB_TITLE_DESC_BOB
                + PHONE_DESC_BOB;
        descriptor = new EditPersonDescriptorBuilder().withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withGender(VALID_GENDER_BOB)
                .withGraduationDate(VALID_GRADUATION_DATE_BOB)
                .withUniversity(VALID_UNIVERSITY_BOB)
                .withMajor(VALID_MAJOR_BOB)
                .withCap(VALID_CAP_VALUE_BOB, VALID_MAXIMUM_CAP_VALUE_BOB)
                .withId(VALID_JOB_ID_BOB)
                .withTitle(VALID_JOB_TITLE_BOB)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
