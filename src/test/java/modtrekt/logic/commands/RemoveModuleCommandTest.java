package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.ModtrektParser;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;
import modtrekt.testutil.ModelStub;
import modtrekt.testutil.ModuleBuilder;

class RemoveModuleCommandTest {
    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        RemoveModuleCommand cmd = new RemoveModuleCommand(new ModCode("CS1231S"));
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        RemoveModuleCommand a = new RemoveModuleCommand(new ModCode("CS1231S"));
        RemoveModuleCommand b = new RemoveModuleCommand(new ModCode("CS2109S"));
        assertNotEquals(a, b);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        RemoveModuleCommand cmd1 = new RemoveModuleCommand(new ModCode("CS1231S"));
        RemoveModuleCommand cmd2 = new RemoveModuleCommand(new ModCode("CS1231S"));
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void testModtRektParser_validCommandWord_returnsValidCommand() throws ParseException {
        RemoveModuleCommand expected = new RemoveModuleCommand(new ModCode("CS1231S"));
        Command actual = new ModtrektParser().parseCommand("remove module CS1231S");
        assertEquals(expected, actual);
    }

    // === === ===
    // Negative test cases

    // NULL

    @Test
    public void testParser_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModtrektParser().parseCommand(null));
    }

    // FLAGS

    @Test
    public void testParser_invalidFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("remove module CS2109S -p"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("remove -a -b -c -p high"));
    }

    @Test
    public void testParser_missingFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("remove"));
    }

    // MODULES

    @Test
    public void testParser_invalidModule_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("remove module CS@0"));
    }

    @Test
    public void testParser_multipleModules_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("remove module CS2100 CS2103T"));
    }

    @Test
    public void testParser_missingModule_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("remove module"));
    }

    // With ModelStub

    @Test
    public void testParser_successfulModuleRemove() {
        ModelStub model = new ModelStub();
        Module module = new ModuleBuilder().build();
        model.addModule(module);
        assertEquals(model.hasModuleWithModCode(new ModCode("CS1231S")), true);
        assertDoesNotThrow(() -> new ModtrektParser().parseCommand("remove module CS1231S").execute(model));
        assertEquals(model.hasModuleWithModCode(new ModCode("CS1231S")), false);;
    }

    @Test
    public void testParser_noSuchModuleRemove() {
        ModelStub model = new ModelStub();
        assertThrows(CommandException.class, () -> new ModtrektParser()
                .parseCommand("remove module CS2109S").execute(model));
    }
}
