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

public class AddDelivererCommandTest {
    @Test
    public void constructor_nullDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDelivererCommand(null));
    }

    @Test
    public void execute_delivererAcceptedByModel_addSuccessful() throws Exception {
        AddDelivererCommandTest.ModelStubAcceptingDelivererAdded modelStub = new ModelStubAcceptingDelivererAdded();
        Deliverer validDeliverer = new PersonBuilder().buildDeliverer();

        CommandResult commandResult = new AddDelivererCommand(validDeliverer).execute(modelStub);

        assertEquals(String.format(AddDelivererCommand.MESSAGE_SUCCESS, validDeliverer),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDeliverer), modelStub.deliverersAdded);
    }

    @Test
    public void execute_duplicateDeliverer_throwsCommandException() {
        Deliverer validDeliverer = new PersonBuilder().buildDeliverer();
        AddDelivererCommand addDelivererCommand = new AddDelivererCommand(validDeliverer);
        AddDelivererCommandTest.ModelStub modelStub =
                new AddDelivererCommandTest.ModelStubWithDeliverer(validDeliverer);

        assertThrows(CommandException.class, AddDelivererCommand.MESSAGE_DUPLICATE_DELIVERER, ()
                -> addDelivererCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Deliverer alice = new PersonBuilder().withName("Alice").buildDeliverer();
        Deliverer bob = new PersonBuilder().withName("Bob").buildDeliverer();
        AddDelivererCommand addAliceCommand = new AddDelivererCommand(alice);
        AddDelivererCommand addBobCommand = new AddDelivererCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddDelivererCommand addAliceCommandCopy = new AddDelivererCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different Deliverer -> returns false
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

        @Override
        public void clearMasterList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single Deliverer.
     */
    private class ModelStubWithDeliverer extends ModelStub {
        private final Deliverer deliverer;

        ModelStubWithDeliverer(Deliverer deliverer) {
            requireNonNull(deliverer);
            this.deliverer = deliverer;
        }

        @Override
        public boolean hasDeliverer(Deliverer deliverer) {
            requireNonNull(deliverer);
            return this.deliverer.isSamePerson(deliverer);
        }
    }

    /**
     * A Model stub that always accept the Deliverer being added.
     */
    private class ModelStubAcceptingDelivererAdded extends ModelStub {
        final ArrayList<Deliverer> deliverersAdded = new ArrayList<>();

        @Override
        public boolean hasDeliverer(Deliverer deliverer) {
            requireNonNull(deliverer);
            return deliverersAdded.stream().anyMatch(deliverer::isSamePerson);
        }

        @Override
        public void addDeliverer(Deliverer deliverer) {
            requireNonNull(deliverer);
            deliverersAdded.add(deliverer);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
