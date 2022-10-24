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
    public void parse_indexSpecified_success() {
        // status present
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        String userInputOffered = " " + targetIndex.getOneBased() + " " + VALID_STATUS_OFFERED;
        StatusCommand expectedCommandOffered = new StatusCommand(INDEX_FIRST_INTERNSHIP, new Status("Offered"));
        assertParseSuccess(parser, userInputOffered, expectedCommandOffered);
/*
        String userInputProgress = targetIndex.getOneBased() + " " + VALID_STATUS_PROGRESS;
        StatusCommand expectedCommandProgress = new StatusCommand(INDEX_FIRST_INTERNSHIP, new Status("Progress"));
        assertParseSuccess(parser, userInputProgress, expectedCommandProgress);

        String userInputRejected = targetIndex.getOneBased() + " " + VALID_STATUS_REJECTED;
        StatusCommand expectedCommandRejected = new StatusCommand(INDEX_FIRST_INTERNSHIP, new Status("Rejected"));
        assertParseSuccess(parser, userInputRejected, expectedCommandRejected);

 */
    }

    @Test
    public void parse_invalidStatus_failure() {

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, StatusCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, StatusCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, StatusCommand.COMMAND_WORD + " " + VALID_STATUS_OFFERED, expectedMessage);
        assertParseFailure(parser, StatusCommand.COMMAND_WORD + " " + VALID_STATUS_PROGRESS, expectedMessage);
        assertParseFailure(parser, StatusCommand.COMMAND_WORD + " " + VALID_STATUS_REJECTED, expectedMessage);
    }
}
