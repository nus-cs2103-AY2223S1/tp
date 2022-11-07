package seedu.realtime.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.realtime.commons.core.GuiSettings;
import seedu.realtime.commons.core.LogsCenter;
import seedu.realtime.logic.commands.Command;
import seedu.realtime.logic.commands.CommandResult;
import seedu.realtime.logic.commands.exceptions.CommandException;
import seedu.realtime.logic.parser.RealTimeParser;
import seedu.realtime.logic.parser.exceptions.ParseException;
import seedu.realtime.model.Model;
import seedu.realtime.model.ReadOnlyRealTime;
import seedu.realtime.model.listing.Listing;
import seedu.realtime.model.meeting.Meeting;
import seedu.realtime.model.offer.Offer;
import seedu.realtime.model.person.Client;
import seedu.realtime.model.person.Person;
import seedu.realtime.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final RealTimeParser realTimeParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        realTimeParser = new RealTimeParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = realTimeParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveRealTime(model.getRealTime());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyRealTime getRealTime() {
        return model.getRealTime();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Client> getFilteredClientList() {
        return model.getFilteredClientList();
    }

    @Override
    public ObservableList<Offer> getFilteredOfferList() {
        return model.getFilteredOfferList();
    }

    @Override
    public ObservableList<Listing> getFilteredListingList() {
        return model.getFilteredListingList();
    }

    @Override
    public ObservableList<Meeting> getFilteredMeetingList() {
        return model.getFilteredMeetingList();
    }

    @Override
    public Path getRealTimeFilePath() {
        return model.getRealTimeFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
