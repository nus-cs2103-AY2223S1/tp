package jeryl.fyp.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import jeryl.fyp.commons.core.GuiSettings;
import jeryl.fyp.commons.core.LogsCenter;
import jeryl.fyp.logic.commands.Command;
import jeryl.fyp.logic.commands.CommandResult;
import jeryl.fyp.logic.commands.exceptions.CommandException;
import jeryl.fyp.logic.parser.FypManagerParser;
import jeryl.fyp.logic.parser.exceptions.ParseException;
import jeryl.fyp.model.Model;
import jeryl.fyp.model.ReadOnlyFypManager;
import jeryl.fyp.model.student.Student;
import jeryl.fyp.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final FypManagerParser fypManagerParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        fypManagerParser = new FypManagerParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException,
            RuntimeException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = fypManagerParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveFypManager(model.getFypManager());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyFypManager getFypManager() {
        return model.getFypManager();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ObservableList<Student> getSortedByProjectNameUncompletedStudentList() {
        return model.getSortedByProjectNameUncompletedStudentList();
    }

    @Override
    public ObservableList<Student> getSortedByProjectStatusUncompletedStudentList() {
        return model.getSortedByProjectStatusUncompletedStudentList();
    }

    @Override
    public ObservableList<Student> getSortedCompletedStudentList() {
        return model.getSortedCompletedStudentList();
    }

    @Override
    public ObservableList<Student> getUncompletedStudentList() {
        return model.getUncompletedStudentList();
    }

    @Override
    public ObservableList<Student> getCompletedStudentList() {
        return model.getCompletedStudentList();
    }

    @Override
    public Path getFypManagerFilePath() {
        return model.getFypManagerFilePath();
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
