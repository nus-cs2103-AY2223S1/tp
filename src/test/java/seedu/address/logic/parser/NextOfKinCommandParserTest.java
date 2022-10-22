package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RELATIONSHIP_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.QUALIFICATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.RELATIONSHIP_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.RELATIONSHIP_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RELATIONSHIP_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalNextOfKins.AMY_NEXTOFKIN;
import static seedu.address.testutil.TypicalNextOfKins.BOB_NEXTOFKIN;
import static seedu.address.testutil.TypicalStudents.AMY_STUDENT;
import static seedu.address.testutil.TypicalStudents.BOB_STUDENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.NextOfKinCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.nextofkin.NextOfKin;
import seedu.address.model.person.nextofkin.Relationship;
import seedu.address.model.person.student.Student;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.NextOfKinBuilder;
import seedu.address.testutil.StudentBuilder;

public class NextOfKinCommandParserTest {
    private NextOfKinCommandParser parser = new NextOfKinCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        NextOfKin expectedNextOfKin = new NextOfKinBuilder(BOB_NEXTOFKIN).withTags(VALID_TAG_FRIEND).build();
        Index targetIndex = INDEX_FIRST_PERSON;

        // 1 of each field
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));

        // multiple names - last name accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));

        // multiple emails - last email accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));

        // multiple tags - all accepted
        NextOfKin expectedNextOfKinMultipleTags = new NextOfKinBuilder(BOB_NEXTOFKIN)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKinMultipleTags));

        // multiple relationships - last relationship accepted
        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_AMY + RELATIONSHIP_DESC_BOB,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));

    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        NextOfKin expectedNextOfKin = new NextOfKinBuilder(AMY_NEXTOFKIN).withTags().build();
        Index targetIndex = INDEX_SECOND_PERSON;

        assertParseSuccess(parser, targetIndex.getOneBased() + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                        + ADDRESS_DESC_AMY + RELATIONSHIP_DESC_AMY,
                new NextOfKinCommand(targetIndex, expectedNextOfKin));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE);
        Index targetIndex = INDEX_FIRST_PERSON;

        // missing name prefix
        assertParseFailure(parser, targetIndex.getOneBased() + " " + VALID_NAME_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + " " + VALID_PHONE_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                expectedMessage);

        // missing email prefix
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + " " + VALID_EMAIL_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                expectedMessage);

        // missing address prefix
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + " " + VALID_ADDRESS_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                expectedMessage);

        // missing relationship prefix
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + TAG_DESC_FRIEND + ADDRESS_DESC_BOB + " " + VALID_RELATIONSHIP_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;

        // invalid name
        assertParseFailure(parser, targetIndex.getOneBased() + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + INVALID_ADDRESS_DESC + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + INVALID_TAG_DESC + RELATIONSHIP_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // invalid relationship
        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + INVALID_RELATIONSHIP_DESC,
                Relationship.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string" + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 invalidprefix/ string" + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_wrongFields_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + SCHOOL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + LEVEL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + QUALIFICATION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + INSTITUTION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + SUBJECT_DESC_CLASS1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + DAY_DESC_CLASS1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));

        assertParseFailure(parser, targetIndex.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                        + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + RELATIONSHIP_DESC_BOB + TIME_DESC_CLASS1,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, NextOfKinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_noFields_success() {
        Index targetIndex = INDEX_THIRD_PERSON;

        assertParseSuccess(parser, targetIndex.getOneBased() + "",
                new NextOfKinCommand(targetIndex));
    }
}