package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.FRIENDS;
import static seedu.address.testutil.TypicalPersons.OWESMONEY;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class TagAddCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void addNullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TagAddCommand(null, null));
        assertThrows(NullPointerException.class, () -> new TagAddCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void add_tag_successful() {
        TagAddCommand tagAddCommand =
                new TagAddCommand(INDEX_THIRD_PERSON, OWESMONEY);
        Person editedPerson = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());
        String expectedMessage = String.format(TagAddCommand.MESSAGE_ADD_TAG_SUCCESS, OWESMONEY);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Person newPerson = new PersonBuilder(editedPerson).withTags("owesMoney").build();
        expectedModel.setPerson(editedPerson, newPerson);
        expectedModel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(tagAddCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_tagDoesNotExist_throwsCommandException() {
        TagAddCommand tagAddCommand =
                new TagAddCommand(INDEX_THIRD_PERSON, new Tag("Test"));
        assertCommandFailure(tagAddCommand, model, TagAddCommand.MESSAGE_NO_SUCH_TAG);
    }

    @Test
    public void execute_tagAlreadyExists_throwsCommandException() {
        TagAddCommand tagAddCommand =
                new TagAddCommand(INDEX_FIRST_PERSON, FRIENDS);
        assertCommandFailure(tagAddCommand, model, TagAddCommand.MESSAGE_TAG_ALREADY_ADDED);
    }
}

