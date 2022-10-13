package seedu.phu.logic.commands;

import static seedu.phu.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.phu.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.phu.testutil.TypicalInternships.getTypicalInternshipBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.phu.model.Model;
import seedu.phu.model.ModelManager;
import seedu.phu.model.UserPrefs;
import seedu.phu.model.internship.Internship;
import seedu.phu.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalInternshipBook(), new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = new InternshipBuilder().build();

        Model expectedModel = new ModelManager(model.getInternshipBook(), new UserPrefs());
        expectedModel.addInternship(validInternship);

        assertCommandSuccess(new AddCommand(validInternship), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validInternship), expectedModel);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship internshipInList = model.getInternshipBook().getInternshipList().get(0);
        assertCommandFailure(new AddCommand(internshipInList), model, AddCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

}
