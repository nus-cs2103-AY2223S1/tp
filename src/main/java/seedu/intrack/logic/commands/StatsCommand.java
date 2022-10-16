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

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        int offered = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Offered"));
        int progress = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Progress"));
        int rejected = model.getFilteredStatusInternshipListSize(new StatusIsKeywordPredicate("Rejected"));
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_STATS_OVERVIEW, offered, progress, rejected));
    }

}
