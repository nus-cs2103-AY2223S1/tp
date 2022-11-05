package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.model.module.ModCode;
import modtrekt.testutil.AddDeadlineCommandBuilder;
import modtrekt.testutil.ModelStub;

public class AddDeadlineCommandTest {
    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103T");
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        AddDeadlineCommand cmd1 = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103T");
        AddDeadlineCommand cmd2 = AddDeadlineCommandBuilder.build("des", "2022-01-01", "CS2103T");
        AddDeadlineCommand cmd3 = AddDeadlineCommandBuilder.build("desc", "2022-01-02", "CS2103T");
        AddDeadlineCommand cmd4 = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103");
        assertNotEquals(cmd1, cmd2);
        assertNotEquals(cmd1, cmd3);
        assertNotEquals(cmd1, cmd4);
        assertNotEquals(cmd2, cmd3);
        assertNotEquals(cmd3, cmd4);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        AddDeadlineCommand cmd1 = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103T");
        AddDeadlineCommand cmd2 = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103T");
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void testCommand_sameObjectValuesEqualsWithNulls_returnsTrue() {
        AddDeadlineCommand cmd1 = AddDeadlineCommandBuilder.build("desc", "2022-01-02", "CS2103T");
        AddDeadlineCommand cmd2 = AddDeadlineCommandBuilder.build("desc", "2022-01-02", "CS2103T");
        AddDeadlineCommand cmd3 = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103");
        AddDeadlineCommand cmd4 = AddDeadlineCommandBuilder.build("desc", "2022-01-01", "CS2103");
        assertEquals(cmd1, cmd2);
        assertEquals(cmd3, cmd4);
    }

    @Test
    public void testCommandNoCurrentModule_throws() {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-09-09", "CS2103T");
        assertThrows(CommandException.class, () -> cmd.execute(new ModelStub()));
    }

    @Test
    public void testCommandNoCurrentModuleWithModuleCode_returnsTrue() throws Exception {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-09-09", "CS2102");
        CommandResult result = cmd.execute(new AddDeadlineCommandTest.ModelHasModuleWithModCode());
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandDeadlineNoCurrentModuleWithModuleCode_returnsTrue() throws Exception {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-04-15", "CS2102");
        CommandResult result = cmd.execute(new AddDeadlineCommandTest.ModelHasModuleWithModCode());
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandModuleCodeInvalid_throws() {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-04-15", "CS2000");
        assertThrows(CommandException.class, "Module code does not exist", ()
                -> cmd.execute(new ModelStub()));
    }

    @Test
    public void testCommandCurrentModuleWithModuleCode_returnsCodeSpecified() throws Exception {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-04-15", "CS2102");
        ModelStub model = new AddDeadlineCommandTest.ModelHasModuleWithModCode();
        model.setCurrentModule(new ModCode("CS2106"));
        CommandResult result = cmd.execute(model);
        assertTrue(result.getFeedbackToUser().contains("desc"));
    }

    @Test
    public void testCommandDeadlineCurrentModuleWithModuleCode_returnsCodeSpecified() throws Exception {
        AddDeadlineCommand cmd = AddDeadlineCommandBuilder.build("desc", "2022-04-15", "CS2102");
        ModelStub model = new AddDeadlineCommandTest.ModelHasModuleWithModCode();
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
