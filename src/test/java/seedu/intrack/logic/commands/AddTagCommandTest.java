package seedu.intrack.logic.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_URGENT;
import static seedu.intrack.logic.commands.CommandTestUtil.VALID_TAG_REMOTE;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.model.InTrack;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.model.tag.Tag;
import seedu.intrack.testutil.InternshipBuilder;

import java.util.*;

class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    private Tag Urgent = new Tag("Urgent");
    private Tag Remote = new Tag("Remote");
    private Tag Help = new Tag("Help");

    private  List<Tag> TAGLIST_STUB = new ArrayList<Tag>(Arrays.asList(Remote,
            Urgent, Help));

    private  List<Tag> TAGLIST_STUB_1 = new ArrayList<Tag>(Arrays.asList(Urgent, Remote));

    @Test
    void execute_validInternshipIndexSelectedList_Success() {
        //test if the actual addtag command is a success
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        Internship expectedInternship = new InternshipBuilder(firstInternship)
                .withTags("Urgent", "Remote", "Help").build();
        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                TAGLIST_STUB);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS,
                expectedInternship);
        Model expectedModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        expectedModel.setInternship(firstInternship, expectedInternship);
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of intrack book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInTrack().getInternshipList().size());
        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, TAGLIST_STUB);
        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTagCommand standardCommand = new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                TAGLIST_STUB);
        // same values -> returns true
        AddTagCommand commandWithSameValues = new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                TAGLIST_STUB);
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_SECOND_INTERNSHIP,
                TAGLIST_STUB)));
        // different taglist -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                TAGLIST_STUB_1)));
    }

}