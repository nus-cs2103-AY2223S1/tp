package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Buyer;
import seedu.address.model.person.Deliverer;
import seedu.address.model.person.Person;
import seedu.address.model.person.Supplier;
import seedu.address.testutil.PersonBuilder;

public class AddCommandTest {
//
//    @Test
//    public void constructor_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddCommand(null));
//    }
//
//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
//        Person validPerson = new PersonBuilder().build();
//
//        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);
//
//        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person validPerson = new PersonBuilder().build();
//        AddCommand addCommand = new AddCommand(validPerson);
//        ModelStub modelStub = new ModelStubWithPerson(validPerson);
//
//        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        Person alice = new PersonBuilder().withName("Alice").build();
//        Person bob = new PersonBuilder().withName("Bob").build();
//        AddCommand addAliceCommand = new AddCommand(alice);
//        AddCommand addBobCommand = new AddCommand(bob);
//
//        // same object -> returns true
//        assertTrue(addAliceCommand.equals(addAliceCommand));
//
//        // same values -> returns true
//        AddCommand addAliceCommandCopy = new AddCommand(alice);
//        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addAliceCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addAliceCommand.equals(null));
//
//        // different person -> returns false
//        assertFalse(addAliceCommand.equals(addBobCommand));
//    }
//
//    /**
//     * A default model stub that have all of the methods failing.
//     */
//    private class ModelStub implements Model {
//        @Override
//        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyUserPrefs getUserPrefs() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public GuiSettings getGuiSettings() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setGuiSettings(GuiSettings guiSettings) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public Path getAddressBookFilePath() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBookFilePath(Path addressBookFilePath) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setAddressBook(ReadOnlyAddressBook newData) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasBuyer(Buyer buyer) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasSupplier(Supplier supplier) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasDeliverer(Deliverer person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteBuyer(Buyer target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteSupplier(Supplier target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteDeliverer(Deliverer target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addBuyer(Buyer buyer) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addSupplier(Supplier supplier) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addDeliverer(Deliverer deliverer) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setBuyer(Buyer target, Buyer editedBuyer) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setSupplier(Supplier target, Supplier editedSupplier) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setDeliverer(Deliverer target, Deliverer editedDeliverer) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Buyer> getFilteredBuyerList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Supplier> getFilteredSupplierList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Deliverer> getFilteredDelivererList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredBuyerList(Predicate<Buyer> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredSupplierList(Predicate<Supplier> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredDelivererList(Predicate<Deliverer> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//    }
//
//    /**
//     * A Model stub that contains a single person.
//     */
//    private class ModelStubWithPerson extends ModelStub {
//        private final Person person;
//
//        ModelStubWithPerson(Person person) {
//            requireNonNull(person);
//            this.person = person;
//        }
//
////        @Override
////        public boolean hasPerson(Person person) {
////            requireNonNull(person);
////            return this.person.isSamePerson(person);
////        }
//    }
//
//    /**
//     * A Model stub that always accept the person being added.
//     */
//    private class ModelStubAcceptingPersonAdded extends ModelStub {
//        final ArrayList<Person> personsAdded = new ArrayList<>();
//
//        @Override
//        public boolean hasPerson(Person person) {
//            requireNonNull(person);
//            return personsAdded.stream().anyMatch(person::isSamePerson);
//        }
//
//        @Override
//        public void addPerson(Person person) {
//            requireNonNull(person);
//            personsAdded.add(person);
//        }
//
//        @Override
//        public ReadOnlyAddressBook getAddressBook() {
//            return new AddressBook();
//        }
//    }

}
