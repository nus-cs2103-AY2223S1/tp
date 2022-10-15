package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.DEADLINE_DESC_2;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_URGENT;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_OIL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_CHICKEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_OIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UpdateTaskCommand;
import seedu.address.logic.commands.UpdateTaskCommand.UpdateTaskDescriptor;
import seedu.address.testutil.UpdateTaskDescriptorBuilder;

public class UpdateTaskCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateTaskCommand.MESSAGE_USAGE);

    private UpdateTaskCommandParser parser = new UpdateTaskCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_OIL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", UpdateTaskCommand.MESSAGE_NOT_UPDATED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_OIL, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_OIL, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 x/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(2);
        String userInput = targetIndex.getOneBased() + TITLE_DESC_OIL + TAG_DESC_URGENT + DEADLINE_DESC_2
                + TAG_DESC_FOOD;

        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL)
                .withDeadline(VALID_DEADLINE2).withTags(VALID_TAG_FOOD, VALID_TAG_URGENT).build();
        UpdateTaskCommand expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + DEADLINE_DESC + TITLE_DESC_OIL;

        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withDeadline(VALID_DEADLINE)
                .withTitle(VALID_TITLE_OIL).build();
        UpdateTaskCommand expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = Index.fromOneBased(3);
        String userInput = targetIndex.getOneBased() + TITLE_DESC_CHICKEN;
        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_CHICKEN).build();
        UpdateTaskCommand expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC;
        descriptor = new UpdateTaskDescriptorBuilder().withDeadline(VALID_DEADLINE).build();
        expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FOOD;
        descriptor = new UpdateTaskDescriptorBuilder().withTags(VALID_TAG_FOOD).build();
        expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = targetIndex.getOneBased() + TITLE_DESC_OIL + DEADLINE_DESC + TITLE_DESC_CHICKEN
                + DEADLINE_DESC_2 + TAG_DESC_FOOD + TAG_DESC_URGENT + TAG_DESC_URGENT + TITLE_DESC_OIL;

        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTitle(VALID_TITLE_OIL)
                .withDeadline(VALID_DEADLINE2).withTags(VALID_TAG_FOOD, VALID_TAG_URGENT).build();
        UpdateTaskCommand expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = Index.fromOneBased(3);
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        UpdateTaskDescriptor descriptor = new UpdateTaskDescriptorBuilder().withTags().build();
        UpdateTaskCommand expectedCommand = new UpdateTaskCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
