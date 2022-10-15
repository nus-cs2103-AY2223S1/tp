package soconnect.logic.commands.tagcommand;

import static soconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.testutil.TypicalPersons.FRIENDS;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.Test;

import soconnect.logic.commands.tagcommands.TagCreateCommand;
import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.SoConnect;
import soconnect.model.UserPrefs;
import soconnect.model.tag.Tag;

public class TagCreateCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new UserPrefs());

    @Test
    public void tag_create_success() {
        TagCreateCommand tagCreateCommand = new TagCreateCommand(new Tag("sibling"));

        String expectedMessage = String.format(TagCreateCommand.MESSAGE_SUCCESS, new Tag("sibling"));

        Model expectedModel = new ModelManager(new SoConnect(model.getSoConnect()), new UserPrefs());
        expectedModel.addTag(new Tag("sibling"));

        assertCommandSuccess(tagCreateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagAlreadyExists_throwsCommandException() {
        TagCreateCommand tagCreateCommand = new TagCreateCommand(FRIENDS);
        assertCommandFailure(tagCreateCommand, model, TagCreateCommand.MESSAGE_DUPLICATE_TAG);
    }
}
