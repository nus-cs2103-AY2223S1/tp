package seedu.address.logic.commands.tag;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import javafx.collections.transformation.FilteredList;
import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;
import seedu.address.testutil.TagBuilder;
import seedu.address.testutil.TypicalTags;

public class AddTagCommandTest {

    public static EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
    public static EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
    public static boolean addTagToContact = true;
    public static boolean addTagToTask = true;
    public static Index contactIndex = Index.fromZeroBased(0);
    public static Index taskIndex = Index.fromZeroBased(0);
    public static List<String> tagList = new ArrayList<>();
    public static Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullContactIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, taskIndex,
        editPersonDescriptor, editTaskDescriptor, addTagToContact, addTagToTask, tagList));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        Tag validTag = new TagBuilder().withName("newTag").build();
        AddressBook ab = getTypicalAddressBook();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(validTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);
        tagList.add(validTag.getName());
        Model expected = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expected.addTag(validTag);
        ObservableList<Tag> expectedTags = expected.getFilteredTagList();

        CommandResult commandResult = new AddTagCommand(contactIndex, taskIndex, editPersonDescriptor,
                editTaskDescriptor, addTagToContact, addTagToTask, tagList).execute(model);

        assertEquals(String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, tagSet),
                commandResult.getFeedbackToUser());

        assertEquals(expectedTags, model.getFilteredTagList());
    }
//
//    @Test
//    public void execute_duplicateTag_throwsCommandException() {
//        Tag validTag = new TagBuilder().build();
//        AddTagCommand addCommand = new AddTagCommand(validTag);
//        ModelStub modelStub = new ModelStubWithTag(validTag);
//
//        assertThrows(CommandException.class,
//                AddTagCommand.MESSAGE_DUPLICATE_TAG_ON_PERSON_OR_TASK, () -> addCommand.execute(modelStub));
//    }
//
//    @Test
//    public void equals() {
//        Tag firstTag = TypicalTags.BIOLOGY_PROJECT;
//        Tag secondTag = TypicalTags.FAMILY_MEMBER;
//        AddTagCommand addFirstTagCommand = new AddTagCommand(firstTag);
//        AddTagCommand addSecondTagCommand = new AddTagCommand(secondTag);
//
//        // same object -> returns true
//        assertTrue(addFirstTagCommand.equals(addFirstTagCommand));
//
//        // same values -> returns true
//        AddTagCommand addFirstTagCommandCopy = new AddTagCommand(firstTag);
//        assertTrue(addFirstTagCommand.equals(addFirstTagCommandCopy));
//
//        // different types -> returns false
//        assertFalse(addFirstTagCommand.equals(1));
//
//        // null -> returns false
//        assertFalse(addFirstTagCommand.equals(null));
//
//        // different tag -> returns false
//        assertFalse(addFirstTagCommand.equals(addSecondTagCommand));
//    }

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
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTag(Tag target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTag(Tag tag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTag(Tag target, Tag editedTag) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTagList(Predicate<Tag> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public double getPercentageCompletion(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortByDeadline() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortById() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Tag> getFilteredTagList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTagCount(Tag toAdd) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void decreaseTagCount(Tag toDelete) {
            throw new AssertionError("This method should not be called.");
        }

    }

    /**
     * A Model stub that contains a single tag.
     */
    private class ModelStubWithTag extends ModelStub {
        private final Tag tag;

        ModelStubWithTag(Tag tag) {
            requireNonNull(tag);
            this.tag = tag;
        }

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return this.tag.isSameTag(tag);
        }
    }

    /**
     * A Model stub that always accepts the tag being added.
     */
    private class ModelStubAcceptingTagAdded extends ModelStub {
        final ArrayList<Tag> tagsAdded = new ArrayList<>();

        @Override
        public boolean hasTag(Tag tag) {
            requireNonNull(tag);
            return tagsAdded.stream().anyMatch(tag::isSameTag);
        }

        @Override
        public void addTag(Tag tag) {
            requireNonNull(tag);
            tagsAdded.add(tag);
        }

        @Override
        public void commitAddressBook() {}

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

