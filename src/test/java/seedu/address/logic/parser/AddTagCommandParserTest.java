package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_STATUS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.model.tag.DeadlineTag;
import seedu.address.model.tag.PriorityTag;
import seedu.address.testutil.DeadlineTagBuilder;
import seedu.address.testutil.PriorityTagBuilder;


public class AddTagCommandParserTest {
    private AddTagCommandParser parser = new AddTagCommandParser();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");

    @Test
    public void parse_allFieldsPresent_success() {
        PriorityTag expectedPriorityTag = new PriorityTagBuilder().withStatus("HIGH").build();
        DeadlineTag expectedDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("22-11-2022", dateTimeFormatter)).build();
        Index expectedIndex = INDEX_FIRST_TASK;
        String allFieldsPresentArgument = "1" + " " + PREFIX_PRIORITY_STATUS + "HIGH"
                + " " + PREFIX_DEADLINE + "22-11-2022";
        String allFieldsPresentWithWhitespace = "           1" + " " + PREFIX_PRIORITY_STATUS + "\t\t"
                + "HIGH" + " " + PREFIX_DEADLINE + "\t\t\t22-11-2022";

        //Valid input with all fields present
        assertParseSuccess(parser, allFieldsPresentArgument,
                new AddTagCommand(expectedPriorityTag, expectedDeadlineTag, expectedIndex));

        //Valid input with all fields present
        assertParseSuccess(parser, allFieldsPresentWithWhitespace,
                new AddTagCommand(expectedPriorityTag, expectedDeadlineTag, expectedIndex));
    }

    @Test
    public void parse_deadlineAndIndexPresent_success() {
        DeadlineTag expectedDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("23-11-2022", dateTimeFormatter)).build();
        String deadlineAndIndexPresent = "1" + " " + PREFIX_DEADLINE + "23-11-2022";
        assertParseSuccess(parser, deadlineAndIndexPresent,
                new AddTagCommand(null, expectedDeadlineTag, INDEX_FIRST_TASK));
    }

    @Test
    public void parse_priorityAndIndexPresent_success() {
        PriorityTag expectedPriorityTag = new PriorityTagBuilder()
                .withStatus("HIGH").build();
        String priorityAndIndexPresent = "1" + " " + PREFIX_PRIORITY_STATUS + "HIGH";
        assertParseSuccess(parser, priorityAndIndexPresent,
                new AddTagCommand(expectedPriorityTag, null, INDEX_FIRST_TASK));
    }

    @Test
    public void parse_missingFields_failure() {
        String expectedMessage = String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                AddTagCommand.MESSAGE_USAGE);

        //Missing index
        String missingIndex = " " + PREFIX_DEADLINE + "23-11-2022" + " " + PREFIX_PRIORITY_STATUS + "HIGH";
        assertParseFailure(parser, missingIndex, expectedMessage);

        //Missing priority prefix and deadline prefix
        String missingPriorityAndDeadlinePrefix = "1" + " 23-11-2022" + " HIGH";
        assertParseFailure(parser, missingPriorityAndDeadlinePrefix, expectedMessage);

    }

    @Test
    public void parse_invalidInputs_failure() {

        //Empty deadline field
        String emptyDeadlineField = "1 " + PREFIX_PRIORITY_STATUS + "HIGH " + PREFIX_DEADLINE;
        assertParseFailure(parser, emptyDeadlineField, DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS);

        //Empty Priority field
        String emptyPriorityField = "1 " + PREFIX_DEADLINE + "23-11-2022 " + PREFIX_PRIORITY_STATUS;
        assertParseFailure(parser, emptyPriorityField, PriorityTag.PRIORITY_TAG_CONSTRAINTS);

        //Invalid index(Non-positive)
        String invalidIndexMessage = ParserUtil.MESSAGE_INVALID_INDEX;
        assertParseFailure(parser, "-1 " + PREFIX_PRIORITY_STATUS + "HIGH", invalidIndexMessage);

        //Invalid index(Above INT_MAX_VALUE)
        assertParseFailure(parser, "9999999999999999999999 " + PREFIX_PRIORITY_STATUS + "HIGH",
                invalidIndexMessage);


        //Invalid status for priority tag
        assertParseFailure(parser, "1 " + PREFIX_PRIORITY_STATUS + "priority",
                PriorityTag.PRIORITY_TAG_CONSTRAINTS);

        //Invalid date format for deadline tag
        assertParseFailure(parser, "1 " + PREFIX_DEADLINE + "17/01/23",
                DeadlineTag.DEADLINE_TAG_FORMAT_CONSTRAINTS);

        //Invalid date chosen for deadline tag
        assertParseFailure(parser, "1 " + PREFIX_DEADLINE + "30-02-2023",
                DeadlineTag.DEADLINE_TAG_INVALID_DATE);

        //Date has passed for deadline tag
        assertParseFailure(parser, "1 " + PREFIX_DEADLINE + "01-01-2022",
                DeadlineTag.DEADLINE_TAG_DATE_HAS_PASSED);

    }
}
