package seedu.boba.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import seedu.boba.commons.core.GuiSettings;
import seedu.boba.logic.commands.exceptions.CommandException;
import seedu.boba.model.BobaBot;
import seedu.boba.model.BobaBotModel;
import seedu.boba.model.ReadOnlyBobaBot;
import seedu.boba.model.ReadOnlyUserPrefs;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.Phone;
import seedu.boba.model.customer.Reward;
import seedu.boba.model.exceptions.NextStateNotFoundException;
import seedu.boba.model.exceptions.PreviousStateNotFoundException;
import seedu.boba.testutil.CustomerBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModelNoBirthdayWithTag_addSuccessful() throws Exception {
        BobaBotModelStubAcceptingPersonAdded modelStub = new BobaBotModelStubAcceptingPersonAdded();
        Customer validCustomer = new CustomerBuilder().withBirthdayMonth("10").withTags("BDAY").build();

        CommandResult commandResult = new AddCommand(validCustomer).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCustomer), modelStub.personsAdded);
    }

    @Test
    public void execute_personAcceptedByModelBirthdayNoTag_addSuccessful() throws Exception {
        BobaBotModelStubAcceptingPersonAdded modelStub = new BobaBotModelStubAcceptingPersonAdded();
        Customer validCustomer = new CustomerBuilder().withBirthdayMonth("11").build();

        CommandResult commandResult = new AddCommand(validCustomer).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validCustomer), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCustomer), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Customer validCustomer = new CustomerBuilder().build();
        AddCommand addCommand = new AddCommand(validCustomer);
        BobaBotModelStub modelStub = new BobaBotModelStubWithPerson(validCustomer);

        assertThrows(CommandException.class,
            AddCommand.MESSAGE_DUPLICATE_CUSTOMER, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Customer alice = new CustomerBuilder().withName("Alice").build();
        Customer bob = new CustomerBuilder().withName("Bob").withPhone("83838000").build();

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

        // different name, same email, different phone -> returns true
        assertTrue(addAliceCommand.equals(addBobCommand));

        // different name, same phone, different email -> returns true
        bob = new CustomerBuilder().withName("Bob").withEmail("bob@example.com").build();
        addBobCommand = new AddCommand(bob);
        assertTrue(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default bobaBotModel stub that have all of the methods failing.
     */
    private class BobaBotModelStub implements BobaBotModel {
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
        public Path getBobaBotFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBobaBotFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBobaBot(ReadOnlyBobaBot newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyBobaBot getBobaBot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Customer customer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Customer target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Customer target, Customer editedCustomer) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Customer> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Customer> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int findNum(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int findEmail(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Reward getCurrentReward(Phone phone) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Reward getCurrentReward(Email email) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitBobaBot() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoBobaBot() throws PreviousStateNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoBobaBot() throws NextStateNotFoundException {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Image> getPromotionList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void parseAllPromotion(String filePath) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A BobaBotModel stub that contains a single customer.
     */
    private class BobaBotModelStubWithPerson extends BobaBotModelStub {
        private final Customer customer;

        BobaBotModelStubWithPerson(Customer customer) {
            requireNonNull(customer);
            this.customer = customer;
        }

        @Override
        public boolean hasPerson(Customer customer) {
            requireNonNull(customer);
            return this.customer.isSamePerson(customer);
        }
    }

    /**
     * A BobaBotModel stub that always accept the customer being added.
     */
    private class BobaBotModelStubAcceptingPersonAdded extends BobaBotModelStub {
        final ArrayList<Customer> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Customer customer) {
            requireNonNull(customer);
            return personsAdded.stream().anyMatch(customer::isSamePerson);
        }

        @Override
        public void addPerson(Customer customer) {
            requireNonNull(customer);
            personsAdded.add(customer);
        }

        @Override
        public ReadOnlyBobaBot getBobaBot() {
            return new BobaBot();
        }
    }

}
