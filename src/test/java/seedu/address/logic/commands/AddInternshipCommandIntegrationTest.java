package seedu.address.logic.commands;

import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.InternshipCommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInternships.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.internship.Internship;
import seedu.address.testutil.InternshipBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddInternshipCommand}.
 */
public class AddInternshipCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newInternship_success() {
        Internship validInternship = new InternshipBuilder().withInternshipId(9).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addInternship(validInternship);

        assertCommandSuccess(new AddInternshipCommand(validInternship), model,
                String.format(AddInternshipCommand.MESSAGE_SUCCESS, validInternship), expectedModel);
    }

    @Test
    public void execute_duplicateInternship_throwsCommandException() {
        Internship internshipInList = model.getAddressBook().getInternshipList().get(0);
        assertCommandFailure(new AddInternshipCommand(internshipInList), model,
                AddInternshipCommand.MESSAGE_DUPLICATE_INTERNSHIP);
    }

}
