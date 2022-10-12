package seedu.intrack.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.intrack.commons.core.GuiSettings;
import seedu.intrack.commons.core.LogsCenter;
import seedu.intrack.logic.commands.Command;
import seedu.intrack.logic.commands.CommandResult;
import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.logic.parser.InTrackParser;
import seedu.intrack.logic.parser.exceptions.ParseException;
import seedu.intrack.model.Model;
import seedu.intrack.model.ReadOnlyInTrack;
import seedu.intrack.model.internship.Internship;
import seedu.intrack.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InTrackParser inTrackParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        inTrackParser = new InTrackParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = inTrackParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInTrack(model.getInTrack());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInTrack getInTrack() {
        return model.getInTrack();
    }

    @Override
    public ObservableList<Internship> getFilteredInternshipList() {
        return model.getFilteredInternshipList();
    }

    @Override
    public Path getInTrackFilePath() {
        return model.getInTrackFilePath();
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
