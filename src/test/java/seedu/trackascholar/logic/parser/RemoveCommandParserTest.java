package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.RemoveCommand;
import seedu.trackascholar.model.applicant.ApplicationStatus;

public class RemoveCommandParserTest {

    private static final RemoveCommandParser parser = new RemoveCommandParser();
    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsRemoveCommand() {
        RemoveCommand expectedFirstRemoveCommand =
                new RemoveCommand(new ApplicationStatus("accepted"));
        RemoveCommand expectedSecondRemoveCommand =
                new RemoveCommand(new ApplicationStatus("rejected"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, ApplicationStatus.ACCEPTED, expectedFirstRemoveCommand);
        assertParseSuccess(parser, ApplicationStatus.REJECTED, expectedSecondRemoveCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + ApplicationStatus.ACCEPTED + " \n", expectedFirstRemoveCommand);
        assertParseSuccess(parser, " \n " + ApplicationStatus.REJECTED + " \n", expectedSecondRemoveCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Applicants with pending status should not be removed
        assertParseFailure(parser, ApplicationStatus.PENDING, ERROR_MESSAGE);

        // Invalid inputs
        assertParseFailure(parser, "invalid", ERROR_MESSAGE);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "     ", ERROR_MESSAGE);
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertParseFailure(parser, "rejected pending", ERROR_MESSAGE);
    }
}
