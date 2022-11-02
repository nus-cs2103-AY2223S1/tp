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
import modtrekt.model.module.ModCredit;
import modtrekt.model.module.ModName;
import modtrekt.testutil.ModelStub;

class AddModuleCommandTest {
    @Test
    public void testCommand_sameObjectReferenceEquals_returnsTrue() {
        AddModuleCommand cmd =
                new AddModuleCommand(new ModCode("CS2109S"),
                        new ModName("Introduction to AI and Machine Learning"), new ModCredit("4"));
        assertEquals(cmd, cmd);
    }

    @Test
    public void testCommand_differentObjectValuesEquals_returnsFalse() {
        AddModuleCommand a =
                new AddModuleCommand(new ModCode("CS2109S"),
                        new ModName("Introduction to AI and Machine Learning"), new ModCredit("4"));
        AddModuleCommand b =
                new AddModuleCommand(new ModCode("CS2109"),
                        new ModName("Introduction to AI and Machine Learning"), new ModCredit("4"));
        assertNotEquals(a, b);
    }

    @Test
    public void testCommand_sameObjectValuesEquals_returnsTrue() {
        AddModuleCommand cmd1 =
                new AddModuleCommand(new ModCode("CS2109S"));
        AddModuleCommand cmd2 =
                new AddModuleCommand(new ModCode("CS2109S"));
        assertEquals(cmd1, cmd2);
    }

    @Test
    public void testModtRektParser_validCommandWord_returnsValidCommand() throws ParseException {
        AddModuleCommand expected =
                new AddModuleCommand(new ModCode("CS2109S"),
                        new ModName("Introduction to AI and Machine Learning"), new ModCredit("4"));
        Command actual =
                new ModtrektParser()
                        .parseCommand("add module CS2109S");
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
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add module CS2109S -p"));
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add -a -b -c -p high"));
    }

    @Test
    public void testParser_missingFlags_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add"));
    }

    // MODULES

    @Test
    public void testParser_invalidModule_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add module CS@0"));
    }

    @Test
    public void testParser_multipleModules_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("add module CS2100 CS2103T"));
    }

    @Test
    public void testParser_missingModule_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add module"));
    }

    // Credits

    @Test
    public void testParser_multipleCredits_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser()
                .parseCommand("add module CS2103T -n hello -cr 4 5"));
    }

    @Test
    public void testParser_missingCredit_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add module CS2103T -n hello -cr"));
    }

    // Names

    @Test
    public void testParser_missingName_throwsParseException() {
        assertThrows(ParseException.class, () -> new ModtrektParser().parseCommand("add module CS2103T -n -cr 4"));
    }

    // With ModelStub

    @Test
    public void testParser_missingBothOptionalParameters_throwsParseException() {
        ModelStub model = new ModelStub();
        assertThrows(CommandException.class, () -> new ModtrektParser()
                .parseCommand("add module CS2103T -n hello").execute(model));
        assertThrows(CommandException.class, () -> new ModtrektParser()
                .parseCommand("add module CS2103T -cr 4").execute(model));
    }

    @Test
    public void testParser_successfulModuleAdd() {
        ModelStub model = new ModelStub();
        assertDoesNotThrow(() -> new ModtrektParser().parseCommand("add module CS2109S").execute(model));
        assertEquals(model.hasModuleWithModCode(new ModCode("CS2109S")), true);
    }

    @Test
    public void testParser_duplicateModuleAdd() {
        ModelStub model = new ModelStub();
        assertDoesNotThrow(() -> new ModtrektParser().parseCommand("add module CS2109S").execute(model));
        assertThrows(CommandException.class, () -> new ModtrektParser()
                .parseCommand("add module CS2109S").execute(model));
    }
}
