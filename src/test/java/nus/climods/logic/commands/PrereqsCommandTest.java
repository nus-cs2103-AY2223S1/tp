package nus.climods.logic.commands;

import static nus.climods.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import nus.climods.logic.commands.exceptions.CommandException;
import nus.climods.model.Model;
import nus.climods.model.ModelManager;
import nus.climods.model.UserPrefs;
import nus.climods.model.module.Module;
import nus.climods.model.module.ModuleList;
import nus.climods.model.module.UniqueUserModuleList;

public class PrereqsCommandTest {
    private static final String testAcademicYear = "2022-2023";
    private static final String INVALID_CODE = "CS9999";
    private static final String VALID_CODE_NULL_PREQ = "AC5001";
    private final Model model = new ModelManager(new ModuleList(testAcademicYear), new UniqueUserModuleList(),
            new UserPrefs());

    @Test
    public void execute_moduleCodeNotValid_throwsException() {
        PrereqsCommand cmd = new PrereqsCommand(INVALID_CODE);
        assertThrows(CommandException.class,
                String.format(PrereqsCommand.MESSAGE_MODULE_NOT_FOUND, INVALID_CODE), () -> cmd.execute(model));
    }

    @Test
    public void execute_prereqIsNull_returnsNoPrereqMessage() {
        PrereqsCommand cmd = new PrereqsCommand(VALID_CODE_NULL_PREQ);
        try {
            CommandResult res = cmd.execute(model);
            Assertions.assertEquals(String.format(PrereqsCommand.MESSAGE_MODULE_NULL_PREREQUISITES,
                    VALID_CODE_NULL_PREQ), res.getFeedbackToUser());
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void execute_prereqNoValidModules_returnsNoPrereqMessage() {
        PrereqsCommand cmd = new PrereqsCommand("MA1301X");
        String errMsg = "Prerequisite description: Pass in GCE ‘O’ Level Additional Mathematics or GCE ‘AO’ Level or "
                + "H1 Mathematics\n" + "Unable to show prerequisites for this module.";
        try {
            CommandResult res = cmd.execute(model);
            Assertions.assertTrue(res.getFeedbackToUser().equals(errMsg));
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void execute_prereqHasValidModules_returnsSuccess() {
        PrereqsCommand cmd = new PrereqsCommand("CS2106");
        String errMsg = "Prerequisite description: CS2100 or EE2007 or EE2024 or EE2028\n"
                + "Showing available prerequisites for CS2106";
        try {
            CommandResult res = cmd.execute(model);
            Assertions.assertTrue(res.getFeedbackToUser().equals(errMsg));
            List<String> codes = model.getFilteredModuleList()
                    .stream().map(Module::getCode).collect(Collectors.toList());
            Assertions.assertEquals(codes, Arrays.asList("CS2100", "EE2024", "EE2028"));
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
    }
}
