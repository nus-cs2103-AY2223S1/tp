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

public class AddTagCommandTest {

    private static boolean addTagToContact = true;
    private static boolean addTagToTask = true;
    private static Index contactIndex = Index.fromZeroBased(0);
    private static Index taskIndex = Index.fromZeroBased(0);
    private static List<String> tagList;
    private static Model model;
    private static EditPersonDescriptor editPersonDescriptor;
    private static EditTaskDescriptor editTaskDescriptor;

    public void initialise() {
        tagList = new ArrayList<>();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        editPersonDescriptor = new EditPersonDescriptor();
        editTaskDescriptor = new EditTaskDescriptor();
    }

    @Test
    public void constructor_nullContactIndex_throwsNullPointerException() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        EditTaskDescriptor editTaskDescriptor = new EditTaskDescriptor();
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, taskIndex,
            editPersonDescriptor, editTaskDescriptor, addTagToContact, addTagToTask, tagList));
    }

    @Test
    public void execute_tagAcceptedByModel_addSuccessful() throws Exception {
        initialise();
        Tag validTag = new TagBuilder().withName("newTag").build();
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

    @Test
    public void execute_duplicateTagOnPerson_throwsCommandException() {
        initialise();
        Tag validTag = new TagBuilder().withName("CS2103T").build();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(validTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);
        tagList.add(validTag.getName());
        AddTagCommand addTag = new AddTagCommand(contactIndex, taskIndex,
                editPersonDescriptor, editTaskDescriptor, addTagToContact, addTagToTask, tagList);

        assertThrows(CommandException.class,
                AddTagCommand.MESSAGE_DUPLICATE_TAG_ON_PERSON_OR_TASK, () -> addTag.execute(model));
    }

    @Test
    public void execute_duplicateTagOnTask_throwsCommandException() {
        initialise();
        Tag validTag = new TagBuilder().withName("CS2103T").build();
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(validTag);
        editPersonDescriptor.setTags(tagSet);
        editTaskDescriptor.setTags(tagSet);
        tagList.add(validTag.getName());
        Index taskWithDuplicateTag = Index.fromZeroBased(1);

        assertThrows(CommandException.class,
                AddTagCommand.MESSAGE_DUPLICATE_TAG_ON_PERSON_OR_TASK, () -> new AddTagCommand(contactIndex,
                    taskWithDuplicateTag, editPersonDescriptor, editTaskDescriptor, addTagToContact,
                    addTagToTask, tagList).execute(model));
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
        AddTagCommand addFirstTagCommand = new AddTagCommand(contactIndex, taskIndex, editPersonDescriptor1,
                editTaskDescriptor1, addTagToContact, addTagToTask, tagList1);
        AddTagCommand addSecondTagCommand = new AddTagCommand(contactIndex, taskIndex, editPersonDescriptor2,
                editTaskDescriptor2, addTagToContact, addTagToTask, tagList2);

        // same object -> returns true
        assertTrue(addFirstTagCommand.equals(addFirstTagCommand));

        // same values -> returns true
        AddTagCommand addFirstTagCommandCopy = new AddTagCommand(contactIndex, taskIndex, editPersonDescriptor1,
                editTaskDescriptor1, addTagToContact, addTagToTask, tagList1);
        assertTrue(addFirstTagCommand.equals(addFirstTagCommandCopy));

        // different types -> returns false
        assertFalse(addFirstTagCommand.equals(1));

        // null -> returns false
        assertFalse(addFirstTagCommand.equals(null));

        // different tag -> returns false
        assertFalse(addFirstTagCommand.equals(addSecondTagCommand));
    }

}

