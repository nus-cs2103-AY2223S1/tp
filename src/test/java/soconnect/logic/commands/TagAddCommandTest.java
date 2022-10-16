package soconnect.logic.commands;

import static soconnect.logic.commands.CommandTestUtil.assertCommandFailure;
import static soconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static soconnect.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static soconnect.testutil.Assert.assertThrows;
import static soconnect.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static soconnect.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static soconnect.testutil.TypicalPersons.FRIENDS;
import static soconnect.testutil.TypicalPersons.OWESMONEY;
import static soconnect.testutil.TypicalPersons.getTypicalSoConnect;

import org.junit.jupiter.api.Test;

import soconnect.model.Model;
import soconnect.model.ModelManager;
import soconnect.model.SoConnect;
import soconnect.model.TodoList;
import soconnect.model.UserPrefs;
import soconnect.model.person.Person;
import soconnect.model.tag.Tag;
import soconnect.testutil.PersonBuilder;

public class TagAddCommandTest {

    private Model model = new ModelManager(getTypicalSoConnect(), new TodoList(), new UserPrefs());

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
        Model expectedModel = new ModelManager(new SoConnect(model.getSoConnect()), new TodoList(), new UserPrefs());
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

