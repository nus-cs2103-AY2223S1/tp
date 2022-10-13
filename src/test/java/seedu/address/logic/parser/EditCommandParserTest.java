package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.DAY_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INSTITUTION_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DAY_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
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
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.TIME_DESC_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DAY_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ENDTIME_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTITUTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INSTITUTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LEVEL_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXTOFKIN_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NEXTOFKIN_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUALIFICATION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SCHOOL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTTIME_CLASS2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_CLASS1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.address.logic.commands.EditCommand.EditTutorDescriptor;
import seedu.address.logic.commands.EditCommand.EditTuitionClassDescriptor;
import seedu.address.model.Model;
import seedu.address.model.level.Level;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.NextOfKin;
import seedu.address.model.person.student.School;
import seedu.address.model.person.tutor.Institution;
import seedu.address.model.person.tutor.Qualification;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuitionclass.Day;
import seedu.address.model.tuitionclass.Subject;
import seedu.address.model.tuitionclass.Time;
import seedu.address.testutil.EditStudentDescriptorBuilder;
import seedu.address.testutil.EditTuitionClassDescriptorBuilder;
import seedu.address.testutil.EditTutorDescriptorBuilder;

public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT_STUDENT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(Model.ListType.STUDENT_LIST));

    private static final String MESSAGE_INVALID_FORMAT_TUTOR =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(Model.ListType.TUTOR_LIST));

    private static final String MESSAGE_INVALID_FORMAT_CLASS =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.getMessageUsage(Model.ListType.TUITIONCLASS_LIST));

    private EditCommandParser parserWhenStudent = new EditCommandParser(Model.ListType.STUDENT_LIST);
    private EditCommandParser parserWhenTutor = new EditCommandParser(Model.ListType.TUTOR_LIST);
    private EditCommandParser parserWhenClass = new EditCommandParser(Model.ListType.TUITIONCLASS_LIST);

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parserWhenStudent, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT_STUDENT);
        assertParseFailure(parserWhenTutor, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT_TUTOR);
        assertParseFailure(parserWhenClass, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT_CLASS);

        // no field specified
        assertParseFailure(parserWhenStudent, "1", EditCommand.MESSAGE_NOT_EDITED);
        assertParseFailure(parserWhenTutor, "1", EditCommand.MESSAGE_NOT_EDITED);
        assertParseFailure(parserWhenClass, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parserWhenStudent, "", MESSAGE_INVALID_FORMAT_STUDENT);
        assertParseFailure(parserWhenTutor, "", MESSAGE_INVALID_FORMAT_TUTOR);
        assertParseFailure(parserWhenClass, "", MESSAGE_INVALID_FORMAT_CLASS);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parserWhenStudent, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT_STUDENT);
        assertParseFailure(parserWhenTutor, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT_TUTOR);
        assertParseFailure(parserWhenClass, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT_CLASS);

        // zero index
        assertParseFailure(parserWhenStudent, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT_STUDENT);
        assertParseFailure(parserWhenTutor, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT_TUTOR);
        assertParseFailure(parserWhenClass, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT_CLASS);

        // invalid arguments being parsed as preamble
        assertParseFailure(parserWhenStudent, "1 some random string", MESSAGE_INVALID_FORMAT_STUDENT);
        assertParseFailure(parserWhenTutor, "1 some random string", MESSAGE_INVALID_FORMAT_TUTOR);
        assertParseFailure(parserWhenClass, "1 some random string", MESSAGE_INVALID_FORMAT_CLASS);

        // invalid prefix being parsed as preamble
        assertParseFailure(parserWhenStudent, "1 invalidprefix/ string", MESSAGE_INVALID_FORMAT_STUDENT);
        assertParseFailure(parserWhenTutor, "1 invalidprefix/ string", MESSAGE_INVALID_FORMAT_TUTOR);
        assertParseFailure(parserWhenClass, "1 invalidprefix/ string", MESSAGE_INVALID_FORMAT_CLASS);
    }

    @Test
    public void parse_invalidValue_failure() {
        // shared across all entities
        assertParseFailure(parserWhenStudent,
                "1" + INVALID_NAME_DESC, seedu.address.model.person.Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parserWhenTutor,
                "1" + INVALID_NAME_DESC, seedu.address.model.person.Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenClass,
                "1" + INVALID_NAME_DESC, seedu.address.model.tuitionclass.Name.MESSAGE_CONSTRAINTS);


        assertParseFailure(parserWhenStudent, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag
        assertParseFailure(parserWhenTutor, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenClass, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parserWhenStudent,
                "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenStudent,
                "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenStudent,
                "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenTutor,
                "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenTutor,
                "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenTutor,
                "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenClass,
                "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenClass,
                "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenClass,
                "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // person stuff
        assertParseFailure(parserWhenStudent, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parserWhenTutor, "1" + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        assertParseFailure(parserWhenStudent, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parserWhenTutor, "1" + INVALID_EMAIL_DESC, Email.MESSAGE_CONSTRAINTS);

        assertParseFailure(parserWhenStudent,
                "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parserWhenTutor, "1" + INVALID_ADDRESS_DESC, Address.MESSAGE_CONSTRAINTS);

        // student stuff
        assertParseFailure(parserWhenStudent, "1" + INVALID_SCHOOL_DESC, School.MESSAGE_CONSTRAINTS); // invalid school

        assertParseFailure(parserWhenStudent, "1" + INVALID_LEVEL_DESC, Level.MESSAGE_CONSTRAINTS); // invalid level

        assertParseFailure(parserWhenStudent,
                "1" + INVALID_NEXTOFKIN_DESC, NextOfKin.MESSAGE_CONSTRAINTS); // invalid nextofkin

        // tutor stuff
        assertParseFailure(parserWhenTutor,
                "1" + INVALID_QUALIFICATION_DESC, Qualification.MESSAGE_CONSTRAINTS); // invalid qualification

        assertParseFailure(parserWhenTutor,
                "1" + INVALID_INSTITUTION_DESC, Institution.MESSAGE_CONSTRAINTS); // invalid institution

        // tuition class stuff
        assertParseFailure(parserWhenClass, "1" + INVALID_SUBJECT_DESC, Subject.MESSAGE_CONSTRAINTS); // invalid subject

        assertParseFailure(parserWhenClass, "1" + INVALID_LEVEL_DESC, Level.MESSAGE_CONSTRAINTS); // invalid level

        assertParseFailure(parserWhenClass, "1" + INVALID_DAY_DESC, Day.MESSAGE_CONSTRAINTS); // invalid day

        assertParseFailure(parserWhenClass, "1" + INVALID_TIME1_DESC, Time.MESSAGE_CONSTRAINTS); // invalid time
        assertParseFailure(parserWhenClass, "1" + INVALID_TIME2_DESC, Time.MESSAGE_CONSTRAINTS);


        // invalid phone followed by valid email
        assertParseFailure(parserWhenStudent, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenTutor, "1" + INVALID_PHONE_DESC + EMAIL_DESC_AMY, Phone.MESSAGE_CONSTRAINTS);

        // invalid subject followed by valid time
        assertParseFailure(parserWhenClass, "1" + INVALID_SUBJECT_DESC + TIME_DESC_CLASS1, Subject.MESSAGE_CONSTRAINTS);

        // valid phone followed by invalid phone. The test case for invalid phone followed by valid phone
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parserWhenStudent, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenTutor, "1" + PHONE_DESC_BOB + INVALID_PHONE_DESC, Phone.MESSAGE_CONSTRAINTS);

        // valid subject followed by valid subject.
        assertParseFailure(parserWhenClass,
                "1" + SUBJECT_DESC_CLASS1 + INVALID_SUBJECT_DESC, Subject.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parserWhenStudent,
                "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY + VALID_SCHOOL_AMY
                        + INVALID_LEVEL_DESC + VALID_NEXTOFKIN_AMY,
                seedu.address.model.person.Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenTutor,
                "1" + INVALID_NAME_DESC + INVALID_EMAIL_DESC + VALID_ADDRESS_AMY + VALID_PHONE_AMY
                        + INVALID_QUALIFICATION_DESC + VALID_INSTITUTION_AMY,
                seedu.address.model.person.Name.MESSAGE_CONSTRAINTS);
        assertParseFailure(parserWhenClass,
                "1" + INVALID_NAME_DESC + INVALID_SUBJECT_DESC + VALID_LEVEL_CLASS1 + VALID_DAY_CLASS1
                        + INVALID_TIME1_DESC,
                seedu.address.model.tuitionclass.Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput;
        EditCommand expectedCommand;

        // student
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY
                + TAG_DESC_FRIEND + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withSchool(VALID_SCHOOL_BOB).withLevel(VALID_LEVEL_BOB)
                .withNextOfKin(VALID_NEXTOFKIN_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        // tutor
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + TAG_DESC_HUSBAND + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY
                + TAG_DESC_FRIEND + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB;

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withQualification(VALID_QUALIFICATION_BOB)
                .withInstitution(VALID_INSTITUTION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // class
        userInput = targetIndex.getOneBased() + SUBJECT_DESC_CLASS1 + TAG_DESC_HUSBAND + LEVEL_DESC_CLASS2
                + DAY_DESC_CLASS2 + NAME_DESC_CLASS1 + TAG_DESC_FRIEND + TIME_DESC_CLASS1;

        EditTuitionClassDescriptor editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder()
                .withName(VALID_NAME_CLASS1).withSubject(VALID_SUBJECT_CLASS1).withLevel(VALID_LEVEL_CLASS2)
                .withDay(VALID_DAY_CLASS2).withTime(VALID_STARTTIME_CLASS1, VALID_ENDTIME_CLASS1)
                .withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput;
        EditCommand expectedCommand;

        // student
        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY + SCHOOL_DESC_BOB;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_AMY).withSchool(VALID_SCHOOL_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        // tutor
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY + NAME_DESC_AMY + QUALIFICATION_DESC_BOB;

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY).withQualification(VALID_QUALIFICATION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // class
        userInput = targetIndex.getOneBased() + SUBJECT_DESC_CLASS1 + DAY_DESC_CLASS2 + TIME_DESC_CLASS1;

        EditTuitionClassDescriptor editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder()
                .withSubject(VALID_SUBJECT_CLASS1).withDay(VALID_DAY_CLASS2)
                .withTime(VALID_STARTTIME_CLASS1, VALID_ENDTIME_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput;
        EditCommand expectedCommand;
        EditStudentDescriptor editStudentDescriptor;
        EditTutorDescriptor editTutorDescriptor;
        EditTuitionClassDescriptor editTuitionClassDescriptor;



        // name
        userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + NAME_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + NAME_DESC_CLASS1;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withName(VALID_NAME_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + PHONE_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withPhone(VALID_PHONE_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + EMAIL_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withEmail(VALID_EMAIL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + EMAIL_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withEmail(VALID_EMAIL_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + ADDRESS_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withAddress(VALID_ADDRESS_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + ADDRESS_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // school
        userInput = targetIndex.getOneBased() + SCHOOL_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withSchool(VALID_SCHOOL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        // level
        userInput = targetIndex.getOneBased() + LEVEL_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withLevel(VALID_LEVEL_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + LEVEL_DESC_CLASS1;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withLevel(VALID_LEVEL_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);

        // next of kin
        userInput = targetIndex.getOneBased() + NEXTOFKIN_DESC_AMY;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withNextOfKin(VALID_NEXTOFKIN_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        // qualification
        userInput = targetIndex.getOneBased() + QUALIFICATION_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withQualification(VALID_QUALIFICATION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        //institution
        userInput = targetIndex.getOneBased() + INSTITUTION_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withInstitution(VALID_INSTITUTION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // subject
        userInput = targetIndex.getOneBased() + SUBJECT_DESC_CLASS1;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withSubject(VALID_SUBJECT_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);

        // day
        userInput = targetIndex.getOneBased() + DAY_DESC_CLASS1;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withDay(VALID_DAY_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);

        // time
        userInput = targetIndex.getOneBased() + TIME_DESC_CLASS1;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder()
                .withTime(VALID_STARTTIME_CLASS1, VALID_ENDTIME_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PERSON;
        EditCommand expectedCommand;
        String userInput;

        // student

        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY + ADDRESS_DESC_AMY
                + NAME_DESC_AMY + SCHOOL_DESC_BOB + LEVEL_DESC_BOB + NEXTOFKIN_DESC_BOB
                + PHONE_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + NAME_DESC_BOB + SCHOOL_DESC_AMY + LEVEL_DESC_AMY
                + NEXTOFKIN_DESC_AMY;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withName(VALID_NAME_BOB)
                .withSchool(VALID_SCHOOL_AMY).withLevel(VALID_LEVEL_AMY).withNextOfKin(VALID_NEXTOFKIN_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        // tutor

        userInput = targetIndex.getOneBased() + PHONE_DESC_BOB + EMAIL_DESC_AMY + ADDRESS_DESC_AMY + NAME_DESC_AMY
                + QUALIFICATION_DESC_BOB + INSTITUTION_DESC_BOB
                + PHONE_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + NAME_DESC_BOB
                + QUALIFICATION_DESC_AMY + INSTITUTION_DESC_AMY;

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                .withQualification(VALID_QUALIFICATION_AMY).withInstitution(VALID_INSTITUTION_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // tuition class

        userInput = targetIndex.getOneBased() + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS2
                + DAY_DESC_CLASS2 + NAME_DESC_CLASS1 + TIME_DESC_CLASS1
                + SUBJECT_DESC_CLASS1 + LEVEL_DESC_CLASS1
                + DAY_DESC_CLASS1 + NAME_DESC_CLASS2 + TIME_DESC_CLASS2;

        EditTuitionClassDescriptor editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder()
                .withName(VALID_NAME_CLASS2).withSubject(VALID_SUBJECT_CLASS1).withLevel(VALID_LEVEL_CLASS1)
                .withDay(VALID_DAY_CLASS1).withTime(VALID_STARTTIME_CLASS2, VALID_ENDTIME_CLASS2).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput;
        EditCommand expectedCommand;

        // student
        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_AMY;
        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + INVALID_PHONE_DESC + PHONE_DESC_AMY + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB;
        editStudentDescriptor = new EditStudentDescriptorBuilder().withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        // tutor
        userInput = targetIndex.getOneBased() + INVALID_QUALIFICATION_DESC + QUALIFICATION_DESC_BOB;
        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptorBuilder()
                .withQualification(VALID_QUALIFICATION_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + INVALID_QUALIFICATION_DESC + QUALIFICATION_DESC_BOB + ADDRESS_DESC_BOB
                + EMAIL_DESC_BOB;
        editTutorDescriptor = new EditTutorDescriptorBuilder().withQualification(VALID_QUALIFICATION_BOB)
                .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        // class
        userInput = targetIndex.getOneBased() + INVALID_DAY_DESC + DAY_DESC_CLASS1;
        EditTuitionClassDescriptor editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withDay(VALID_DAY_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);

        userInput = targetIndex.getOneBased() + INVALID_DAY_DESC + DAY_DESC_CLASS1 + SUBJECT_DESC_CLASS1
                + NAME_DESC_CLASS1;
        editTuitionClassDescriptor = new EditTuitionClassDescriptorBuilder().withDay(VALID_DAY_CLASS1)
                .withSubject(VALID_SUBJECT_CLASS1).withName(VALID_NAME_CLASS1).build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;
        EditCommand expectedCommand;

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptorBuilder().withTags().build();
        expectedCommand = new EditCommand(targetIndex, editStudentDescriptor);

        assertParseSuccess(parserWhenStudent, userInput, expectedCommand);

        EditTutorDescriptor editTutorDescriptor = new EditTutorDescriptorBuilder().withTags().build();
        expectedCommand = new EditCommand(targetIndex, editTutorDescriptor);

        assertParseSuccess(parserWhenTutor, userInput, expectedCommand);

        EditTuitionClassDescriptor editTuitionClassDescriptor =
                new EditTuitionClassDescriptorBuilder().withTags().build();
        expectedCommand = new EditCommand(targetIndex, editTuitionClassDescriptor);

        assertParseSuccess(parserWhenClass, userInput, expectedCommand);
    }
}
