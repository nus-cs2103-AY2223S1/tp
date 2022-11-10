package seedu.travelr.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.travelr.commons.core.GuiSettings;
import seedu.travelr.commons.core.LogsCenter;
import seedu.travelr.logic.commands.Command;
import seedu.travelr.logic.commands.CommandResult;
import seedu.travelr.logic.commands.exceptions.CommandException;
import seedu.travelr.logic.parser.TravelrParser;
import seedu.travelr.logic.parser.exceptions.ParseException;
import seedu.travelr.model.Model;
import seedu.travelr.model.ReadOnlyTravelr;
import seedu.travelr.model.SummaryVariables;
import seedu.travelr.model.event.Event;
import seedu.travelr.model.trip.ObservableTrip;
import seedu.travelr.model.trip.Trip;
import seedu.travelr.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TravelrParser travelrParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        travelrParser = new TravelrParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = travelrParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTravelr(model.getTravelr());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTravelr getTravelr() {
        return model.getTravelr();
    }

    @Override
    public ObservableList<Trip> getFilteredTripList() {
        return model.getFilteredTripList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return model.getFilteredEventList();
    }

    @Override
    public ObservableTrip getSelectedTrip() {
        return model.getSelectedTrip();
    }

    @Override
    public Path getTravelrFilePath() {
        return model.getTravelrFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public SummaryVariables getTravelrSummary() {
        return model.getSummaryVariables();
    };

    @Override
    public void refreshTravelrSummary() {
        model.refreshSummaryVariables();
    };
}
