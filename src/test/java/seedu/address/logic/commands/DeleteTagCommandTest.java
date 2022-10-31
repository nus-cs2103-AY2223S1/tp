package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_TAGS_NOT_FOUND;
import static seedu.address.logic.commands.CommandTestUtil.ModelStub;
import static seedu.address.logic.commands.DeleteTagCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWES_MONEY;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;


public class DeleteTagCommandTest {

    @Test
    public void contructor_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null));
    }

    @Test
    public void execute_noDuplicateTags_addSuccessful() throws Exception {
        ModelStubAcceptingTagsAdded modelStub = new ModelStubAcceptingTagsAdded();

        Set<Tag> tagsToDelete = new HashSet<>();
        tagsToDelete.add(VALID_TAG_FRIENDS);
        tagsToDelete.add(VALID_TAG_OWES_MONEY);

        CommandResult commandResult = new DeleteTagCommand(tagsToDelete).execute(modelStub);

        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToDelete)), commandResult.getFeedbackToUser());
        assertEquals(tagsToDelete, modelStub.tagsRemoved);
    }

    @Test
    public void execute_duplicateTags_userNotified() throws Exception {
        ModelStubAcceptingTagsAdded modelStub = new ModelStubAcceptingTagsAdded();
        modelStub.deleteTags(Set.of(VALID_TAG_FRIENDS));

        Set<Tag> tagsToAdd = Set.of(VALID_TAG_FRIENDS, VALID_TAG_OWES_MONEY);

        CommandResult commandResult = new DeleteTagCommand(tagsToAdd).execute(modelStub);

        String duplicateMessage =
                String.format(MESSAGE_TAGS_NOT_FOUND, Tag.toString(Set.of(VALID_TAG_FRIENDS))) + "\n";
        String successMessage = String.format(MESSAGE_SUCCESS, Tag.toString(Set.of(VALID_TAG_OWES_MONEY)));
        assertEquals(duplicateMessage + successMessage, commandResult.getFeedbackToUser());
        assertEquals(tagsToAdd, modelStub.tagsRemoved);
    }

    @Test
    public void equals() throws Exception {
        Set<Tag> tagsToAdd1 = Set.of(VALID_TAG_FRIENDS, VALID_TAG_OWES_MONEY);
        Command command1 = new DeleteTagCommand(tagsToAdd1);

        Set<Tag> tagsToAdd2 = Set.of(VALID_TAG_FRIENDS);
        Command command2 = new DeleteTagCommand(tagsToAdd2);

        Set<Tag> tagsToAdd3 = Set.of(VALID_TAG_OWES_MONEY, VALID_TAG_FRIENDS);
        Command command3 = new DeleteTagCommand(tagsToAdd3);

        // same command -> return true
        assertTrue(command1.equals(command1));

        // same tags -> return true
        assertTrue(command1.equals(new DeleteTagCommand(tagsToAdd1)));

        // different tags -> return false
        assertFalse(command1.equals(command2));

        // different order -> return true
        assertTrue(command1.equals(command3));

        // null -> return false
        assertFalse(tagsToAdd1.equals(null));

        // different type -> return false
        assertFalse(tagsToAdd1.equals(0));
    }

    private class ModelStubAcceptingTagsAdded extends ModelStub {
        private Set<Tag> tagsRemoved = new HashSet<>();

        @Override
        public boolean hasTag(Tag tag) {
            return !tagsRemoved.contains(tag);
        }

        @Override
        public Set<Tag> deleteTags(Set<Tag> tags) {
            Set<Tag> deletedTags = new HashSet<>(tags);
            deletedTags.removeAll(tagsRemoved);
            tagsRemoved.addAll(tags);
            return deletedTags;
        }
    }
}
