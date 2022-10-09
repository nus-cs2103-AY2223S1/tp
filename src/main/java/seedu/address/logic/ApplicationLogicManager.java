package seedu.address.logic;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ApplicationCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ApplicationBookParser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.ApplicationModel;
import seedu.address.model.ReadOnlyApplicationBook;
import seedu.address.model.application.Application;
import seedu.address.storage.ApplicationStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * The main ApplicationLogicManager of the app.
 */
public class ApplicationLogicManager implements ApplicationLogic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(ApplicationLogicManager.class);

    private final ApplicationModel applicationModel;
    private final ApplicationStorage applicationStorage;
    private final ApplicationBookParser applicationBookParser;

    /**
     * Constructs a {@code ApplicationLogicManager} with the given {@code ApplicationModel} and {@code ApplicationStorage}.
     */
    public ApplicationLogicManager(ApplicationModel applicationModel, ApplicationStorage applicationStorage) {
        this.applicationModel = applicationModel;
        this.applicationStorage = applicationStorage;
        applicationBookParser = new ApplicationBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        ApplicationCommand command = applicationBookParser.parseCommand(commandText);
        commandResult = command.execute(applicationModel);

        try {
            applicationStorage.saveApplicationBook(applicationModel.getApplicationBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyApplicationBook getApplicationBook() {
        return applicationModel.getApplicationBook();
    }

    @Override
    public ObservableList<Application> getFilteredApplicationList() {
        return applicationModel.getFilteredApplicationList();
    }

    @Override
    public Path getApplicationBookFilePath() {
        return applicationModel.getApplicationBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return applicationModel.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        applicationModel.setGuiSettings(guiSettings);
    }
}
