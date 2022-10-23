package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.SortCommand;
import seedu.trackascholar.model.applicant.Applicant;

public class SortCommandParserTest {
    private SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand expectedNameSortCommand =
            new SortCommand(Applicant.sortByName());
        SortCommand expectedScholarshipSortCommand =
            new SortCommand(Applicant.sortByScholarship());
        SortCommand expectedStatusSortCommand =
            new SortCommand(Applicant.sortByStatus());

        // no leading and trailing whitespaces
        assertParseSuccess(parser, "name", expectedNameSortCommand);
        assertParseSuccess(parser, "scholarship", expectedScholarshipSortCommand);
        assertParseSuccess(parser, "status", expectedStatusSortCommand);

        // with multiple trailing and leading whitespaces
        assertParseSuccess(parser, "\n name \n", expectedNameSortCommand);
        assertParseSuccess(parser, "\n scholarship \n", expectedScholarshipSortCommand);
        assertParseSuccess(parser, "\n status \n", expectedStatusSortCommand);
    }

    @Test
    public void parse_invalidArgs_throwsError() {
        assertParseFailure(parser, "ILoveSoftwareEngineering",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        //Throws error when sort by phone
        assertParseFailure(parser, "phone",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        //Throws error when sort by email
        assertParseFailure(parser, "email",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));

        //Throw error when sort by applicationStatuses.It should be status.
        assertParseFailure(parser, "applicationStatus",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsError() {
        assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
    }

}
