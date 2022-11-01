package seedu.address.logic.parser;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_EXAM_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditExamCommand;
import seedu.address.model.exam.ExamDate;
import seedu.address.model.exam.ExamDescription;
import seedu.address.model.module.ModuleCode;
import seedu.address.testutil.EditExamDescriptorBuilder;

public class EditExamCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditExamCommand.MESSAGE_USAGE);

    private EditExamCommandParser parser = new EditExamCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, EXAMDATEONE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditExamCommand.MESSAGE_NO_FIELDS_PROVIDED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);

        //no prefix
        assertParseFailure(parser, "1" + VALID_DATE_EXAMONE, MESSAGE_INVALID_FORMAT);

    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + EXAMDESCRIPTIONONE, MESSAGE_INVALID_EXAM_INDEX);

        // zero index
        assertParseFailure(parser, "0" + EXAMDESCRIPTIONONE, MESSAGE_INVALID_EXAM_INDEX);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_MODULE, ModuleCode.MODULE_CODE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_EXAM_DESCRIPTION,ExamDescription.DESCRIPTION_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATEONE, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATETWO, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATETHREE, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATEFOUR, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATEFIVE, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATESIX, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATESEVEN, ExamDate.DATE_FORMAT_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_EXAM_DATEONE, ExamDate.VALID_DATE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_EXAM_DATETWO, ExamDate.VALID_DATE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PAST_EXAMDATEONE, ExamDate.NOT_A_PAST_DATE_CONSTRAINTS);
        assertParseFailure(parser, "1" + INVALID_PAST_EXAMDATETWO, ExamDate.NOT_A_PAST_DATE_CONSTRAINTS);

        //two fields
        assertParseFailure(parser, "1" + INVALID_MODULE + EXAMDESCRIPTIONONE, ModuleCode.MODULE_CODE_CONSTRAINTS);
        assertParseFailure(parser, "1" + EXAMDESCRIPTIONONE+ INVALID_FORMAT_EXAM_DATESEVEN, ExamDate.DATE_FORMAT_CONSTRAINTS);

        //invalid module followed by valid description and date
        assertParseFailure(parser, "1" + INVALID_MODULE + EXAMDESCRIPTIONONE+ EXAMDATEONE, ModuleCode.MODULE_CODE_CONSTRAINTS);

        //invalid description followed by valid date and module
        assertParseFailure(parser, "1" + INVALID_EXAM_DESCRIPTION + EXAMMODULEONE + EXAMDATEONE, ExamDescription.DESCRIPTION_CONSTRAINTS);

        //invalid date followed by valid module  and description
        assertParseFailure(parser, "1" + INVALID_FORMAT_EXAM_DATESEVEN + EXAMMODULEONE + EXAMDESCRIPTIONONE, ExamDate.DATE_FORMAT_CONSTRAINTS);
        // valid description and date followed by invalid module.
        assertParseFailure(parser, "1" + EXAMDESCRIPTIONONE + EXAMDATEONE + INVALID_MODULE, ModuleCode.MODULE_CODE_CONSTRAINTS);
       //valid module and date followed by invalid description
        assertParseFailure(parser, "1" + EXAMMODULEONE + EXAMDATEONE+ INVALID_EXAM_DESCRIPTION , ExamDescription.DESCRIPTION_CONSTRAINTS);
       //valid module and description followed by invalid date
        assertParseFailure(parser, "1" +EXAMMODULEONE + EXAMDESCRIPTIONONE+ INVALID_FORMAT_EXAM_DATESEVEN, ExamDate.DATE_FORMAT_CONSTRAINTS);
        //valid module, then invalid description then valid date
        assertParseFailure(parser, "1" +EXAMMODULEONE + INVALID_EXAM_DESCRIPTION+ EXAMDATEONE, ExamDescription.DESCRIPTION_CONSTRAINTS);

    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + EXAMDESCRIPTIONONE + EXAMDATEONE + EXAMMODULEONE;

        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withDescription(VALID_DESCRIPTION_EXAMONE)
                .withDate(VALID_DATE_EXAMONE).withModule(VALID_MODULE_EXAMONE).build();
        EditExamCommand expectedCommand = new EditExamCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + EXAMDESCRIPTIONONE + EXAMDATEONE;

        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withDescription(VALID_DESCRIPTION_EXAMONE)
                .withDate(VALID_DATE_EXAMONE).build();
        EditExamCommand expectedCommand = new EditExamCommand(targetIndex, descriptor);
        //make changesto the equals method of descriptor not sure if is correct.
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_someFieldsSpecifiedTestTwo_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + EXAMMODULEONE + EXAMDATEONE;

        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withModule(VALID_MODULE_EXAMONE)
                .withDate(VALID_DATE_EXAMONE).build();
        EditExamCommand expectedCommand = new EditExamCommand(targetIndex, descriptor);
        //make changesto the equals method of descriptor not sure if is correct.
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // date
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + EXAMDATEONE;
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder()
                .withDate(VALID_DATE_EXAMONE).build();
        EditExamCommand expectedCommand = new EditExamCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
         userInput = targetIndex.getOneBased() + EXAMDESCRIPTIONONE;
         descriptor = new EditExamDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_EXAMONE).build();
         expectedCommand = new EditExamCommand(targetIndex, descriptor);
         assertParseSuccess(parser, userInput, expectedCommand);

        // module
        userInput = targetIndex.getOneBased() + EXAMMODULEONE;
        descriptor = new EditExamDescriptorBuilder()
                .withModule(VALID_MODULE_EXAMONE).build();
        expectedCommand = new EditExamCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased()
                + EXAMMODULETWO + EXAMDATETWO + EXAMDESCRIPTIONTWO
                + EXAMMODULEONE + EXAMDATEONE + EXAMDESCRIPTIONONE;


        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withModule(VALID_MODULE_EXAMONE)
                .withDate(VALID_DATE_EXAMONE).withDescription(VALID_DESCRIPTION_EXAMONE).build();
        EditExamCommand expectedCommand = new EditExamCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + INVALID_EXAM_DATETWO + EXAMDATETWO;
        EditExamCommand.EditExamDescriptor descriptor = new EditExamDescriptorBuilder().withDate(VALID_DATE_EXAMTWO).build();
        EditExamCommand expectedCommand = new EditExamCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + EXAMDATEONE + INVALID_EXAM_DESCRIPTION + EXAMMODULETWO
                + EXAMDESCRIPTIONTWO;
        descriptor = new EditExamDescriptorBuilder().withDate(VALID_DATE_EXAMONE)
                .withDescription(VALID_DESCRIPTION_EXAMTWO).withModule(VALID_MODULE_EXAMTWO).build();

        expectedCommand = new EditExamCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
