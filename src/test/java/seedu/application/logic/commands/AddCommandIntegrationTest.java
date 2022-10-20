package seedu.application.logic.commands;

import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalApplications.getTypicalApplicationBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.application.Application;
import seedu.application.testutil.ApplicationBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplication_success() {
        Application validApplication = new ApplicationBuilder().build();

        Model expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.addApplication(validApplication);

        assertCommandSuccess(new AddCommand(validApplication), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validApplication), expectedModel);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application applicationInList = model.getApplicationBook().getApplicationList().get(0);
        assertCommandFailure(new AddCommand(applicationInList), model, AddCommand.MESSAGE_DUPLICATE_APPLICATION);
    }

}
