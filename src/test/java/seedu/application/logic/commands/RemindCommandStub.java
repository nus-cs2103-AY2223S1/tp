package seedu.application.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.application.model.Model;
import seedu.application.model.application.UpcomingInterviewPredicateStub;

/**
 * Reminds users of upcoming interviews within 1 week from now stored in CinternS.
 * The predicate used in this stub has a pre-configured date and time reference
 * and does not follow the system clock.
 */
public class RemindCommandStub extends Command {

    public static final String COMMAND_WORD = "remind";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows all upcoming interviews within 1 week from "
            + "now stored in CinternS.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_REMIND_MESSAGE = "Opened reminder window.";

    private static final UpcomingInterviewPredicateStub UPCOMING_INTERVIEW_PREDICATE =
            new UpcomingInterviewPredicateStub();

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult(SHOWING_REMIND_MESSAGE, true, false, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || other instanceof RemindCommandStub; // instanceof handles nulls
    }
}
