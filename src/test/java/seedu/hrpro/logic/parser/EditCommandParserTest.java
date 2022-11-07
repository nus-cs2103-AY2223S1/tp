package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.commands.CommandTestUtil.BUDGET_DESC_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.BUDGET_DESC_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.DEADLINE_DESC_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.DEADLINE_DESC_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_BUDGET_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_DEADLINE_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_THIRD_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditCommand;
import seedu.hrpro.logic.commands.EditCommand.EditProjectDescriptor;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.tag.Tag;
import seedu.hrpro.testutil.EditProjectDescriptorBuilder;

/**
 * Contains test cases for EditCommandParser.
 */
public class EditCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, ProjectName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_BUDGET_DESC, Budget.MESSAGE_CONSTRAINTS); // invalid budget
        assertParseFailure(parser, "1" + INVALID_DEADLINE_DESC, Deadline.MESSAGE_CONSTRAINTS); // invalid deadline
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid budget followed by valid deadline
        assertParseFailure(parser, "1" + INVALID_BUDGET_DESC + DEADLINE_DESC_AMY, Budget.MESSAGE_CONSTRAINTS);

        // valid budget followed by invalid budget. The test case for invalid budget followed by valid budget
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + BUDGET_DESC_BOB + INVALID_BUDGET_DESC, Budget.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Project} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_HUSBAND + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DEADLINE_DESC + VALID_BUDGET_AMY,
                ProjectName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PROJECT;
        String userInput = targetIndex.getOneBased() + BUDGET_DESC_BOB + TAG_DESC_HUSBAND
                + DEADLINE_DESC_AMY + NAME_DESC_AMY + TAG_DESC_FRIEND;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withName(VALID_NAME_AMY)
                .withBudget(VALID_BUDGET_BOB).withDeadline(VALID_DEADLINE_AMY)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + BUDGET_DESC_BOB + DEADLINE_DESC_AMY;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withBudget(VALID_BUDGET_BOB)
                .withDeadline(VALID_DEADLINE_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withName(VALID_NAME_AMY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // budget
        userInput = targetIndex.getOneBased() + BUDGET_DESC_AMY;
        descriptor = new EditProjectDescriptorBuilder().withBudget(VALID_BUDGET_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // deadline
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_AMY;
        descriptor = new EditProjectDescriptorBuilder().withDeadline(VALID_DEADLINE_AMY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_FRIEND;
        descriptor = new EditProjectDescriptorBuilder().withTags(VALID_TAG_FRIEND).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + BUDGET_DESC_AMY + DEADLINE_DESC_AMY
                + TAG_DESC_FRIEND + BUDGET_DESC_AMY + DEADLINE_DESC_AMY + TAG_DESC_FRIEND
                + BUDGET_DESC_BOB + DEADLINE_DESC_BOB + TAG_DESC_HUSBAND;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withBudget(VALID_BUDGET_BOB)
                .withDeadline(VALID_DEADLINE_BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_PROJECT;
        String userInput = targetIndex.getOneBased() + INVALID_BUDGET_DESC + BUDGET_DESC_BOB;
        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withBudget(VALID_BUDGET_BOB).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + DEADLINE_DESC_BOB + INVALID_BUDGET_DESC + BUDGET_DESC_BOB;
        descriptor = new EditProjectDescriptorBuilder().withBudget(VALID_BUDGET_BOB).withDeadline(VALID_DEADLINE_BOB)
                .build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_PROJECT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditProjectDescriptor descriptor = new EditProjectDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
