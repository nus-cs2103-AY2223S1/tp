package modtrekt.logic.commands;

import static modtrekt.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.module.ModCode;
import modtrekt.model.module.Module;

import org.junit.jupiter.api.Test;

public class DoneModuleCommandTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        ModCode moduleCode = null;
        assertThrows(NullPointerException.class, () -> new DoneModuleCommand(moduleCode));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        Model model = null;
        ModCode moduleCode = new ModCode("CS1101S");
        DoneModuleCommand command = new DoneModuleCommand(moduleCode);
        assertThrows(NullPointerException.class, () -> command.execute(model));
    }
}
