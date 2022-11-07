package seedu.clinkedin.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.Map;

import seedu.clinkedin.commons.core.Messages;
import seedu.clinkedin.model.Model;

/**
 * Calculates and displays the statistics of the persons in the user's ClInkedIn.
 * Uses the DoubleSummaryStatistics class to calculate the statistics.
 * Only the persons currently displayed in the user's ClInkedIn will be used to calculate the statistics. i.e. the
 * statistics will not include persons that are filtered out by the user.
 */
public class StatsCommand extends Command {

    public static final String COMMAND_WORD = "stats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Calculates and displays the statistics of the persons "
            + "in the user's ClInkedIn.\n"
            + "Example: " + COMMAND_WORD;

    private Map<String, Map<String, Integer>> stats;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        DoubleSummaryStatistics tagStats = model.getStats();
        stats = new HashMap<>();
        Map<String, Integer> ratingStatsMap = model.getRatingCount();
        stats.put("Rating Statistics", ratingStatsMap);
        return new CommandResult(String.format(
                Messages.MESSAGE_STATS_DISPLAYED_OVERVIEW, tagStats.getCount()), stats);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof StatsCommand // instanceof handles nulls
                ); // state check
    }
}
