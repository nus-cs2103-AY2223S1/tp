package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EXAMDATEONE;
import static seedu.address.logic.commands.CommandTestUtil.EXAMDATETWO;
import static seedu.address.logic.commands.CommandTestUtil.EXAMDESCRIPTIONONE;
import static seedu.address.logic.commands.CommandTestUtil.EXAMDESCRIPTIONTWO;
import static seedu.address.logic.commands.CommandTestUtil.EXAMMODULEONE;
import static seedu.address.logic.commands.CommandTestUtil.EXAMMODULETWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXAM_DATEONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXAM_DATETWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EXAM_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATEFIVE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATEFOUR;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATEONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATESEVEN;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATESIX;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATETHREE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_FORMAT_EXAM_DATETWO;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAST_EXAMDATEONE;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PAST_EXAMDATETWO;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_EXAMONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_EXAMONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_EXAMONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalExams.EXAMONE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddExamCommand;
import seedu.address.model.exam.Exam;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.ExamBuilder;


public class AddExamCommandParserTest {
    private AddExamCommandParser parser = new AddExamCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Exam expectedExam = new ExamBuilder(EXAMONE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EXAMMODULEONE
                + EXAMDESCRIPTIONONE + EXAMDATEONE,
                new AddExamCommand(expectedExam));

        //no white space
        assertParseSuccess(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + EXAMDATEONE,
                new AddExamCommand(expectedExam));

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + EXAMMODULEONE
                        + EXAMDESCRIPTIONONE + EXAMDATEONE + PREAMBLE_WHITESPACE,
                new AddExamCommand(expectedExam));

        // multiple modules - last module accepted
        assertParseSuccess(parser, EXAMMODULETWO + EXAMMODULEONE
                + EXAMDESCRIPTIONONE + EXAMDATEONE, new AddExamCommand(expectedExam));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONTWO + EXAMDESCRIPTIONONE + EXAMDATEONE, new AddExamCommand(expectedExam));

        //multiple dates - last date accepted
        assertParseSuccess(parser, EXAMMODULEONE + EXAMDESCRIPTIONONE
                + EXAMDATETWO + EXAMDATEONE, new AddExamCommand(expectedExam));
    }

    @Test
    public void parse_compulsoryFieldMissingPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE);

        // missing module prefix
        assertParseFailure(parser, VALID_MODULE_EXAMONE + EXAMDESCRIPTIONONE + EXAMDATEONE, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, EXAMMODULEONE + VALID_DESCRIPTION_EXAMONE + EXAMDATEONE, expectedMessage);


        //missing date prefix
        assertParseFailure(parser, EXAMMODULEONE + EXAMDESCRIPTIONONE + VALID_DATE_EXAMONE, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_MODULE_EXAMONE
                + VALID_DESCRIPTION_EXAMONE + VALID_DATE_EXAMONE, expectedMessage);
    }


    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE);
        // missing module
        assertParseFailure(parser, EXAMDESCRIPTIONONE + EXAMDATEONE, expectedMessage);

        // missing description
        assertParseFailure(parser, EXAMMODULEONE + EXAMDATEONE, expectedMessage);

        //missing date prefix
        assertParseFailure(parser, EXAMMODULEONE + EXAMDESCRIPTIONONE , expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, "", expectedMessage);

    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid module
        assertParseFailure(parser, INVALID_MODULE
                + EXAMDESCRIPTIONONE + EXAMDATEONE, ModuleCode.MODULE_CODE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, EXAMMODULEONE + INVALID_EXAM_DESCRIPTION + EXAMDATEONE,
                ExamDescription.DESCRIPTION_CONSTRAINTS);

        //date not in DD-MM-YYYY format
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATEONE,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATETWO,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATETHREE,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATEFOUR,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATEFIVE,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATESIX,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_FORMAT_EXAM_DATESEVEN,
                ExamDate.DATE_FORMAT_CONSTRAINTS);
        //date is not valid
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_EXAM_DATEONE,
                ExamDate.VALID_DATE_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_EXAM_DATETWO,
                ExamDate.VALID_DATE_CONSTRAINTS);
        //date is a past date
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_PAST_EXAMDATEONE,
                ExamDate.NOT_A_PAST_DATE_CONSTRAINTS);
        assertParseFailure(parser, EXAMMODULEONE
                + EXAMDESCRIPTIONONE + INVALID_PAST_EXAMDATETWO,
                ExamDate.NOT_A_PAST_DATE_CONSTRAINTS);

        // three invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_MODULE
                        + INVALID_EXAM_DESCRIPTION + INVALID_EXAM_DATEONE,
                ModuleCode.MODULE_CODE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY
                        + EXAMMODULEONE + EXAMDESCRIPTIONONE + EXAMDATEONE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExamCommand.MESSAGE_USAGE));
    }
}
