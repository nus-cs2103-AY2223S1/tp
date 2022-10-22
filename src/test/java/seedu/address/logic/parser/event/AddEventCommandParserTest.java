package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_CCA;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_SWE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_PRESENTATION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_PRACTICE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CCA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_SWE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_PRACTICE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalEvents.PRACTICE;
import static seedu.address.testutil.TypicalEvents.PRESENTATION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.event.AddEventCommand;
import seedu.address.model.event.DateTime;
import seedu.address.model.event.Event;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EventBuilder;

public class AddEventCommandParserTest {
    private AddEventCommandParser parser = new AddEventCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Event expectedEvent = new EventBuilder(PRACTICE).withTags(VALID_TAG_SWE).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_PRACTICE + START_DESC_PRACTICE + END_DESC_PRACTICE
               + TAG_DESC_SWE, new AddEventCommand(expectedEvent));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_PRESENTATION + TITLE_DESC_PRACTICE + START_DESC_PRACTICE
                + END_DESC_PRACTICE + TAG_DESC_SWE, new AddEventCommand(expectedEvent));

        // multiple ends - last end accepted
        assertParseSuccess(parser, TITLE_DESC_PRACTICE + START_DESC_PRESENTATION + START_DESC_PRACTICE
                + END_DESC_PRACTICE + TAG_DESC_SWE, new AddEventCommand(expectedEvent));

        // multiple starts - last start accepted
        assertParseSuccess(parser, TITLE_DESC_PRACTICE + START_DESC_PRACTICE + END_DESC_PRESENTATION + END_DESC_PRACTICE
               + TAG_DESC_SWE, new AddEventCommand(expectedEvent));
        // multiple tags - all accepted
        Event expectedEventMultipleTags = new EventBuilder(PRACTICE).withTags(VALID_TAG_SWE, VALID_TAG_CCA)
                .build();

        assertParseSuccess(parser, TITLE_DESC_PRACTICE + START_DESC_PRACTICE + END_DESC_PRACTICE
                       + TAG_DESC_SWE + TAG_DESC_CCA,
                new AddEventCommand(expectedEventMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Event expectedEvent = new EventBuilder(PRESENTATION).withTags().build();
        assertParseSuccess(parser, TITLE_DESC_PRESENTATION + START_DESC_PRESENTATION + END_DESC_PRESENTATION,
                new AddEventCommand(expectedEvent));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_PRACTICE + START_DESC_PRACTICE + END_DESC_PRACTICE,
                expectedMessage);

        // missing end prefix
        assertParseFailure(parser, TITLE_DESC_PRACTICE + VALID_START_PRACTICE + END_DESC_PRACTICE,
                expectedMessage);

        // missing start prefix
        assertParseFailure(parser, TITLE_DESC_PRACTICE + START_DESC_PRACTICE + VALID_END_PRACTICE,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_PRACTICE + VALID_START_PRACTICE + VALID_END_PRACTICE,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {

        // invalid end
        assertParseFailure(parser, TITLE_DESC_PRACTICE + INVALID_START_DESC + END_DESC_PRACTICE
                + TAG_DESC_SWE + TAG_DESC_CCA, DateTime.MESSAGE_CONSTRAINTS);

        // invalid start
        assertParseFailure(parser, TITLE_DESC_PRACTICE + START_DESC_PRACTICE + INVALID_END_DESC
                + TAG_DESC_CCA + TAG_DESC_SWE, DateTime.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_PRACTICE + START_DESC_PRACTICE + END_DESC_PRACTICE
                + INVALID_TAG_DESC + VALID_TAG_CCA, Tag.MESSAGE_CONSTRAINTS);

        // non-empty optionArgs
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_PRACTICE + START_DESC_PRACTICE + END_DESC_PRACTICE
                        + TAG_DESC_SWE + TAG_DESC_CCA,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
    }
}
