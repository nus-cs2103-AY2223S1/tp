package seedu.realtime.logic.parser;

import static seedu.realtime.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.realtime.logic.commands.CommandTestUtil.INVALID_LISTING_ID;
import static seedu.realtime.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.realtime.logic.commands.CommandTestUtil.INVALID_OFFER_PRICE;
import static seedu.realtime.logic.commands.CommandTestUtil.LISTING_ID_DESC_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.LISTING_ID_DESC_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.OFFER_PRICE_DESC_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.OFFER_PRICE_DESC_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_2;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_2;
import static seedu.realtime.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realtime.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.realtime.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.realtime.testutil.TypicalIndexes.THIRD_INDEX;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.core.index.Index;
import seedu.realtime.logic.commands.EditOfferCommand;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Name;
import seedu.realtime.testutil.EditOfferDescriptorBuilder;

public class EditOfferCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
        String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditOfferCommand.MESSAGE_USAGE);

    private EditOfferCommandParser parser = new EditOfferCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditOfferCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_OFFER_PRICE, Price.MESSAGE_CONSTRAINTS); // invalid price
        assertParseFailure(parser, "1" + INVALID_LISTING_ID, ListingId.MESSAGE_CONSTRAINTS); // invalid listing ID


        // invalid offer price followed by valid listing ID
        assertParseFailure(parser, "1" + INVALID_OFFER_PRICE + LISTING_ID_DESC_AMY, Price.MESSAGE_CONSTRAINTS);

        // valid price followed by invalid price. The test case for invalid price followed by valid price
        // is tested at {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + OFFER_PRICE_DESC_BOB + INVALID_OFFER_PRICE, Price.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_OFFER_PRICE + VALID_LISTING_ID_1,
            Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = SECOND_INDEX;
        String userInput = targetIndex.getOneBased() + NAME_DESC_BOB + OFFER_PRICE_DESC_BOB + LISTING_ID_DESC_BOB;

        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_BOB)
            .withOfferPrice(VALID_PRICE_2).withListing(VALID_LISTING_ID_2).build();
        EditOfferCommand expectedCommand = new EditOfferCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + OFFER_PRICE_DESC_BOB + NAME_DESC_BOB;

        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder().withOfferPrice(VALID_PRICE_2)
            .withBuyer(VALID_NAME_BOB).build();
        EditOfferCommand expectedCommand = new EditOfferCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = THIRD_INDEX;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY;
        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder()
                .withBuyer(VALID_NAME_AMY).build();
        EditOfferCommand expectedCommand = new EditOfferCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // offer price
        userInput = targetIndex.getOneBased() + OFFER_PRICE_DESC_AMY;
        descriptor = new EditOfferDescriptorBuilder().withOfferPrice(VALID_PRICE_1).build();
        expectedCommand = new EditOfferCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // listing ID
        userInput = targetIndex.getOneBased() + LISTING_ID_DESC_AMY;
        descriptor = new EditOfferDescriptorBuilder().withListing(VALID_LISTING_ID_1).build();
        expectedCommand = new EditOfferCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AMY + LISTING_ID_DESC_AMY + OFFER_PRICE_DESC_AMY
            + NAME_DESC_AMY + LISTING_ID_DESC_AMY + OFFER_PRICE_DESC_AMY
            + NAME_DESC_BOB + LISTING_ID_DESC_BOB + OFFER_PRICE_DESC_BOB;

        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_BOB)
                .withListing(VALID_LISTING_ID_2).withOfferPrice(VALID_PRICE_2).build();
        EditOfferCommand expectedCommand = new EditOfferCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = FIRST_INDEX;
        String userInput = targetIndex.getOneBased() + INVALID_OFFER_PRICE + OFFER_PRICE_DESC_BOB;
        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder()
                .withOfferPrice(VALID_PRICE_2).build();
        EditOfferCommand expectedCommand = new EditOfferCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + NAME_DESC_BOB + LISTING_ID_DESC_BOB + OFFER_PRICE_DESC_BOB;
        descriptor = new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_BOB).withListing(VALID_LISTING_ID_2)
            .withOfferPrice(VALID_PRICE_2).build();
        expectedCommand = new EditOfferCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
