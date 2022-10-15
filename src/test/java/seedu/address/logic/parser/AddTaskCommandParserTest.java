package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_OIL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_FALSE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CHICKEN;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.model.task.Task;
import seedu.address.testutil.TaskBuilder;

public class AddTaskCommandParserTest {
    private AddTaskCommandParser parser = new AddTaskCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder().withTags(VALID_TAG_FOOD).withDeadline(VALID_DEADLINE)
                .withStatus(VALID_STATUS_FALSE).withTitle(VALID_TITLE_CHICKEN).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_CHICKEN + DEADLINE_DESC
                + TAG_DESC_FOOD, new AddTaskCommand(expectedTask));

        // multiple title - last title accepted
        assertParseSuccess(parser, TITLE_DESC_OIL + TITLE_DESC_CHICKEN + DEADLINE_DESC
                + TAG_DESC_FOOD, new AddTaskCommand(expectedTask));

        // multiple deadline - last deadline accepted
        assertParseSuccess(parser, TITLE_DESC_CHICKEN + DEADLINE_DESC_2 + DEADLINE_DESC
                + TAG_DESC_FOOD, new AddTaskCommand(expectedTask));

        // multiple tags - all accepted
        Task expectedTaskMultipleTags = new TaskBuilder().withTags(VALID_TAG_FOOD, VALID_TAG_URGENT)
                .withDeadline(VALID_DEADLINE).withStatus(VALID_STATUS_FALSE).withTitle(VALID_TITLE_CHICKEN).build();

        assertParseSuccess(parser, TITLE_DESC_OIL + TITLE_DESC_CHICKEN + DEADLINE_DESC
                + TAG_DESC_FOOD + TAG_DESC_URGENT, new AddTaskCommand(expectedTaskMultipleTags));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_CHICKEN + DEADLINE_DESC + TAG_DESC_FOOD, expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, TITLE_DESC_CHICKEN + VALID_DEADLINE + TAG_DESC_FOOD, expectedMessage);

        // missing tag prefix
        assertParseFailure(parser, TITLE_DESC_CHICKEN + DEADLINE_DESC + VALID_TAG_FOOD, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_CHICKEN + VALID_DEADLINE + VALID_TAG_FOOD, expectedMessage);
    }
}
