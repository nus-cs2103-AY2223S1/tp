package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for CommentCommand.
 */
public class AddCommentUnfilteredList {

    private static final String COMMENT_STUB = "Some comment";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    /**
     * Test for comment
     */
    @Test
    public void execute_addCommentUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withComment(COMMENT_STUB).build();

        CommentCommand commentCommand = new CommentCommand(INDEX_FIRST_PERSON,
                new Comment(editedPerson.getComment().value));

        String expectedMessage = String.format(CommentCommand.MESSAGE_ADD_COMMENT_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(commentCommand, model, expectedMessage, expectedModel);
    }
}
