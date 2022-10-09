package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ModelStub;
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
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;



public class TagCommandTest {

    @Test
    public void contructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagCommand(null, new HashSet<>()));
    }

    @Test
    public void contructor_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_indexDoesNotExist_throwsCommandException() {
        ModelStubWithPersonList modelStub = new ModelStubWithPersonList();
        assertThrows(
                CommandException.class, () -> new TagCommand(
                        Index.fromZeroBased(0), new HashSet<>()).execute(modelStub));
    }

    @Test
    public void execute_noTagsPassedIn_userNotified() throws Exception {
        ModelStubWithPersonList modelStub = new ModelStubWithPersonList();
        modelStub.personList.add(ALICE);
        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), new HashSet<>()).execute(modelStub);
        assertEquals(MESSAGE_NO_TAGS_ADDED, commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_userDoesNotAlreadyHaveTags_success() throws Exception {
        ModelStubWithPersonList modelStub = new ModelStubWithPersonList();
        modelStub.personList.add(ALICE);
        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_OWES_MONEY);
        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), tagsToAdd).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToAdd)), commandResult.getFeedbackToUser());
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

    static class ModelStubWithPersonList extends ModelStub {
        private Set<Tag> tags = new HashSet<>();
        private ObservableList<Person> personList = FXCollections.observableArrayList();

        public ModelStubWithPersonList() {
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
        }

        @Override
        public void clearFiltersInFilteredPersonList() {
        }

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
        }
    }
}
