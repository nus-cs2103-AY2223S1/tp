package seedu.application.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.AddInterviewCommand;
import seedu.application.logic.commands.EditCommand.EditApplicationDescriptor;
import seedu.application.model.application.interview.Location;
import seedu.application.model.application.interview.InterviewDate;
import seedu.application.model.application.interview.InterviewTime;
import seedu.application.model.application.interview.Round;
import seedu.application.model.tag.Tag;
import seedu.application.testutil.EditApplicationDescriptorBuilder;

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
        // no index specified
        assertParseFailure(parser, VALID_ROUND_GOOGLE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", AddInterviewCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ROUND_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ROUND_DESC_GOOGLE, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ROUND_DESC, Round.MESSAGE_CONSTRAINTS); // invalid round
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_DATE_DESC, InterviewDate.MESSAGE_CONSTRAINTS); // invalid interviewDate
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_TIME_DESC, InterviewTime.MESSAGE_CONSTRAINTS); // invalid interviewTime
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid location

        // invalid interviewDate followed by valid interviewTime
        assertParseFailure(parser, "1" + INVALID_INTERVIEW_DATE_DESC + INTERVIEW_TIME_DESC_GOOGLE,
                InterviewDate.MESSAGE_CONSTRAINTS);

        // valid interviewDate followed by invalid interviewDate. The test case for invalid interviewDate followed by valid interviewDate
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + INTERVIEW_DATE_DESC_GOOGLE + INVALID_INTERVIEW_DATE_DESC,
                InterviewDate.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Application} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_PREFERRED + TAG_DESC_TECH_ROUND + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_PREFERRED + TAG_EMPTY + TAG_DESC_TECH_ROUND,
                Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_PREFERRED + TAG_DESC_TECH_ROUND,
                Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ROUND_DESC + INVALID_INTERVIEW_TIME_DESC + VALID_DATE_GOOGLE
                + VALID_INTERVIEW_DATE_GOOGLE + VALID_LOCATION_GOOGLE, Round.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + INTERVIEW_DATE_DESC_GOOGLE + LOCATION_DESC_GOOGLE + TAG_DESC_PREFERRED
                + INTERVIEW_TIME_DESC_GOOGLE + DATE_DESC_GOOGLE + ROUND_DESC_GOOGLE + TAG_DESC_TECH_ROUND;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withRound(VALID_ROUND_GOOGLE).withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE)
                .withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE).withDate(VALID_DATE_GOOGLE)
                .withLocation(VALID_LOCATION_GOOGLE).withTags(VALID_TAG_PREFERRED, VALID_TAG_TECH_ROUND).build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INTERVIEW_DATE_DESC_GOOGLE + INTERVIEW_TIME_DESC_GOOGLE;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE)
                .withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE).build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // round
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + ROUND_DESC_GOOGLE;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withRound(VALID_ROUND_GOOGLE).build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interviewDate
        userInput = targetIndex.getOneBased() + INTERVIEW_DATE_DESC_GOOGLE;
        descriptor = new EditApplicationDescriptorBuilder().withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE).build();
        expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_GOOGLE;
        descriptor = new EditApplicationDescriptorBuilder().withDate(VALID_DATE_GOOGLE).build();
        expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interviewTime
        userInput = targetIndex.getOneBased() + INTERVIEW_TIME_DESC_GOOGLE;
        descriptor = new EditApplicationDescriptorBuilder().withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE).build();
        expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_GOOGLE;
        descriptor = new EditApplicationDescriptorBuilder().withLocation(VALID_LOCATION_GOOGLE).build();
        expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_TECH_ROUND;
        descriptor = new EditApplicationDescriptorBuilder().withTags(VALID_TAG_TECH_ROUND).build();
        expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_APPLICATION;
        String userInput = targetIndex.getOneBased() + INTERVIEW_DATE_DESC_GOOGLE + DATE_DESC_GOOGLE + TAG_DESC_PREFERRED
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE + INTERVIEW_DATE_DESC_GOOGLE + DATE_DESC_GOOGLE
                + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE + INTERVIEW_DATE_DESC_GOOGLE + DATE_DESC_GOOGLE
                + TAG_DESC_TECH_ROUND + INTERVIEW_TIME_DESC_GOOGLE + LOCATION_DESC_GOOGLE + TAG_DESC_PREFERRED;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE)
                .withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE).withDate(VALID_DATE_GOOGLE).withLocation(VALID_LOCATION_GOOGLE)
                .withTags(VALID_TAG_PREFERRED, VALID_TAG_TECH_ROUND).build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_SECOND_APPLICATION;
        String userInput = targetIndex.getOneBased() + INVALID_INTERVIEW_DATE_DESC + INTERVIEW_DATE_DESC_GOOGLE;
        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder()
                .withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE).build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + INTERVIEW_TIME_DESC_GOOGLE + INVALID_INTERVIEW_DATE_DESC + DATE_DESC_GOOGLE
                + INTERVIEW_DATE_DESC_GOOGLE;
        descriptor = new EditApplicationDescriptorBuilder().withInterviewDate(VALID_INTERVIEW_DATE_GOOGLE)
                .withInterviewTime(VALID_INTERVIEW_TIME_GOOGLE).withDate(VALID_DATE_GOOGLE).build();
        expectedCommand = new AddInterviewCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_APPLICATION;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditApplicationDescriptor descriptor = new EditApplicationDescriptorBuilder().withTags().build();
        AddInterviewCommand expectedCommand = new AddInterviewCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
