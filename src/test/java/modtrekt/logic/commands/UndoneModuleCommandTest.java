package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import modtrekt.model.Model;
import modtrekt.model.module.ModCode;

public class UndoneModuleCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        ModCode moduleCode = null;
        assertThrows(NullPointerException.class, () -> new UndoneModuleCommand(moduleCode));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = null;
        ModCode moduleCode = new ModCode("CS1101S");
        UndoneModuleCommand command = new UndoneModuleCommand(moduleCode);
        assertThrows(NullPointerException.class, () -> command.execute(model));
    }
}
