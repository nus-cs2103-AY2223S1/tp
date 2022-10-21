package seedu.trackascholar.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.trackascholar.commons.core.GuiSettings;
import seedu.trackascholar.commons.core.LogsCenter;
import seedu.trackascholar.logic.commands.Command;
import seedu.trackascholar.logic.commands.CommandResult;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.logic.parser.TrackAScholarParser;
import seedu.trackascholar.logic.parser.exceptions.ParseException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.ReadOnlyTrackAScholar;
import seedu.trackascholar.model.applicant.Applicant;
import seedu.trackascholar.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TrackAScholarParser trackAScholarParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        trackAScholarParser = new TrackAScholarParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = trackAScholarParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTrackAScholar(model.getTrackAScholar());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTrackAScholar getTrackAScholar() {
        return model.getTrackAScholar();
    }

    @Override
    public ObservableList<Applicant> getFilteredApplicantList() {
        return model.getFilteredApplicantList();
    }

    @Override
    public Path getTrackAScholarFilePath() {
        return model.getTrackAScholarFilePath();
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
