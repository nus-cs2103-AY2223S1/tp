package seedu.trackascholar.logic.parser;

import static seedu.trackascholar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackascholar.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.trackascholar.logic.commands.FilterCommand;
import seedu.trackascholar.model.applicant.ApplicationStatus;
import seedu.trackascholar.model.applicant.ApplicationStatusPredicate;

public class FilterCommandParserTest {

    private static final FilterCommandParser parser = new FilterCommandParser();
    private static final String ERROR_MESSAGE =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE);

    @Test
    public void parse_validArgs_returnsFilterCommand() {
        // no leading and trailing whitespaces
        FilterCommand expectedFilterCommand =
                new FilterCommand(new ApplicationStatusPredicate(ApplicationStatus.ACCEPTED));
        assertParseSuccess(parser, ApplicationStatus.ACCEPTED, expectedFilterCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n " + ApplicationStatus.ACCEPTED + " \n", expectedFilterCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        assertParseFailure(parser, "emotionalDamage", ERROR_MESSAGE);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", ERROR_MESSAGE);
        assertParseFailure(parser, "     ", ERROR_MESSAGE);
    }

    @Test
    public void parse_multipleArgs_throwsParseException() {
        assertParseFailure(parser, ApplicationStatus.PENDING + ApplicationStatus.ACCEPTED
                + ApplicationStatus.REJECTED, ERROR_MESSAGE);
    }
}
