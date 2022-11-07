package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Lesson;
import seedu.address.model.person.Person;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code RemoveIndexCommand}.
 */
public class RemoveIndexCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Lesson lesson = new LessonBuilder().build();

    @Test
    public void execute_validIndexAndLesson_success() {
        Index indexFirstPerson = Index.fromZeroBased(0);
        Person firstPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person personWithLesson = personInList.withLessons(lesson).build();
        model.setPerson(model.getFilteredPersonList().get(0), personWithLesson);

        RemoveIndexCommand removeIndexCommand = new RemoveIndexCommand(lesson, indexFirstPerson);

        String expectedMessage = String.format(RemoveIndexCommand.MESSAGE_REMOVE_LESSON_SUCCESS, lesson.toFullString(),
                indexFirstPerson.getZeroBased(), firstPerson.getName());

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        assertCommandSuccess(removeIndexCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAndValidLesson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        RemoveIndexCommand removeIndexCommand = new RemoveIndexCommand(lesson, outOfBoundIndex);

        assertCommandFailure(removeIndexCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        RemoveIndexCommand removeFirstCommand = new RemoveIndexCommand(lesson, INDEX_FIRST_PERSON);
        RemoveIndexCommand removeSecondCommand = new RemoveIndexCommand(lesson, INDEX_SECOND_PERSON);

        assertTrue(removeFirstCommand.equals(removeFirstCommand));

        RemoveIndexCommand removeFirstCommandCopy = new RemoveIndexCommand(lesson, INDEX_FIRST_PERSON);
        assertTrue(removeFirstCommand.equals(removeFirstCommandCopy));

        assertFalse(removeFirstCommand.equals(1));

        assertFalse(removeFirstCommand.equals(null));

        assertFalse(removeFirstCommand.equals(removeSecondCommand));
    }
}
