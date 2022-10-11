package modtrekt.logic.commands;

import static modtrekt.logic.commands.CommandTestUtil.assertCommandFailure;
import static modtrekt.logic.commands.CommandTestUtil.assertCommandSuccess;
import static modtrekt.testutil.TypicalModules.getTypicalModuleList;
import static modtrekt.testutil.TypicalTasks.getTypicalTaskBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modtrekt.model.Model;
import modtrekt.model.ModelManager;
import modtrekt.model.UserPrefs;
import modtrekt.model.module.Module;
import modtrekt.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalModuleList(), getTypicalTaskBook(), new UserPrefs());
    }

    @Test
    public void execute_newModule_success() {
        Module validModule = new ModuleBuilder().build();

        Model expectedModel = new ModelManager(model.getModuleList(), model.getTaskBook(), new UserPrefs());
        expectedModel.addModule(validModule);

        assertCommandSuccess(new AddCommand(validModule), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validModule), expectedModel);
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        Module personInList = model.getModuleList().getModuleList().get(0);
        assertCommandFailure(new AddCommand(personInList), model, AddCommand.MESSAGE_DUPLICATE_MODULE);
    }

}
