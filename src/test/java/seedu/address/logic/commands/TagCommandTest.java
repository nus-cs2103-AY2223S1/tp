package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TAGS_NOT_FOUND;
import static seedu.address.logic.commands.TagCommand.MESSAGE_DUPLICATE_TAGS;
import static seedu.address.logic.commands.TagCommand.MESSAGE_NO_TAGS_ADDED;
import static seedu.address.logic.commands.TagCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWES_MONEY;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandTestUtil.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.person.TargetPerson;
import seedu.address.model.tag.Tag;



public class TagCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagCommand(null, new HashSet<>()));
    }

    @Test
    public void constructor_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_indexDoesNotExist_throwsCommandException() {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        assertThrows(
                CommandException.class, () -> new TagCommand(
                        Index.fromZeroBased(0), new HashSet<>()).execute(modelStub));
    }

    @Test
    public void execute_noTargetPerson_throwsCommandException() {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        // no index provided
        assertThrows(CommandException.class, () -> new TagCommand(new HashSet<>()).execute(modelStub));
    }

    @Test
    public void execute_noTagsPassedIn_userNotified() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.personList.add(ALICE);
        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), new HashSet<>()).execute(modelStub);
        assertEquals(MESSAGE_NO_TAGS_ADDED, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_personDoesNotAlreadyHaveTags_success() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.personList.add(ALICE);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_OWES_MONEY);
        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), tagsToAdd).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToAdd)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_targetPersonDoesNotAlreadyHaveTags_success() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.personList.add(ALICE);
        modelStub.setTargetPerson(ALICE);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_OWES_MONEY);
        // no index provided
        CommandResult commandResult = new TagCommand(tagsToAdd).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToAdd)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_tagDoesNotExist_tagsNotFound() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.personList.add(ALICE);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(new Tag("FakeTag"));
        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), tagsToAdd).execute(modelStub);
        assertEquals(String.format(MESSAGE_TAGS_NOT_FOUND, Tag.toString(tagsToAdd)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_personAlreadyHaveTagsAndNoNewTagsAdded_duplicateTagsAndNoTagsAddedMessage() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.personList.add(ALICE);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_OWES_MONEY);
        CommandResult command0Result = new TagCommand(Index.fromZeroBased(0), tagsToAdd).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToAdd)), command0Result.getFeedbackToUser());

        CommandResult command1Result = new TagCommand(Index.fromZeroBased(0), tagsToAdd).execute(modelStub);
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(String.format(MESSAGE_DUPLICATE_TAGS, Tag.toString(tagsToAdd)) + "\n");
        expectedResult.append(String.format(MESSAGE_NO_TAGS_ADDED));
        assertEquals(expectedResult.toString(), command1Result.getFeedbackToUser());
    }

    @Test
    public void execute_personAlreadyHaveTagsAndANewTagAdded_duplicateTagsAndTagAddedAddedMessage() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.personList.add(ALICE);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_OWES_MONEY);
        // Alice already have tag friends
        tagsToAdd.add(VALID_TAG_FRIENDS);

        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), tagsToAdd).execute(modelStub);
        StringBuilder expectedResult = new StringBuilder();
        expectedResult.append(String.format(MESSAGE_DUPLICATE_TAGS, VALID_TAG_FRIENDS.tagName) + "\n");
        expectedResult.append(String.format(MESSAGE_SUCCESS, VALID_TAG_OWES_MONEY.tagName));
        assertEquals(expectedResult.toString(), commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index index = Index.fromZeroBased(0);
        Set<Tag> tagSet1 = new HashSet<>();
        tagSet1.add(VALID_TAG_FRIENDS);
        Set<Tag> tagSet2 = new HashSet<>();
        tagSet2.add(VALID_TAG_OWES_MONEY);

        TagCommand tagCommand1 = new TagCommand(index, tagSet1);
        TagCommand tagCommand2 = new TagCommand(index, tagSet1);
        TagCommand tagCommand3 = new TagCommand(index, tagSet2);


        // same tags -> return true
        assertTrue(tagCommand1.equals(tagCommand2));

        // different tags -> return false
        assertFalse(tagCommand1.equals(tagCommand3));

        // null -> return false
        assertFalse(tagCommand1.equals(null));
    }

    static class ModelStubWithPersonListAndTargetPerson extends ModelStub {
        private Set<Tag> tags = new HashSet<>();
        private ObservableList<Person> personList = FXCollections.observableArrayList();
        private TargetPerson targetPerson = new TargetPerson();

        public ModelStubWithPersonListAndTargetPerson() {
            tags.add(VALID_TAG_FRIENDS);
            tags.add(VALID_TAG_OWES_MONEY);
        }

        @Override
        public void addPerson(Person person) {
            personList.add(person);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            if (personList.contains(target)) {
                int i = personList.indexOf(target);
                personList.set(i, editedPerson);
            }

            if (isTargetPerson(target)) {
                setTargetPerson(editedPerson);
            }
        }

        @Override
        public void clearFiltersInFilteredPersonList() {}

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return personList;
        }

        @Override
        public boolean hasTag(Tag tag) {
            return tags.contains(tag);
        }

        @Override
        public void addTag(Tag tag) {
            tags.add(tag);
        }

        @Override
        public void removeTags(Person target, Collection<Tag> tagsToRemove) {
            Set<Tag> newTags = new HashSet<>(target.getTags());
            newTags.removeAll(tagsToRemove);

            Person untaggedPerson = new Person(
                    target.getName(), target.getPhone(), target.getEmail(),
                    target.getAddress(), target.getRemark(), newTags);
            int i = personList.indexOf(target);
            personList.set(i, untaggedPerson);

            if (isTargetPerson(target)) {
                setTargetPerson(untaggedPerson);
            }
        }

        @Override
        public void setTargetPerson(Person person) {
            targetPerson.set(person);
        }

        @Override
        public void clearTargetPerson() {
            targetPerson.clear();
        }

        @Override
        public boolean isTargetPerson(Person person) {
            return targetPerson.isSamePerson(person);
        }

        @Override
        public boolean hasTargetPerson() {
            return targetPerson.isPresent();
        }

        @Override
        public Person getTargetPerson() {
            return targetPerson.get();
        }
    }
}
