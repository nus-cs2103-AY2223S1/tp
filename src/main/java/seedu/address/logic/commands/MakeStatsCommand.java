package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;

/**
* Generates statistics for the event specified
*/
public class MakeStatsCommand extends Command {

    public static final String COMMAND_WORD = "makeStats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates the Statistics of the Event at the user"
        + "specified index. Type of statistics shown is based off a user input parameter.\n"
        + "Parameters INDEX " + PREFIX_TYPE + "TYPE. \n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TYPE + "g";

    public static final String SHOWING_STATS_MESSAGE = "Opened statistics window.";

    public final Index index;
    public final boolean isGenderStatistic;

    /**
     * Constructs a {@code MakeStatsCommand} to create statistics
     *  for the event at the specified {@code index}
     */
    public MakeStatsCommand(Index index, boolean isGenderStatistic) {
        requireAllNonNull(index, isGenderStatistic);
        this.index = index;
        this.isGenderStatistic = isGenderStatistic;
    }

    @Override
    public CommandResult execute(Model model) {
        //todo: add UID list integration
        ObservableList<Event> eventList = model.getFilteredEventList();
        Event targetEvent = eventList.get(index.getZeroBased());
        ObservableList<Person> personList = model.getFilteredPersonList();
        ObservableList<PieChart.Data> data = FXCollections.observableArrayList(
            new PieChart.Data("Grapefruit", 13),
            new PieChart.Data("Oranges", 25),
            new PieChart.Data("Plums", 10),
            new PieChart.Data("Pears", 22),
            new PieChart.Data("Apples", 30));
        model.setData(data);
        return new CommandResult(SHOWING_STATS_MESSAGE,
        false, true, false);
    }
}
