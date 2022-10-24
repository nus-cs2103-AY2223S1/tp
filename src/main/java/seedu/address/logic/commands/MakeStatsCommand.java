package seedu.address.logic.commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.model.Model;

/**
 * Format full help instructions for every command for display.
 */
public class MakeStatsCommand extends Command {

    public static final String COMMAND_WORD = "makeStats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_STATS_MESSAGE = "Opened statistics window.";

    @Override
    public CommandResult execute(Model model) {
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
                new PieChart.Data("GYOURMOMefruit", 13),
                new PieChart.Data("Oranges", 25),
                new PieChart.Data("Plums", 10),
                new PieChart.Data("Pears", 22),
                new PieChart.Data("Apples", 30));
        model.setData(data);
        return new CommandResult(SHOWING_STATS_MESSAGE,
                false, true, false);
    }
}
