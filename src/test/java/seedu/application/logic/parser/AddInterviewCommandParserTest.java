package seedu.application.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.AddInterviewCommand;
import seedu.application.model.application.interview.*;
import seedu.application.testutil.InterviewBuilder;

import static seedu.application.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.*;

public class AddInterviewCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

    private AddInterviewCommandParser parser = new AddInterviewCommandParser();

    @Test
    public void parse_missingParts_failure() {
        String userInput = ROUND_DESC_GOOGLE+ INTERVIEW_DATE_DESC_GOOGLE
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE;

        // no index specified
        assertParseFailure(parser, userInput, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }


    @Test
    public void parse_invalidPreamble_failure() {
        String userInput = ROUND_DESC_GOOGLE+ INTERVIEW_DATE_DESC_GOOGLE
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE;

        // negative index
        assertParseFailure(parser, "-5" + userInput, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + userInput, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROUND_DESC_GOOGLE + INTERVIEW_DATE_DESC_GOOGLE
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE;

        Interview interview = new InterviewBuilder().withRound(VALID_ROUND_GOOGLE)
                .withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE).withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE)
                .withLocation(VALID_LOCATION_GOOGLE).build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, interview);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

        //missing round
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + INTERVIEW_DATE_DESC_GOOGLE
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE, expectedMessage);

        //missing interviewDate
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + ROUND_DESC_GOOGLE
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE, expectedMessage);

        //missing interviewTime
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + ROUND_DESC_GOOGLE
                + INTERVIEW_DATE_DESC_GOOGLE + LOCATION_DESC_GOOGLE, expectedMessage);

        //missing Location
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + ROUND_DESC_GOOGLE
                + INTERVIEW_DATE_DESC_GOOGLE + INTERVIEW_TIME_DESC_GOOGLE, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        //invalid Round
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + INVALID_ROUND_DESC
                        + INTERVIEW_DATE_DESC_GOOGLE + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE,
                Round.MESSAGE_CONSTRAINTS);

        //invalid interviewDate
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + ROUND_DESC_GOOGLE
                        + INVALID_INTERVIEW_DATE_DESC + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE,
                InterviewDate.MESSAGE_CONSTRAINTS);

        //invalid Round
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + ROUND_DESC_GOOGLE
                        + INTERVIEW_DATE_DESC_GOOGLE + INVALID_INTERVIEW_TIME_DESC + LOCATION_DESC_GOOGLE,
                InterviewTime.MESSAGE_CONSTRAINTS);

        //invalid Round
        assertParseFailure(parser, INDEX_FIRST_APPLICATION.getOneBased() + ROUND_DESC_GOOGLE
                        + INTERVIEW_DATE_DESC_GOOGLE + INTERVIEW_TIME_DESC_GOOGLE + INVALID_LOCATION_DESC,
                Location.MESSAGE_CONSTRAINTS);
    }
}
