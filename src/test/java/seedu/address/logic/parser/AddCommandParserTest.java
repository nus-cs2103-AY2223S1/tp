package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_CLASS;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.ENTITY_DESC_TUTOR;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ENTITY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_INSTITUTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LEVEL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NEXTOFKIN_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_QUALIFICATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SCHOOL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_SUBJECT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME1_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME2_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.LEVEL_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.NEXTOFKIN_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NEXTOFKIN_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.QUALIFICATION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.QUALIFICATION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.SCHOOL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.SUBJECT_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXTOFKIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalStudents.AMY_STUDENT;
import static seedu.address.testutil.TypicalStudents.BOB_STUDENT;
import static seedu.address.testutil.TypicalTuitionClasses.CHEMISTRY_CLASS;
import static seedu.address.testutil.TypicalTutors.AMY_TUTOR;
import static seedu.address.testutil.TypicalTutors.BOB_TUTOR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.person.tutor.Tutor;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.model.tuitionclass.TuitionClass;
import seedu.address.testutil.StudentBuilder;
import seedu.address.testutil.TuitionClassBuilder;
import seedu.address.testutil.TutorBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Student expectedStudent = new StudentBuilder(BOB_STUDENT).withTags(VALID_TAG_FRIEND).build();

        // 1 of each field
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB,
                AddCommand.of(expectedStudent));

        // multiple names - last name accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB,
                AddCommand.of(expectedStudent));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB,
                AddCommand.of(expectedStudent));

        // multiple emails - last email accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, AddCommand.of(expectedStudent));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB,
                AddCommand.of(expectedStudent));

        // multiple tags - all accepted
        Student expectedStudentMultipleTags = new StudentBuilder(BOB_STUDENT)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();

        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, AddCommand.of(expectedStudentMultipleTags));

        // multiple schools - last school accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_AMY + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, AddCommand.of(expectedStudent));

        // multiple levels - last level accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_AMY + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, AddCommand.of(expectedStudent));

        // multiple nextofkins - last nextofkin accepted
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_AMY
                + NEXTOFKIN_DESC_BOB, AddCommand.of(expectedStudent));


        Tutor expectedTutor = new TutorBuilder(BOB_TUTOR).withTags(VALID_TAG_FRIEND).build();

        // 1 of each field
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                AddCommand.of(expectedTutor));

        // multiple names - last name accepted
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_AMY + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                AddCommand.of(expectedTutor));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_AMY + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                AddCommand.of(expectedTutor));

        // multiple emails - last email accepted
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                AddCommand.of(expectedTutor));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                AddCommand.of(expectedTutor));

        // multiple tags - all accepted
        Tutor expectedTutorMultipleTags = new TutorBuilder(BOB_TUTOR).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                AddCommand.of(expectedTutorMultipleTags));

        // multiple qualifications - last qualification accepted
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_AMY + QUALIFICATION_DESC_BOB
                + INSTITUTION_DESC_BOB, AddCommand.of(expectedTutor));

        // multiple institutions - last institution accepted
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_AMY
                + INSTITUTION_DESC_BOB, AddCommand.of(expectedTutor));


        TuitionClass expectedTuitionClass = new TuitionClassBuilder(CHEMISTRY_CLASS).withTags(VALID_TAG_FRIEND).build();

        // 1 of each field
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND, AddCommand.of(expectedTuitionClass));

        // multiple names - last name accepted
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS2 + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1
                + LEVEL_DESC_CLASS1 + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND,
                AddCommand.of(expectedTuitionClass));

        // multiple subjects - last subject accepted
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS2 + SUBJECT_DESC_CLASS1
                + LEVEL_DESC_CLASS1 + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND,
                AddCommand.of(expectedTuitionClass));

        // multiple levels - last level accepted
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS2
                + LEVEL_DESC_CLASS1 + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND,
                AddCommand.of(expectedTuitionClass));

        // multiple days - last day accepted
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS2 + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND,
                AddCommand.of(expectedTuitionClass));

        // multiple times - last time accepted
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS2 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND,
                AddCommand.of(expectedTuitionClass));

        // multiple tags - all accepted
        TuitionClass expectedTuitionClassMultipleTags = new TuitionClassBuilder(CHEMISTRY_CLASS)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                AddCommand.of(expectedTuitionClassMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags student
        Student expectedStudent = new StudentBuilder(AMY_STUDENT).withTags().build();
        assertParseSuccess(parser, ENTITY_DESC_STUDENT + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + SCHOOL_DESC_AMY + LEVEL_DESC_AMY + NEXTOFKIN_DESC_AMY,
                AddCommand.of(expectedStudent));

        // zero tags tutor
        Tutor expectedTutor = new TutorBuilder(AMY_TUTOR).withTags().build();
        assertParseSuccess(parser, ENTITY_DESC_TUTOR + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + QUALIFICATION_DESC_AMY + INSTITUTION_DESC_AMY,
                AddCommand.of(expectedTutor));

        // zero tags tuition class
        TuitionClass expectedTuitionClass = new TuitionClassBuilder(CHEMISTRY_CLASS).withTags().build();
        assertParseSuccess(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1, AddCommand.of(expectedTuitionClass));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, ENTITY_DESC_STUDENT + PHONE_DESC_BOB + " " + VALID_NAME_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + " " + VALID_PHONE_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + " " + VALID_EMAIL_BOB
                + ADDRESS_DESC_BOB + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB + " "
                + VALID_ADDRESS_BOB + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // empty preamble
        assertParseFailure(parser, NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        // all prefixes missing so invalid preamble
        assertParseFailure(parser, ENTITY_DESC_STUDENT + " " + VALID_NAME_BOB + " " + VALID_PHONE_BOB + " "
                + VALID_EMAIL_BOB + " " + VALID_ADDRESS_BOB + " " + VALID_SCHOOL_BOB + " " + VALID_LEVEL_BOB + " "
                + VALID_NEXTOFKIN_BOB, AddCommand.Entity.MESSAGE_CONSTRAINTS);

        // invalid preamble
        assertParseFailure(parser, INVALID_ENTITY_DESC + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, AddCommand.Entity.MESSAGE_CONSTRAINTS);


        // invalid name
        // student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, Name.MESSAGE_CONSTRAINTS);

        // tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // class
        assertParseFailure(parser, ENTITY_DESC_CLASS + INVALID_NAME_DESC + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND, Name.MESSAGE_CONSTRAINTS);

        // invalid phone
        // student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, Phone.MESSAGE_CONSTRAINTS);

        // tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + INVALID_PHONE_DESC + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        // student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, Email.MESSAGE_CONSTRAINTS);

        // tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + INVALID_EMAIL_DESC
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                Email.MESSAGE_CONSTRAINTS);


        // invalid address
        // student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, Address.MESSAGE_CONSTRAINTS);

        // tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                Address.MESSAGE_CONSTRAINTS);

        // invalid tag
        // student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, Tag.MESSAGE_CONSTRAINTS);

        // tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + INVALID_TAG_DESC + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                Tag.MESSAGE_CONSTRAINTS);

        // class
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid school
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + INVALID_SCHOOL_DESC + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB, School.MESSAGE_CONSTRAINTS);

        // invalid level
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + INVALID_LEVEL_DESC
                + NEXTOFKIN_DESC_BOB, Level.MESSAGE_CONSTRAINTS);

        // invalid next of kin
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + INVALID_NEXTOFKIN_DESC, NextOfKin.MESSAGE_CONSTRAINTS);

        // invalid qualification
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + INVALID_QUALIFICATION_DESC + INSTITUTION_DESC_BOB,
                Qualification.MESSAGE_CONSTRAINTS);

        // invalid institution
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INVALID_INSTITUTION_DESC,
                Institution.MESSAGE_CONSTRAINTS);

        // invalid subject
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + INVALID_SUBJECT_DESC + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND, Subject.MESSAGE_CONSTRAINTS);

        // invalid level
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + INVALID_LEVEL_DESC
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND, Level.MESSAGE_CONSTRAINTS);

        // invalid day
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + INVALID_DAY_DESC + TIME_DESC_CLASS1 + TAG_DESC_FRIEND, Day.MESSAGE_CONSTRAINTS);

        // invalid time
        // not using dash
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + INVALID_TIME1_DESC + TAG_DESC_FRIEND, Time.MESSAGE_CONSTRAINTS);

        // format okay but start time after end time
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + INVALID_TIME2_DESC + TAG_DESC_FRIEND, Time.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, ENTITY_DESC_STUDENT + INVALID_NAME_DESC + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + INVALID_ADDRESS_DESC + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

    }

    @Test
    public void parse_wrongFields_failure() {

        // student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB + QUALIFICATION_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB + INSTITUTION_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB + SUBJECT_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB + DAY_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB + TIME_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        //tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + SCHOOL_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + LEVEL_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + NEXTOFKIN_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + SUBJECT_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + LEVEL_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + DAY_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + TIME_DESC_CLASS1, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        //class
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + PHONE_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + EMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + ADDRESS_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + SCHOOL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                        + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + NEXTOFKIN_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                        + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                        + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + INSTITUTION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        //more than one wrong field
        //student
        assertParseFailure(parser, ENTITY_DESC_STUDENT + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB
                + NEXTOFKIN_DESC_BOB + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));

        //tutor
        assertParseFailure(parser, ENTITY_DESC_TUTOR + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + ADDRESS_DESC_BOB + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + SCHOOL_DESC_BOB + LEVEL_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddCommand.MESSAGE_USAGE));

        //class
        assertParseFailure(parser, ENTITY_DESC_CLASS + NAME_DESC_CLASS1 + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + TIME_DESC_CLASS1 + TAG_DESC_FRIEND + PHONE_DESC_BOB + EMAIL_DESC_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
