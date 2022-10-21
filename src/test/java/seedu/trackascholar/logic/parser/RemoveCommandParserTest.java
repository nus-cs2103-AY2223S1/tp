package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.RemoveCommand;
import seedu.trackascholar.model.applicant.ApplicationStatus;

public class RemoveCommandParserTest {

    private RemoveCommandParser parser = new RemoveCommandParser();

    @Test
    public void parse_validArgs_returnsRemoveCommand() {
        RemoveCommand expectedFirstRemoveCommand =
                new RemoveCommand(new ApplicationStatus("accepted"));
        RemoveCommand expectedSecondRemoveCommand =
                new RemoveCommand(new ApplicationStatus("rejected"));

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "accepted", expectedFirstRemoveCommand);
        assertParseSuccess(parser, "rejected", expectedSecondRemoveCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n accepted \n", expectedFirstRemoveCommand);
        assertParseSuccess(parser, " \n rejected \n", expectedSecondRemoveCommand);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // Applicants with pending status should not be removed
        assertParseFailure(parser, "pending",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));

        // Invalid inputs
        assertParseFailure(parser, "invalid",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemoveCommand.MESSAGE_USAGE));
    }
}
