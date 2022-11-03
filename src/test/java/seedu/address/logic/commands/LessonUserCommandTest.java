package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Lesson;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.UserBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code LessonUserCommand}.
 */
public class LessonUserCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Lesson lesson = new LessonBuilder().build();

    @Test
    public void execute_success() {
        LessonUserCommand lessonUserCommand = new LessonUserCommand(lesson);

        String expectedMessage = String.format(LessonUserCommand.MESSAGE_ADD_LESSON_SUCCESS, lesson.toFullString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        // copy with new User containing the same data
        expectedModel.deleteUser();
        expectedModel.addUser(new UserBuilder()
                .withName("Zephyr Yosef")
                .withAddress("333, Yishun St 33, #03-33")
                .withPhone("90123456")
                .withEmail("zephYosef@example.com")
                .withGithub("ZephYosef")
                .withCurrentModules("CS2103T")
                .withPreviousModules("CS2040S")
                .withPlannedModules("CS2109S")
                .build());

        try {
            expectedModel.addLessonToUser(lesson);
        } catch (CommandException e) {
            System.out.println(e);
            assertTrue(false);
        }

        assertCommandSuccess(lessonUserCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_emptyUser_throwsCommandException() {
        Model modelWithoutUser = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithoutUser.deleteUser();
        LessonUserCommand lessonUserCommand = new LessonUserCommand(lesson);

        String expectedMessage = LessonUserCommand.MESSAGE_NO_USER;

        assertCommandFailure(lessonUserCommand, modelWithoutUser, expectedMessage);
    }

    @Test
    public void equals() {
        LessonUserCommand firstCommand = new LessonUserCommand(lesson);
        LessonUserCommand secondCommand = new LessonUserCommand(new LessonBuilder().withModule("XX0000X").build());

        assertTrue(firstCommand.equals(firstCommand));

        LessonUserCommand firstCommandCopy = new LessonUserCommand(lesson);
        assertTrue(firstCommand.equals(firstCommandCopy));

        assertFalse(firstCommand.equals(1));

        assertFalse(firstCommand.equals(null));

        assertFalse(firstCommand.equals(secondCommand));
    }
}
