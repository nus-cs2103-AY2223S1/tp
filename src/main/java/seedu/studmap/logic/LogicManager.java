package seedu.studmap.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.studmap.commons.core.GuiSettings;
import seedu.studmap.commons.core.LogsCenter;
import seedu.studmap.logic.commands.Command;
import seedu.studmap.logic.commands.CommandResult;
import seedu.studmap.logic.commands.exceptions.CommandException;
import seedu.studmap.logic.parser.StudMapParser;
import seedu.studmap.logic.parser.exceptions.ParseException;
import seedu.studmap.model.Model;
import seedu.studmap.model.ReadOnlyStudMap;
import seedu.studmap.model.student.Student;
import seedu.studmap.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final StudMapParser studMapParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        studMapParser = new StudMapParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = studMapParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveStudMap(model.getStudMap());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyStudMap getStudMap() {
        return model.getStudMap();
    }

    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return model.getFilteredStudentList();
    }

    @Override
    public Path getStudMapFilePath() {
        return model.getStudMapFilePath();
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
