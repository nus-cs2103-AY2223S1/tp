package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.statistics.StatisticChartType;

public class GenerateStatsCommand extends Command {

    public GenerateStatsCommand(Index index, StatisticChartType type) {

    }

    public CommandResult execute(Model model) throws CommandException{
        return new CommandResult(null);
    }
}
