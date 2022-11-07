package soconnect.logic.commands.tagcommand;

import static soconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.testutil.TypicalPersons.FRIENDS;
import static soconnect.testutil.TypicalPersons.OWESMONEY;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.Test;

import soconnect.logic.commands.tagcommands.TagEditCommand;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.SoConnect;
import soconnect.model.TodoList;
import soconnect.model.UserPrefs;
import soconnect.model.tag.Tag;

public class TagEditCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());

    @Test
    public void edit_tag_successful() {
        TagEditCommand tagEditCommand = new TagEditCommand(FRIENDS, new Tag("bestFriend"));
        String expectedMessage = String.format(TagEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                FRIENDS, new Tag("bestFriend"));
        Model expectedModel = new ModelManager(new SoConnect(model.getSoConnect()), new TodoList(), new UserPrefs());
        expectedModel.editTag(FRIENDS, new Tag("bestFriend"));

        assertCommandSuccess(tagEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_newTagAlreadyExists_throwsCommandException() {
        TagEditCommand tagEditCommand = new TagEditCommand(FRIENDS, OWESMONEY);
        String expectedMessage = String.format(TagEditCommand.MESSAGE_DUPLICATE_TAG, OWESMONEY);

        assertCommandFailure(tagEditCommand, model, expectedMessage);
    }

    @Test
    public void execute_oldTagDoesNotExist_throwCommandException() {
        TagEditCommand tagEditCommand = new TagEditCommand(new Tag("fake"), new Tag("Fake"));
        String expectedMessage = String.format(TagEditCommand.MESSAGE_TAG_NOT_FOUND, new Tag("fake"));

        assertCommandFailure(tagEditCommand, model, expectedMessage);
    }

}
