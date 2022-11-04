package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.waddle.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.Model;
import seedu.waddle.model.ReadOnlyUserPrefs;
import seedu.waddle.model.ReadOnlyWaddle;
import seedu.waddle.model.Waddle;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.ItineraryBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullItinerary_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_itineraryAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingItineraryAdded modelStub = new ModelStubAcceptingItineraryAdded();
        Itinerary validItinerary = new ItineraryBuilder().build();

        CommandResult commandResult = new AddCommand(validItinerary).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validItinerary), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validItinerary), modelStub.itinerariesAdded);
    }

    @Test
    public void execute_duplicateItinerary_throwsCommandException() {
        Itinerary validItinerary = new ItineraryBuilder().build();
        AddCommand addCommand = new AddCommand(validItinerary);
        ModelStub modelStub = new ModelStubWithItinerary(validItinerary);

        assertThrows(CommandException.class,
                AddCommand.MESSAGE_DUPLICATE_ITINERARY, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Itinerary summer = new ItineraryBuilder().withName("Summer").build();
        Itinerary winter = new ItineraryBuilder().withName("Winter").build();
        AddCommand addSummerCommand = new AddCommand(summer);
        AddCommand addWinterCommand = new AddCommand(winter);

        // same object -> returns true
        assertTrue(addSummerCommand.equals(addSummerCommand));

        // same values -> returns true
        AddCommand addSummerCommandCopy = new AddCommand(summer);
        assertTrue(addSummerCommand.equals(addSummerCommandCopy));

        // different types -> returns false
        assertFalse(addSummerCommand.equals(1));

        // null -> returns false
        assertFalse(addSummerCommand.equals(null));

        // different itinerary -> returns false
        assertFalse(addSummerCommand.equals(addWinterCommand));
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
        public Path getWaddleFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWaddleFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setWaddle(ReadOnlyWaddle newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyWaddle getWaddle() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteItinerary(Itinerary target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setItinerary(Itinerary target, Itinerary editedItinerary) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Itinerary> getFilteredItineraryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredItineraryList(Predicate<Itinerary> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single itinerary.
     */
    private class ModelStubWithItinerary extends ModelStub {
        private final Itinerary itinerary;

        ModelStubWithItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            this.itinerary = itinerary;
        }

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            return this.itinerary.isSameItinerary(itinerary);
        }
    }

    /**
     * A Model stub that always accept the itinerary being added.
     */
    private class ModelStubAcceptingItineraryAdded extends ModelStub {
        final ArrayList<Itinerary> itinerariesAdded = new ArrayList<>();

        @Override
        public boolean hasItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            return itinerariesAdded.stream().anyMatch(itinerary::isSameItinerary);
        }

        @Override
        public void addItinerary(Itinerary itinerary) {
            requireNonNull(itinerary);
            itinerariesAdded.add(itinerary);
        }

        @Override
        public ReadOnlyWaddle getWaddle() {
            return new Waddle();
        }
    }

}
