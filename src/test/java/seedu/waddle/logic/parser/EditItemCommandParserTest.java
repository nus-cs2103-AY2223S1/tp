package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.COST_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.COST_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.COST_DESC_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COST_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_ITEM_DESC_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_ITEM_DURATION_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DESC_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DESC_DESC_BREAKFAST;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DESC_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DURATION_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DURATION_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.PRIORITY_DESC_BEACH;
import static seedu.waddle.logic.commands.CommandTestUtil.PRIORITY_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.PRIORITY_DESC_TOUR;
import static seedu.waddle.logic.commands.CommandTestUtil.START_TIME_DESC_1715;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SKINNY;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_BREAKFAST;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_TOUR;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_START_TIME_1715;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.waddle.commons.core.index.Index;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.commands.EditItemCommand;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Description;
import seedu.waddle.testutil.EditItemDescriptorBuilder;

public class EditItemCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditItemCommand.MESSAGE_USAGE);

    private final EditItemCommandParser parser = new EditItemCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_ITEM_DESC_ART, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditItemCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + ITEM_DESC_DESC_ART, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + ITEM_DESC_DESC_ART, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_ITEM_DESC_DESC, Description.MESSAGE_CONSTRAINTS); // invalid desc
        assertParseFailure(parser, "1" + INVALID_ITEM_DURATION_DESC, Duration.MESSAGE_CONSTRAINTS); // invalid duration
        assertParseFailure(parser, "1" + INVALID_COST_DESC, Cost.MESSAGE_CONSTRAINTS); // invalid cost
        assertParseFailure(parser, "1" + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS); // invalid priority

        // invalid desc followed by valid duration
        assertParseFailure(parser, "1" + INVALID_ITEM_DESC_DESC + ITEM_DURATION_DESC_SHOPPING,
                Description.MESSAGE_CONSTRAINTS);

        // valid duration followed by invalid duration.
        assertParseFailure(parser, "1" + ITEM_DURATION_DESC_SHOPPING + INVALID_ITEM_DURATION_DESC,
                Duration.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_ITEM_DESC_DESC + INVALID_ITEM_DURATION_DESC
                + INVALID_COST_DESC + INVALID_PRIORITY_DESC, Description.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        MultiIndex targetIndex = new MultiIndex();
        targetIndex.addIndex(Index.fromZeroBased(0));
        String userInput = Index.fromZeroBased(0).getOneBased() + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withDescription(VALID_ITEM_DESC_SHOPPING).withDuration(VALID_DURATION_SHOPPING)
                .withCost(VALID_COST_SHOPPING).withPriority(VALID_PRIORITY_SHOPPING).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        MultiIndex targetIndex = new MultiIndex();
        targetIndex.addIndex(Index.fromZeroBased(0));
        String userInput = Index.fromZeroBased(0).getOneBased() + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withDescription(VALID_ITEM_DESC_SHOPPING).withDuration(VALID_DURATION_SHOPPING).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        MultiIndex targetIndex = new MultiIndex();
        targetIndex.addIndex(Index.fromZeroBased(0));

        // desc
        String userInput = Index.fromZeroBased(0).getOneBased() + ITEM_DESC_DESC_SHOPPING;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withDescription(VALID_ITEM_DESC_SHOPPING).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // duration
        userInput = Index.fromZeroBased(0).getOneBased() + ITEM_DURATION_DESC_SHOPPING;
        descriptor = new EditItemDescriptorBuilder().withDuration(VALID_DURATION_SHOPPING).build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // cost
        userInput = Index.fromZeroBased(0).getOneBased() + COST_DESC_SHOPPING;
        descriptor = new EditItemDescriptorBuilder().withCost(VALID_COST_SHOPPING).build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // priority
        userInput = Index.fromZeroBased(0).getOneBased() + PRIORITY_DESC_SHOPPING;
        descriptor = new EditItemDescriptorBuilder().withPriority(VALID_PRIORITY_SHOPPING).build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // startTime
        targetIndex.addIndex(Index.fromZeroBased(0));
        userInput = targetIndex + START_TIME_DESC_1715;
        descriptor = new EditItemDescriptorBuilder().withStartTime(VALID_START_TIME_1715.toString()).build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        MultiIndex targetIndex = new MultiIndex();
        targetIndex.addIndex(Index.fromZeroBased(0));
        String userInput = Index.fromZeroBased(0).getOneBased() + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + ITEM_DURATION_DESC_ART + ITEM_DESC_DESC_BREAKFAST + COST_DESC_ART + PRIORITY_DESC_BEACH
                + COST_DESC_SKINNY + PRIORITY_DESC_TOUR;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withDescription(VALID_ITEM_DESC_BREAKFAST).withDuration(VALID_DURATION_ART)
                .withCost(VALID_COST_SKINNY).withPriority(VALID_PRIORITY_TOUR).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        MultiIndex targetIndex = new MultiIndex();
        targetIndex.addIndex(Index.fromZeroBased(0));
        String userInput = Index.fromZeroBased(0).getOneBased() + INVALID_ITEM_DESC_DESC + ITEM_DESC_DESC_SHOPPING;
        EditItemCommand.EditItemDescriptor descriptor = new EditItemDescriptorBuilder()
                .withDescription(VALID_ITEM_DESC_SHOPPING).build();
        EditItemCommand expectedCommand = new EditItemCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

        //priority
        userInput = Index.fromZeroBased(0).getOneBased() + ITEM_DESC_DESC_SHOPPING + INVALID_ITEM_DURATION_DESC
                + COST_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING;
        descriptor = new EditItemDescriptorBuilder().withDescription(VALID_ITEM_DESC_SHOPPING)
                .withCost(VALID_COST_SHOPPING).withDuration(VALID_DURATION_SHOPPING).build();
        expectedCommand = new EditItemCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
