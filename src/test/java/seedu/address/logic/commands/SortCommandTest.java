package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.SortCommand.SortArgument;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

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
        SortCommand sortCommand = new SortCommand(List.of(new SortArgument(new Prefix("n/"), false, null)));
        Model modelStub = new ModelStubThatSorts();

        assertEquals(SortCommand.MESSAGE_SUCCESS, sortCommand.execute(modelStub).getFeedbackToUser());
    }

    @Test
    void testEquals() {
        SortCommand sampleA = new SortCommand(List.of(new SortArgument(new Prefix("n/"), false, null)));
        SortCommand sampleB = new SortCommand(List.of(new SortArgument(new Prefix("a/"), false, null)));
        SortCommand sampleC = new SortCommand(List.of(
            new SortArgument(new Prefix("n/"), false, null),
            new SortArgument(new Prefix("n/"), false, null)));
        SortCommand sampleD = new SortCommand(List.of(new SortArgument(new Prefix("n/"), false, null)));

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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
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
        public ObservableList<Tag> getTagList() {
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
