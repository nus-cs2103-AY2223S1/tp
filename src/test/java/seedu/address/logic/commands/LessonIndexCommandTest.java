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
 * {@code LessonIndexCommand}.
 */
public class LessonIndexCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Lesson lesson = new LessonBuilder().build();

    @Test
    public void execute_validIndexAndLesson_success() {
        Index indexFirstPerson = Index.fromZeroBased(0);
        Person firstPerson = model.getFilteredPersonList().get(0);

        PersonBuilder personInList = new PersonBuilder(firstPerson);
        Person personWithLesson = personInList.withLessons(lesson).build();

        LessonIndexCommand lessonIndexCommand = new LessonIndexCommand(lesson, indexFirstPerson);

        String expectedMessage = String.format(LessonIndexCommand.MESSAGE_ADD_LESSON_SUCCESS, lesson.toFullString(),
                indexFirstPerson.getZeroBased(), firstPerson.getName());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(0), personWithLesson);

        assertCommandSuccess(lessonIndexCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexAndValidLesson_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        LessonIndexCommand lessonIndexCommand = new LessonIndexCommand(lesson, outOfBoundIndex);

        assertCommandFailure(lessonIndexCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        LessonIndexCommand lessonFirstCommand = new LessonIndexCommand(lesson, INDEX_FIRST_PERSON);
        LessonIndexCommand lessonSecondCommand = new LessonIndexCommand(lesson, INDEX_SECOND_PERSON);

        assertTrue(lessonFirstCommand.equals(lessonFirstCommand));

        LessonIndexCommand lessonFirstCommandCopy = new LessonIndexCommand(lesson, INDEX_FIRST_PERSON);
        assertTrue(lessonFirstCommand.equals(lessonFirstCommandCopy));

        assertFalse(lessonFirstCommand.equals(1));

        assertFalse(lessonFirstCommand.equals(null));

        assertFalse(lessonFirstCommand.equals(lessonSecondCommand));
    }
}
