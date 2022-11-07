package seedu.waddle.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.waddle.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.waddle.testutil.Assert.assertThrows;
import static seedu.waddle.testutil.TypicalItineraries.getTypicalWaddle;

import java.nio.file.Path;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.waddle.commons.core.GuiSettings;
import seedu.waddle.commons.core.index.MultiIndex;
import seedu.waddle.logic.StageManager;
import seedu.waddle.logic.commands.exceptions.CommandException;
import seedu.waddle.model.*;
import seedu.waddle.model.item.Item;
import seedu.waddle.model.itinerary.Itinerary;
import seedu.waddle.testutil.ItemBuilder;
import seedu.waddle.testutil.ItineraryBuilder;


public class AddItemCommandTest {

    @Test
    public void constructor_nullItem_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddItemCommand(null));
    }

    @Test
    public void execute_itemAcceptedByModel_addSuccessful() throws Exception {
        Item validItem = new ItemBuilder().build();
        Itinerary validItinerary = new ItineraryBuilder().build();
        AddItemCommandTest.ModelStubWithItinerary modelStub = new ModelStubWithItinerary(validItinerary);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setWishStage(validItinerary);
        CommandResult commandResult = new AddItemCommand(validItem).execute(modelStub);

        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, validItem), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateItem_throwsDuplicateItemException() {
        Item validItem = new ItemBuilder().build();
        Itinerary validItinerary = new ItineraryBuilder().build();
        validItinerary.addItem(validItem);
        AddItemCommand addItemCommand = new AddItemCommand(validItem);
        AddItemCommandTest.ModelStub modelStub = new ModelStubWithItinerary(validItinerary);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setWishStage(validItinerary);

        assertThrows(CommandException.class,
                AddItemCommand.MESSAGE_DUPLICATE_ITEM, () -> addItemCommand.execute(modelStub));
    }

    // integration test with typical Waddle
    @Test
    public void execute_newItem_addSuccessful() throws Exception {
        Item validItem = new ItemBuilder().build();
        Itinerary validItinerary = new ItineraryBuilder().build();
        Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());
        StageManager stageManager = StageManager.getInstance();
        stageManager.setWishStage(validItinerary);
        CommandResult commandResult = new AddItemCommand(validItem).execute(model);

        assertEquals(String.format(AddItemCommand.MESSAGE_SUCCESS, validItem),
                commandResult.getFeedbackToUser());
        assertEquals(true, validItinerary.hasItem(validItem));
    }

    // integration test with typical Waddle
    @Test
    public void execute_duplicateItem_throwsDuplicateItemException_modelIntegration() {
        Model model = new ModelManager(getTypicalWaddle(), new UserPrefs());
        Itinerary itineraryInList = model.getWaddle().getItineraryList().get(1);
        Item itemInList = itineraryInList.getItemList().get(0);
        StageManager stageManager = StageManager.getInstance();
        stageManager.setWishStage(itineraryInList);

        assertCommandFailure(new AddItemCommand(itemInList), model,
                AddItemCommand.MESSAGE_DUPLICATE_ITEM);
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
    }
}
