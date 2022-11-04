package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Lesson;
import seedu.address.testutil.LessonBuilder;
import seedu.address.testutil.UserBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code TimetableUserCommand}.
 */
public class TimetableUserCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Lesson lesson = new LessonBuilder().build();

    @Test
    public void execute_success() {
        TimetableUserCommand timetableUserCommand = new TimetableUserCommand();

        // add lesson into model
        model.deleteUser();
        model.addUser(new UserBuilder()
                .withName("Zephyr Yosef")
                .withAddress("333, Yishun St 33, #03-33")
                .withPhone("90123456")
                .withEmail("zephYosef@example.com")
                .withGithub("ZephYosef")
                .withCurrentModules("CS2103T")
                .withPreviousModules("CS2040S")
                .withPlannedModules("CS2109S")
                .withLessons(lesson)
                .build());

        String expectedMessage = TimetableUserCommand.MESSAGE_TIMETABLE_ACKNOWLEDGEMENT;
        CommandResult expectedResult = new CommandResult(expectedMessage, false, false, true);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
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
                .withLessons(lesson)
                .build());

        assertCommandSuccess(timetableUserCommand, model, expectedResult, expectedModel);
    }

    @Test
    public void execute_emptyUser_throwsCommandException() {
        Model modelWithoutUser = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modelWithoutUser.deleteUser();

        TimetableUserCommand timetableUserCommand = new TimetableUserCommand();

        String expectedMessage = TimetableUserCommand.MESSAGE_NO_USER;

        assertCommandFailure(timetableUserCommand, modelWithoutUser, expectedMessage);
    }

    @Test
    public void execute_validUserNoLessons_throwsCommandException() {
        TimetableUserCommand timetableUserCommand = new TimetableUserCommand();

        assertCommandFailure(timetableUserCommand, model, TimetableUserCommand.MESSAGE_NO_LESSONS);
    }

    @Test
    public void equals() {
        TimetableUserCommand firstCommand = new TimetableUserCommand();
        TimetableUserCommand secondCommand = new TimetableUserCommand();

        assertTrue(firstCommand.equals(firstCommand));

        assertFalse(firstCommand.equals(1));

        assertFalse(firstCommand.equals(null));

        assertTrue(firstCommand.equals(secondCommand));
    }

}
