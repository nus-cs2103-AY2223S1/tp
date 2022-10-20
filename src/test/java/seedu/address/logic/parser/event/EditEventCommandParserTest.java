package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CCA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SWE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PRESENTATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.logic.commands.event.EditEventCommand.EditEventDescriptor;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Title;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_PRACTICE, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_PRESENTATION, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_PRESENTATION, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS);

        // invalid start date time
        assertParseFailure(parser, "1" + INVALID_START_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // invalid end date time
        assertParseFailure(parser, "1" + INVALID_END_DESC, DateTime.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // invalid start date time followed by valid end date time
        assertParseFailure(parser, "1" + INVALID_START_DESC + END_DESC_PRACTICE,
                DateTime.MESSAGE_CONSTRAINTS);

        // valid start followed by invalid start. The test case for invalid start followed by valid start
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + START_DESC_PRACTICE + INVALID_START_DESC,
                DateTime.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Event} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAG_DESC_CCA + TAG_DESC_SWE + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_DESC_CCA + TAG_EMPTY + TAG_DESC_SWE, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_SWE + TAG_DESC_CCA, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_START_DESC + VALID_END_PRESENTATION,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + START_DESC_PRESENTATION + TAG_DESC_CCA
                + END_DESC_PRESENTATION + TITLE_DESC_PRESENTATION + TAG_DESC_SWE;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_PRESENTATION)
                .withStartDateTime(VALID_START_PRESENTATION).withEndDateTime(VALID_END_PRESENTATION)
                .withTags(VALID_TAG_CCA, VALID_TAG_SWE).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + START_DESC_PRESENTATION + END_DESC_PRACTICE;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withStartDateTime(VALID_START_PRESENTATION)
                .withEndDateTime(VALID_END_PRACTICE).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_PRESENTATION;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_PRESENTATION).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date time
        userInput = targetIndex.getOneBased() + START_DESC_PRESENTATION;
        descriptor = new EditEventDescriptorBuilder().withStartDateTime(VALID_START_PRESENTATION).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start date
        userInput = targetIndex.getOneBased() + END_DESC_PRACTICE;
        descriptor = new EditEventDescriptorBuilder().withEndDateTime(VALID_END_PRACTICE).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_CCA;
        descriptor = new EditEventDescriptorBuilder().withTags(VALID_TAG_CCA).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + START_DESC_PRACTICE + END_DESC_PRACTICE
                + TAG_DESC_SWE + START_DESC_PRACTICE + END_DESC_PRACTICE + TAG_DESC_CCA
                + START_DESC_PRESENTATION + END_DESC_PRESENTATION;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withStartDateTime(VALID_START_PRESENTATION)
                .withEndDateTime(VALID_END_PRESENTATION).withTags(VALID_TAG_CCA, VALID_TAG_SWE)
                .build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_END_DESC + END_DESC_PRACTICE;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withEndDateTime(VALID_END_PRACTICE).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TITLE_DESC_PRACTICE + INVALID_END_DESC
                + END_DESC_PRESENTATION;
        descriptor = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_PRACTICE)
                .withEndDateTime(VALID_END_PRESENTATION).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
