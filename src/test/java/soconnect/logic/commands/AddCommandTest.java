package soconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static soconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import soconnect.commons.core.GuiSettings;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.model.Model;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.ReadOnlyUserPrefs;
import soconnect.model.SoConnect;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Todo;
import soconnect.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validPerson), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertEquals(addAliceCommand, addAliceCommandCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);

        // different person -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
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
        public String getAttributeOrder() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public String getHiddenAttributes() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getSoConnectFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSoConnectFilePath(Path soConnectFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTodoListFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTodoListFilePath(Path soConnectFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSoConnect(ReadOnlySoConnect newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySoConnect getSoConnect() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean areTagsAvailable(Person person) {
            return true;
        }

        @Override
        public boolean areTagsAvailable(Todo todo) {
            return true;
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
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editTag(Tag oldTag, Tag newTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Tag getTagFromList(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ArrayList<Tag> getTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTagList(List<Tag> tagList) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByName(Boolean isReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByPhone(Boolean isReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByEmail(Boolean isReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByAddress(Boolean isReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByTag(Tag tag, Boolean isReverse) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTodoList(ReadOnlyTodoList newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTodoList getTodoList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTodo(Todo todo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTodo(Todo target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTodo(Todo target, Todo editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Boolean isFilteredPersonListEmpty() {
            return getFilteredPersonList().size() == 0;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Todo> getFilteredTodoList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTodoList(Predicate<Todo> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public SimpleStringProperty getTodoListHeader() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTodoListHeader(String header) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlySoConnect getSoConnect() {
            return new SoConnect();
        }
    }

}
