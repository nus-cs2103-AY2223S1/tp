package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    public void construct_nullModule_throwsException() {
        assertThrows(NullPointerException.class, () -> new PickCommand(null, null, null));
    }

    @Test
    public void execute_pick_success() throws CommandException {
        String expectedMessage = String.format(PickCommand.MESSAGE_SUCCESS, "CS1010J TUT 02");
        model.addUserModule(new UserModule("CS1010J", SemestersEnum.S1));

        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.TUT, "02");
        CommandResult commandResult = pickCommand.execute(model);

        assertEquals(commandResult.getFeedbackToUser(), expectedMessage);
    }

    /**
     * CS1010J is not offered in Semester 2.
     * @throws CommandException
     */
    @Test
    public void execute_addLessonTypeNotOfferedInSemester_throwsException() {
        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.REC, "15");

        assertThrows(CommandException.class, () -> pickCommand.execute(model));
    }

    /**
     * CS1010J is not offered in Semester 2.
     * @throws CommandException
     */
    @Test
    public void execute_addLessonIdNotOfferedInSemester_throwsException() {
        PickCommand pickCommand = new PickCommand("CS1010J", LessonTypeEnum.TUT, "15");

        assertThrows(CommandException.class, () -> pickCommand.execute(model));
    }
}
