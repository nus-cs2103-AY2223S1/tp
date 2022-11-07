package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERNSHIP_STATUS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INTERVIEW_DATE;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortInternshipCommand;

public class SortInternshipCommandParserTest {

    private final SortInternshipCommandParser parser = new SortInternshipCommandParser();

    @Test
    public void parse_noPrefixes_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInternshipCommand.MESSAGE_USAGE);
        CommandParserTestUtil.assertParseFailure(parser, "", expectedMessage);
    }

    @Test
    public void parse_multiplePrefixes_failure() {
        // all valid prefixes
        String userInput = PREFIX_COMPANY_NAME + " " + PREFIX_INTERVIEW_DATE + " " + PREFIX_INTERNSHIP_STATUS;
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInternshipCommand.MESSAGE_USAGE);
        CommandParserTestUtil.assertParseFailure(parser, userInput, expectedMessage);

        // mix of valid and invalid prefixes
        userInput = PREFIX_COMPANY_NAME + " " + PREFIX_INTERNSHIP;
        expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInternshipCommand.MESSAGE_USAGE);
        CommandParserTestUtil.assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_singleInvalidPrefix_failure() {
        String userInput = String.valueOf(PREFIX_INTERNSHIP_ROLE);
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortInternshipCommand.MESSAGE_USAGE);
        CommandParserTestUtil.assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_singleValidPrefix_success() {
        // company name
        String userInput = String.valueOf(PREFIX_COMPANY_NAME);
        SortInternshipCommand expectedCommand = new SortInternshipCommand(SortInternshipCommand.Criteria.COMPANY_NAME);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // interview date
        userInput = String.valueOf(PREFIX_INTERVIEW_DATE);
        expectedCommand = new SortInternshipCommand(SortInternshipCommand.Criteria.INTERVIEW_DATE);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);

        // internship status
        userInput = String.valueOf(PREFIX_INTERNSHIP_STATUS);
        expectedCommand = new SortInternshipCommand(SortInternshipCommand.Criteria.INTERNSHIP_STATUS);
        CommandParserTestUtil.assertParseSuccess(parser, userInput, expectedCommand);
    }
}
