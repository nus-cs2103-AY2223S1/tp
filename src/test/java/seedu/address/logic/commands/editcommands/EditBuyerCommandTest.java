package seedu.address.logic.commands.editcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;

public class EditBuyerCommandTest {

    @Test
    public void constructor_nullBuyer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditBuyerCommand(INDEX_FIRST, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Buyer validBuyer = new PersonBuilder().withName("Darwin").buildBuyer();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(validBuyer).build();
        assertThrows(NullPointerException.class, () -> new EditBuyerCommand(null, descriptor));
    }

    @Test
    public void execute_buyerAcceptedByModel_addSuccessful() throws Exception {
        //Set up
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer firstBuyer = new PersonBuilder().withName("Spongebob").buildBuyer();
        Buyer secondBuyer = new PersonBuilder().withName("Patrick").buildBuyer();
        modelStub.addBuyer(firstBuyer);

        //INDEX 1 -> success

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(secondBuyer).build();
        CommandResult commandResult = new EditBuyerCommand(INDEX_FIRST, descriptor).execute(modelStub);
        String expectedResult = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, secondBuyer);
        assertEquals(expectedResult, commandResult.getFeedbackToUser());

        //Set up
        Buyer thirdBuyer = new PersonBuilder().withName("Squidward").buildBuyer();
        Buyer fourthBuyer = new PersonBuilder().withName("MrKrabs").buildBuyer();
        modelStub.addBuyer(thirdBuyer);

        //INDEX 2 -> success
        descriptor = new EditPersonDescriptorBuilder(fourthBuyer).build();
        commandResult = new EditBuyerCommand(INDEX_SECOND, descriptor).execute(modelStub);
        expectedResult = String.format(EditBuyerCommand.MESSAGE_EDIT_PERSON_SUCCESS, fourthBuyer);
        assertEquals(expectedResult, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateBuyer_throwsCommandException() {
        //Set up
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer firstBuyer = new PersonBuilder().withName("Spongebob").buildBuyer();
        Buyer secondBuyer = new PersonBuilder().withName("Patrick").buildBuyer();
        Buyer firstBuyerCopy = new PersonBuilder().withName("Spongebob").buildBuyer();
        modelStub.addBuyer(firstBuyer);
        modelStub.addBuyer(secondBuyer);

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstBuyerCopy).build();

        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(INDEX_SECOND, descriptor);
        assertThrows(CommandException.class,
                EditBuyerCommand.MESSAGE_DUPLICATE_PERSON, () -> editBuyerCommand.execute(modelStub));
    }

    @Test
    public void execute_largeIndex_throwsCommandException() {
        //Set up
        ModelStubAcceptingBuyerAdded modelStub = new ModelStubAcceptingBuyerAdded();
        Buyer firstBuyer = new PersonBuilder().withName("Spongebob").buildBuyer();
        Buyer secondBuyer = new PersonBuilder().withName("Patrick").buildBuyer();
        modelStub.addBuyer(firstBuyer);

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(secondBuyer).build();
        EditBuyerCommand editBuyerCommand = new EditBuyerCommand(Index.fromOneBased(Integer.MAX_VALUE), descriptor);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> editBuyerCommand.execute(modelStub));
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
        public ObservableList<Object> getFilteredCurrList() {
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
        public void clearCurrList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToBuyerList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToSupplierList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToDelivererList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToOrderList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToPetList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void switchToMainList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkBuyerOrder(Buyer buyer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkSupplierPet(Supplier supplier) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkDelivererOrder(Deliverer deliverer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkBuyerOfOrder(Order order) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void checkSupplierOfPet(Pet pet) {
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
        public ObservableList<Buyer> getFilteredBuyerList() {
            AddressBook ab = new AddressBook();
            ab.addBuyer(buyer);
            return ab.getBuyerList();
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
        private ArrayList<Buyer> buyers = new ArrayList<>();

        @Override
        public ObservableList<Object> getFilteredCurrList() {
            AddressBook ab = new AddressBook();
            for (Buyer buyer : buyers) {
                ab.addBuyer(buyer);
            }

            @SuppressWarnings("unchecked")
            ObservableList<?> res = ab.getBuyerList();
            return (ObservableList<Object>) res;
        }

        @Override
        public ObservableList<Buyer> getFilteredBuyerList() {
            AddressBook ab = new AddressBook();
            for (Buyer buyer : buyers) {
                ab.addBuyer(buyer);
            }
            return ab.getBuyerList();
        }

        @Override
        public boolean hasBuyer(Buyer buyer) {
            requireNonNull(buyer);
            return buyers.stream().anyMatch(buyer::isSamePerson);
        }

        @Override
        public void addBuyer(Buyer buyer) {
            requireNonNull(buyer);
            buyers.add(buyer);
        }

        @Override
        public void setBuyer(Buyer buyerToEdit, Buyer editedBuyer) {
            buyers.remove(buyerToEdit);
            buyers.add(editedBuyer);
        }

        @Override
        public void updateFilteredBuyerList(Predicate<Buyer> predicate) {}

        @Override
        public void switchToBuyerList() {}

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
