package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TAGS_NOT_FOUND;
import static seedu.address.logic.commands.RemoveTagCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.RemoveTagCommand.MESSAGE_TAGS_NOT_BELONG_TO_USER;
import static seedu.address.logic.commands.TagCommandTest.ModelStubWithPersonListAndTargetPerson;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWES_MONEY;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.tag.Tag;

public class RemoveTagCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveTagCommand(null, new HashSet<>()));
    }

    @Test
    public void constructor_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> new RemoveTagCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_indexDoesNotExist_throwsCommandException() {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        assertThrows(CommandException.class, () -> new RemoveTagCommand(
                Index.fromZeroBased(0), new HashSet<>()).execute(modelStub));
    }

    @Test
    public void execute_noTargetPerson_throwsCommandException() {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        // no index provided
        assertThrows(CommandException.class, () -> new RemoveTagCommand(new HashSet<>()).execute(modelStub));
    }

    @Test
    public void execute_tagsExistAndBelongToPerson_success() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.addPerson(ALICE);
        Set<Tag> tagsToRemove = new HashSet<>();
        tagsToRemove.add(VALID_TAG_FRIENDS);
        CommandResult commandResult =
                new RemoveTagCommand(Index.fromZeroBased(0), tagsToRemove).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToRemove)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_tagsExistAndBelongToTargetPerson_success() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.addPerson(ALICE);
        modelStub.setTargetPerson(ALICE);
        Set<Tag> tagsToRemove = new HashSet<>();
        tagsToRemove.add(VALID_TAG_FRIENDS);
        // no index provided
        CommandResult commandResult = new RemoveTagCommand(tagsToRemove).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToRemove)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_tagDoesNotExist_tagsNotFound() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.addPerson(ALICE);
        Set<Tag> tagsToRemove = new HashSet<>();
        tagsToRemove.add(new Tag("FakeTag"));
        CommandResult commandResult = new TagCommand(Index.fromZeroBased(0), tagsToRemove).execute(modelStub);
        assertEquals(String.format(MESSAGE_TAGS_NOT_FOUND, Tag.toString(tagsToRemove)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_tagsExistButDoesNotBelongToPerson_tagDoesNotBelongToPersonMessage() throws Exception {
        ModelStubWithPersonListAndTargetPerson modelStub = new ModelStubWithPersonListAndTargetPerson();
        modelStub.addPerson(ALICE);
        Set<Tag> tagsToRemove = new HashSet<>();
        tagsToRemove.add(VALID_TAG_OWES_MONEY);
        CommandResult commandResult =
                new RemoveTagCommand(Index.fromZeroBased(0), tagsToRemove).execute(modelStub);
        assertEquals(String.format(MESSAGE_TAGS_NOT_BELONG_TO_USER, Tag.toString(tagsToRemove)),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void equals() {
        Index index = Index.fromZeroBased(0);
        Set<Tag> tagSet1 = new HashSet<>();
        tagSet1.add(VALID_TAG_FRIENDS);
        Set<Tag> tagSet2 = new HashSet<>();
        tagSet2.add(VALID_TAG_OWES_MONEY);

        RemoveTagCommand removeTagCommand1 = new RemoveTagCommand(index, tagSet1);
        RemoveTagCommand removeTagCommand2 = new RemoveTagCommand(index, tagSet1);
        RemoveTagCommand removeTagCommand3 = new RemoveTagCommand(index, tagSet2);

        // same tags -> return true
        assertTrue(removeTagCommand1.equals(removeTagCommand2));

        // different tags -> return false
        assertFalse(removeTagCommand1.equals(removeTagCommand3));

        // null -> return false
        assertFalse(removeTagCommand1.equals(null));
    }
}
