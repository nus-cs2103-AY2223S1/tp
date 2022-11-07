package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.SortCommand;
import seedu.trackascholar.model.applicant.Applicant;

public class SortCommandParserTest {

    private static final SortCommandParser parser = new SortCommandParser();
    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsSortCommand() {
        SortCommand expectedNameSortCommand =
            new SortCommand(Applicant.sortByName());
        SortCommand expectedScholarshipSortCommand =
            new SortCommand(Applicant.sortByScholarship());
        SortCommand expectedStatusSortCommand =
            new SortCommand(Applicant.sortByStatus());

        // no leading and trailing whitespaces
        assertParseSuccess(parser, SortCommandParser.NAME, expectedNameSortCommand);
        assertParseSuccess(parser, SortCommandParser.SCHOLARSHIP, expectedScholarshipSortCommand);
        assertParseSuccess(parser, SortCommandParser.STATUS, expectedStatusSortCommand);

        // with multiple trailing and leading whitespaces
        assertParseSuccess(parser, "\n "
                + SortCommandParser.NAME + " \n", expectedNameSortCommand);
        assertParseSuccess(parser, "\n "
                + SortCommandParser.SCHOLARSHIP + " \n", expectedScholarshipSortCommand);
        assertParseSuccess(parser, "\n "
                + SortCommandParser.STATUS + " \n", expectedStatusSortCommand);
    }

    @Test
    public void parse_validArgsWithReverseFlag_returnsSortCommand() {
        SortCommand expectedReverseNameSortCommand =
                new SortCommand(Applicant.sortByName().reversed());
        SortCommand expectedReverseScholarshipSortCommand =
                new SortCommand(Applicant.sortByScholarship().reversed());
        SortCommand expectedReverseStatusSortCommand =
                new SortCommand(Applicant.sortByStatus().reversed());

        // reverse flag after argument
        assertParseSuccess(parser, SortCommandParser.NAME
                + "  " + SortCommandParser.REVERSE_FLAG, expectedReverseNameSortCommand);
        assertParseSuccess(parser, SortCommandParser.SCHOLARSHIP
                + "  " + SortCommandParser.REVERSE_FLAG, expectedReverseScholarshipSortCommand);
        assertParseSuccess(parser, SortCommandParser.STATUS
                + "  " + SortCommandParser.REVERSE_FLAG, expectedReverseStatusSortCommand);

        // reverse flag before argument
        assertParseSuccess(parser, SortCommandParser.REVERSE_FLAG + "   "
                + SortCommandParser.NAME, expectedReverseNameSortCommand);
        assertParseSuccess(parser, SortCommandParser.REVERSE_FLAG + "   "
                + SortCommandParser.SCHOLARSHIP, expectedReverseScholarshipSortCommand);
        assertParseSuccess(parser, SortCommandParser.REVERSE_FLAG + "   "
                + SortCommandParser.STATUS, expectedReverseStatusSortCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "ILoveSoftwareEngineering", ERROR_MESSAGE);

        // Throws an error when sort by phone
        assertParseFailure(parser, "phone", ERROR_MESSAGE);

        // Throws an error when sort by email
        assertParseFailure(parser, "email", ERROR_MESSAGE);

        // Throws an error when sort by applicationStatuses. It should be status.
        assertParseFailure(parser, "applicationStatus", ERROR_MESSAGE);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "     ", ERROR_MESSAGE);
    }

    @Test
    public void parse_tooManyValidArgs_throwsParseException() {
        assertParseFailure(parser, SortCommandParser.NAME + " " + SortCommandParser.NAME, ERROR_MESSAGE);
        assertParseFailure(parser, SortCommandParser.SCHOLARSHIP + " "
                + SortCommandParser.STATUS + " " + SortCommandParser.REVERSE_FLAG, ERROR_MESSAGE);
    }
}
