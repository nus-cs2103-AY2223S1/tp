package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.clinkedin.testutil.Assert.assertThrows;
import static seedu.clinkedin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.clinkedin.testutil.TypicalPersons.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.clinkedin.commons.core.GuiSettings;
import seedu.clinkedin.commons.core.index.Index;
import seedu.clinkedin.logic.commands.exceptions.CommandException;
import seedu.clinkedin.model.AddressBook;
import seedu.clinkedin.model.Model;
import seedu.clinkedin.model.ModelManager;
import seedu.clinkedin.model.ReadOnlyAddressBook;
import seedu.clinkedin.model.ReadOnlyUserPrefs;
import seedu.clinkedin.model.UserPrefs;
import seedu.clinkedin.model.person.Person;
import seedu.clinkedin.model.person.UniqueTagTypeMap;
import seedu.clinkedin.model.tag.TagType;

public class AddTagCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndexNullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, new UniqueTagTypeMap()));
    }

    @Test
    public void constructor_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        AddTagCommand addTagCommand = new AddTagCommand(Index.fromZeroBased(0), new UniqueTagTypeMap());
        assertThrows(NullPointerException.class, () -> addTagCommand.execute(null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        AddTagCommand addTagCommand = new AddTagCommand(Index.fromZeroBased(100), new UniqueTagTypeMap());
        assertThrows(CommandException.class, () -> addTagCommand.execute(model));
    }

    @Test
    public void execute_emptyTag_noChangeSuccess() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        AddTagCommand addTagCommand = new AddTagCommand(Index.fromZeroBased(0), new UniqueTagTypeMap());
        String expectedMessage = String.format(AddTagCommand.MESSAGE_SUCCESS, personToEdit.toString());
        Person editedPerson = new Person(personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTagTypeMap(), personToEdit.getStatus(),
                personToEdit.getNote(),
                personToEdit.getRating(), personToEdit.getLinks());
        expectedModel.setPerson(personToEdit, editedPerson);
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTag_duplicateTagExceptionThrown() {
        Person personToEdit = model.getFilteredPersonList().get(0);
        UniqueTagTypeMap toAdd = new UniqueTagTypeMap();
        toAdd.setTagTypeMap(personToEdit.getTagTypeMap());
        AddTagCommand addTagCommand = new AddTagCommand(Index.fromZeroBased(0), toAdd);
        assertThrows(CommandException.class, () -> addTagCommand.execute(model));
    }

    // TODO: Fix valid tag test case
    // @Test
    // public void execute_validTag_success() {
    // Person personToEdit = model.getFilteredPersonList().get(0);
    // UniqueTagTypeMap toAdd = new UniqueTagTypeMap();
    // toAdd.mergeTag(new TagType("Skills"), new Tag("Test Tag"));
    // AddTagCommand addTagCommand = new AddTagCommand(Index.fromZeroBased(0),
    // toAdd);
    // String expectedMessage = String.format(AddTagCommand.MESSAGE_SUCCESS,
    // personToEdit.toString());

    // UniqueTagTypeMap editedTagTypeMap = new UniqueTagTypeMap();
    // Map<TagType, UniqueTagList> tagTypeMap = new HashMap<>();
    // tagTypeMap.putAll(personToEdit.getTagTypeMap().asUnmodifiableObservableMap());
    // editedTagTypeMap.setTagTypeMap(toAdd);
    // editedTagTypeMap.mergeTagTypeMap(personToEdit.getTagTypeMap());

    // Person editedPerson = new Person(personToEdit.getName(),
    // personToEdit.getPhone(), personToEdit.getEmail(),
    // personToEdit.getAddress(), editedTagTypeMap, personToEdit.getStatus(),
    // personToEdit.getNote(),
    // personToEdit.getRating(), personToEdit.getLinks());
    // expectedModel.setPerson(personToEdit, editedPerson);
    // assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    // }

    @Test
    public void equals() {
        Index firstIndex = Index.fromZeroBased(0);
        Index secondIndex = Index.fromZeroBased(1);
        UniqueTagTypeMap firstTag = new UniqueTagTypeMap();
        UniqueTagTypeMap secondTag = new UniqueTagTypeMap();

        AddTagCommand addFirstTagCommand = new AddTagCommand(firstIndex, firstTag);
        AddTagCommand addSecondTagCommand = new AddTagCommand(secondIndex, secondTag);

        // same object -> returns true
        assertTrue(addFirstTagCommand.equals(addFirstTagCommand));

        // same values -> returns true
        AddTagCommand addFirstTagCommandCopy = new AddTagCommand(firstIndex, firstTag);
        assertTrue(addFirstTagCommand.equals(addFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(addFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstTagCommand.equals(null));

        // different add tag -> returns false
        assertFalse(addFirstTagCommand.equals(addSecondTagCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
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
        public void commitAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoAddressBook() {
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
        public int getTotalNumberOfPersons() {
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

        /**
         * Updates the comparator of the sorted person list to sort by the given
         * {@code comparator}
         *
         * @param comparator comparator to update sorted persons list with.
         */
        @Override
        public void updateSort(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTagTypeForAllPerson(TagType toDelete) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void editTagTypeForAllPerson(TagType toEdit, TagType editTo) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public int getFilteredNumberOfPersons() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public DoubleSummaryStatistics getStats() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public HashMap<String, Integer> getRatingCount() {
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
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
