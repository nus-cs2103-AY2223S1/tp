package seedu.travelr.logic.parser;

import static seedu.travelr.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.travelr.logic.commands.CommandTestUtil.DESCRIPTION_DESC_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.DESCRIPTION_DESC_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.EVENT_DESC_EATING;
import static seedu.travelr.logic.commands.CommandTestUtil.EVENT_DESC_SIGHTSEEING;
import static seedu.travelr.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.travelr.logic.commands.CommandTestUtil.INVALID_EVENT_DESC;
import static seedu.travelr.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.travelr.logic.commands.CommandTestUtil.TITLE_DESC_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_ANTARCTICA;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_DESCRIPTION_GERMANY;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_EATING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_EVENT_SIGHTSEEING;
import static seedu.travelr.logic.commands.CommandTestUtil.VALID_TITLE_GERMANY;
import static seedu.travelr.logic.parser.CliSyntax.PREFIX_EVENT;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.travelr.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_FIRST_TRIP;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_SECOND_TRIP;
import static seedu.travelr.testutil.TypicalIndexes.INDEX_THIRD_TRIP;

import org.junit.jupiter.api.Test;

import seedu.travelr.commons.core.index.Index;
import seedu.travelr.logic.commands.EditCommand;
import seedu.travelr.logic.commands.EditCommand.EditTripDescriptor;
import seedu.travelr.model.component.Description;
import seedu.travelr.model.component.Title;
import seedu.travelr.model.event.Event;
import seedu.travelr.testutil.EditTripDescriptorBuilder;

public class EditCommandParserTest {

    private static final String EVENT_EMPTY = " " + PREFIX_EVENT;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TITLE_GERMANY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_GERMANY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_GERMANY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC, Title.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS);
        // invalid description
        assertParseFailure(parser, "1" + INVALID_EVENT_DESC, Event.MESSAGE_CONSTRAINTS); // invalid event

        // while parsing {@code PREFIX_EVENT} alone will reset the events of the {@code Trip} being edited,
        // parsing it together with a valid event results in error
        assertParseFailure(parser, "1" + EVENT_DESC_EATING + EVENT_DESC_SIGHTSEEING + EVENT_EMPTY,
                Event.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + EVENT_DESC_EATING + EVENT_EMPTY + EVENT_DESC_SIGHTSEEING,
                Event.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + EVENT_EMPTY + EVENT_DESC_EATING + EVENT_DESC_SIGHTSEEING,
                Event.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_DESCRIPTION_DESC,
                Title.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TRIP;
        String userInput = targetIndex.getOneBased() + EVENT_DESC_SIGHTSEEING
                + DESCRIPTION_DESC_GERMANY + TITLE_DESC_GERMANY + EVENT_DESC_EATING;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withTitle(VALID_TITLE_GERMANY)
                .withDescription(VALID_DESCRIPTION_GERMANY)
                .withEvents(VALID_EVENT_SIGHTSEEING, VALID_EVENT_EATING).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TRIP;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_GERMANY + DESCRIPTION_DESC_ANTARCTICA;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withTitle(VALID_TITLE_GERMANY)
                .withDescription(VALID_DESCRIPTION_ANTARCTICA).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_TRIP;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_GERMANY;
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withTitle(VALID_TITLE_GERMANY).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_GERMANY;
        descriptor = new EditTripDescriptorBuilder().withDescription(VALID_DESCRIPTION_GERMANY).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // events
        userInput = targetIndex.getOneBased() + EVENT_DESC_EATING;
        descriptor = new EditTripDescriptorBuilder().withEvents(VALID_EVENT_EATING).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TRIP;
        String userInput = targetIndex.getOneBased() + DESCRIPTION_DESC_GERMANY
                + EVENT_DESC_EATING + DESCRIPTION_DESC_GERMANY + EVENT_DESC_EATING
                + DESCRIPTION_DESC_ANTARCTICA + EVENT_DESC_SIGHTSEEING;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder()
                .withDescription(VALID_DESCRIPTION_ANTARCTICA).withEvents(VALID_EVENT_EATING, VALID_EVENT_SIGHTSEEING)
                .build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TRIP;
        String userInput = targetIndex.getOneBased() + INVALID_TITLE_DESC + VALID_DESCRIPTION_ANTARCTICA;
        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withDescription(
                VALID_DESCRIPTION_ANTARCTICA).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetEvents_success() {
        Index targetIndex = INDEX_THIRD_TRIP;
        String userInput = targetIndex.getOneBased() + EVENT_EMPTY;

        EditTripDescriptor descriptor = new EditTripDescriptorBuilder().withEvents().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
