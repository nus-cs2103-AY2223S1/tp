package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.RemoveTagCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.TagCommandTest.ModelStubWithPersonList;
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
    public void contructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveTagCommand(null, new HashSet<>()));
    }

    @Test
    public void contructor_nullSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new RemoveTagCommand(Index.fromZeroBased(0), null));
    }

    @Test
    public void execute_indexDoesNotExist_throwsCommandException() {
        ModelStubWithPersonList modelStub = new ModelStubWithPersonList();
        assertThrows(CommandException.class, () -> new RemoveTagCommand(
                Index.fromZeroBased(0), new HashSet<>()).execute(modelStub));
    }

    @Test
    public void execute_tagsExistAndBelongToUser_success() throws Exception {
        ModelStubWithPersonList modelStub = new ModelStubWithPersonList();
        modelStub.addPerson(ALICE);
        Set<Tag> tagsToRemove = new HashSet<>();
        tagsToRemove.add(VALID_TAG_FRIENDS);
        CommandResult commandResult = new RemoveTagCommand(Index.fromZeroBased(0), tagsToRemove).execute(modelStub);
        assertEquals(String.format(MESSAGE_SUCCESS, Tag.toString(tagsToRemove)), commandResult.getFeedbackToUser());
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
