package soconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static soconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static soconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static soconnect.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import soconnect.commons.core.GuiSettings;
import soconnect.logic.commands.SortCommand.SortArgument;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.parser.Prefix;
import soconnect.model.Model;
import soconnect.model.ReadOnlySoConnect;
import soconnect.model.ReadOnlyTodoList;
import soconnect.model.ReadOnlyUserPrefs;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.model.todo.Todo;

class SortCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SortCommand(null));
    }

    @Test
    public void execute_wrongPrefix_throwsCommandException() {
        SortCommand sortCommand = new SortCommand(List.of(new SortArgument(new Prefix("x/"), false, null)));
        Model modelStub = new ModelStub();

        assertThrows(CommandException.class, SortCommand.MESSAGE_WRONG_PREFIX, () -> sortCommand.execute(modelStub));
    }

    @Test
    void execute_success() throws CommandException {
        SortCommand sortCommand = new SortCommand(List.of(new SortArgument(PREFIX_NAME, false, null)));
        Model modelStub = new ModelStubThatSorts();

        assertEquals(SortCommand.MESSAGE_SUCCESS, sortCommand.execute(modelStub).getFeedbackToUser());
    }

    @Test
    void testEquals() {
        SortCommand sampleA = new SortCommand(List.of(new SortArgument(PREFIX_NAME, false, null)));
        SortCommand sampleB = new SortCommand(List.of(new SortArgument(PREFIX_ADDRESS, false, null)));
        SortCommand sampleC = new SortCommand(List.of(
            new SortArgument(PREFIX_NAME, false, null),
            new SortArgument(PREFIX_ADDRESS, false, null)));
        SortCommand sampleD = new SortCommand(List.of(new SortArgument(PREFIX_NAME, false, null)));

        assertEquals(sampleA, sampleA); // same object
        assertNotEquals(1, sampleA); // not SortCommand
        assertNotEquals(sampleA, sampleC); // argList of different lengths
        assertNotEquals(sampleA, sampleB); // different value
        assertEquals(sampleA, sampleD); // same value
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
            return false;
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
            throw new AssertionError("This method should not be called.");
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
     * A Model stub that allows sorting.
     */
    private class ModelStubThatSorts extends ModelStub {
        @Override
        public void sortByName(Boolean isReverse) {}

        @Override
        public void sortByPhone(Boolean isReverse) {}

        @Override
        public void sortByEmail(Boolean isReverse) {}

        @Override
        public void sortByAddress(Boolean isReverse) {}

        @Override
        public void sortByTag(Tag tag, Boolean isReverse) {}
    }
}
