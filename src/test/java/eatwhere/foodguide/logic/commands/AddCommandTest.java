package eatwhere.foodguide.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import eatwhere.foodguide.commons.core.GuiSettings;
import eatwhere.foodguide.logic.commands.exceptions.CommandException;
import eatwhere.foodguide.model.FoodGuide;
import eatwhere.foodguide.model.Model;
import eatwhere.foodguide.model.ReadOnlyFoodGuide;
import eatwhere.foodguide.model.ReadOnlyUserPrefs;
import eatwhere.foodguide.model.eatery.Eatery;
import eatwhere.foodguide.testutil.Assert;
import eatwhere.foodguide.testutil.EateryBuilder;
import javafx.collections.ObservableList;

public class AddCommandTest {

    @Test
    public void constructor_nullEatery_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_eateryAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingEateryAdded modelStub = new ModelStubAcceptingEateryAdded();
        Eatery validEatery = new EateryBuilder().build();

        CommandResult commandResult = new AddCommand(validEatery).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validEatery), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEatery), modelStub.eateriesAdded);
    }

    @Test
    public void execute_duplicateEatery_throwsCommandException() {
        Eatery validEatery = new EateryBuilder().build();
        AddCommand addCommand = new AddCommand(validEatery);
        ModelStub modelStub = new ModelStubWithEatery(validEatery);

        Assert.assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_EATERY, ()
                -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Eatery alice = new EateryBuilder().withName("Alice").build();
        Eatery bob = new EateryBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different eatery -> returns false
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
        public Path getFoodGuideFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodGuideFilePath(Path foodGuideFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEatery(Eatery eatery) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setFoodGuide(ReadOnlyFoodGuide foodGuide) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyFoodGuide getFoodGuide() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEatery(Eatery eatery) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEatery(Eatery target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEatery(Eatery target, Eatery editedEatery) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Eatery> getFilteredEateryList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEateryList(Predicate<Eatery> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single eatery.
     */
    private class ModelStubWithEatery extends ModelStub {
        private final Eatery eatery;

        ModelStubWithEatery(Eatery eatery) {
            requireNonNull(eatery);
            this.eatery = eatery;
        }

        @Override
        public boolean hasEatery(Eatery eatery) {
            requireNonNull(eatery);
            return this.eatery.isSameEatery(eatery);
        }
    }

    /**
     * A Model stub that always accept the eatery being added.
     */
    private class ModelStubAcceptingEateryAdded extends ModelStub {
        final ArrayList<Eatery> eateriesAdded = new ArrayList<>();

        @Override
        public boolean hasEatery(Eatery eatery) {
            requireNonNull(eatery);
            return eateriesAdded.stream().anyMatch(eatery::isSameEatery);
        }

        @Override
        public void addEatery(Eatery eatery) {
            requireNonNull(eatery);
            eateriesAdded.add(eatery);
        }

        @Override
        public ReadOnlyFoodGuide getFoodGuide() {
            return new FoodGuide();
        }
    }

}
