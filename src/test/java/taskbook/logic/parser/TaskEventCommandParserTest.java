package taskbook.logic.parser;

import static taskbook.logic.commands.CommandTestUtil.ASSIGN_FROM_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_FROM_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_TO_DESC_AMY;
import static taskbook.logic.commands.CommandTestUtil.ASSIGN_TO_DESC_BOB;
import static taskbook.logic.commands.CommandTestUtil.DATE_DESC_1999;
import static taskbook.logic.commands.CommandTestUtil.DATE_DESC_2022;
import static taskbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_STUDY;
import static taskbook.logic.commands.CommandTestUtil.DESCRIPTION_DESC_WORK;
import static taskbook.logic.commands.CommandTestUtil.INVALID_ASSIGN_FROM_DESC;
import static taskbook.logic.commands.CommandTestUtil.INVALID_ASSIGN_TO_DESC;
import static taskbook.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static taskbook.logic.commands.CommandTestUtil.VALID_DATE_1999;
import static taskbook.logic.commands.CommandTestUtil.VALID_DATE_2022;
import static taskbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_STUDY;
import static taskbook.logic.commands.CommandTestUtil.VALID_DESCRIPTION_WORK;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static taskbook.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static taskbook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.tasks.TaskEventCommand;
import taskbook.logic.parser.tasks.TaskEventCommandParser;
import taskbook.model.person.Name;
import taskbook.model.task.Description;
import taskbook.model.task.enums.Assignment;

public class TaskEventCommandParserTest {

    private TaskEventCommandParser parser = new TaskEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Name expectedNameAmy = new Name(VALID_NAME_AMY);
        Name expectedNameBob = new Name(VALID_NAME_BOB);
        Description expectedDescriptionWork = new Description(VALID_DESCRIPTION_WORK);
        Description expectedDescriptionStudy = new Description(VALID_DESCRIPTION_STUDY);
        Assignment expectedAssignmentFrom = Assignment.FROM;
        Assignment expectedAssignmentTo = Assignment.TO;
        LocalDate expectedDate2022 = LocalDate.parse(VALID_DATE_2022);
        LocalDate expectedDate1999 = LocalDate.parse(VALID_DATE_1999);

        // whitespace only preamble
        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameAmy,
                        expectedDescriptionWork,
                        expectedAssignmentFrom,
                        expectedDate2022));

        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + ASSIGN_TO_DESC_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameAmy,
                        expectedDescriptionWork,
                        expectedAssignmentTo,
                        expectedDate2022));

        // multiple names - last name accepted
        assertParseSuccess(parser,
                ASSIGN_FROM_DESC_AMY + ASSIGN_FROM_DESC_BOB + DESCRIPTION_DESC_WORK + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameBob,
                        expectedDescriptionWork,
                        expectedAssignmentFrom,
                        expectedDate2022));

        assertParseSuccess(parser,
                ASSIGN_TO_DESC_AMY + ASSIGN_TO_DESC_BOB + DESCRIPTION_DESC_WORK + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameBob,
                        expectedDescriptionWork,
                        expectedAssignmentTo,
                        expectedDate2022));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser,
                ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + DESCRIPTION_DESC_STUDY + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameAmy,
                        expectedDescriptionStudy,
                        expectedAssignmentFrom,
                        expectedDate2022));

        assertParseSuccess(parser,
                ASSIGN_TO_DESC_AMY + DESCRIPTION_DESC_WORK + DESCRIPTION_DESC_STUDY + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameAmy,
                        expectedDescriptionStudy,
                        expectedAssignmentTo,
                        expectedDate2022));

        // multiple dates - last date accepted
        assertParseSuccess(parser,
                ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_2022 + DATE_DESC_1999,
                new TaskEventCommand(
                        expectedNameAmy,
                        expectedDescriptionWork,
                        expectedAssignmentFrom,
                        expectedDate1999));

        assertParseSuccess(parser,
                ASSIGN_TO_DESC_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_1999 + DATE_DESC_2022,
                new TaskEventCommand(
                        expectedNameAmy,
                        expectedDescriptionWork,
                        expectedAssignmentTo,
                        expectedDate2022));
    }

    @Test
    public void parse_doubleAssignmentFields_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                TaskEventCommand.MESSAGE_USAGE);

        assertParseFailure(parser,
                ASSIGN_FROM_DESC_AMY + ASSIGN_TO_DESC_BOB + DESCRIPTION_DESC_WORK + DATE_DESC_2022,
                expectedMessage);

        assertParseFailure(parser,
                ASSIGN_TO_DESC_BOB + ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_2022,
                expectedMessage);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                TaskEventCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_AMY + DESCRIPTION_DESC_WORK + DATE_DESC_2022, expectedMessage);

        // missing description prefix
        assertParseFailure(parser, ASSIGN_FROM_DESC_AMY + VALID_DESCRIPTION_WORK + DATE_DESC_2022, expectedMessage);

        // missing date prefix
        assertParseFailure(parser, ASSIGN_FROM_DESC_AMY + DESCRIPTION_DESC_WORK + VALID_DATE_2022, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser,
                INVALID_ASSIGN_FROM_DESC + DESCRIPTION_DESC_WORK + DATE_DESC_2022, Name.MESSAGE_CONSTRAINTS);

        assertParseFailure(parser,
                INVALID_ASSIGN_TO_DESC + DESCRIPTION_DESC_WORK + DATE_DESC_2022, Name.MESSAGE_CONSTRAINTS);
    }
}
