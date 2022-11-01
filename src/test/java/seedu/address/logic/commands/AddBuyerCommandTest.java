package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.BuyerBook;
import seedu.address.model.ReadOnlyBuyerBook;
import seedu.address.model.ReadOnlyPropertyBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.property.Property;
import seedu.address.testutil.PersonBuilder;

public class AddBuyerCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBuyerCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Buyer validBuyer = new PersonBuilder().build();

        CommandResult commandResult = new AddBuyerCommand(validBuyer).execute(modelStub);

        assertEquals(String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBuyer), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Buyer validBuyer = new PersonBuilder().build();
        AddBuyerCommand addBuyerCommand = new AddBuyerCommand(validBuyer);
        ModelStub modelStub = new ModelStubWithPerson(validBuyer);

        assertThrows(CommandException.class,
                AddBuyerCommand.MESSAGE_DUPLICATE_BUYER, () -> addBuyerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Buyer alice = new PersonBuilder().withName("Alice").build();
        Buyer bob = new PersonBuilder().withName("Bob").build();
        AddBuyerCommand addAliceCommand = new AddBuyerCommand(alice);
        AddBuyerCommand addBobCommand = new AddBuyerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddBuyerCommand addAliceCommandCopy = new AddBuyerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different buyer -> returns false
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
        public Path getBuyerBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBuyerBookFilePath(Path personBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getPropertyBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBookFilePath(Path personBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBuyerBook(ReadOnlyBuyerBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBuyerBook getBuyerBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBuyer(Buyer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBuyer(Buyer target, Buyer editedBuyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Buyer> getFilteredBuyerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortBuyerList(Comparator<Buyer> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        //        @Override
        //        public ObservableList<Buyer> getSortedPersonList() {
        //            throw new AssertionError("This method should not be called.");
        //        }
        //
        //        @Override
        //        public void updateSortedPersonList(Comparator<Buyer> comparator) {
        //            throw new AssertionError("This method should not be called.");
        //        }
        //
        //        @Override
        //        public ObservableList<Buyer> getLastShownBuyersList() {
        //            throw new AssertionError("This method should not be called.");
        //        }

        @Override
        public void addProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPropertyBook(ReadOnlyPropertyBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyPropertyBook getPropertyBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasProperty(Property property) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteProperty(Property target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setProperty(Property target, Property editedProperty) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Property> getFilteredPropertyList() {
            throw new AssertionError("This method should not be called");
        }

        @Override
        public void updateFilteredPropertyList(Predicate<Property> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPropertyList(Comparator<Property> comparator) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single buyer.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Buyer buyer;

        ModelStubWithPerson(Buyer buyer) {
            requireNonNull(buyer);
            this.buyer = buyer;
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return this.buyer.isSameBuyer(buyer);
        }
    }

    /**
     * A Model stub that always accept the buyer being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Buyer> personsAdded = new ArrayList<>();

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return personsAdded.stream().anyMatch(buyer::isSameBuyer);
        }

        @Override
        public void addBuyer(Buyer buyer) {
            requireNonNull(buyer);
            personsAdded.add(buyer);
        }

        @Override
        public ReadOnlyBuyerBook getBuyerBook() {
            return new BuyerBook();
        }
    }

}
