package seedu.application.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.application.commons.core.GuiSettings;
import seedu.application.commons.core.LogsCenter;
import seedu.application.logic.commands.Command;
import seedu.application.logic.commands.CommandResult;
import seedu.application.logic.commands.exceptions.CommandException;
import seedu.application.logic.parser.ApplicationBookParser;
import seedu.application.logic.parser.exceptions.ParseException;
import seedu.application.model.Model;
import seedu.application.model.ReadOnlyApplicationBook;
import seedu.application.model.application.Application;
import seedu.application.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final ApplicationBookParser applicationBookParser;

    /**
     * Constructs a {@code LogicManager} with the given
     * {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        applicationBookParser = new ApplicationBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = applicationBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveApplicationBook(model.getApplicationBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyApplicationBook getApplicationBook() {
        return model.getApplicationBook();
    }

    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return model.getFilteredApplicationList();
    }

    @Override
    public ObservableList<Application> getApplicationListWithInterview() {
        return model.getApplicationListWithInterview();
    }

    @Override
    public ObservableList<Application> getApplicationListWithUpcomingInterview() {
        return model.getApplicationsWithUpcomingInterviewList();
    }

    @Override
    public Path getApplicationBookFilePath() {
        return model.getApplicationBookFilePath();
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
