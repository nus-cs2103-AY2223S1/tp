package seedu.address.logic.commands.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTags.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditPersonDescriptor;
import seedu.address.logic.commands.EditTaskDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.TagBuilder;
import seedu.address.testutil.TypicalTags;

public class DeleteTagCommandTest {

    private static boolean deleteTagFromContact = true;
    private static boolean deleteTagFromTask = true;
    private static Index contactIndex = Index.fromZeroBased(1);
    private static Index taskIndex = Index.fromZeroBased(1);
    private static List<String> tagList;
    private static Model model;
    private static EditPersonDescriptor editPersonDescriptor;
    private static EditTaskDescriptor editTaskDescriptor;

    public void initialise() {
        tagList = new ArrayList<>();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        editPersonDescriptor = new EditPersonDescriptor();
        editTaskDescriptor = new EditTaskDescriptor();
        deleteTagFromContact = true;
        deleteTagFromTask = true;
    }

    @Test
    public void constructor_nullContactIndex_throwsNullPointerException() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, taskIndex,
                editPersonDescriptor, editTaskDescriptor, deleteTagFromContact, deleteTagFromTask, tagList));
    }

    @Test
    public void execute_tagRemovedByModelFromContact_deleteSuccessful() throws Exception {
        initialise();
        deleteTagFromTask = false;
        Tag validTag = new TagBuilder().withName("friends").build();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(validTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);
        tagList.add(validTag.getName());
        Model expected = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expected.decreaseTagCount(validTag);
        ObservableList<Tag> expectedTags = expected.getFilteredTagList();

        CommandResult commandResult = new DeleteTagCommand(contactIndex, taskIndex, editPersonDescriptor,
                editTaskDescriptor, deleteTagFromContact, deleteTagFromTask, tagList).execute(model);

        assertEquals(String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, tagSet),
                commandResult.getFeedbackToUser());

        assertEquals(expectedTags, model.getFilteredTagList());
    }

    @Test
    public void execute_tagNotFoundOnContact_deleteUnsuccessful() throws Exception {
        initialise();
        deleteTagFromTask = false;
        Tag validTag = new TagBuilder().withName("tagThatDoesNotExist").build();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(validTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);
        tagList.add(validTag.getName());

        assertThrows(CommandException.class,
                DeleteTagCommand.MESSAGE_TAGS_DO_NOT_EXIST, () -> new DeleteTagCommand(contactIndex, taskIndex,
                editPersonDescriptor, editTaskDescriptor, deleteTagFromContact, deleteTagFromTask, tagList)
                .execute(model));
    }

    @Test
    public void equals() {
        Tag firstTag = TypicalTags.CS2103T;
        Tag secondTag = TypicalTags.CS2101;
        EditPersonDescriptor editPersonDescriptor1 = new EditPersonDescriptor();
        EditTaskDescriptor editTaskDescriptor1 = new EditTaskDescriptor();
        EditPersonDescriptor editPersonDescriptor2 = new EditPersonDescriptor();
        EditTaskDescriptor editTaskDescriptor2 = new EditTaskDescriptor();
        Set<Tag> tagSet1 = new HashSet<>();
        Set<Tag> tagSet2 = new HashSet<>();
        tagSet1.add(firstTag);
        tagSet2.add(secondTag);
        editPersonDescriptor1.setTags(tagSet1);
        editTaskDescriptor1.setTags(tagSet1);
        editPersonDescriptor2.setTags(tagSet2);
        editTaskDescriptor2.setTags(tagSet2);
        List<String> tagList1 = new ArrayList<>();
        tagList1.add(firstTag.getName());
        List<String> tagList2 = new ArrayList<>();
        tagList1.add(secondTag.getName());
        DeleteTagCommand deleteFirstTagCommand = new DeleteTagCommand(contactIndex, taskIndex, editPersonDescriptor1,
                editTaskDescriptor1, deleteTagFromContact, deleteTagFromTask, tagList1);
        DeleteTagCommand deleteSecondTagCommand = new DeleteTagCommand(contactIndex, taskIndex, editPersonDescriptor2,
                editTaskDescriptor2, deleteTagFromContact, deleteTagFromTask, tagList2);

        // same object -> returns true
        assertTrue(deleteFirstTagCommand.equals(deleteFirstTagCommand));

        // same values -> returns true
        DeleteTagCommand deleteFirstTagCommandCopy = new DeleteTagCommand(contactIndex, taskIndex,
            editPersonDescriptor1, editTaskDescriptor1, deleteTagFromContact, deleteTagFromTask, tagList1);
        assertTrue(deleteFirstTagCommand.equals(deleteFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstTagCommand.equals(null));

        // different tag -> returns false
        assertFalse(deleteFirstTagCommand.equals(deleteSecondTagCommand));
    }

}

