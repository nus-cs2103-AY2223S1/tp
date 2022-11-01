package seedu.address.logic.commands.addcommands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.core.index.UniqueId;
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
import seedu.address.testutil.PetBuilder;
import seedu.address.testutil.TypicalSuppliers;

public class AddPetCommandTest {

    @Test
    public void execute_petAcceptedByModel_addSuccessful() throws Exception {
        //One Pet added
        ModelStubAcceptingPetAdded modelStub = new ModelStubAcceptingPetAdded();
        Pet validPet = new PetBuilder().build();
        CommandResult commandResult = new AddPetCommand(validPet, INDEX_FIRST).execute(modelStub);
        String expectedResult = String.format(AddPetCommand.MESSAGE_SUCCESS, validPet);

        assertEquals(expectedResult, commandResult.getFeedbackToUser());

        List<UniqueId> idList = modelStub.getFilteredSupplierList().get(0).getPetIds();
        assertEquals(idList.get(0), validPet.getId());

        //clear pets added
        modelStub.getFilteredSupplierList().get(0).deletePet(0);

        //multiple Pets added
        modelStub = new ModelStubAcceptingPetAdded();
        modelStub.deletePet(validPet);
        Pet firstValidPet = new PetBuilder().build();
        Pet secondValidPet = new PetBuilder().build();
        Pet thirdValidPet = new PetBuilder().build();
        List<Pet> validPets = Arrays.asList(firstValidPet, secondValidPet, thirdValidPet);

        for (Pet pet : validPets) {
            commandResult = new AddPetCommand(pet, INDEX_FIRST).execute(modelStub);
            expectedResult = String.format(AddPetCommand.MESSAGE_SUCCESS, pet);
            assertEquals(expectedResult, commandResult.getFeedbackToUser());
        }

        idList = modelStub.getFilteredSupplierList().get(0).getPetIds();

        for (int i = 0; i < validPets.size(); i++) {
            assertEquals(idList.get(i), validPets.get(i).getId());
        }

        // clear pets added
        for (Pet pet : validPets) {
            modelStub.getFilteredSupplierList().get(0).deletePet(0);
        }

        // Different index
        modelStub = new ModelStubAcceptingPetAdded();
        validPet = new PetBuilder().build();
        commandResult = new AddPetCommand(validPet, INDEX_SECOND).execute(modelStub);
        expectedResult = String.format(AddPetCommand.MESSAGE_SUCCESS, validPet);

        assertEquals(expectedResult, commandResult.getFeedbackToUser());

        idList = modelStub.getFilteredSupplierList().get(1).getPetIds();
        assertTrue(idList.contains(validPet.getId()));

        //clear pets added
        modelStub.getFilteredSupplierList().get(1).deletePet(0);
    }

    @Test
    public void execute_largeIndex_throwsCommandException() {
        Pet validPet = new PetBuilder().build();
        AddPetCommand addPetCommand = new AddPetCommand(validPet, Index.fromOneBased(Integer.MAX_VALUE));
        ModelStubAcceptingPetAdded modelStub = new ModelStubAcceptingPetAdded();

        assertThrows(CommandException.class, () -> addPetCommand.execute(modelStub));
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
     * A Model stub that contains a single buyer.
     */
    private class ModelStubWithPet extends AddPetCommandTest.ModelStub {
        private final Pet pet;

        ModelStubWithPet(Pet pet) {
            requireNonNull(pet);
            this.pet = pet;
        }

        @Override
        public boolean hasPet(Pet pet) {
            requireNonNull(pet);
            return this.pet.isSamePet(pet);
        }
    }

    /**
     * A Model stub that always accept the buyer being added.
     */
    private class ModelStubAcceptingPetAdded extends AddPetCommandTest.ModelStub {
        final ArrayList<Pet> petsAdded = new ArrayList<>();
        final ObservableList<Supplier> suppliers = TypicalSuppliers.getTypicalSupplierAddressBook()
                                                                    .getSupplierList();

        @Override
        public void deletePet(Pet target) {
            petsAdded.remove(target);
        }

        @Override
        public ObservableList<Supplier> getFilteredSupplierList() {
            return suppliers;
        }

        @Override
        public boolean hasPet(Pet pet) {
            requireNonNull(pet);
            return petsAdded.stream().anyMatch(pet::isSamePet);
        }

        @Override
        public void addPet(Pet pet) {
            requireNonNull(pet);
            petsAdded.add(pet);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
