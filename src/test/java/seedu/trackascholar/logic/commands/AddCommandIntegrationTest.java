package seedu.trackascholar.logic.commands;

import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.trackascholar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackascholar.testutil.TypicalApplicants.getTypicalTrackAScholar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ModelManager;
import seedu.trackascholar.model.UserPrefs;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTrackAScholar(), new UserPrefs());
    }

    @Test
    public void execute_newApplicant_success() {
        Applicant validApplicant = new ApplicantBuilder().build();

        Model expectedModel = new ModelManager(model.getTrackAScholar(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddCommand(validApplicant), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validApplicant), expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant applicantInList = model.getTrackAScholar().getApplicantList().get(0);
        assertCommandFailure(new AddCommand(applicantInList), model, AddCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

}
