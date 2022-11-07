package seedu.waddle.logic.parser;

import static seedu.waddle.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.waddle.logic.commands.CommandTestUtil.COST_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.COST_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_COST_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_ITEM_DESC_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_ITEM_DURATION_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DESC_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DESC_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DURATION_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.ITEM_DURATION_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.waddle.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.waddle.logic.commands.CommandTestUtil.PRIORITY_DESC_ART;
import static seedu.waddle.logic.commands.CommandTestUtil.PRIORITY_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_COST_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_DURATION_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_ITEM_DESC_SHOPPING;
import static seedu.waddle.logic.commands.CommandTestUtil.VALID_PRIORITY_SHOPPING;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.waddle.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.waddle.testutil.TypicalItems.getShopping;

import org.junit.jupiter.api.Test;

import seedu.waddle.logic.commands.AddItemCommand;
import seedu.waddle.model.item.Cost;
import seedu.waddle.model.item.Duration;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.item.Priority;
import seedu.waddle.model.itinerary.Description;

public class AddItemCommandParserTest {
    private final AddItemCommandParser parser = new AddItemCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Item expectedItem = getShopping();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING, new AddItemCommand(expectedItem));

        // multiple desc - last desc accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_DESC_DESC_ART + ITEM_DESC_DESC_SHOPPING
                + ITEM_DURATION_DESC_SHOPPING + COST_DESC_SHOPPING
                + PRIORITY_DESC_SHOPPING, new AddItemCommand(expectedItem));

        // multiple duration - last duration accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_ART
                + ITEM_DURATION_DESC_SHOPPING + COST_DESC_SHOPPING
                + PRIORITY_DESC_SHOPPING, new AddItemCommand(expectedItem));

        // multiple cost - last cost accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + COST_DESC_ART + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING, new AddItemCommand(expectedItem));

        // multiple priority - last priority accepted
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + COST_DESC_SHOPPING + PRIORITY_DESC_ART + PRIORITY_DESC_SHOPPING, new AddItemCommand(expectedItem));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE);

        // missing desc prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_ITEM_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING, expectedMessage);

        // missing duration prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + ITEM_DESC_DESC_SHOPPING + VALID_DURATION_SHOPPING
                + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING, expectedMessage);

        // missing cost prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_ITEM_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + VALID_COST_SHOPPING + PRIORITY_DESC_SHOPPING, expectedMessage);

        // missing priority prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_ITEM_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                + COST_DESC_SHOPPING + VALID_PRIORITY_SHOPPING, expectedMessage);

        // all prefixes missing prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_ITEM_DESC_SHOPPING + VALID_DURATION_SHOPPING
                + VALID_COST_SHOPPING + VALID_PRIORITY_SHOPPING, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid desc
        assertParseFailure(parser, INVALID_ITEM_DESC_DESC + ITEM_DURATION_DESC_SHOPPING + COST_DESC_SHOPPING
                + PRIORITY_DESC_SHOPPING, Description.MESSAGE_CONSTRAINTS);

        // invalid duration
        assertParseFailure(parser, ITEM_DESC_DESC_SHOPPING + INVALID_ITEM_DURATION_DESC + COST_DESC_SHOPPING
                + PRIORITY_DESC_SHOPPING, Duration.MESSAGE_CONSTRAINTS);

        // invalid cost
        assertParseFailure(parser, ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING + INVALID_COST_DESC
                + PRIORITY_DESC_SHOPPING, Cost.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING + COST_DESC_SHOPPING
                + INVALID_PRIORITY_DESC, Priority.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_ITEM_DESC_DESC + ITEM_DURATION_DESC_SHOPPING + COST_DESC_SHOPPING
                + INVALID_PRIORITY_DESC, Description.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + ITEM_DESC_DESC_SHOPPING + ITEM_DURATION_DESC_SHOPPING
                        + COST_DESC_SHOPPING + PRIORITY_DESC_SHOPPING,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddItemCommand.MESSAGE_USAGE));
    }
}
