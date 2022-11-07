package seedu.realtime.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.realtime.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.RealTime;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.testutil.ModelStub;
import seedu.realtime.testutil.OfferBuilder;

public class AddOfferCommandTest {

    @Test
    public void constructor_nullOffer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddOfferCommand(null));
    }

    @Test
    public void execute_offerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingOfferAdded modelStub = new ModelStubAcceptingOfferAdded();
        Offer validOffer = new OfferBuilder().build();

        CommandResult commandResult = new AddOfferCommand(validOffer).execute(modelStub);

        assertEquals(String.format(AddOfferCommand.MESSAGE_SUCCESS, validOffer), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validOffer), modelStub.offersAdded);
    }

    @Test
    public void execute_duplicateOffer_throwsCommandException() {
        Offer validOffer = new OfferBuilder().build();
        AddOfferCommand addOfferCommand = new AddOfferCommand(validOffer);
        ModelStub modelStub = new ModelStubWithOffer(validOffer);

        assertThrows(CommandException.class,
            AddOfferCommand.MESSAGE_DUPLICATE_OFFER, () -> addOfferCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Offer alice = new OfferBuilder().withBuyer("Alice").build();
        Offer bob = new OfferBuilder().withBuyer("Bob").build();
        AddOfferCommand addAliceCommand = new AddOfferCommand(alice);
        AddOfferCommand addBobCommand = new AddOfferCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddOfferCommand addAliceCommandCopy = new AddOfferCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> return false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different client -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single offer.
     */
    private class ModelStubWithOffer extends ModelStub {
        private final Offer offer;

        ModelStubWithOffer(Offer offer) {
            requireNonNull(offer);
            this.offer = offer;
        }

        @Override
        public boolean hasOffer(Offer offer) {
            requireNonNull(offer);
            return this.offer.isSameOffer(offer);
        }
    }

    /**
     * A Model stub that always accept the offer being added.
     */
    private class ModelStubAcceptingOfferAdded extends ModelStub {
        final ArrayList<Offer> offersAdded = new ArrayList<>();

        @Override
        public boolean hasOffer(Offer offer) {
            requireNonNull(offer);
            return offersAdded.stream().anyMatch(offer::isSameOffer);
        }

        @Override
        public void addOffer(Offer offer) {
            requireNonNull(offer);
            offersAdded.add(offer);
        }

        @Override
        public ReadOnlyRealTime getRealTime() {
            return new RealTime();
        }
    }
}
