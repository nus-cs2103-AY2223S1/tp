package soconnect.logic.commands;

import static soconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.testutil.TypicalPersons.*;

import org.junit.jupiter.api.Test;

import soconnect.model.SoConnect;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.UserPrefs;
import soconnect.model.tag.Tag;

public class TagEditCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());

    @Test
    public void edit_tag_successful() {
        TagEditCommand tagEditCommand = new TagEditCommand(FRIENDS, new Tag("bestFriend"));
        String expectedMessage = String.format(TagEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                FRIENDS, new Tag("bestFriend"));
        Model expectedModel = new ModelManager(new SoConnect(model.getSoConnect()), new UserPrefs());
        expectedModel.editTag(FRIENDS, new Tag("bestFriend"));

        assertCommandSuccess(tagEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_newTagAlreadyExists_throwsCommandException() {
        TagEditCommand tagEditCommand = new TagEditCommand(FRIENDS, OWESMONEY);

        assertCommandFailure(tagEditCommand, model, TagEditCommand.MESSAGE_DUPLICATE_TAG);
    }

    @Test
    public void execute_oldTagDoesNotExist_throwCommandException() {
        TagEditCommand tagEditCommand = new TagEditCommand(new Tag("fake"), new Tag("Fake"));

        assertCommandFailure(tagEditCommand, model, TagEditCommand.MESSAGE_TAG_NOT_FOUND);
    }

}
