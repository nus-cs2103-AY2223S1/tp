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

class DeleteTagCommandTest {

    private Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());

    private Tag urgent = new Tag("Urgent");
    private Tag remote = new Tag("Remote");
    private Tag help = new Tag("Help");

    private List<Tag> taglistStub = new ArrayList<Tag>(Arrays.asList(urgent));

    private List<Tag> taglistStub0 = new ArrayList<Tag>(Arrays.asList(remote, urgent));

    private Set<Tag> taglistStub1 = new HashSet<>(Arrays.asList(remote, urgent));

    private Set<Tag> taglistStub2 = new HashSet<>(Arrays.asList(remote));

    /* this test affects other tests for some reason
    @Test
    void execute_validInternshipIndexSelectedList_Success() {
        //test if the actual deltag command is a success by deleting the Urgent tag from Meta, leaving Remote tag only
        //deleting a tag might affect the test cases, so we should use an isolated internship for testing delete.
        List<Internship> InternshipStub = new ArrayList<>();
        Model dummyModel = new ModelManager(new InTrack(model.getInTrack()), new UserPrefs());
        Internship initInternship = new Internship(new Name("Snapple"), new Position("CEO"), new Status("Offered"),
                new Email("WWW.IamABoss@gmail.com"), new Website("Snapple.com"), new ArrayList<Task>(),
                new Salary("10000"), taglistStub_1, new Remark("easy"));
        dummyModel.addInternship(initInternship);
        InternshipStub.add(initInternship);
        //we need a replacement internship to avoid it from affecting the rest of the tests
        Internship dummyInternship = InternshipStub.get(0);
        Internship expectedInternship = new Internship(initInternship.getName(), initInternship.getPosition(),
        initInternship.getStatus(), initInternship.getEmail(), initInternship.getWebsite(), initInternship.getTasks(),
        initInternship.getSalary(), taglistStub_2, initInternship.getRemark());

        DeleteTagCommand delTagCommand = new DeleteTagCommand(Index.fromZeroBased(4),
                taglistStub);
        String expectedMessage = String.format(delTagCommand.MESSAGE_DELETE_TAG_SUCCESS,
                expectedInternship);
        Model expectedModel = new ModelManager(new InTrack(dummyModel.getInTrack()), new UserPrefs());
        expectedModel.setInternship(initInternship, expectedInternship);
        assertCommandSuccess(delTagCommand, dummyModel, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidInternshipIndexFilteredList_failure() {
        showInternshipAtIndex(model, INDEX_FIRST_INTERNSHIP);
        Index outOfBoundIndex = INDEX_SECOND_INTERNSHIP;
        // ensures that outOfBoundIndex is still in bounds of intrack book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getInTrack().getInternshipList().size());
        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, taglistStub);
        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
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
                taglistStub0)));
    }

}
