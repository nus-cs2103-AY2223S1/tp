//package seedu.address.logic.commands.client;
//
//import static java.util.Objects.requireNonNull;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.testutil.Assert.assertThrows;
//
//import java.nio.file.Path;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.function.Predicate;
//
//import org.junit.jupiter.api.Test;
//
//import javafx.collections.ObservableList;
//import seedu.address.commons.core.GuiSettings;
//import seedu.address.logic.commands.CommandResult;
//import seedu.address.logic.commands.exceptions.CommandException;
//import seedu.address.model.AddressBook;
//import seedu.address.model.Model;
//import seedu.address.model.ReadOnlyAddressBook;
//import seedu.address.model.ReadOnlyUserPrefs;
//import seedu.address.model.client.Person;
//import seedu.address.model.issue.Issue;
//import seedu.address.model.project.Project;
//import seedu.address.testutil.PersonBuilder;
//import seedu.address.ui.StubUiManager;
//import seedu.address.ui.Ui;
//
//public class AddClientCommandTest {
//
//    @Test
//    public void constructor_nullPerson_throwsNullPointerException() {
//        assertThrows(NullPointerException.class, () -> new AddClientCommand(null));
//    }
//
//    @Test
//    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
//        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
//        Person validPerson = new PersonBuilder().build();
//        Ui stubUi = new StubUiManager();
//
//        CommandResult commandResult = new AddClientCommand(validPerson).execute(modelStub, stubUi);
//
//        assertEquals(String.format(AddClientCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
//        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
//    }
//
//    @Test
//    public void execute_duplicatePerson_throwsCommandException() {
//        Person validPerson = new PersonBuilder().build();
//        AddClientCommand addClientCommand = new AddClientCommand(validPerson);
//        ModelStub modelStub = new ModelStubWithPerson(validPerson);
//        Ui stubUi = new StubUiManager();
//
//        assertThrows(CommandException.class, AddClientCommand.MESSAGE_DUPLICATE_PERSON, ()
//                -> addClientCommand.execute(modelStub, stubUi));
//    }
//
//    @Test
//    public void equals() {
//        Person alice = new PersonBuilder().withName("Alice").build();
//        Person bob = new PersonBuilder().withName("Bob").build();
//        AddClientCommand addAliceCommand = new AddClientCommand(alice);
//        AddClientCommand addBobCommand = new AddClientCommand(bob);
//
//        // same object -> returns true
//        assertTrue(addAliceCommand.equals(addAliceCommand));
//
//        // same values -> returns true
//        AddClientCommand addAliceCommandCopy = new AddClientCommand(alice);
//        assertTrue(addAliceCommand.equals(addAliceCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addAliceCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addAliceCommand.equals(null));
//
//        // different client -> returns false
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
//        public void addPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addProject(Project project) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void addIssue(Issue issue) {
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
//        public boolean hasPerson(Person person) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasProject(Project project) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public boolean hasIssue(Issue issue) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deletePerson(Person target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteProject(Project target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void deleteIssue(Issue target) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setPerson(Person target, Person editedPerson) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setProject(Project target, Project editedProject) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void setIssue(Issue target, Issue editedIssue) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Person> getFilteredPersonList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Project> getFilteredProjectList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public ObservableList<Issue> getFilteredIssueList() {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredPersonList(Predicate<Person> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredProjectList(Predicate<Project> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//
//        @Override
//        public void updateFilteredIssueList(Predicate<Issue> predicate) {
//            throw new AssertionError("This method should not be called.");
//        }
//    }
//
//    /**
//     * A Model stub that contains a single client.
//     */
//    private class ModelStubWithPerson extends ModelStub {
//        private final Person person;
//
//        ModelStubWithPerson(Person person) {
//            requireNonNull(person);
//            this.person = person;
//        }
//
//        @Override
//        public boolean hasPerson(Person person) {
//            requireNonNull(person);
//            return this.person.isSamePerson(person);
//        }
//    }
//
//    /**
//     * A Model stub that always accept the client being added.
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
//
//}
