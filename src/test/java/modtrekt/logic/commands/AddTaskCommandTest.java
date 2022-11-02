package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.module.ModCode;
import modtrekt.testutil.AddTaskCommandBuilder;
import modtrekt.testutil.ModelStub;

public class AddTaskCommandTest {

    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, null, "none");
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        AddTaskCommand cmd1 = AddTaskCommandBuilder.build("desc", "2022-01-01", "CS2103T", "none");
        AddTaskCommand cmd2 = AddTaskCommandBuilder.build("des", "2022-01-01", "CS2103T", "none");
        AddTaskCommand cmd3 = AddTaskCommandBuilder.build("desc", "2022-01-02", "CS2103T", "none");
        AddTaskCommand cmd4 = AddTaskCommandBuilder.build("desc", "2022-01-01", "CS2103", "none");
        assertNotEquals(cmd1, cmd2);
        assertNotEquals(cmd1, cmd3);
        assertNotEquals(cmd1, cmd4);
        assertNotEquals(cmd2, cmd3);
        assertNotEquals(cmd3, cmd4);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        AddTaskCommand cmd1 = AddTaskCommandBuilder.build("desc", "2022-01-01", "CS2103T", "none");
        AddTaskCommand cmd2 = AddTaskCommandBuilder.build("desc", "2022-01-01", "CS2103T", "none");
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void testCommand_sameObjectValuesEqualsWithNulls_returnsTrue() {
        AddTaskCommand cmd1 = AddTaskCommandBuilder.build("desc", null, "CS2103T", "none");
        AddTaskCommand cmd2 = AddTaskCommandBuilder.build("desc", null, "CS2103T", "none");
        AddTaskCommand cmd3 = AddTaskCommandBuilder.build("desc", "2022-01-01", null, "none");
        AddTaskCommand cmd4 = AddTaskCommandBuilder.build("desc", "2022-01-01", null, "none");
        assertEquals(cmd1, cmd2);
        assertEquals(cmd3, cmd4);
    }

    @Test
    public void testCommandNoCurrentModuleAndNoModuleCode_throws() {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, null, "none");
        assertThrows(CommandException.class, () -> cmd.execute(new ModelStub()));
    }

    @Test
    public void testCommandNoCurrentModuleWithModuleCode_returnsTrue() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, "CS2102", "none");
        CommandResult result = cmd.execute(new ModelHasModuleWithModCode());
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandDeadlineNoCurrentModuleWithModuleCode_returnsTrue() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", "2022-04-15", "CS2102", "none");
        CommandResult result = cmd.execute(new ModelHasModuleWithModCode());
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandCurrentModuleWithoutModuleCode_returnsTrue() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, null, "none");
        ModelStub model = new ModelStub();
        model.setCurrentModule(new ModCode("CS2106"));
        CommandResult result = cmd.execute(model);
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandDeadlineCurrentModuleWithoutModuleCode_returnsTrue() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", "2022-04-15", null, "none");
        ModelStub model = new ModelStub();
        model.setCurrentModule(new ModCode("CS2106"));
        CommandResult result = cmd.execute(model);
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandModuleCodeInvalid_throws() {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, "CS2000", "none");
        assertThrows(CommandException.class, "Module code CS2000 does not exist", ()
                -> cmd.execute(new ModelStub()));
    }

    @Test
    public void testCommandCurrentModuleWithModuleCode_returnsCodeSpecified() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, "CS2102", "none");
        ModelStub model = new ModelHasModuleWithModCode();
        model.setCurrentModule(new ModCode("CS2106"));
        CommandResult result = cmd.execute(model);
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandDeadlineCurrentModuleWithModuleCode_returnsCodeSpecified() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", "2022-04-15", "CS2102", "none");
        ModelStub model = new ModelHasModuleWithModCode();
        model.setCurrentModule(new ModCode("CS2106"));
        CommandResult result = cmd.execute(model);
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }


    /**
     * A model stub that returns true for the hasModuleWithModCode method, to mimic specified behaviour.
     */
    private class ModelHasModuleWithModCode extends ModelStub {
        @Override
        public boolean hasModuleWithModCode(ModCode code) {
            return true;
        }
    }
}
