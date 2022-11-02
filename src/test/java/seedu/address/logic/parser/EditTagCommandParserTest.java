package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_TASK;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditTagCommand;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.testutil.DeadlineTagBuilder;
import seedu.address.testutil.PriorityTagBuilder;


public class EditTagCommandParserTest {
    private EditTagCommandParser parser = new EditTagCommandParser();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    @Test
    public void parse_allFieldsPresent_success() {
        PriorityTag expectedPriorityTag = new PriorityTagBuilder().withStatus("MEDIUM").build();
        DeadlineTag expectedDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("25-12-2022", dateTimeFormatter)).build();
        Index expectedIndex = INDEX_SECOND_TASK;
        String allFieldsPresentArgument = "2" + " " + PREFIX_PRIORITY_STATUS + "MEDIUM"
                + " " + PREFIX_DEADLINE + "25-12-2022";
        String allFieldsPresentWithWhitespace = "           2" + " " + PREFIX_PRIORITY_STATUS + "\t\t"
                + "MEDIUM" + " " + PREFIX_DEADLINE + "\t\t\t25-12-2022";

        //Valid input with all fields present
        assertParseSuccess(parser, allFieldsPresentArgument,
                new EditTagCommand(expectedIndex, expectedPriorityTag, expectedDeadlineTag));

        //Valid input with all fields present
        assertParseSuccess(parser, allFieldsPresentWithWhitespace,
                new EditTagCommand(expectedIndex, expectedPriorityTag, expectedDeadlineTag));
    }

    @Test
    public void parse_deadlineAndIndexPresent_success() {
        DeadlineTag expectedDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("24-12-2022", dateTimeFormatter)).build();
        String deadlineAndIndexPresent = "2" + " " + PREFIX_DEADLINE + "24-12-2022";
        assertParseSuccess(parser, deadlineAndIndexPresent,
                new EditTagCommand(INDEX_SECOND_TASK, null, expectedDeadlineTag));
    }

    @Test
    public void parse_priorityAndIndexPresent_success() {
        PriorityTag expectedPriorityTag = new PriorityTagBuilder()
                .withStatus("MEDIUM").build();
        String priorityAndIndexPresent = "2" + " " + PREFIX_PRIORITY_STATUS + "MEDIUM";
        assertParseSuccess(parser, priorityAndIndexPresent,
                new EditTagCommand(INDEX_SECOND_TASK, expectedPriorityTag, null));
    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                EditTagCommand.MESSAGE_USAGE);

        //Missing index
        String missingIndex = PREFIX_DEADLINE + "26-12-2022" + " " + PREFIX_PRIORITY_STATUS + "HIGH";
        assertParseFailure(parser, missingIndex, expectedMessage);

        //Missing priority prefix and deadline prefix
        String missingPriorityAndDeadlinePrefix = "4" + " 26-12-2022" + " HIGH";
        assertParseFailure(parser, missingPriorityAndDeadlinePrefix, expectedMessage);
    }

    @Test
    public void parse_invalidInputs_failure() {

        //Empty deadline field
        String emptyDeadlineField = "7 " + PREFIX_PRIORITY_STATUS + "MEDIUM " + PREFIX_DEADLINE;
        assertParseFailure(parser, emptyDeadlineField, DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS);

        //Empty Priority field
        String emptyPriorityField = "66 " + PREFIX_DEADLINE + "29-11-2022 " + PREFIX_PRIORITY_STATUS;
        assertParseFailure(parser, emptyPriorityField, PriorityTag.PRIORITY_TAG_CONSTRAINTS);

        //Invalid index(Non-positive)
        String invalidIndexMessage = EditTagCommandParser.INVALID_INDEX_EDIT_TAG;
        assertParseFailure(parser, "-50 " + PREFIX_PRIORITY_STATUS + "LOW", invalidIndexMessage);

        //Invalid index(Above INT_MAX_VALUE) (Waiting for merged code for bug to check again)
        /*
        assertParseFailure(parser, "9999999999999999999999 " + PREFIX_PRIORITY_STATUS + "HIGH",
                invalidIndexMessage);
         */

        //Invalid status for priority tag
        assertParseFailure(parser, "1 " + PREFIX_PRIORITY_STATUS + "status",
                PriorityTag.PRIORITY_TAG_CONSTRAINTS);

        //Invalid date format for deadline tag
        assertParseFailure(parser, "2 " + PREFIX_DEADLINE + "17/01/2023",
                DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS);

        //Invalid date chosen for deadline tag
        assertParseFailure(parser, "42 " + PREFIX_DEADLINE + "29-02-2023",
                DeadlineTag.DEADLINE_TAG_INVALID_DATE);

        //Date has passed for deadline tag
        assertParseFailure(parser, "5 " + PREFIX_DEADLINE + "01-01-2000",
                DeadlineTag.DEADLINE_TAG_DATE_HAS_PASSED);

    }


}
