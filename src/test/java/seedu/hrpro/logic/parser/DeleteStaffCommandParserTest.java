package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.hrpro.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hrpro.testutil.TypicalIndexes.INVALID_INDEX_MAX_PLUS_ONE;
import static seedu.hrpro.testutil.TypicalIndexes.VALID_INDEX_ONE;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.DeleteStaffCommand;
import seedu.hrpro.model.project.ProjectName;

/**
 * Contains test cases for DeleteStaffCommandParser.
 */
public class DeleteStaffCommandParserTest {

    private DeleteStaffCommandParser parser = new DeleteStaffCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        ProjectName projectName = new ProjectName(VALID_NAME_BOB);
        String userInput = " " + VALID_INDEX_ONE + " " + NAME_DESC_BOB;
        Index index = Index.fromOneBased(1);
        assertParseSuccess(parser, userInput, new DeleteStaffCommand(index, projectName));
    }

    @Test
    public void parse_missingCompulsoryFields_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE);
        String inValidInputMessage = ProjectName.MESSAGE_CONSTRAINTS;

        // Missing project name field
        assertParseFailure(parser, VALID_INDEX_ONE, expectedMessage);

        // Have project name prefix but no project name
        assertParseFailure(parser, VALID_INDEX_ONE + " pn/", inValidInputMessage);

        // Missing staff index field
        assertParseFailure(parser, NAME_DESC_BOB, expectedMessage);
    }

    @Test
    public void parse_missingCompulsoryPrefix_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE);

        // Missing project name prefix
        assertParseFailure(parser, VALID_INDEX_ONE + VALID_NAME_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStaffCommand.MESSAGE_USAGE);

        // Invalid project name
        assertParseFailure(parser, VALID_INDEX_ONE + " " + INVALID_NAME_DESC, ProjectName.MESSAGE_CONSTRAINTS);

        // Negative index
        assertParseFailure(parser, "-10 " + NAME_DESC_AMY, expectedMessage);

        // Zero index
        assertParseFailure(parser, "0 " + NAME_DESC_AMY, expectedMessage);

        // Index larger than max integer
        assertParseFailure(parser, INVALID_INDEX_MAX_PLUS_ONE, expectedMessage);
    }
}
