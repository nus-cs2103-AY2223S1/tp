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
import static seedu.hrpro.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.hrpro.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_DEADLINE_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalProjects.AMY;
import static seedu.hrpro.testutil.TypicalProjects.BOB;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.AddCommand;
import seedu.hrpro.model.deadline.Deadline;
import seedu.hrpro.model.project.Budget;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.tag.Tag;
import seedu.hrpro.testutil.ProjectBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Project expectedProject = new ProjectBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedProject));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_AMY + NAME_DESC_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedProject));

        // multiple deadlines - last deadline accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_AMY + DEADLINE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedProject));

        // multiple budgets - last budget accepted
        assertParseSuccess(parser, NAME_DESC_BOB + BUDGET_DESC_AMY + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + TAG_DESC_FRIEND, new AddCommand(expectedProject));

        // multiple tags - all accepted
        Project expectedProjectMultipleTags = new ProjectBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, NAME_DESC_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, new AddCommand(expectedProjectMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Project expectedProject = new ProjectBuilder(AMY).withTags().build();
        assertParseSuccess(parser, NAME_DESC_AMY + BUDGET_DESC_AMY + DEADLINE_DESC_AMY,
                new AddCommand(expectedProject));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_BOB,
                expectedMessage);

        // missing budget prefix
        assertParseFailure(parser, NAME_DESC_BOB + VALID_BUDGET_BOB + DEADLINE_DESC_BOB,
                expectedMessage);

        // missing deadline prefix
        assertParseFailure(parser, NAME_DESC_BOB + BUDGET_DESC_BOB + VALID_DEADLINE_BOB,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_BUDGET_BOB + VALID_DEADLINE_BOB,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, ProjectName.MESSAGE_CONSTRAINTS);

        // invalid budget
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_BUDGET_DESC + DEADLINE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Budget.MESSAGE_CONSTRAINTS);

        // invalid deadline
        assertParseFailure(parser, NAME_DESC_BOB + BUDGET_DESC_BOB + INVALID_DEADLINE_DESC
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND, Deadline.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, NAME_DESC_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + INVALID_TAG_DESC + VALID_TAG_FRIEND, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + BUDGET_DESC_BOB + INVALID_DEADLINE_DESC,
                ProjectName.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + BUDGET_DESC_BOB + DEADLINE_DESC_BOB
                + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
