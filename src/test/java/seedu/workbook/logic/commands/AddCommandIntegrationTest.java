package seedu.workbook.logic.commands;

import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.workbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.workbook.testutil.TypicalInternships.getTypicalWorkBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.workbook.model.Model;
import seedu.workbook.model.ModelManager;
import seedu.workbook.model.UserPrefs;
import seedu.workbook.model.internship.Internship;
import seedu.workbook.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalWorkBook(), new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = new InternshipBuilder().build();

        Model expectedModel = new ModelManager(model.getWorkBook(), new UserPrefs());
        expectedModel.addInternship(validInternship);
        expectedModel.commitWorkBook();

        assertCommandSuccess(new AddCommand(validInternship), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validInternship), expectedModel);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship internshipInList = model.getWorkBook().getInternshipList().get(0);
        assertCommandFailure(new AddCommand(internshipInList), model, AddCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

}
