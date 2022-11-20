package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.ModelStub;
import static seedu.address.logic.commands.CreateTagCommand.MESSAGE_DUPLICATE_TAGS;
import static seedu.address.logic.commands.CreateTagCommand.MESSAGE_SUCCESS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.VALID_TAG_FRIENDS;
import static seedu.address.testutil.TypicalTags.VALID_TAG_OWES_MONEY;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;


public class CreateTagCommandTest {

    @Test
    public void constructor_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CreateTagCommand(null));
    }

    @Test
    public void execute_noDuplicateTags_addSuccessful() throws Exception {
        ModelStubAcceptingTagsAdded modelStub = new ModelStubAcceptingTagsAdded();

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_FRIENDS);
        tagsToAdd.add(VALID_TAG_OWES_MONEY);

        CommandResult commandResult = new CreateTagCommand(tagsToAdd).execute(modelStub);

        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToAdd)), commandResult.getFeedbackToUser());
        assertEquals(tagsToAdd, modelStub.tagsAdded);
    }

    @Test
    public void execute_duplicateTags_userNotified() throws Exception {
        ModelStubAcceptingTagsAdded modelStub = new ModelStubAcceptingTagsAdded();
        modelStub.addTag(VALID_TAG_FRIENDS);

        Set<Tag> tagsToAdd = new HashSet<>();
        tagsToAdd.add(VALID_TAG_FRIENDS);
        tagsToAdd.add(VALID_TAG_OWES_MONEY);

        CommandResult commandResult = new CreateTagCommand(tagsToAdd).execute(modelStub);

        String duplicateMessage = String.format(MESSAGE_DUPLICATE_TAGS, Tag.toString(Set.of(VALID_TAG_FRIENDS))) + "\n";
        String successMessage = String.format(MESSAGE_SUCCESS, Tag.toString(Set.of(VALID_TAG_OWES_MONEY)));
        assertEquals(duplicateMessage + successMessage, commandResult.getFeedbackToUser());
        assertEquals(tagsToAdd, modelStub.tagsAdded);
    }

    @Test
    public void equals() throws Exception {
        ModelStubAcceptingTagsAdded modelStub = new ModelStubAcceptingTagsAdded();

        Set<Tag> tagsToAdd1 = new HashSet<>();
        tagsToAdd1.add(VALID_TAG_FRIENDS);
        CommandResult commandResult1 = new CreateTagCommand(tagsToAdd1).execute(modelStub);

        Set<Tag> tagsToAdd2 = new HashSet<>();
        tagsToAdd2.add(VALID_TAG_FRIENDS);
        CommandResult commandResult2 = new CreateTagCommand(tagsToAdd2).execute(modelStub);

        Set<Tag> tagsToAdd3 = new HashSet<>();
        tagsToAdd3.add(VALID_TAG_OWES_MONEY);
        CommandResult commandResult3 = new CreateTagCommand(tagsToAdd3).execute(modelStub);

        // same tags added -> return true
        assertTrue(tagsToAdd1.equals(tagsToAdd2));

        // different tags added -> return false
        assertFalse(tagsToAdd1.equals(tagsToAdd3));

        // null -> return false
        assertFalse(tagsToAdd1.equals(null));
    }

    private class ModelStubAcceptingTagsAdded extends ModelStub {
        private Set<Tag> tagsAdded = new HashSet<>();

        @Override
        public boolean hasTag(Tag tag) {
            return tagsAdded.contains(tag);
        }

        @Override
        public void addTag(Tag tag) {
            tagsAdded.add(tag);
        }
    }
}
