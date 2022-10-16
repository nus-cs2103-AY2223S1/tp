package foodwhere.logic.commands;

import static foodwhere.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.GuiSettings;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.AddressBook;
import foodwhere.model.Model;
import foodwhere.model.ReadOnlyAddressBook;
import foodwhere.model.ReadOnlyUserPrefs;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;
import foodwhere.testutil.StallBuilder;
import javafx.collections.ObservableList;

public class SAddCommandTest {

    @Test
    public void constructor_nullStall_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SAddCommand(null));
    }

    @Test
    public void execute_stallAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingStallAdded modelStub = new ModelStubAcceptingStallAdded();
        Stall validStall = new StallBuilder().build();

        CommandResult commandResult = new SAddCommand(validStall).execute(modelStub);

        assertEquals(String.format(SAddCommand.MESSAGE_SUCCESS, validStall), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStall), modelStub.stallsAdded);
    }

    @Test
    public void execute_duplicateStall_throwsCommandException() {
        Stall validStall = new StallBuilder().build();
        SAddCommand sAddCommand = new SAddCommand(validStall);
        ModelStub modelStub = new ModelStubWithStall(validStall);

        assertThrows(CommandException.class, SAddCommand.MESSAGE_DUPLICATE_STALL, () -> sAddCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Stall alice = new StallBuilder().withName("Alice").build();
        Stall bob = new StallBuilder().withName("Bob").build();
        SAddCommand addAliceCommand = new SAddCommand(alice);
        SAddCommand addBobCommand = new SAddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        SAddCommand addAliceCommandCopy = new SAddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different stall -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStall(Stall stall) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addReview(Review review) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReview(Review target, Review editedReview) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStall(Stall stall) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasReview(Review review) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStall(Stall target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteReview(Review target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStall(Stall target, Stall editedStall) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortStalls() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortReviews() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Stall> getFilteredStallList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Review> getFilteredReviewList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStallList(Predicate<Stall> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredReviewList(Predicate<Review> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single stall.
     */
    private class ModelStubWithStall extends ModelStub {
        private final Stall stall;

        ModelStubWithStall(Stall stall) {
            requireNonNull(stall);
            this.stall = stall;
        }

        @Override
        public boolean hasStall(Stall stall) {
            requireNonNull(stall);
            return this.stall.isSameStall(stall);
        }
    }

    /**
     * A Model stub that always accept the stall being added.
     */
    private class ModelStubAcceptingStallAdded extends ModelStub {
        final ArrayList<Stall> stallsAdded = new ArrayList<>();

        @Override
        public boolean hasStall(Stall stall) {
            requireNonNull(stall);
            return stallsAdded.stream().anyMatch(stall::isSameStall);
        }

        @Override
        public void addStall(Stall stall) {
            requireNonNull(stall);
            stallsAdded.add(stall);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
