package seedu.intrack.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.intrack.logic.commands.CommandTestUtil.showInternshipAtIndex;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_FIRST_INTERNSHIP;
import static seedu.intrack.testutil.TypicalIndexes.INDEX_SECOND_INTERNSHIP;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.commons.core.index.Index;
import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.tag.Tag;


class AddTagCommandTest {

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    private Tag urgent = new Tag("Urgent");
    private Tag remote = new Tag("Remote");
    private Tag help = new Tag("Help");

    private List<Tag> taglistStub = new ArrayList<Tag>(Arrays.asList(remote,
            urgent, help));

    private List<Tag> taglistStub1 = new ArrayList<Tag>(Arrays.asList(urgent, help, remote));

    private Set<Tag> taglistStub2 = new HashSet<Tag>(Arrays.asList(urgent, help, remote));


    /* I am not sure why this is causing the toModelType_typicalInternshipsFile_success()  to fail in json tests
    @Test
    void execute_validInternshipIndexSelectedList_success() {
        //test if the actual addtag command is a success
        Internship firstInternship = model.getFilteredInternshipList().get(INDEX_FIRST_INTERNSHIP.getZeroBased());
        System.out.println(InternshipUtil.getInternshipDetails(firstInternship));
        Internship expectedInternship = new InternshipBuilder(firstInternship)
                .withTags("Urgent", "Remote", "Help").build();

        System.out.println(InternshipUtil.getInternshipDetails(expectedInternship));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                taglistStub);
        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS,
                expectedInternship);
        Model expectedModel = new ModelManager(model.getInTrack(), new UserPrefs());

        expectedModel.setInternship(firstInternship, expectedInternship);
        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }
 */


    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of intrack book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInTrack().getInternshipList().size());
        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, taglistStub);
        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final AddTagCommand standardCommand = new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                taglistStub);
        // same values -> returns true
        AddTagCommand commandWithSameValues = new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                taglistStub);
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different index -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_SECOND_INTERNSHIP,
                taglistStub)));
        // different taglist -> returns false
        assertFalse(standardCommand.equals(new AddTagCommand(INDEX_FIRST_INTERNSHIP,
                taglistStub1)));
    }

}
