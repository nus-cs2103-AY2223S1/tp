package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.event.Event;
import seedu.address.model.person.Gender;
import seedu.address.model.person.Person;
import seedu.address.model.statistics.StatisticDataList;

/**
* Generates statistics for the event specified
*/
public class MakeStatsCommand extends Command {

    public static final String COMMAND_WORD = "makeStats";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates the statistics of the Event at the index "
        + "specified by the user. Type of statistics shown is based off a user input parameter.\n"
        + "Parameters INDEX " + PREFIX_TYPE + "TYPE \n"
        + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TYPE + "g";

    public static final String SHOWING_STATS_MESSAGE = "Opened statistics window.";
    public static final String NO_STATS_MESSAGE = "No person is tagged to event, no statistics to generate.";
    public static final String INDEX_OUT_OF_BOUNDS_MESSAGE = "Event index given is out of event list range.";

    public final Index index;
    public final boolean isGenderStatistic;

    /**
     * Constructs a {@code MakeStatsCommand} to create statistics
     * for the event at the specified {@code index} of the events list.
     */
    public MakeStatsCommand(Index index, boolean isGenderStatistic) {
        requireAllNonNull(index, isGenderStatistic);
        this.index = index;
        this.isGenderStatistic = isGenderStatistic;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        ObservableList<Event> eventList = model.getFilteredEventList();
        if (eventList.size() <= index.getZeroBased() || index.getZeroBased() < 0) {
            throw new CommandException(INDEX_OUT_OF_BOUNDS_MESSAGE);
        }
        Event targetEvent = eventList.get(index.getZeroBased());
        ObservableList<Person> personList = targetEvent.getUids().getPersons(model);
        StatisticDataList generatedStats;
        if (this.isGenderStatistic) {
            generatedStats = getGenderStatistic(personList);
        } else {
            generatedStats = getAgeStatistic(personList);
        }
        if (generatedStats.isEmpty()) {
            throw new CommandException(NO_STATS_MESSAGE);
        }
        model.setData(generatedStats.asUnmodifiableObservableList());
        return new CommandResult(SHOWING_STATS_MESSAGE,
        false, true, false);
    }

    /**
     * Returns a {@code StatisticDataList} with the data populated with the gender statistics.
     */
    public StatisticDataList getGenderStatistic(ObservableList<Person> personList) {
        StatisticDataList newDataList = new StatisticDataList();
        for (Person person : personList) {
            Gender gender = person.getGender();
            newDataList.addToStatistic(gender.toString());
        }
        return newDataList;
    }

    /**
     * Returns a {@code StatisticDataList} with the data populated with the age statistics.
     */
    public StatisticDataList getAgeStatistic(ObservableList<Person> personList) {
        StatisticDataList newDataList = new StatisticDataList();
        for (Person person : personList) {
            newDataList.addToStatistic(person.getAgeGroup());
        }
        return newDataList;
    }

}
