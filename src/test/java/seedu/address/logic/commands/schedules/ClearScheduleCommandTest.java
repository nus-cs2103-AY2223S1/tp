package seedu.address.logic.commands.schedules;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalSchedules.getTypicalModuleCodeFromTypicalSchedules;
import static seedu.address.testutil.TypicalSchedules.getTypicalProfNusWithSchedules;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.schedule.ClearScheduleCommand;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;

public class ClearScheduleCommandTest {

    private Model model = new ModelManager(getTypicalProfNusWithSchedules(), new UserPrefs());


    @Test
    public void execute_emptyProfNUS_success() {
        Model curModel = new ModelManager();
        Model expectedModel = new ModelManager();
        CommandResult expectedCommandResult = new CommandResult(
            ClearScheduleCommand.MESSAGE_CLEAR_ALL_SCHEDULES_SUCCESS,
                false, false, false, false,
                false, true, false, false);

        assertCommandSuccess(new ClearScheduleCommand(),  curModel, expectedCommandResult, expectedModel);
    }


    @Test
    public void equals() {

        ArrayList<ModuleCode> allModuleCodes = getTypicalModuleCodeFromTypicalSchedules();

        ClearScheduleCommand clearScheduleFirstCommand = new ClearScheduleCommand(allModuleCodes);
        ClearScheduleCommand clearScheduleEmptyCommand = new ClearScheduleCommand();

        assertTrue(clearScheduleFirstCommand.equals(clearScheduleFirstCommand));
        assertTrue(clearScheduleEmptyCommand.equals(clearScheduleEmptyCommand));
        assertTrue(clearScheduleFirstCommand.equals(new ClearScheduleCommand(allModuleCodes)));
    }

    @Test
    public void execute_nonExistModuleCode_throwCommandException() {
        ArrayList<ModuleCode> nonExistModuleCodes = getTypicalModuleCodeFromTypicalSchedules();
        nonExistModuleCodes.add(new ModuleCode("AB1234"));
        ClearScheduleCommand clearScheduleCommand = new ClearScheduleCommand(nonExistModuleCodes);

        assertThrows(CommandException.class, () -> clearScheduleCommand.execute(model));
    }

    @Test
    public void execute_allModuleCodes_throwCommandException() {
        ArrayList<ModuleCode> allModuleCodes = getTypicalModuleCodeFromTypicalSchedules();
        ClearScheduleCommand clearScheduleCommand = new ClearScheduleCommand(allModuleCodes);

        Model expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
        expectedModel.clearSchedules(allModuleCodes);

        CommandResult expectedCommandResult = new CommandResult(
                ClearScheduleCommand.MESSAGE_CLEAR_ALL_SCHEDULES_SUCCESS, false, false,
                false, false,
                false, true, false, false);

        assertCommandSuccess(clearScheduleCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_partialModuleCodes_throwCommandException() {
        ArrayList<ModuleCode> partialCodes = getTypicalModuleCodeFromTypicalSchedules();
        partialCodes.remove(0);
        ClearScheduleCommand clearScheduleCommand = new ClearScheduleCommand(partialCodes);

        Model expectedModel = new ModelManager(model.getProfNus(), new UserPrefs());
        expectedModel.clearSchedules(partialCodes);

        CommandResult expectedCommandResult = new CommandResult(
                ClearScheduleCommand.MESSAGE_CLEAR_ALL_SCHEDULES_SUCCESS, false, false,
                false, false,
                false, true, false, false);

        assertCommandSuccess(clearScheduleCommand, model, expectedCommandResult, expectedModel);
    }

}
