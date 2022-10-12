package tuthub.logic.commands;

import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalIndexes.INDEX_FIRST_TUTOR;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import org.junit.jupiter.api.Test;

import tuthub.model.Tuthub;
import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Comment;
import tuthub.model.tutor.Tutor;
import tuthub.testutil.TutorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CommentCommand.
 */
public class AddCommentUnfilteredList {

    private static final String COMMENT_STUB = "Some comment";

    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());

    /**
     * Test for comment
     */
    @Test
    public void execute_addCommentUnfilteredList_success() {
        Tutor firstTutor = model.getFilteredTutorList().get(INDEX_FIRST_TUTOR.getZeroBased());
        Tutor editedTutor = new TutorBuilder(firstTutor).withComment(COMMENT_STUB).build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_TUTOR,
                new Comment(editedTutor.getComment().value));

        String expectedMessage = String.format(CommentCommand.MESSAGE_ADD_COMMENT_SUCCESS, editedTutor);

        Model expectedModel = new ModelManager(new Tuthub(model.getTuthub()), new UserPrefs());
        expectedModel.setTutor(firstTutor, editedTutor);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
    }
}
