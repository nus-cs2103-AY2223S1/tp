package seedu.intrack.logic.commands;

import static seedu.intrack.commons.core.Messages.MESSAGE_INTERNSHIPS_STATS_OVERVIEW;
import static seedu.intrack.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.intrack.testutil.TypicalInternships.getTypicalInTrack;

import org.junit.jupiter.api.Test;

import seedu.intrack.model.Model;
import seedu.intrack.model.ModelManager;
import seedu.intrack.model.UserPrefs;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;

public class StatsCommandTest {

    @Test
    public void execute_emptyInTrack_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        String expectedMessage = String.format(MESSAGE_INTERNSHIPS_STATS_OVERVIEW, 0, 0.00, 0, 0.00, 0, 0.00);

        assertCommandSuccess(new StatsCommand(), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_nonEmptyInTrack_success() {
        Model model = new ModelManager(getTypicalInTrack(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalInTrack(), new UserPrefs());

        int offered = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Offered"));
        int progress = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Progress"));
        int rejected = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Rejected"));

        int total = offered + progress + rejected;

        float offeredPercentage = (float) offered / total * 100;
        float progressPercentage = (float) progress / total * 100;
        float rejectedPercentage = (float) rejected / total * 100;

        String expectedMessage = String.format(
                MESSAGE_INTERNSHIPS_STATS_OVERVIEW, offered, offeredPercentage, progress, progressPercentage,
                rejected, rejectedPercentage);

        assertCommandSuccess(new StatsCommand(), model, expectedMessage, expectedModel);
    }

}
