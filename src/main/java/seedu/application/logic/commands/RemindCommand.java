package seedu.application.logic.commands;

import seedu.application.model.Model;
import seedu.application.model.application.CompanyContainsKeywordsPredicate;
import seedu.application.model.application.PositionContainsKeywordsPredicate;
import seedu.application.model.application.UpcomingInterviewPredicate;

import static java.util.Objects.requireNonNull;

/**
 * Reminds users of upcoming interviews within 1 week from now stored in CinternS.
 */
public class RemindCommand extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all upcoming interviews within 1 week from "
            + "now stored in CinternS.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_REMIND_MESSAGE = "Opened reminder window.";

    private final UpcomingInterviewPredicate upcomingInterviewPredicate;

    /**
     * Constructs RemindCommand object.
     */
    public RemindCommand() {
        this.upcomingInterviewPredicate = new UpcomingInterviewPredicate();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicationsWithUpcomingInterviewList(upcomingInterviewPredicate);
        return new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
    }
}
