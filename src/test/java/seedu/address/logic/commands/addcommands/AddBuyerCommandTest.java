package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.order.Order;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Supplier;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PersonBuilder;

public class AddBuyerCommandTest {

    @Test
    public void constructor_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBuyerCommand(null, null));
    }

    @Test
    public void execute_buyerAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer validBuyer = new PersonBuilder().buildBuyer();
        CommandResult commandResult = new AddBuyerCommand(validBuyer, new ArrayList<>()).execute(modelStub);

        String expectedResult = String.format(AddBuyerCommand.MESSAGE_SUCCESS, validBuyer)
                + "\n" + "0 orders added.\n";

        assertEquals(expectedResult, commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBuyer), modelStub.buyersAdded);
    }

    @Test
    public void execute_duplicateBuyer_throwsCommandException() {
        Buyer validBuyer = new PersonBuilder().buildBuyer();
        AddBuyerCommand addBuyerCommand = new AddBuyerCommand(validBuyer, new ArrayList<>());
        ModelStub modelStub = new ModelStubWithBuyer(validBuyer);

        assertThrows(CommandException.class,
                AddBuyerCommand.MESSAGE_DUPLICATE_BUYER, () -> addBuyerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Buyer alice = new PersonBuilder().withName("Alice").buildBuyer();
        Buyer bob = new PersonBuilder().withName("Bob").buildBuyer();
        AddBuyerCommand addAliceCommand = new AddBuyerCommand(alice, new ArrayList<>());
        AddBuyerCommand addBobCommand = new AddBuyerCommand(bob, new ArrayList<>());

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddBuyerCommand addAliceCommandCopy = new AddBuyerCommand(alice, new ArrayList<>());
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        //different buyer -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all the methods failing.
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
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasDeliverer(Deliverer person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPet(Pet pet) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteBuyer(Buyer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteSupplier(Supplier target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteDeliverer(Deliverer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePet(Pet target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrder(Order target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addDeliverer(Deliverer deliverer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPet(Pet pet) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBuyer(Buyer target, Buyer editedBuyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSupplier(Supplier target, Supplier editedSupplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setDeliverer(Deliverer target, Deliverer editedDeliverer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPet(Pet target, Pet editedPet) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrder(Order target, Order editedOrder) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortBuyer(Comparator<Buyer> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortSupplier(Comparator<Supplier> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortDeliverer(Comparator<Deliverer> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortOrder(Comparator<Order> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortPet(Comparator<Pet> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Buyer> getFilteredBuyerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Supplier> getFilteredSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Deliverer> getFilteredDelivererList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Pet> getFilteredPetList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Order> getFilteredOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Object> getFilteredMainList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredDelivererList(Predicate<Deliverer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPetList(Predicate<Pet> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderList(Predicate<Order> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Order> getOrdersFromBuyer(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Order> getOrdersFromDeliverer(Deliverer deliverer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Pet> getPetsFromSupplier(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single buyer.
     */
    private class ModelStubWithBuyer extends ModelStub {
        private final Buyer buyer;

        ModelStubWithBuyer(Buyer buyer) {
            requireNonNull(buyer);
            this.buyer = buyer;
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return this.buyer.isSamePerson(buyer);
        }
    }

    /**
     * A Model stub that always accept the buyer being added.
     */
    private class ModelStubAcceptingBuyerAdded extends ModelStub {
        final ArrayList<Buyer> buyersAdded = new ArrayList<>();

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return buyersAdded.stream().anyMatch(buyer::isSamePerson);
        }

        @Override
        public void addBuyer(Buyer buyer) {
            requireNonNull(buyer);
            buyersAdded.add(buyer);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
