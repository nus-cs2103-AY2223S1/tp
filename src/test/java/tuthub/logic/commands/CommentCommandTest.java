package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.Assert.assertThrows;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Tutor;
import tuthub.testutil.TutorBuilder;

public class CommentCommandTest {

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void equals() {
        Index index1 = Index.fromZeroBased(0);
        Comment comment1 = new Comment("test");
        CommentCommand commentCommand1 = new CommentCommand(index1, comment1);

        Index index2 = Index.fromZeroBased(1);
        Comment comment2 = new Comment("test 2");
        CommentCommand commentCommand2 = new CommentCommand(index2, comment2);

        CommentCommand commentCommand3 = new CommentCommand(index1, comment2);

        // same object -> returns true
        assertTrue(commentCommand1.equals(commentCommand1));

        // same values -> returns true
        CommentCommand commentFirstCommandCopy = new CommentCommand(index1, comment1);
        assertTrue(commentCommand1.equals(commentFirstCommandCopy));

        // different types -> returns false
        assertFalse(commentCommand1.equals(1));

        // null -> returns false
        assertFalse(commentCommand1.equals(null));

        // different index -> returns false
        assertFalse(commentCommand2.equals(commentCommand3));

        // different comment -> returns false
        assertFalse(commentCommand1.equals(commentCommand3));

        // different everything -> returns false
        assertFalse(commentCommand1.equals(commentCommand2));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index index = Index.fromZeroBased(200);
        CommentCommand commentCommand = new CommentCommand(index, new Comment("test"));
        assertThrows(CommandException.class, () -> commentCommand.execute(model));
    }

    @Test
    public void execute_invalidComment_throwsCommandException() {
        Index index = Index.fromZeroBased(0);
        CommentCommand commentCommand = new CommentCommand(index, new Comment(""));
        assertThrows(CommandException.class, () -> commentCommand.execute(model));
    }

    @Test
    public void execute_validArgs_success() {
        Index index = Index.fromZeroBased(0);
        CommentCommand commentCommand = new CommentCommand(index, new Comment("test"));

        Tutor tutorInFilteredList = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        Tutor editedTutor = new TutorBuilder(tutorInFilteredList).withComment("test").build();
        Model expectedModel = new ModelManager(new Tuthub(model.getTuthub()), new UserPrefs());
        expectedModel.setTutor(model.getFilteredTutorList().get(0), editedTutor);

        String expectedMessage = String.format(CommentCommand.MESSAGE_ADD_COMMENT_SUCCESS,
                editedTutor);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
        System.out.println(model.getFilteredTutorList().get(0));
    }
}
