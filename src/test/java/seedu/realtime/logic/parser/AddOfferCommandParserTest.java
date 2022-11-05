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
import static seedu.realtime.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.realtime.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static seedu.realtime.logic.commands.CommandTestUtil.showClientAtIndex;
import static seedu.realtime.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.realtime.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.realtime.testutil.TypicalOffers.ALICE;
import static seedu.realtime.testutil.TypicalOffers.AMY;

import org.junit.jupiter.api.Test;

import seedu.realtime.logic.commands.AddOfferCommand;
import seedu.realtime.logic.commands.CommandTestUtil;
import seedu.realtime.model.listing.ListingId;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.offer.Price;
import seedu.realtime.model.person.Name;
import seedu.realtime.testutil.OfferBuilder;

public class AddOfferCommandParserTest {
    private AddOfferCommandParser parser = new AddOfferCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Offer expectedOffer = new OfferBuilder(AMY).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + LISTING_ID_DESC_AMY + NAME_DESC_AMY
            + OFFER_PRICE_DESC_AMY, new AddOfferCommand(expectedOffer));

        // multiple names - last name accepted
        assertParseSuccess(parser, NAME_DESC_BOB + NAME_DESC_AMY + LISTING_ID_DESC_AMY
            + OFFER_PRICE_DESC_AMY, new AddOfferCommand(expectedOffer));

        // multiple listing IDs - last listing ID accepted
        assertParseSuccess(parser, NAME_DESC_AMY + LISTING_ID_DESC_BOB + LISTING_ID_DESC_AMY
            + OFFER_PRICE_DESC_AMY, new AddOfferCommand(expectedOffer));

        // multiple offer prices - last offer prices accepted
        assertParseSuccess(parser, NAME_DESC_AMY +  LISTING_ID_DESC_AMY + OFFER_PRICE_DESC_BOB
            + OFFER_PRICE_DESC_AMY, new AddOfferCommand(expectedOffer));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOfferCommand.MESSAGE_USAGE);

        // missing name prefix
        assertParseFailure(parser, VALID_NAME_BOB + LISTING_ID_DESC_BOB + OFFER_PRICE_DESC_BOB,
                expectedMessage);

        // missing offer price prefix
        assertParseFailure(parser, NAME_DESC_BOB + LISTING_ID_DESC_BOB + VALID_PRICE_1, expectedMessage);

        // missing listing ID prefix
        assertParseFailure(parser, NAME_DESC_BOB + OFFER_PRICE_DESC_BOB + VALID_LISTING_ID_1, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_NAME_BOB + VALID_LISTING_ID_1 + VALID_PRICE_1, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_NAME_DESC + LISTING_ID_DESC_AMY + OFFER_PRICE_DESC_AMY,
                Name.MESSAGE_CONSTRAINTS);

        // invalid listing ID
        assertParseFailure(parser, NAME_DESC_BOB + INVALID_LISTING_ID + OFFER_PRICE_DESC_BOB,
                ListingId.MESSAGE_CONSTRAINTS);

        // invalid offer price
        assertParseFailure(parser, NAME_DESC_BOB + LISTING_ID_DESC_BOB + INVALID_OFFER_PRICE,
                Price.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_NAME_DESC + LISTING_ID_DESC_BOB + INVALID_OFFER_PRICE,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_BOB + LISTING_ID_DESC_BOB
            + OFFER_PRICE_DESC_BOB, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddOfferCommand.MESSAGE_USAGE));
    }
}
