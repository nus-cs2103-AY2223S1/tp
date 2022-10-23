package seedu.intrack.logic.parser;

import static seedu.intrack.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.intrack.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.index.Index;
import seedu.intrack.logic.commands.StatusCommand;
import seedu.intrack.model.internship.Status;

public class StatusCommandParserTest {
    private StatusCommandParser parser = new StatusCommandParser();
    private final String VALID_STATUS_OFFERED = "o";
    private final String VALID_STATUS_PROGRESS = "p";
    private final String VALID_STATUS_REJECTED = "r";

    @Test
    public void parse_indexSpecified_offered_success() {
        // remark present
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInput = targetIndex.getOneBased() + " " + VALID_STATUS_OFFERED;
        StatusCommand expectedCommand = new StatusCommand(INDEX_FIRST_INTERNSHIP, new Status("Offered"));
        assertParseSuccess(parser, userInput, expectedCommand);

        // remark not present
        userInput = targetIndex.getOneBased() + " " + PREFIX_REMARK;
        expectedCommand = new RemarkCommand(INDEX_FIRST_INTERNSHIP, new Remark(""));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidStatus_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, RemarkCommand.COMMAND_WORD + " " + nonEmptyRemark, expectedMessage);
    }
}
