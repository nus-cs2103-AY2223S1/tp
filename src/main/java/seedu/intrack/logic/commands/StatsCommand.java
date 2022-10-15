package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;

/**
 * Lists all internships in the internship tracker to the user.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    private static int offered = 0;

    private static int progress = 0;

    private static int rejected = 0;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Offered"));
        offered = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Offered"));
        progress = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Progress"));
        rejected = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Rejected"));
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_STATS_OVERVIEW, offered, progress, rejected));
    }

}
