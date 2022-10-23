package taskbook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import taskbook.commons.core.GuiSettings;
import taskbook.commons.core.LogsCenter;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.CommandResult;
import taskbook.logic.commands.exceptions.CommandException;
import taskbook.logic.parser.TaskBookParser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.model.Model;
import taskbook.model.ReadOnlyTaskBook;
import taskbook.model.person.Person;
import taskbook.model.task.Task;
import taskbook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TaskBookParser taskBookParser;
    private final CommandHistory commandHistory;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        taskBookParser = new TaskBookParser();
        commandHistory = new CommandHistoryManager();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        commandHistory.addCommand(commandText);

        CommandResult commandResult;
        Command command = taskBookParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTaskBook(model.getTaskBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTaskBook getTaskBook() {
        return model.getTaskBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Person> getSortedPersonList() {
        return model.getSortedPersonList();
    }

    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }

    @Override
    public ObservableList<Task> getSortedTaskList() {
        return model.getSortedTaskList();
    }

    @Override
    public Path getTaskBookFilePath() {
        return model.getTaskBookFilePath();
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
    public String getPreviousCommand() {
        return commandHistory.getPreviousCommand();
    }

    @Override
    public String getNextCommand() {
        return commandHistory.getNextCommand();
    }
}
