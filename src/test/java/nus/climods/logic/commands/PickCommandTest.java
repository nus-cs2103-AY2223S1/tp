package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openapitools.client.model.SemestersEnum;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.LessonTypeEnum;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;
import nus.climods.model.module.UserModule;


public class PickCommandTest {
    private static final String testAcademicYear = "2022-2023";
    private final Model model = new ModelManager(new ModuleList(testAcademicYear), new UniqueUserModuleList(),
            new UserPrefs());
    private void addCS1010J() {
        model.addUserModule(new UserModule("CS1010J", SemestersEnum.S1));
    }

    /**
     * Null module throws exception.
     */
    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new PickCommand(null, null, null));
    }

    /**
     * Successful execution of PickCommand.
     * @throws CommandException
     */
    @Test
    public void execute_pick_success() throws CommandException {
        String expectedMessage = String.format(PickCommand.MESSAGE_SUCCESS, "CS1010J TUT 02");
        addCS1010J();
        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.TUT, "02");
        CommandResult commandResult = pickCommand.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    /**
     * Recitations not offered in CS1010J
     * @throws CommandException
     */
    @Test
    public void execute_addLessonTypeNotOfferedInSemester_throwsException() {
        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.REC, "15");
        addCS1010J();
        assertThrows(CommandException.class, PickCommand.MESSAGE_INVALID_LESSON_TYPE, () -> pickCommand.execute(model));
    }

    /**
     * Tut 15 not a valid tutorial for CS1010J
     * @throws CommandException
     */
    @Test
    public void execute_addLessonIdNotOfferedInSemester_throwsException() {
        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.TUT, "15");
        addCS1010J();
        assertThrows(CommandException.class, PickCommand.MESSAGE_INVALID_LESSON_ID, () -> pickCommand.execute(model));
    }

    /**
     * Lecture is unpickable for cs1010j.
     * @throws CommandException
     */
    @Test
    public void execute_addLessonIdUnpickable_throwsException() {
        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.LEC, "15");
        addCS1010J();
        assertThrows(CommandException.class, PickCommand.MESSAGE_PICK_UNSELECTABLE_LESSON, ()
                -> pickCommand.execute(model));
    }

    /**
     * Missing module.
     * @throws CommandException
     */
    @Test
    public void execute_pickLessonForMissingModule_throwsException() {
        PickCommand pickCommand = new PickCommand("CS2100", LessonTypeEnum.TUT, "15");

        assertThrows(CommandException.class, PickCommand.MESSAGE_MODULE_MISSING, () -> pickCommand.execute(model));
    }

    /**
     * Tests that both lab and tutorial can be added
     */
    @Test
    public void execute_addLabAndTutorial_success() {
        PickCommand addLab = new PickCommand("CS2100", LessonTypeEnum.LAB, "01");
        PickCommand addTutorial = new PickCommand("CS2100", LessonTypeEnum.TUT, "02");
        model.addUserModule(new UserModule("CS2100", SemestersEnum.S1));
        Assertions.assertDoesNotThrow(() -> addLab.execute(model));
        Assertions.assertDoesNotThrow(() -> addTutorial.execute(model));
    }

}
