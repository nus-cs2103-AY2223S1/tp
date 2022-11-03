package longtimenosee.logic.commands;


import static java.util.Objects.requireNonNull;
import static longtimenosee.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import longtimenosee.commons.core.GuiSettings;
import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.AddressBook;
import longtimenosee.model.Model;
import longtimenosee.model.ReadOnlyAddressBook;
import longtimenosee.model.ReadOnlyUserPrefs;
import longtimenosee.model.event.Event;
import longtimenosee.model.person.Person;
import longtimenosee.model.policy.FinancialAdvisorIncome;
import longtimenosee.model.policy.Policy;
import longtimenosee.testutil.PolicyBuilder;

public class PolicyAddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PolicyAddCommand(null));
    }

    @Test
    public void execute_policyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPolicyAdded modelStub = new ModelStubAcceptingPolicyAdded();
        Policy validPolicy = new PolicyBuilder().build();

        CommandResult commandResult = new PolicyAddCommand(validPolicy).execute(modelStub);

        assertEquals(String.format(PolicyAddCommand.MESSAGE_SUCCESS, validPolicy), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPolicy), modelStub.policiesAdded);
    }

    @Test
    public void execute_duplicatePolicy_throwsCommandException() {
        Policy validPolicy = new PolicyBuilder().build();
        PolicyAddCommand policyAddCommand = new PolicyAddCommand(validPolicy);
        ModelStub modelStub = new ModelStubWithPolicy(validPolicy);

        assertThrows(CommandException.class,
                policyAddCommand.MESSAGE_DUPLICATE_POLICY, () -> policyAddCommand.execute(
                modelStub));
    }

    @Test
    public void equals() {
        Policy pruShield = new PolicyBuilder().withTitle("PruShield").build();
        Policy goodShield = new PolicyBuilder().withTitle("GoodShield").build();
        PolicyAddCommand addPruShieldCommand = new PolicyAddCommand(pruShield);
        PolicyAddCommand addGoodShieldCommand = new PolicyAddCommand(goodShield);

        // same object -> returns true
        assertTrue(addPruShieldCommand.equals(addPruShieldCommand));

        // same values -> returns true
        PolicyAddCommand addPruShieldCommandCopy = new PolicyAddCommand(pruShield);
        assertTrue(addPruShieldCommand.equals(addPruShieldCommandCopy));

        // different types -> returns false
        assertFalse(addPruShieldCommand.equals(1));

        // null -> returns false
        assertFalse(addPruShieldCommand.equals(null));

        // different title -> returns false
        assertFalse(addPruShieldCommand.equals(addGoodShieldCommand));
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        public FinancialAdvisorIncome getIncome() {
            return new FinancialAdvisorIncome();
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
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sort(Comparator comparator) {
            throw new AssertionError("This method should not be called");
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
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPolicy(Policy toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPolicy(Policy toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePolicy(Policy toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPolicy(Policy target, Policy editedPolicy) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Policy> getFilteredPolicyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPolicyList(Predicate<Policy> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void addEvent(Event toAdd, String personName) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasEventOverlap(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public List<Event> listEventsOverlap(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean hasEvent(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void deleteEvent(Event toDelete) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public ObservableList<Event> getFilteredEventList() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public List<Event> listEventsSameDay(Event toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * @return
         */
        @Override
        public List<Event> calendarView() {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void updateFilteredEventList(Predicate<Event> predicate) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public void removeEventsUnderPerson(Person personToDelete) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPolicy extends ModelStub {
        private final Policy policy;

        ModelStubWithPolicy(Policy policy) {
            requireNonNull(policy);
            this.policy = policy;
        }

        @Override
        public boolean hasPolicy(Policy policy) {
            requireNonNull(policy);
            return this.policy.isSamePolicy(policy);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPolicyAdded extends ModelStub {
        final ArrayList<Policy> policiesAdded = new ArrayList<>();

        @Override
        public boolean hasPolicy(Policy policy) {
            requireNonNull(policy);
            return policiesAdded.stream().anyMatch(policy::isSamePolicy);
        }

        @Override
        public void addPolicy(Policy policy) {
            requireNonNull(policy);
            policiesAdded.add(policy);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
