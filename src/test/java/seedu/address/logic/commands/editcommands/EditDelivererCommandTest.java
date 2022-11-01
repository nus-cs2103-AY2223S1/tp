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

public class EditDelivererCommandTest {

    @Test
    public void constructor_nullDeliverer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditDelivererCommand(INDEX_FIRST, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        Deliverer validDeliverer = new PersonBuilder().withName("Darwin").buildDeliverer();
        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(validDeliverer).build();
        assertThrows(NullPointerException.class, () -> new EditBuyerCommand(null, descriptor));
    }

    @Test
    public void execute_delivererAcceptedByModel_addSuccessful() throws Exception {
        //Set up
        ModelStubAcceptingDelivererAdded modelStub = new ModelStubAcceptingDelivererAdded();
        Deliverer firstDeliverer = new PersonBuilder().withName("Spongebob").buildDeliverer();
        Deliverer secondDeliverer = new PersonBuilder().withName("Patrick").buildDeliverer();
        modelStub.addDeliverer(firstDeliverer);

        //INDEX 1 -> success

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(secondDeliverer).build();
        CommandResult commandResult = new EditDelivererCommand(INDEX_FIRST, descriptor).execute(modelStub);
        String expectedResult = String.format(EditDelivererCommand.MESSAGE_EDIT_PERSON_SUCCESS, secondDeliverer);
        assertEquals(expectedResult, commandResult.getFeedbackToUser());

        //Set up
        Deliverer thirdDeliverer = new PersonBuilder().withName("Squidward").buildDeliverer();
        Deliverer fourthDeliverer = new PersonBuilder().withName("MrKrabs").buildDeliverer();
        modelStub.addDeliverer(thirdDeliverer);

        //INDEX 2 -> success
        descriptor = new EditPersonDescriptorBuilder(fourthDeliverer).build();
        commandResult = new EditDelivererCommand(INDEX_SECOND, descriptor).execute(modelStub);
        expectedResult = String.format(EditDelivererCommand.MESSAGE_EDIT_PERSON_SUCCESS, fourthDeliverer);
        assertEquals(expectedResult, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_duplicateDeliverer_throwsCommandException() {
        //Set up
        ModelStubAcceptingDelivererAdded modelStub = new ModelStubAcceptingDelivererAdded();
        Deliverer firstDeliverer = new PersonBuilder().withName("Spongebob").buildDeliverer();
        Deliverer secondDeliverer = new PersonBuilder().withName("Patrick").buildDeliverer();
        Deliverer firstDelivererCopy = new PersonBuilder().withName("Spongebob").buildDeliverer();
        modelStub.addDeliverer(firstDeliverer);
        modelStub.addDeliverer(secondDeliverer);

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(firstDelivererCopy).build();

        EditDelivererCommand editDelivererCommand = new EditDelivererCommand(INDEX_SECOND, descriptor);
        assertThrows(CommandException.class,
                EditDelivererCommand.MESSAGE_DUPLICATE_PERSON, () -> editDelivererCommand.execute(modelStub));
    }

    @Test
    public void execute_largeIndex_throwsCommandException() {
        //Set up
        ModelStubAcceptingDelivererAdded modelStub = new ModelStubAcceptingDelivererAdded();
        Deliverer firstDeliverer = new PersonBuilder().withName("Spongebob").buildDeliverer();
        Deliverer secondDeliverer = new PersonBuilder().withName("Patrick").buildDeliverer();
        modelStub.addDeliverer(firstDeliverer);

        EditCommand.EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(secondDeliverer).build();
        EditDelivererCommand editDelivererCommand = new EditDelivererCommand(Index.fromOneBased(Integer.MAX_VALUE),
                descriptor);
        assertThrows(CommandException.class,
                Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, () -> editDelivererCommand.execute(modelStub));
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
     * A Model stub that always accept the buyer being added.
     */
    private class ModelStubAcceptingDelivererAdded extends ModelStub {
        private ArrayList<Deliverer> deliverers = new ArrayList<>();

        @Override
        public ObservableList<Deliverer> getFilteredDelivererList() {
            AddressBook ab = new AddressBook();
            for (Deliverer deliverer : deliverers) {
                ab.addDeliverer(deliverer);
            }
            return ab.getDelivererList();
        }

        @Override
        public boolean hasDeliverer(Deliverer deliverer) {
            requireNonNull(deliverer);
            return deliverers.stream().anyMatch(deliverer::isSamePerson);
        }

        @Override
        public void addDeliverer(Deliverer deliverer) {
            requireNonNull(deliverer);
            deliverers.add(deliverer);
        }

        @Override
        public void setDeliverer(Deliverer delivererToEdit, Deliverer editedDeliverer) {
            deliverers.remove(delivererToEdit);
            deliverers.add(editedDeliverer);
        }

        @Override
        public void updateFilteredDelivererList(Predicate<Deliverer> predicate) {}

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
