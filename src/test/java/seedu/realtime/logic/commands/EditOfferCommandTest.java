package seedu.realtime.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_OFFER_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.DESC_OFFER_BOB;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_LISTING_ID_1;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.realtime.logic.commands.CommandTestUtil.VALID_PRICE_1;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.realtime.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realtime.logic.commands.CommandTestUtil.showOfferAtIndex;
import static seedu.realtime.testutil.TypicalIndexes.FIRST_INDEX;
import static seedu.realtime.testutil.TypicalIndexes.SECOND_INDEX;
import static seedu.realtime.testutil.TypicalOffers.getTypicalRealTime;

import org.junit.jupiter.api.Test;

import seedu.realtime.commons.core.Messages;
import seedu.realtime.commons.core.index.Index;
import seedu.realtime.model.Model;
import seedu.realtime.model.ModelManager;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.UserPrefs;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.testutil.EditOfferDescriptorBuilder;
import seedu.realtime.testutil.OfferBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditOfferCommand.
 */
public class EditOfferCommandTest {

    private Model model = new ModelManager(getTypicalRealTime(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Offer editedOffer = new OfferBuilder().build();
        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder(editedOffer).build();
        EditOfferCommand editOfferCommand = new EditOfferCommand(FIRST_INDEX, descriptor);

        String expectedMessage = String.format(EditOfferCommand.MESSAGE_EDIT_OFFER_SUCCESS, editedOffer);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());
        expectedModel.setOffer(model.getFilteredOfferList().get(0), editedOffer);

        assertCommandSuccess(editOfferCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastOffer = Index.fromOneBased(model.getFilteredOfferList().size());
        Offer lastOffer = model.getFilteredOfferList().get(indexLastOffer.getZeroBased());

        OfferBuilder offerInList = new OfferBuilder(lastOffer);
        Offer editedOffer = offerInList.withBuyer(VALID_NAME_AMY).withOfferPrice(VALID_PRICE_1)
                .withListing(VALID_LISTING_ID_1).build();

        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_AMY)
                .withOfferPrice(VALID_PRICE_1).withListing(VALID_LISTING_ID_1).build();
        EditOfferCommand editOfferCommand = new EditOfferCommand(indexLastOffer, descriptor);

        String expectedMessage = String.format(EditOfferCommand.MESSAGE_EDIT_OFFER_SUCCESS, editedOffer);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());
        expectedModel.setOffer(lastOffer, editedOffer);

        assertCommandSuccess(editOfferCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditOfferCommand editOfferCommand = new EditOfferCommand(
                FIRST_INDEX, new EditOfferCommand.EditOfferDescriptor());
        Offer editedOffer = model.getFilteredOfferList().get(FIRST_INDEX.getZeroBased());

        String expectedMessage = String.format(EditOfferCommand.MESSAGE_EDIT_OFFER_SUCCESS, editedOffer);

        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());

        assertCommandSuccess(editOfferCommand, model, expectedMessage, expectedModel);
    }

//    @Test
//    public void execute_filteredList_success() {
//        showOfferAtIndex(model, FIRST_INDEX);
//
//        Offer offerInFilteredList = model.getFilteredOfferList().get(FIRST_INDEX.getZeroBased());
//        Offer editedOffer = new OfferBuilder(offerInFilteredList).withBuyer(VALID_NAME_AMY).build();
//        EditOfferCommand editOfferCommand = new EditOfferCommand(FIRST_INDEX,
//            new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_AMY).build());
//
//        String expectedMessage = String.format(EditOfferCommand.MESSAGE_EDIT_OFFER_SUCCESS, editedOffer);
//
//        Model expectedModel = new ModelManager(new RealTime(model.getRealTime()), new UserPrefs());
//        expectedModel.setOffer(model.getFilteredOfferList().get(0), editedOffer);
//
//        assertCommandSuccess(editOfferCommand, model, expectedMessage, expectedModel);
//    }

    @Test
    public void execute_duplicateOfferUnfilteredList_failure() {
        Offer firstOffer = model.getFilteredOfferList().get(FIRST_INDEX.getZeroBased());
        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder(firstOffer).build();
        EditOfferCommand editOfferCommand = new EditOfferCommand(SECOND_INDEX, descriptor);

        assertCommandFailure(editOfferCommand, model, EditOfferCommand.MESSAGE_DUPLICATE_OFFER);
    }

//    @Test
//    public void execute_duplicateOfferFilteredList_failure() {
//        showOfferAtIndex(model, FIRST_INDEX);
//
//        // edit offer in filtered list into a duplicate in realtime
//        Offer offerInList = model.getRealTime().getOfferList().get(SECOND_INDEX.getZeroBased());
//        EditOfferCommand editOfferCommand = new EditOfferCommand(FIRST_INDEX,
//                new EditOfferDescriptorBuilder(offerInList).build());
//
//        assertCommandFailure(editOfferCommand, model, EditOfferCommand.MESSAGE_DUPLICATE_OFFER);
//    }

    @Test
    public void execute_invalidOfferIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredOfferList().size() + 1);
        EditOfferCommand.EditOfferDescriptor descriptor = new EditOfferDescriptorBuilder()
                .withBuyer(VALID_NAME_AMY).build();
        EditOfferCommand editOfferCommand = new EditOfferCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editOfferCommand, model, Messages.MESSAGE_INVALID_OFFER_DISPLAYED_INDEX);
    }

//    /**
//     * Edit filtered list where index is larger than size of filtered list,
//     * but smaller than size of realtime
//     */
//    @Test
//    public void execute_invalidOfferIndexFilteredList_failure() {
//        showOfferAtIndex(model, FIRST_INDEX);
//        Index outOfBoundIndex = SECOND_INDEX;
//        // ensures that outOfboundIndex is still in bounds of realtime list
//        assertTrue(outOfBoundIndex.getZeroBased() < model.getRealTime().getOfferList().size());
//
//        EditOfferCommand editOfferCommand = new EditOfferCommand(outOfBoundIndex,
//                new EditOfferDescriptorBuilder().withBuyer(VALID_NAME_AMY).build());
//
//        assertCommandFailure(editOfferCommand, model, Messages.MESSAGE_INVALID_OFFER_DISPLAYED_INDEX);
//    }

    @Test
    public void equals() {
        final EditOfferCommand standardCommand = new EditOfferCommand(FIRST_INDEX, DESC_OFFER_AMY);

        // same values -> returns true
        EditOfferCommand.EditOfferDescriptor copyDescriptor = new EditOfferCommand.EditOfferDescriptor(DESC_OFFER_AMY);
        EditOfferCommand commandWithSameValues = new EditOfferCommand(FIRST_INDEX, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditOfferCommand(SECOND_INDEX, DESC_OFFER_AMY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditOfferCommand(FIRST_INDEX, DESC_OFFER_BOB)));
    }
}
