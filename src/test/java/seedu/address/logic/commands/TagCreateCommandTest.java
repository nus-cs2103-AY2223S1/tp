package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.FRIENDS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class TagCreateCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void tag_create_success() {
        TagCreateCommand tagCreateCommand = new TagCreateCommand(new Tag("sibling"));

        String expectedMessage = String.format(TagCreateCommand.MESSAGE_SUCCESS, new Tag("sibling"));

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addTag(new Tag("sibling"));

        assertCommandSuccess(tagCreateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagAlreadyExists_throwsCommandException() {
        TagCreateCommand tagCreateCommand = new TagCreateCommand(FRIENDS);
        assertCommandFailure(tagCreateCommand, model, TagCreateCommand.MESSAGE_DUPLICATE_TAG);
    }
}
