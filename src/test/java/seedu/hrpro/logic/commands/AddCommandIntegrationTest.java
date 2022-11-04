package seedu.hrpro.logic.commands;

import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.testutil.TypicalHRPro.getTypicalHRPro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.testutil.ProjectBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHRPro(), new UserPrefs());
    }

    @Test
    public void execute_newProject_success() {
        Project validProject = new ProjectBuilder().build();

        Model expectedModel = new ModelManager(model.getHRPro(), new UserPrefs());
        expectedModel.addProject(validProject);

        assertCommandSuccess(new AddCommand(validProject), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validProject), expectedModel);
    }

    @Test
    public void execute_duplicateProject_throwsCommandException() {
        Project projectInList = model.getHRPro().getProjectList().get(0);
        assertCommandFailure(new AddCommand(projectInList), model, AddCommand.MESSAGE_DUPLICATE_PROJECT);
    }

}
