package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_ACCEPTED;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_INTERVIEWED;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_INVALID;
import static seedu.address.logic.commands.CommandTestUtil.APPLICATION_STATUS_REJECTED;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.model.internship.ApplicationStatus;

public class MarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_validArgs_returnsMarkCommand() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;

        //For applied
        String userInputApplied = targetIndex.getOneBased() + APPLICATION_STATUS_APPLIED;
        assertParseSuccess(parser, userInputApplied, new MarkCommand(targetIndex, ApplicationStatus.Applied));

        //For rejected
        String userInputAccepted = targetIndex.getOneBased() + APPLICATION_STATUS_ACCEPTED;
        assertParseSuccess(parser, userInputAccepted, new MarkCommand(targetIndex, ApplicationStatus.Accepted));

        //For interviewed
        String userInputInterviewed = targetIndex.getOneBased() + APPLICATION_STATUS_INTERVIEWED;
        assertParseSuccess(parser, userInputInterviewed, new MarkCommand(targetIndex, ApplicationStatus.Interviewed));

        //For rejected
        String userInputRejected = targetIndex.getOneBased() + APPLICATION_STATUS_REJECTED;
        assertParseSuccess(parser, userInputRejected, new MarkCommand(targetIndex, ApplicationStatus.Rejected));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + APPLICATION_STATUS_ACCEPTED, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + APPLICATION_STATUS_ACCEPTED, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_invalidApplicationStatus_failure() {
        Index targetIndex = INDEX_FIRST_INTERNSHIP;
        assertParseFailure(parser, targetIndex.getOneBased() + APPLICATION_STATUS_INVALID,
                ApplicationStatus.MESSAGE_CONSTRAINTS);
    }

}
