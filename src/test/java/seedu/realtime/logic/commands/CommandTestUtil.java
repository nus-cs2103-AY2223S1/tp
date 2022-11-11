package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_ASKING_PRICE;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_LISTING_ID;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_OFFER;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.realtime.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.realtime.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.realtime.commons.core.index.Index;
import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.model.Model;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.listing.IdContainsKeywordsPredicate;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.listing.exceptions.ListingNotFoundException;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.offer.OfferContainsListingIdPredicate;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.NameContainsKeywordsPredicate;
import seedu.realtime.testutil.EditClientDescriptorBuilder;
import seedu.realtime.testutil.EditListingDescriptorBuilder;
import seedu.realtime.testutil.EditOfferDescriptorBuilder;


/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_TAG_4_BEDROOM = "4bedroom";
    public static final String VALID_TAG_GARDEN = "garden";
    public static final String VALID_TAG_POOL = "pool";
    public static final String VALID_TAG_VIEWING = "viewing";
    public static final String VALID_TAG_CONTRACT = "contractSigning";
    public static final String VALID_TAG_CHILDREN = "withChildren";
    public static final String VALID_ID_AMY = "amy";
    public static final String VALID_ID_BOB = "bob";
    public static final String VALID_PRICE_1 = "1";
    public static final String VALID_PRICE_2 = "2";
    public static final String VALID_PRICE_3 = "1000000";
    public static final String VALID_DATETIME_1 = "2022-10-23 23:59";
    public static final String VALID_DATETIME_2 = "2000-11-24 12:00";
    public static final String VALID_LISTING_ID_BEDOK = "BEDOK";
    public static final String VALID_LISTING_ID_1 = "1";
    public static final String VALID_LISTING_ID_2 = "2";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ADDRESS_DESC_AMY = " " + PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String TAG_DESC_4_BEDROOM = " " + PREFIX_TAG + VALID_TAG_4_BEDROOM;
    public static final String TAG_DESC_GARDEN = " " + PREFIX_TAG + VALID_TAG_GARDEN;
    public static final String TAG_DESC_POOL = " " + PREFIX_TAG + VALID_TAG_POOL;
    public static final String ID_DESC_AMY = " " + PREFIX_ID + VALID_ID_AMY;
    public static final String ID_DESC_BOB = " " + PREFIX_ID + VALID_ID_BOB;
    public static final String PRICE_DESC_1 = " " + PREFIX_ASKING_PRICE + VALID_PRICE_1;
    public static final String PRICE_DESC_2 = " " + PREFIX_ASKING_PRICE + VALID_PRICE_2;
    public static final String LISTING_ID_DESC_AMY = " " + PREFIX_LISTING_ID + VALID_LISTING_ID_1;
    public static final String LISTING_ID_DESC_BOB = " " + PREFIX_LISTING_ID + VALID_LISTING_ID_2;
    public static final String OFFER_PRICE_DESC_AMY = " " + PREFIX_OFFER + VALID_PRICE_1;
    public static final String OFFER_PRICE_DESC_BOB = " " + PREFIX_OFFER + VALID_PRICE_2;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ADDRESS_DESC = " " + PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_OFFER_PRICE = " " + PREFIX_OFFER + "123p123"; // 'p' not allowed in prices
    public static final String INVALID_LISTING_ID = " " + PREFIX_LISTING_ID; // empty string not allowed for listing IDs

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditClientCommand.EditClientDescriptor DESC_AMY;
    public static final EditClientCommand.EditClientDescriptor DESC_BOB;


    static {
        DESC_AMY = new EditClientDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditClientDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    public static final EditListingCommand.EditListingDescriptor DESC_LISTING_AMY;
    public static final EditListingCommand.EditListingDescriptor DESC_LISTING_BOB;

    static {
        DESC_LISTING_AMY = new EditListingDescriptorBuilder().withId(VALID_ID_AMY).withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY).withAskingPrice(VALID_PRICE_1).withTags(VALID_TAG_4_BEDROOM).build();
        DESC_LISTING_BOB = new EditListingDescriptorBuilder().withId(VALID_ID_BOB).withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB).withAskingPrice(VALID_PRICE_2).withTags(VALID_TAG_POOL).build();
    }

    public static final EditOfferCommand.EditOfferDescriptor DESC_OFFER_AMY;
    public static final EditOfferCommand.EditOfferDescriptor DESC_OFFER_BOB;

    static {
        DESC_OFFER_AMY = new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_AMY)
                .withOfferPrice(VALID_PRICE_1).withListing(VALID_LISTING_ID_1).build();
        DESC_OFFER_BOB = new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_BOB)
                .withOfferPrice(VALID_PRICE_2).withListing(VALID_LISTING_ID_2).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RealTime expectedRealTime = new RealTime(actualModel.getRealTime());
        List<Client> expectedFilteredClientList = new ArrayList<>(actualModel.getFilteredClientList());
        List<Offer> expectedFilteredOfferList = new ArrayList<>(actualModel.getFilteredOfferList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRealTime, actualModel.getRealTime());
        assertEquals(expectedFilteredClientList, actualModel.getFilteredClientList());
        assertEquals(expectedFilteredOfferList, actualModel.getFilteredOfferList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered client list and selected client in {@code actualModel} remain unchanged
     */
    public static void assertListingNotFoundFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        RealTime expectedRealTime = new RealTime(actualModel.getRealTime());
        List<Listing> expectedFilteredListingList = new ArrayList<>(actualModel.getFilteredListingList());

        assertThrows(ListingNotFoundException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedRealTime, actualModel.getRealTime());
        assertEquals(expectedFilteredListingList, actualModel.getFilteredListingList());
    }


    /**
     * Updates {@code model}'s filtered list to show only the client at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showClientAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredClientList().size());

        Client client = model.getFilteredClientList().get(targetIndex.getZeroBased());
        final String[] splitName = client.getName().fullName.split("\\s+");
        model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredClientList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the listing at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showListingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredListingList().size());

        Listing listing = model.getFilteredListingList().get(targetIndex.getZeroBased());
        final String id = listing.getId().value;
        model.updateFilteredListingList(new IdContainsKeywordsPredicate(id));

        assertEquals(1, model.getFilteredListingList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the offer at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showOfferAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredOfferList().size());

        Offer offer = model.getFilteredOfferList().get(targetIndex.getZeroBased());
        final String[] splitListingId = offer.getListing().toString().split("\\s+");
        model.updateFilteredOfferList(new OfferContainsListingIdPredicate(Arrays.asList(splitListingId[0])));

        assertEquals(2, model.getFilteredOfferList().size());
    }

}
