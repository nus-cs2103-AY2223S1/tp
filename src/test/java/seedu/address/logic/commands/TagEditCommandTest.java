package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.FRIENDS;
import static seedu.address.testutil.TypicalPersons.OWESMONEY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.tag.Tag;

public class TagEditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void edit_tag_successful() {
        TagEditCommand tagEditCommand = new TagEditCommand(FRIENDS, new Tag("bestFriend"));
        String expectedMessage = String.format(TagEditCommand.MESSAGE_EDIT_PERSON_SUCCESS,
                FRIENDS, new Tag("bestFriend"));
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
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
